package ch.etmles.auction.Controllers;

import ch.etmles.auction.Entities.Notification;
import ch.etmles.auction.Repositories.NotificationRepository;
import ch.etmles.auction.Exceptions.NotificationErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationController(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @GetMapping("/user/{userId}")
    public List<Notification> getUserNotifications(@PathVariable Long userId) {
        return notificationRepository.findByUser_Id(userId);
    }

    @PostMapping
    public Notification createNotification(@RequestBody Notification notification) {
        notification.setTimestamp(LocalDateTime.now());
        return notificationRepository.save(notification);
    }

    @PutMapping("/{id}/read")
    public Notification markAsRead(@PathVariable Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new NotificationErrorException("Notification not found"));
        notification.setRead(true);
        return notificationRepository.save(notification);
    }
}
