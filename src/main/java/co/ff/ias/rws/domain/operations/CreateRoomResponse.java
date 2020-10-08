package co.ff.ias.rws.domain.operations;

import co.ff.ias.rws.domain.Room;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "with")
public class CreateRoomResponse {
    Room room;
}
