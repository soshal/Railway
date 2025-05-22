package paymentservice.service;

import paymentservice.dto.PaymentRequest;
import paymentservice.model.Payment;
import paymentservice.repository.PaymentRepository;
import com.indusspay.sdk.upi.UPIPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment processPayment(PaymentRequest request) {
        // Simulate UPI Payment Gateway (you can use other payment providers here like Razorpay, Stripe, etc.)
        UPIPayment upiPayment = new UPIPayment();
        upiPayment.setAmount(request.getAmount());
        upiPayment.setPnrNumber(request.getPnrNumber());
        upiPayment.setUserEmail(request.getUserEmail());

        // Assuming the UPI payment is processed here
        String paymentStatus = upiPayment.process();  // You will handle the payment response from the gateway

        // Create payment record
        Payment payment = new Payment();
        payment.setPaymentId("PAY" + System.currentTimeMillis());
        payment.setUserEmail(request.getUserEmail());
        payment.setPnrNumber(request.getPnrNumber());
        payment.setAmount(request.getAmount());
        payment.setStatus(paymentStatus);

        return paymentRepository.save(payment);
    }
}