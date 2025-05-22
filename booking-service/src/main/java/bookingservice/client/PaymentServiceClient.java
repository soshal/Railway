package bookingservice.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service")
public interface PaymentServiceClient {
    @PostMapping("/payments/process")
    PaymentResponse processPayment(@RequestBody PaymentRequest request);
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class PaymentRequest {
    private String userEmail;
    private double amount;
    private String currency;
}

@Data @AllArgsConstructor @NoArgsConstructor
class PaymentResponse {
    private boolean success;
    private String transactionId;
}