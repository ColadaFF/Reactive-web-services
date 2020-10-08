package co.ff.ias.rws.services;

import co.ff.ias.rws.adapters.out.RoomRepository;
import co.ff.ias.rws.domain.operations.DeleteRoomRequest;
import co.ff.ias.rws.domain.operations.DeleteRoomResponse;
import co.ff.ias.rws.usecases.DeleteRoomUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DeleteRoomService implements DeleteRoomUseCase {
    private final RoomRepository roomRepository;

    @Override
    public Mono<DeleteRoomResponse> process(DeleteRoomRequest input) {
        return roomRepository.deleteRoom(input.getRoomId())
                .map(room -> DeleteRoomResponse.builder()
                        .withDeleted(true)
                        .withRoom(room)
                        .build())
                .onErrorResume(throwable -> {
                    DeleteRoomResponse roomResponse = DeleteRoomResponse.builder()
                            .withDeleted(false)
                            .withMessage(throwable.getMessage())
                            .build();
                    return Mono.just(roomResponse);
                });
    }
}
