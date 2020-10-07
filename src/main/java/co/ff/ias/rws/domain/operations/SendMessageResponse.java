package co.ff.ias.rws.domain.operations;

import co.ff.ias.rws.domain.Message;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "with")
public class SendMessageResponse {
    Message message;
}
