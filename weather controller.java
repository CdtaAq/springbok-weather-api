package com.weatherapp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@RestController
public class WeatherController {

    @Value("${weather.api.key}")
    private String apiKey; // API key for the weather service
    
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";

    @GetMapping("/weather")
    public String getWeather(@RequestParam String city) {
        String url = BASE_URL + "?q=" + city + "&appid=" + apiKey + "&units=metric";
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        
        return response.getBody(); // You can customize the response to return specific details like temperature, humidity etc.
    }
}
