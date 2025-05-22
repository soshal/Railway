package NotificationService.controller;



import NotificationService.dto.EmailRequest;
import NotificationService.dto.SMSRequest;
import NotificationService.service.EmailService;
import NotificationService.service.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private SMSService smsService;

    @PostMapping("/email")
    public String sendEmail(@RequestBody EmailRequest request) {
        emailService.sendEmail(request);
        return "Email sent successfully!";
    }

    @PostMapping("/sms")
    public String sendSMS(@RequestBody SMSRequest request) {
        smsService.sendSMS(request);
        return "SMS sent successfully!";
    }
}
