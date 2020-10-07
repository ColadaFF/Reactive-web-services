package co.ff.ias.rws.services;

import co.ff.ias.rws.domain.operations.CreateRoomRequest;
import co.ff.ias.rws.domain.operations.CreateRoomResponse;
import co.ff.ias.rws.usecases.CreateRoomUseCase;
import reactor.core.publisher.Mono;

public class CreateRoomService implements CreateRoomUseCase {
    @Override
    public Mono<CreateRoomResponse> process(CreateRoomRequest input) {
        return null;
    }
}
