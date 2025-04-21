package dev.masitano.api.weather.service;

import dev.masitano.api.weather.openweather.OpenWeatherApiService;
import dev.masitano.api.weather.openweather.OpenWeatherResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementation of the WeatherService interface using the OpenWeather API.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OpenWeatherService implements WeatherService {

    private final OpenWeatherApiService openWeatherApiService;

    @Override
    public OpenWeatherResponse getCurrentWeather(String city) {
        log.info("Getting current weather for city: {}", city);
        return openWeatherApiService.getCurrentWeather(city);
    }

    @Override
    public OpenWeatherResponse getCurrentWeatherByCityId(String cityId) {
        log.info("Getting current weather for city ID: {}", cityId);
        return openWeatherApiService.getCurrentWeatherByCityId(cityId);
    }

    @Override
    public OpenWeatherResponse getCurrentWeatherByCoordinates(double latitude, double longitude) {
        log.info("Getting current weather for coordinates: lat={}, lon={}", latitude, longitude);
        return openWeatherApiService.getCurrentWeatherByCoordinates(latitude, longitude);
    }

}
