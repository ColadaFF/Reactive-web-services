package co.ff.ias.rws.adapters.out;

import co.ff.ias.rws.domain.Room;
import co.ff.ias.rws.domain.RoomNotification;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoryRoomRepository implements RoomRepository {
    private final Map<UUID, Room> roomMap = new HashMap<>();


    @Override
    public Mono<Room> createRoom(Room room) {
        return null;
    }

    @Override
    public Mono<Room> deleteRoom(UUID roomId) {
        return null;
    }

    @Override
    public Flux<RoomNotification> notifications() {
        return null;
    }
}
