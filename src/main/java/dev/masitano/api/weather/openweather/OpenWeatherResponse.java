package dev.masitano.api.weather.openweather;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;

@Data
public class OpenWeatherResponse {
  private Coord coord;
  private List<Weather> weather;
  private Main main;
  private Wind wind;
  private String name;
  private Clouds clouds;
  private long dt;
  private Sys sys;
  private long id;

  @Data
  public static class Coord {
    private double lon;
    private double lat;
  }

  @Data
  public static class Weather {
    private int id;
    private String main;
    private String description;
    private String icon;
  }

  @Data
  public static class Main {
    private double temp;
    @SerializedName("feels_like")
    private double feels_like;
    @SerializedName("temp_min")
    private double temp_min;
    @SerializedName("temp_max")
    private double temp_max;
    private int pressure;
    private int humidity;
  }

  @Data
  public static class Wind {
    private double speed;
    private int deg;
  }

  @Data
  public static class Clouds {
    private int all;
  }

  @Data
  public static class Sys {
    private int type;
    private int id;
    private String country;
    private long sunrise;
    private long sunset;
  }
}