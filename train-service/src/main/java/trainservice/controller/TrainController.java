package trainservice.controller;



import trainservice.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



import trainservice.dto.TrainStatusResponse;
import trainservice.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trains")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @GetMapping("/search")
    public Object searchTrains(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam String date) {
        return trainService.getTrainsBetweenStations(from, to, date);
    }


    @GetMapping("/{trainNumber}/availability")
    public TrainStatusResponse getAvailability(
            @PathVariable String trainNumber,
            @RequestParam String date) {

        return trainService.checkSeatAvailability(trainNumber, date);
    }
}