package co.ff.ias.rws.domain.operations;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@Builder(setterPrefix = "with")
public class SubscribeToRoomMessagesRequest {
    UUID roomId;


}
