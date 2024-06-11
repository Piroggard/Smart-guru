package notifications.message.impl;

import lombok.RequiredArgsConstructor;
import notifications.dto.GraduateEventDto;
import notifications.message.MessageBuilder;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GraduateMessageBuilder implements MessageBuilder<GraduateEventDto> {

    private final MessageSource messageSource;

    @Override
    public String buildMessage(GraduateEventDto event) {
        return null;
    }

    @Override
    public Class<GraduateEventDto> getEventType() {
        return GraduateEventDto.class;
    }
}
