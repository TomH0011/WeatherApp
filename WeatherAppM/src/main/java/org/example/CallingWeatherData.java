package org.example;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

public class CallingWeatherData {

    public static TempPlusDescription getWeatherData(double lat, double lon) {
        String url = String.format("%s?lat=%f&lon=%f&appid=%s&units=metric", WeatherAPI.BASE_URL, lat, lon, WeatherAPI.API_KEY);

        HttpResponse<JsonNode> response = Unirest.get(url).asJson();

        if (response.getStatus() == 200) {
            JSONObject weatherData = response.getBody().getObject();

            // Extract specific data
            String forecast = weatherData.getJSONArray("weather").getJSONObject(0).getString("main");
            double temperature = weatherData.getJSONObject("main").getDouble("temp");
            String weatherDescription = weatherData.getJSONArray("weather").getJSONObject(0).getString("description");

            System.out.println("Current Temperature: " + temperature + "°C");
            System.out.println("Weather Description: " + weatherDescription);

            // Return a new WeatherData object containing the temperature and description
            return new TempPlusDescription(temperature, weatherDescription, forecast);
        } else {
            System.out.println("Failed to fetch weather data: " + response.getStatusText());
            return null;
        }
    }
}
