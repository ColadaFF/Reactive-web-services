package co.ff.ias.rws.domain.operations;

import co.ff.ias.rws.domain.RoomOperationType;

public abstract class RoomOperation {
    private final RoomOperationType type;

    protected RoomOperation(RoomOperationType type) {
        this.type = type;
    }


    public RoomOperationType getType() {
        return type;
    }
}
