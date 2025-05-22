package bookingservice.controller;



import bookingservice.dto.BookingRequest;
import bookingservice.dto.BookingResponse;
import bookingservice.model.Booking;
import bookingservice.service.BookingRepository;
import bookingservice.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import bookingservice.dto.BookingRequest;
import bookingservice.dto.BookingResponse;
import bookingservice.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    private final BookingRepository bookingRepository;

    // Endpoint to book a ticket
    @PostMapping("/book")
    public BookingResponse bookTicket(@RequestBody BookingRequest bookingRequest) {
        return bookingService.bookTicket(bookingRequest);
    }

    // Endpoint to update booking by PNR number
    @PutMapping("/{pnrNumber}")
    public BookingResponse updateBooking(@PathVariable String pnrNumber, @RequestBody BookingRequest bookingRequest) {
        return bookingService.updateBooking(pnrNumber, bookingRequest);
    }

    // Endpoint to cancel booking by PNR number
    @DeleteMapping("/{pnrNumber}")
    public void cancelBooking(@PathVariable String pnrNumber) {
        bookingService.cancelBooking(pnrNumber);
    }

    // Endpoint to get booking by PNR number
    @GetMapping("/{pnrNumber}")
    public BookingResponse getBookingByPnr(@PathVariable String pnrNumber) {
        // Fetch from the database
        Booking booking = bookingRepository.findById(pnrNumber)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        return new BookingResponse(booking.getPnrNumber(), booking.getTrainNumber(), booking.getClassType(), booking.getNumberOfSeats());
    }
}
