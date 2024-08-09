package ru.home.adapterweather.service.AdapterWeatherService.services;

import ru.home.adapterweather.service.AdapterWeatherService.dto.MsgA;

public interface WeatherService {

    String getCurrentTemperature(MsgA message) throws Exception;

}
