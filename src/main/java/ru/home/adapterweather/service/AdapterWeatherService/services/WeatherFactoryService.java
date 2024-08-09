package ru.home.adapterweather.service.AdapterWeatherService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.home.adapterweather.service.AdapterWeatherService.constants.Constants;
import ru.home.adapterweather.service.AdapterWeatherService.enums.WeatherServiceName;

@Component
public class WeatherFactoryService {

    @Value("${weather.service.name}")
    private String weatherServiceName;
    private final GismeteoWeatherService gismeteoWeatherService;

    @Autowired
    public WeatherFactoryService(GismeteoWeatherService gismeteoWeatherService) {
        this.gismeteoWeatherService = gismeteoWeatherService;
    }

    public WeatherService getWeatherService() {
        if (weatherServiceName.equals(WeatherServiceName.GISMETEO.getName())) {
            return gismeteoWeatherService;
        }
        throw new IllegalArgumentException(Constants.UNKNOWN_WEATHER_SERVICE + weatherServiceName);
    }
}
