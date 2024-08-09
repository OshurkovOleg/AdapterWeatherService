package ru.home.adapterweather.service.AdapterWeatherService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.home.adapterweather.service.AdapterWeatherService.constants.Constants;

@Service
public class WeatherDataService {

    private final RestTemplate restTemplate;

    @Autowired
    public WeatherDataService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void send(String messageTemperature) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(messageTemperature, headers);

        ResponseEntity<String> response;
        try {
            response = restTemplate.exchange("SeviceB", HttpMethod.POST, httpEntity, String.class);
        } catch (Exception e) {
            throw new RuntimeException(Constants.SERVICE_B_NOT_AVAILABLE);
        }

        if (response.getStatusCode().is2xxSuccessful()) {
            String body = response.getBody();
            // как-то обрабатываем ответ, если требуется
        }
    }

}
