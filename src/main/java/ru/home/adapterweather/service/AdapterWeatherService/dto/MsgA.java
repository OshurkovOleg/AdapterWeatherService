package ru.home.adapterweather.service.AdapterWeatherService.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MsgA {
    private String msg;
    private String lng;
    private Coordinates coordinates;

    @Setter
    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Coordinates {
        private String latitude;
        private String longitude;
    }

}
