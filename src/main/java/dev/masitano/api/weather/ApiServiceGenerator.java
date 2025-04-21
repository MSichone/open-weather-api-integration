package dev.masitano.api.weather;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
public class ApiServiceGenerator {

  /* returns a functioning REST client */
  public static Retrofit createService(String baseUrl){

    // add logging support
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

    OkHttpClient client = new OkHttpClient.Builder()
        .addInterceptor(chain -> {
          Request request = chain.request();
          Request headers = request.newBuilder()
              .addHeader("Content-Type", "application/json")
              .addHeader("Accept", "application/json")
              .build();
          return chain.proceed(headers);
        })
        .addInterceptor(loggingInterceptor)
        .build();

    // build HTTP client
    return new Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build();
  }
}
