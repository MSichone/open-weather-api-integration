package dev.masitano.api.weather.controller;

import dev.masitano.api.weather.openweather.OpenWeatherResponse;
import dev.masitano.api.weather.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
@Tag(name = "Weather API", description = "API for retrieving weather information from various sources")
public class WeatherController {

    private final WeatherService weatherService;

    @Operation(summary = "Get current weather by city name", description = "Returns current weather information for the specified city")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved weather data",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = OpenWeatherResponse.class))),
        @ApiResponse(responseCode = "404", description = "Weather data not found for the specified city",
                content = @Content)
    })
    @GetMapping("/current")
    public ResponseEntity<OpenWeatherResponse> getCurrentWeather(
            @Parameter(description = "City name", example = "London", required = true) @RequestParam String city) {
        log.info("Request to get current weather for city: {}", city);
        OpenWeatherResponse response = weatherService.getCurrentWeather(city);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get current weather by city ID", description = "Returns current weather information for the specified city ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved weather data",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = OpenWeatherResponse.class))),
        @ApiResponse(responseCode = "404", description = "Weather data not found for the specified city ID",
                content = @Content)
    })
    @GetMapping("/current/city-id")
    public ResponseEntity<OpenWeatherResponse> getCurrentWeatherByCityId(
            @Parameter(description = "City ID", example = "2643743", required = true) @RequestParam String cityId) {
        log.info("Request to get current weather for city ID: {}", cityId);
        OpenWeatherResponse response = weatherService.getCurrentWeatherByCityId(cityId);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get current weather by geographic coordinates", description = "Returns current weather information for the specified latitude and longitude")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved weather data",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = OpenWeatherResponse.class))),
        @ApiResponse(responseCode = "404", description = "Weather data not found for the specified coordinates",
                content = @Content)
    })
    @GetMapping("/current/coordinates")
    public ResponseEntity<OpenWeatherResponse> getCurrentWeatherByCoordinates(
            @Parameter(description = "Latitude", example = "51.5074", required = true) @RequestParam double lat,
            @Parameter(description = "Longitude", example = "-0.1278", required = true) @RequestParam double lon) {
        log.info("Request to get current weather for coordinates: lat={}, lon={}", lat, lon);
        OpenWeatherResponse response = weatherService.getCurrentWeatherByCoordinates(lat, lon);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

}
