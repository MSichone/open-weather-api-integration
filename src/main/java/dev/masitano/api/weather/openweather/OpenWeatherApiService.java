package dev.masitano.api.weather.openweather;

import dev.masitano.api.weather.ApiService;
import dev.masitano.api.weather.ApiServiceGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;

@Slf4j
@Service
public class OpenWeatherApiService extends ApiService {

  private final OpenWeatherApi openWeatherApi;

  @Value("${openweather.api.key}")
  private String apiKey;

  public OpenWeatherApiService(@Value("${openweather.api.url}") String baseUrl) {
    this.openWeatherApi = ApiServiceGenerator.createService(baseUrl).create(OpenWeatherApi.class);
  }

  public OpenWeatherResponse getCurrentWeather(String city) {
    log.info("OpenWeatherApiService.getCurrentWeather for city {}", city);
    Call<OpenWeatherResponse> call = openWeatherApi.getCurrentWeatherByCity(city, apiKey, "metric");
    return executeCall(call, "getCurrentWeather", city);
  }

  public OpenWeatherResponse getCurrentWeatherByCityId(String cityId) {
    log.info("OpenWeatherApiService.getCurrentWeatherByCityId for id {}", cityId);
    Call<OpenWeatherResponse> call = openWeatherApi.getCurrentWeatherByCityId(cityId, apiKey, "metric");
    return executeCall(call, "getCurrentWeatherByCityId", cityId);
  }

  public OpenWeatherResponse getCurrentWeatherByCoordinates(double latitude, double longitude) {
    log.info("OpenWeatherApiService.getCurrentWeatherByCoordinates for lat: {}, lon: {}", latitude, longitude);
    Call<OpenWeatherResponse> call = openWeatherApi.getCurrentWeatherByCoordinates(latitude, longitude, apiKey, "metric");
    return executeCall(call, "getCurrentWeatherByCoordinates", latitude + "," + longitude);
  }

}
