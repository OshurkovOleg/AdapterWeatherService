package ru.home.adapterweather.service.AdapterWeatherService.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.home.adapterweather.service.AdapterWeatherService.constants.Constants;
import ru.home.adapterweather.service.AdapterWeatherService.dto.MsgA;

@Service
public class GismeteoWeatherService implements WeatherService {

    private final RestTemplate restTemplate;

    @Value("${TOKEN_VALUE_GISMETEO}")
    private String tokenValueGismeteo;

    public GismeteoWeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getCurrentTemperature(MsgA message) throws Exception {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(Constants.URL_API_GISMETEO)
                .queryParam(Constants.LATITUDE, message.getCoordinates().getLatitude())
                .queryParam(Constants.LONGITUDE, message.getCoordinates().getLongitude());

        HttpHeaders headers = new HttpHeaders();
        headers.set(Constants.TOKEN_GISMETEO, tokenValueGismeteo);
        headers.set(Constants.HEADERS_ACCEPT, Constants.HEADERS_APP_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        // Выполняем GET-запрос к API
        ResponseEntity<String> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET,
                httpEntity, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            // как-то обрабатываем ответ и вытаскиваем нужные данные для передачи в Service2
        }

        return response.getBody();
    }
}
