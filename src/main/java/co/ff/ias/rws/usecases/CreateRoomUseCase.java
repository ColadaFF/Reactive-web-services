package co.ff.ias.rws.usecases;

import co.ff.ias.rws.domain.operations.CreateRoomRequest;
import co.ff.ias.rws.domain.operations.CreateRoomResponse;
import co.ff.ias.rws.utils.UseCase;
import reactor.core.publisher.Mono;

public interface CreateRoomUseCase
        extends UseCase<CreateRoomRequest, Mono<CreateRoomResponse>> {

}
