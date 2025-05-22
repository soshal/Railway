package trainservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "train-api", url = "${railway.api.url}")
public interface TrainApiClient {

    @GetMapping("/api/v3/trainBetweenStations")
    Object getTrainsBetweenStations(
            @RequestHeader("X-RapidAPI-Key") String apiKey,
            @RequestHeader("X-RapidAPI-Host") String apiHost,
            @RequestParam String fromStationCode,
            @RequestParam String toStationCode,
            @RequestParam String dateOfJourney);
}