package co.ff.ias.rws.usecases;

import co.ff.ias.rws.domain.operations.DeleteRoomRequest;
import co.ff.ias.rws.domain.operations.DeleteRoomResponse;
import co.ff.ias.rws.utils.UseCase;
import reactor.core.publisher.Mono;

public interface DeleteRoomUseCase
        extends UseCase<DeleteRoomRequest, Mono<DeleteRoomResponse>> {

}
