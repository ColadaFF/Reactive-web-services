package co.ff.ias.rws.usecases;

import co.ff.ias.rws.domain.Room;
import co.ff.ias.rws.utils.UseCase;
import reactor.core.publisher.Flux;

public interface SubscribeToRoomsNotificationsUseCase
        extends UseCase<Void, Flux<Room>> {

}
