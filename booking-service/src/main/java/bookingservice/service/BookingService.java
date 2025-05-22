package bookingservice.service;


import bookingservice.dto.BookingRequest;
import bookingservice.dto.BookingResponse;
import bookingservice.model.Booking;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;



import bookingservice.client.TrainServiceClient;
import bookingservice.dto.BookingRequest;
import bookingservice.dto.BookingResponse;
import bookingservice.dto.TrainAvailabilityResponse;
import bookingservice.model.Booking;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final TrainServiceClient trainServiceClient;

    public BookingService(BookingRepository bookingRepository, TrainServiceClient trainServiceClient) {
        this.bookingRepository = bookingRepository;
        this.trainServiceClient = trainServiceClient;
    }


    @Transactional
    public BookingResponse bookTicket(BookingRequest bookingRequest) {
        // Check seat availability from Train Service
        TrainAvailabilityResponse trainAvailability = trainServiceClient.getSeatAvailability(bookingRequest.getTrainNumber());

        if (trainAvailability.getAvailableSeats() < bookingRequest.getNumberOfSeats()) {
            throw new RuntimeException("Not enough seats available.");
        }

        // Generate PNR Number
        String pnrNumber = generatePnrNumber();

        // Create Booking object
        Booking booking = new Booking();
        booking.setTrainNumber(bookingRequest.getTrainNumber());
        booking.setClassType(bookingRequest.getClassType());
        booking.setNumberOfSeats(bookingRequest.getNumberOfSeats());
        booking.setUserEmail(bookingRequest.getUserEmail());
        booking.setPnrNumber(pnrNumber);

        // Save booking to the database
        bookingRepository.save(booking);

        // Return the booking response with PNR number
        return new BookingResponse(pnrNumber, bookingRequest.getTrainNumber(), bookingRequest.getClassType(), bookingRequest.getNumberOfSeats());
    }

    private String generatePnrNumber() {
        // Generate a unique PNR number using UUID
        return UUID.randomUUID().toString().substring(0, 10);
    }

    public BookingResponse updateBooking(String pnrNumber, BookingRequest bookingRequest) {
        Booking existingBooking = bookingRepository.findById(pnrNumber)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // Update the booking details
        existingBooking.setTrainNumber(bookingRequest.getTrainNumber());
        existingBooking.setClassType(bookingRequest.getClassType());
        existingBooking.setNumberOfSeats(bookingRequest.getNumberOfSeats());

        // Save updated booking
        bookingRepository.save(existingBooking);

        return new BookingResponse(existingBooking.getPnrNumber(), existingBooking.getTrainNumber(),
                existingBooking.getClassType(), existingBooking.getNumberOfSeats());
    }

    public void cancelBooking(String pnrNumber) {
        Booking booking = bookingRepository.findById(pnrNumber)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // Cancel the booking (remove it from the database)
        bookingRepository.delete(booking);
    }


}
