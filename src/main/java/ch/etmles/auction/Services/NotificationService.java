package ch.etmles.auction.Services;

import ch.etmles.auction.Entities.Customer;
import ch.etmles.auction.Entities.Lot;
import ch.etmles.auction.Entities.Notification;
import ch.etmles.auction.Repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void addNotification(String message, Customer user, Lot lot, BigDecimal bidAmount) {
        Notification notification = new Notification(message, user, lot, bidAmount);
        notificationRepository.save(notification);
    }
}
