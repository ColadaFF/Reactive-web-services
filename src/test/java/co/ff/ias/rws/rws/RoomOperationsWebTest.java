package co.ff.ias.rws.rws;

import co.ff.ias.rws.adapters.in.RoomOperationsHandler;
import co.ff.ias.rws.adapters.in.RoomOperationsRouter;
import co.ff.ias.rws.adapters.out.RoomRepository;
import co.ff.ias.rws.domain.Room;
import co.ff.ias.rws.domain.operations.CreateRoomRequest;
import co.ff.ias.rws.domain.operations.CreateRoomResponse;
import co.ff.ias.rws.services.CreateRoomService;
import co.ff.ias.rws.services.DeleteRoomService;
import co.ff.ias.rws.services.SubscribeToRoomNotificationsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@WebFluxTest
@Import({
        RoomOperationsRouter.class,
        SubscribeToRoomNotificationsService.class,
        RoomOperationsHandler.class,
        CreateRoomService.class,
        DeleteRoomService.class
})
@ExtendWith(SpringExtension.class)
public class RoomOperationsWebTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private RoomRepository roomRepository;

    @Test
    public void createRoomTest() {
        // arrange
        String roomName = "room name";
        Room room = Room.builder()
                .withId(UUID.randomUUID())
                .withName(roomName)
                .build();
        Mockito.when(roomRepository.createRoom(ArgumentMatchers.any(Room.class)))
                .thenReturn(Mono.just(room));

        var roomRequest = CreateRoomRequest.builder()
                .roomName(roomName)
                .build();

        CreateRoomResponse build = CreateRoomResponse.builder().withRoom(room).build();


        // act
        this.webTestClient.post()
                .uri("/rooms")
                .body(Mono.just(roomRequest), CreateRoomRequest.class)
                .accept(MediaType.APPLICATION_JSON).exchange()

                // assert
                .expectStatus().isOk().expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.room.name").isEqualTo(roomName);
    }
}
