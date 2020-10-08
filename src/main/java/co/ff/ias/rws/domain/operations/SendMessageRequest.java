package co.ff.ias.rws.domain.operations;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder(setterPrefix = "with")
public class SendMessageRequest {
    UUID roomId;
    String body;
    LocalDateTime dateTime;
    String author;
}
