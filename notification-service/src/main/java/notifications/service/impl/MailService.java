package notifications.service.impl;

import notifications.service.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class MailService implements NotificationService {

    @Override
    public void sendNotification(String message, Object obj) {

    }
}
