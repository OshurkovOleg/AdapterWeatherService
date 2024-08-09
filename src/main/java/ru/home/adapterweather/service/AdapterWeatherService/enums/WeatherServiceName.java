package ru.home.adapterweather.service.AdapterWeatherService.enums;

public enum WeatherServiceName {

    GISMETEO("gismeteo"),
    ANOTHER("another");

    private final String serviceName;

    WeatherServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getName() {
        return serviceName;
    }
}
