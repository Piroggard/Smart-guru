package notifications.message.impl;

import lombok.RequiredArgsConstructor;
import notifications.dto.NewsCourseEventDto;
import notifications.message.MessageBuilder;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewsCourseMessageBuilder implements MessageBuilder<NewsCourseEventDto> {

    private final MessageSource messageSource;

    @Override
    public String buildMessage(NewsCourseEventDto event) {
        return null;
    }

    @Override
    public Class<NewsCourseEventDto> getEventType() {
        return NewsCourseEventDto.class;
    }
}
