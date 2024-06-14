package notifications.message.impl;

import lombok.RequiredArgsConstructor;
import notifications.dto.UserRegEventDto;
import notifications.message.MessageBuilder;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRegMessageBuilder implements MessageBuilder<UserRegEventDto> {

    private final MessageSource messageSource;


    @Override
    public String buildMessage(UserRegEventDto event) {
        return null;
    }

    @Override
    public Class<UserRegEventDto> getEventType() {
        return UserRegEventDto.class;
    }
}
