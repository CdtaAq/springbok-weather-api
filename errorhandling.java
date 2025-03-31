package com.weatherapp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WeatherController {

    @Value("${weather.api.key}")
    private String apiKey;
    
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";
    
    @GetMapping("/weather")
    public ResponseEntity<String> getWeather(@RequestParam String city) {
        String url = BASE_URL + "?q=" + city + "&appid=" + apiKey + "&units=metric";
        
        RestTemplate restTemplate = new RestTemplate();
        try {
            String weatherData = restTemplate.getForObject(url, String.class);
            return ResponseEntity.ok(weatherData); // Return the raw response, or format it as needed
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error: Unable to fetch weather data for " + city);
        }
    }
}
