package co.ff.ias.rws.adapters.in;

import co.ff.ias.rws.domain.RoomNotification;
import co.ff.ias.rws.domain.operations.CreateRoomRequest;
import co.ff.ias.rws.domain.operations.CreateRoomResponse;
import co.ff.ias.rws.domain.operations.DeleteRoomResponse;
import co.ff.ias.rws.usecases.CreateRoomUseCase;
import co.ff.ias.rws.usecases.DeleteRoomUseCase;
import co.ff.ias.rws.usecases.SubscribeToRoomsNotificationsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class RoomOperationsRouter {


    @Bean
    RouterFunction<ServerResponse> roomOperationsRouterProvider(
            RoomOperationsHandler handler,
            CreateRoomUseCase createRoomUseCase,
            SubscribeToRoomsNotificationsUseCase subscribeToRoomsNotificationsUseCase
    ) {

        var jsonRp = accept(MediaType.APPLICATION_JSON)
                .or(accept(MediaType.APPLICATION_JSON_UTF8));
        var sseRp = accept(MediaType.TEXT_EVENT_STREAM);

        return route()
                .before(serverRequest -> {
                    serverRequest.attributes().put("UUID", UUID.randomUUID());
                    return serverRequest;
                })
                .after((serverRequest, serverResponse) -> {
                    Optional<Object> uuid = serverRequest.attribute("UUID");

                    return serverResponse;
                })
                .filter((request, next) -> {
                    var reply = next.handle(request);

                    return reply;
                })
                .nest(path("/rooms"), builder -> builder
                        .nest(jsonRp, jsonBuilder -> jsonBuilder
                                .POST("", postRequest(createRoomUseCase))
                                .DELETE("/{id}", handler::deleteRoom)
                        )
                        .add(route(sseRp, request -> {
                            Flux<ServerSentEvent<String>> process = subscribeToRoomsNotificationsUseCase.process(null)
                                    .map(roomNotification -> {
                                        return ServerSentEvent.<String>builder()
                                                .data(roomNotification.toString())
                                                .event("notification")
                                                .id(UUID.randomUUID().toString())
                                                .build();
                                    });
                            return ok()
                                    .body(process, ServerSentEvent.class);
                        }))

                )
                .build();
    }


    private HandlerFunction<ServerResponse> postRequest(CreateRoomUseCase createRoomUseCase) {
        return request -> {
            return request.bodyToMono(CreateRoomRequest.class)
                    .doOnError(Throwable::printStackTrace)
                    .flatMap(createRoomUseCase::process)
                    .flatMap(createRoomResponse -> ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(Mono.just(createRoomResponse), CreateRoomResponse.class)
                    );
        };
    }
}
