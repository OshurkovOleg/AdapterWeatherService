package ru.home.adapterweather.service.AdapterWeatherService.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MsgB {
    private String text;
    private OffsetDateTime time;
    private int currentTemp;
}
