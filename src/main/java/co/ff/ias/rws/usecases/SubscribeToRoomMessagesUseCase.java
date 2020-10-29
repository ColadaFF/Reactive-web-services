package co.ff.ias.rws.usecases;

import co.ff.ias.rws.domain.Message;
import co.ff.ias.rws.domain.operations.SubscribeToRoomMessagesRequest;
import co.ff.ias.utils.UseCase;
import reactor.core.publisher.Flux;

public interface SubscribeToRoomMessagesUseCase
        extends UseCase<SubscribeToRoomMessagesRequest, Flux<Message>> {
}
