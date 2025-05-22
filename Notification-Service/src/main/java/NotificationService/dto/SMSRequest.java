package NotificationService.dto;



import lombok.Data;

@Data
public class SMSRequest {
    private String to;
    private String body;
}
