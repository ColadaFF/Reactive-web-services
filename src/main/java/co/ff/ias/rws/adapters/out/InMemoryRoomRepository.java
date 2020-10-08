package co.ff.ias.rws.adapters.out;

import co.ff.ias.rws.domain.Room;
import co.ff.ias.rws.domain.RoomNotification;
import co.ff.ias.rws.domain.RoomStatus;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Repository
public class InMemoryRoomRepository implements RoomRepository {
    private final Map<UUID, Room> roomMap = new HashMap<>();
    private final EmitterProcessor<RoomNotification> emitterProcessor = EmitterProcessor.create();


    @Override
    public Mono<Room> createRoom(Room room) {
        return Mono.fromSupplier(() -> {
            roomMap.put(room.getId(), room);
            return room;
        })
                .doOnNext(createdRoom -> {
                    var notification = RoomNotification.builder()
                            .withRoom(createdRoom)
                            .withStatus(RoomStatus.CREATED)
                            .withDateTime(LocalDateTime.now())
                            .build();
                    emitterProcessor.onNext(notification);
                });
    }

    @Override
    public Mono<Room> deleteRoom(UUID roomId) {
        if (roomMap.containsKey(roomId)) {
            return Mono.fromSupplier(() -> roomMap.remove(roomId))
                    .doOnNext(deletedRoom -> {
                        var notification = RoomNotification.builder()
                                .withRoom(deletedRoom)
                                .withStatus(RoomStatus.DELETED)
                                .withDateTime(LocalDateTime.now())
                                .build();
                        emitterProcessor.onNext(notification);
                    });
        } else {
            return Mono.error(new RuntimeException("Room does not exists: " + roomId));
        }
    }

    @Override
    public Flux<RoomNotification> notifications() {
        return emitterProcessor.doOnNext(System.out::println);
    }
}
