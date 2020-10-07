package co.ff.ias.rws.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "with")
public class RoomNotification {
    RoomStatus status;
    Room room;
}
