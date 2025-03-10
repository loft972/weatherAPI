package com.lflolo.weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("v1/weatherApi")
public class WeatherController {

    Logger log = LoggerFactory.getLogger(WeatherController.class);

    @GetMapping("/city")
    public ResponseEntity<String> wheatherByCity(@RequestParam String city) throws IOException, InterruptedException {
        log.info("city : " + city);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/Lille?unitGroup=metric&include=current&key=8TCUXRHYJJFPT8U9FTV4XBTM2&contentType=json"))
                .method("GET", HttpRequest.BodyPublishers.noBody()).build();
        HttpResponse response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return new ResponseEntity(response.body(), HttpStatus.OK);
    }
}
