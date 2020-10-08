package co.ff.ias.rws.adapters.in;

import co.ff.ias.rws.domain.operations.CreateRoomRequest;
import co.ff.ias.rws.domain.operations.CreateRoomResponse;
import co.ff.ias.rws.domain.operations.DeleteRoomRequest;
import co.ff.ias.rws.domain.operations.DeleteRoomResponse;
import co.ff.ias.rws.usecases.CreateRoomUseCase;
import co.ff.ias.rws.usecases.DeleteRoomUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class RoomOperationsHandler {
    private final CreateRoomUseCase createRoomUseCase;
    private final DeleteRoomUseCase deleteRoomUseCase;

    public Mono<CreateRoomResponse> createRoom(CreateRoomRequest request) {
        return createRoomUseCase.process(request);
    }


    public Mono<ServerResponse> deleteRoom(ServerRequest request) {

        String id = request.pathVariable("id");
        UUID idToDelete = UUID.fromString(id);
        DeleteRoomRequest deleteRoomRequest = DeleteRoomRequest.builder()
                .withRoomId(idToDelete)
                .build();

        return deleteRoomUseCase.process(deleteRoomRequest)
                .flatMap(deleteRoomResponse -> {
                    if (deleteRoomResponse.getDeleted()) {
                        return ok().bodyValue(deleteRoomResponse);
                    } else {
                        return notFound().build();
                    }
                });
    }
}
