package co.ff.ias.rws.domain.operations;

import co.ff.ias.rws.domain.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
public class CreateRoomResponse {
    Room room;
}
