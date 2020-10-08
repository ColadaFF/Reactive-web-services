package co.ff.ias.rws.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder(setterPrefix = "with")
public class RoomNotification {
    RoomStatus status;
    LocalDateTime dateTime;
    Room room;
}
