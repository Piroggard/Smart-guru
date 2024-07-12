package org.example.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserContext {

    private ThreadLocal<UUID> userIdHolder = new ThreadLocal<>();

    public void setUserId(UUID userId) {
        userIdHolder.set(userId);
    }

    public UUID getUserId() {
        return userIdHolder.get();
    }

    public void clear() {
        userIdHolder.remove();
    }
}
