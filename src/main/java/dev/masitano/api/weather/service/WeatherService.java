package dev.masitano.api.weather.service;

import dev.masitano.api.weather.openweather.OpenWeatherResponse;

/**
 * Service interface for weather-related operations.
 */
public interface WeatherService {

    /**
     * Get the current weather for a specified city.
     *
     * @param city the name of the city
     * @return the current weather data for the city, or null if not found
     */
    OpenWeatherResponse getCurrentWeather(String city);

    /**
     * Get the current weather for a specified city ID.
     *
     * @param cityId the ID of the city
     * @return the current weather data for the city, or null if not found
     */
    OpenWeatherResponse getCurrentWeatherByCityId(String cityId);

    /**
     * Get the current weather for specified geographic coordinates.
     *
     * @param latitude the latitude coordinate
     * @param longitude the longitude coordinate
     * @return the current weather data for the location, or null if not found
     */
    OpenWeatherResponse getCurrentWeatherByCoordinates(double latitude, double longitude);

}
