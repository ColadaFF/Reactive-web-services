package co.ff.ias.rws.usecases;

import co.ff.ias.rws.domain.operations.SendMessageRequest;
import co.ff.ias.rws.domain.operations.SendMessageResponse;
import co.ff.ias.rws.utils.UseCase;
import reactor.core.publisher.Mono;

public interface SendMessageUseCase
        extends UseCase<SendMessageRequest, Mono<SendMessageResponse>> {

}
