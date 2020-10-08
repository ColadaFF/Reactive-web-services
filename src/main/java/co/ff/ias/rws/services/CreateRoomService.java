package co.ff.ias.rws.services;

import co.ff.ias.rws.adapters.out.RoomRepository;
import co.ff.ias.rws.domain.Room;
import co.ff.ias.rws.domain.operations.CreateRoomRequest;
import co.ff.ias.rws.domain.operations.CreateRoomResponse;
import co.ff.ias.rws.usecases.CreateRoomUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateRoomService implements CreateRoomUseCase {
    private final RoomRepository roomRepository;

    @Override
    public Mono<CreateRoomResponse> process(CreateRoomRequest input) {
        Room room = Room.builder()
                .withId(UUID.randomUUID())
                .withName(input.getRoomName())
                .build();
        return roomRepository.createRoom(room)
                .map(createdRoom -> CreateRoomResponse.builder()
                        .withRoom(createdRoom)
                        .build());
    }
}
