package co.ff.ias.rws.domain;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.util.Objects;

public class MessageContent {
    private final String content;

    public MessageContent(String content) {
        Objects.requireNonNull(content, "Message content can not be null");
        Validate.isTrue(StringUtils.isNotBlank(content), "Message content can not be empty");
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "MessageContent{" +
                "content='" + content + '\'' +
                '}';
    }
}
