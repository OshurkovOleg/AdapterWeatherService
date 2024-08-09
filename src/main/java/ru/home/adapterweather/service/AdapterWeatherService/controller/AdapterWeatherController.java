package ru.home.adapterweather.service.AdapterWeatherService.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.home.adapterweather.service.AdapterWeatherService.constants.Constants;
import ru.home.adapterweather.service.AdapterWeatherService.dto.MsgA;
import ru.home.adapterweather.service.AdapterWeatherService.services.WeatherDataService;
import ru.home.adapterweather.service.AdapterWeatherService.services.WeatherFactoryService;
import ru.home.adapterweather.service.AdapterWeatherService.services.WeatherService;

@RestController
@RequestMapping("/api")
public class AdapterWeatherController {

    private final WeatherFactoryService weatherFactoryService;
    private final WeatherDataService weatherDataService;

    @Autowired
    public AdapterWeatherController(WeatherFactoryService weatherFactoryService, WeatherDataService weatherDataService) {
        this.weatherFactoryService = weatherFactoryService;
        this.weatherDataService = weatherDataService;
    }

    @Operation(summary = "Получить сообщение с координатами, отправить координаты в gismeteo, полученную температуру передать в ServiceB")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно получены данные о погоде и переданы в Service B"),
            @ApiResponse(responseCode = "400", description = "Сообщение не может быть пустым"),
            @ApiResponse(responseCode = "500", description = "Сервис погоды не доступен")})
    @PostMapping("/message")
    public ResponseEntity<HttpStatus> saveNewCheck(@RequestBody MsgA msgA) {
        if (msgA.getMsg() == null || msgA.getMsg().isEmpty()) {
            throw new IllegalArgumentException(Constants.MESSAGE_CANNOT_EMPTY);
        }

        if (!msgA.getLng().equals("ru")){
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        WeatherService weatherService = weatherFactoryService.getWeatherService();
        String currentTemperature;
        try {
            currentTemperature = weatherService.getCurrentTemperature(msgA);
        }catch (Exception e) {
            throw new RuntimeException(Constants.WEATHER_SERVICE_NOT_AVAILABLE);
        }

        weatherDataService.send(currentTemperature);

        return ResponseEntity.ok(HttpStatus.OK);
    }



}
