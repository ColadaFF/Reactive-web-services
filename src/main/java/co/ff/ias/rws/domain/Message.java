package co.ff.ias.rws.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder(setterPrefix = "with")
public class Message {
    UUID roomId;
    UUID messageId;
    String content;
    LocalDateTime dateTime;
}
