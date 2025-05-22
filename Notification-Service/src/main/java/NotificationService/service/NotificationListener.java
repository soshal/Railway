package NotificationService.service;



import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {

    @KafkaListener(topics = "booking-events", groupId = "notification-group")
    public void consumeBookingEvent(String message) {
        System.out.println("Received booking event: " + message);
        // Process and send notifications (Email/SMS)
    }
}
