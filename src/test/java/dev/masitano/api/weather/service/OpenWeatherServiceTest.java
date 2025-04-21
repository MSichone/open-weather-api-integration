package dev.masitano.api.weather.service;

import dev.masitano.api.weather.openweather.OpenWeatherApiService;
import dev.masitano.api.weather.openweather.OpenWeatherResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OpenWeatherServiceTest {

    @Mock
    private OpenWeatherApiService openWeatherApiService;

    @InjectMocks
    private OpenWeatherService openWeatherService;

    @Captor
    private ArgumentCaptor<String> stringCaptor;

    @Captor
    private ArgumentCaptor<Double> doubleCaptor;

    @Test
    public void testGetCurrentWeather_Success() {
        // Given
        String city = "London";
        OpenWeatherResponse expectedResponse = createMockWeatherResponse(city);
        when(openWeatherApiService.getCurrentWeather(city)).thenReturn(expectedResponse);

        // When
        OpenWeatherResponse actualResponse = openWeatherService.getCurrentWeather(city);

        // Then
        assertWeatherResponse(expectedResponse, actualResponse, city);

        // Verify interactions
        verify(openWeatherApiService).getCurrentWeather(stringCaptor.capture());
        assertEquals(city, stringCaptor.getValue());
        verifyNoMoreInteractions(openWeatherApiService);
    }

    @Test
    public void testGetCurrentWeatherByCityId_Success() {
        // Given
        String cityId = "2643743"; // London
        String cityName = "London";
        OpenWeatherResponse expectedResponse = createMockWeatherResponse(cityName);
        when(openWeatherApiService.getCurrentWeatherByCityId(cityId)).thenReturn(expectedResponse);

        // When
        OpenWeatherResponse actualResponse = openWeatherService.getCurrentWeatherByCityId(cityId);

        // Then
        assertWeatherResponse(expectedResponse, actualResponse, cityName);

        // Verify interactions
        verify(openWeatherApiService).getCurrentWeatherByCityId(stringCaptor.capture());
        assertEquals(cityId, stringCaptor.getValue());
        verifyNoMoreInteractions(openWeatherApiService);
    }

    @Test
    public void testGetCurrentWeatherByCoordinates_Success() {
        // Given
        double latitude = 51.5074;
        double longitude = -0.1278;
        String cityName = "London";
        OpenWeatherResponse expectedResponse = createMockWeatherResponse(cityName);
        when(openWeatherApiService.getCurrentWeatherByCoordinates(latitude, longitude)).thenReturn(expectedResponse);

        // When
        OpenWeatherResponse actualResponse = openWeatherService.getCurrentWeatherByCoordinates(latitude, longitude);

        // Then
        assertWeatherResponse(expectedResponse, actualResponse, cityName);

        // Verify interactions
        verify(openWeatherApiService).getCurrentWeatherByCoordinates(
                doubleCaptor.capture(), doubleCaptor.capture());
        assertEquals(latitude, doubleCaptor.getAllValues().get(0));
        assertEquals(longitude, doubleCaptor.getAllValues().get(1));
        verifyNoMoreInteractions(openWeatherApiService);
    }


    private OpenWeatherResponse createMockWeatherResponse(String cityName) {
        OpenWeatherResponse response = new OpenWeatherResponse();
        response.setName(cityName);

        OpenWeatherResponse.Main main = new OpenWeatherResponse.Main();
        main.setTemp(15.5);
        main.setHumidity(80);
        response.setMain(main);

        return response;
    }

    private void assertWeatherResponse(OpenWeatherResponse expected, OpenWeatherResponse actual, String cityName) {
        assertEquals(expected, actual);
        assertEquals(cityName, actual.getName());
        assertEquals(15.5, actual.getMain().getTemp());
        assertEquals(80, actual.getMain().getHumidity());
    }
}
