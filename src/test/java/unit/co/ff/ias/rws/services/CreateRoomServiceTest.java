package unit.co.ff.ias.rws.services;

import co.ff.ias.rws.adapters.out.RoomRepository;
import co.ff.ias.rws.domain.Room;
import co.ff.ias.rws.domain.operations.CreateRoomRequest;
import co.ff.ias.rws.domain.operations.CreateRoomResponse;
import co.ff.ias.rws.services.CreateRoomService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Objects;
import java.util.UUID;


public class CreateRoomServiceTest {

    @Test
    public void processUseCase() {
        // arrange
        RoomRepository roomRepository = Mockito.mock(RoomRepository.class);
        String RoomName = "Some room";
        UUID uuid = UUID.randomUUID();
        Room expectedRoom = Room.builder()
                .withName(RoomName)
                .withId(uuid)
                .build();
        CreateRoomRequest roomRequest = CreateRoomRequest.builder()
                .roomName(RoomName)
                .build();
        // test scenario
        Mockito.when(roomRepository.createRoom(ArgumentMatchers.any(Room.class)))
                .thenReturn(Mono.just(expectedRoom));


        // act
        CreateRoomService createRoomService = new CreateRoomService(roomRepository);
        Mono<CreateRoomResponse> process = createRoomService.process(roomRequest);

        // verify
        StepVerifier.create(process)
                .expectNextMatches(createRoomResponse -> {
                    Room room = createRoomResponse.getRoom();
                    return RoomName.equals(room.getName()) && Objects.nonNull(room.getId());
                })
                .verifyComplete();


    }
}
