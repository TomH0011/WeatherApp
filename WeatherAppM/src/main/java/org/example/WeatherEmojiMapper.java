package org.example;

public class WeatherEmojiMapper {
    public static String getWeatherEmoji(String forecast) {
        return switch (forecast.toLowerCase()) {
            case "sunny", "clear" -> "☀️"; // Sunny or Clear
            case "partly cloudy", "cloudy" -> "⛅"; // Partly Cloudy or Cloudy
            case "overcast" -> "☁️"; // Overcast
            case "rain", "light rain", "showers" -> "🌧️"; // Rain or Showers
            case "thunderstorm" -> "⛈️"; // Thunderstorm
            case "snow", "light snow" -> "🌨️"; // Snow or Light Snow
            case "sleet", "freezing rain" -> "🌨️"; // Sleet or Freezing Rain
            case "fog", "mist" -> "🌫️"; // Fog or Mist
            case "hail" -> "🌨️"; // Hail
            case "windy" -> "🌬️"; // Windy
            case "blizzard" -> "🌪️"; // Blizzard
            default -> "❓"; // Unknown weather condition
        };
    }
}
