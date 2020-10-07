package co.ff.ias.rws.domain;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder(setterPrefix = "with")
public class Room {
    UUID id;
    String name;
    RoomStatus status;
}
