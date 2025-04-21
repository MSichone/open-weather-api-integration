package dev.masitano.api.weather.openweather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherApi {

  @GET("weather")
  Call<OpenWeatherResponse> getCurrentWeatherByCity(
      @Query("q") String city,
      @Query("APPID") String apiKey,
      @Query("units") String units // e.g., "metric" for Celsius
  );

  @GET("weather")
  Call<OpenWeatherResponse> getCurrentWeatherByCityId(
      @Query("id") String cityId,
      @Query("APPID") String apiKey,
      @Query("units") String units
  );

  @GET("weather")
  Call<OpenWeatherResponse> getCurrentWeatherByCoordinates(
      @Query("lat") double latitude,
      @Query("lon") double longitude,
      @Query("APPID") String apiKey,
      @Query("units") String units
  );

}
