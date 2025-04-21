package dev.masitano.api.weather;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Response;

@Slf4j
public abstract class ApiService {


  protected <T> T executeCall(Call<T> call, String context, String query) {
    try {
      Response<T> response = call.execute();
      if (response.isSuccessful() && response.body() != null) {
        return response.body();
      } else {
        log.error("Failed to {}: HTTP {} - {}", context, response.code(), response.message());
        return null;
      }
    } catch (IOException e) {
      log.error("Error during {}: {}", context, e.getMessage(), e);
      return null;
    }
  }
}
