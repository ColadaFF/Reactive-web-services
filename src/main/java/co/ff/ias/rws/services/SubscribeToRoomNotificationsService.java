package co.ff.ias.rws.services;

import co.ff.ias.rws.adapters.out.RoomRepository;
import co.ff.ias.rws.domain.Room;
import co.ff.ias.rws.domain.RoomNotification;
import co.ff.ias.rws.usecases.SubscribeToRoomsNotificationsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class SubscribeToRoomNotificationsService
        implements SubscribeToRoomsNotificationsUseCase {

    private final RoomRepository roomRepository;

    @Override
    public Flux<RoomNotification> process(Void input) {
        return roomRepository.notifications();
    }
}
