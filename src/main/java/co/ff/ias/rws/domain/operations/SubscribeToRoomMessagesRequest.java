package co.ff.ias.rws.domain.operations;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder(setterPrefix = "with")
public class SubscribeToRoomMessagesRequest extends RoomOperation {
    UUID roomId;
}
