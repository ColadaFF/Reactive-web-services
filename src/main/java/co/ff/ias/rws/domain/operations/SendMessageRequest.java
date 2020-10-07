package co.ff.ias.rws.domain.operations;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder(setterPrefix = "with")
public class SendMessageRequest extends RoomOperation {
    UUID roomId;
    String body;
    LocalDateTime dateTime;
    String author;
}
