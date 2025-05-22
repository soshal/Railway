package bookingservice.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service")
public interface NotificationServiceClient {
    @PostMapping("/notifications/send")
    void sendNotification(@RequestBody NotificationRequest request);
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class NotificationRequest {
    private String recipient;
    private String message;
}