package notifications.message.impl;

import lombok.RequiredArgsConstructor;
import notifications.dto.CourseCreationEventDto;
import notifications.message.MessageBuilder;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseCreationMessageBuilder implements MessageBuilder<CourseCreationEventDto> {

    private final MessageSource messageSource;

    @Override
    public String buildMessage(CourseCreationEventDto event) {
        return null;
    }

    @Override
    public Class<CourseCreationEventDto> getEventType() {
        return null;
    }
}
