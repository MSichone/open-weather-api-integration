# Weather API

This project provides a REST API for retrieving weather data from the OpenWeather API.

## API Endpoints

### Get Current Weather by City Name
```
GET /api/weather/current?city={city}
```
- **Parameters**:
  - `city`: The name of the city (e.g., "London")
- **Response**: Weather data for the specified city

### Get Current Weather by City ID
```
GET /api/weather/current/city-id?cityId={cityId}
```
- **Parameters**:
  - `cityId`: The OpenWeather city ID (e.g., "2643743" for London)
- **Response**: Weather data for the specified city ID

### Get Current Weather by Geographic Coordinates
```
GET /api/weather/current/coordinates?lat={latitude}&lon={longitude}
```
- **Parameters**:
  - `lat`: The latitude coordinate (e.g., 51.5074)
  - `lon`: The longitude coordinate (e.g., -0.1278)
- **Response**: Weather data for the specified coordinates

## Response Format

All endpoints return weather data in the following format:

```json
{
  "coord": {
    "lon": -0.13,
    "lat": 51.51
  },
  "weather": [
    {
      "id": 300,
      "main": "Drizzle",
      "description": "light intensity drizzle",
      "icon": "09d"
    }
  ],
  "main": {
    "temp": 15.5,
    "feels_like": 14.8,
    "temp_min": 14.0,
    "temp_max": 17.0,
    "pressure": 1012,
    "humidity": 80
  },
  "wind": {
    "speed": 4.1,
    "deg": 80
  },
  "clouds": {
    "all": 90
  },
  "dt": 1485789600,
  "sys": {
    "type": 1,
    "id": 5091,
    "country": "GB",
    "sunrise": 1485762037,
    "sunset": 1485794875
  },
  "id": 2643743,
  "name": "London"
}
```

## API Documentation
The API documentation is available at [Swagger UI](http://localhost:8080/swagger-ui/index.html).

## Configuration

The application requires the following configuration in `application.properties`:

```properties
openweather.api.url=https://api.openweathermap.org/data/2.5/
openweather.api.key=your_api_key_here
```

Replace `your_api_key_here` with your OpenWeather API key.