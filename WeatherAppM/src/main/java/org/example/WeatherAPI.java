package org.example;

import java.util.Scanner;

public class WeatherAPI {

    public static final String API_KEY = "Replace with your API key"; // Replace with your API key
    public static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    public static void weathergrouped() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a place name (e.g., Paris): ");
        String placeName = scanner.nextLine();

        double[] latLon = LongLatFromName.getLatLonFromPlaceName(placeName);
        if (latLon != null) {
            CallingWeatherData.getWeatherData(latLon[0], latLon[1]);
        } else {
            System.out.println("Could not find the location. Please try again.");
        }

        scanner.close(); // Closing the scanner after operations are complete
    }
}
