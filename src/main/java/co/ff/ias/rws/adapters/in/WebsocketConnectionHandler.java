package co.ff.ias.rws.adapters.in;

import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class WebsocketConnectionHandler implements WebSocketHandler {
    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {

        System.out.println(webSocketSession.getId());

        Mono<Void> input = webSocketSession.receive()
                .doOnNext(message -> {

                    // ...
                })
                .then();

        Flux<String> source = Flux.interval(Duration.ofMillis(500))
                .map(aLong -> "Value: " + aLong);

        Mono<Void> output = webSocketSession.send(source.map(webSocketSession::textMessage));

        return Mono.zip(input, output).then();
    }
}
