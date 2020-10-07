package co.ff.ias.rws.adapters.out;

import co.ff.ias.rws.domain.Room;
import co.ff.ias.rws.domain.RoomNotification;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface RoomRepository {
    Mono<Room> createRoom(Room room);

    Mono<Room> deleteRoom(UUID roomId);

    Flux<RoomNotification> notifications();
}
