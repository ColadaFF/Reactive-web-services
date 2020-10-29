package co.ff.ias.rws.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageContentTest {

    @Test
    @DisplayName("Message content throws exception on null value")
    public void validateNotNullInInvalid(){
        assertThrows(NullPointerException.class, () -> new MessageContent(null));
    }

    @Test
    @DisplayName("Message content throws exception on empty value")
    public void validateEmptyIsInvalid(){
        assertThrows(IllegalArgumentException.class, () -> new MessageContent(""));
    }

}