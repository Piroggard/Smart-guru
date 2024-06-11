package notifications.message;

public interface MessageBuilder<T> {

    String buildMessage(T event);

    Class<T> getEventType();
}
