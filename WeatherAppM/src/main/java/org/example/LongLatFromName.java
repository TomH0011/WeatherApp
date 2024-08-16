package org.example;

import org.json.JSONArray;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class LongLatFromName {
    // Method to get latitude and longitude from place name
    public static double[] getLatLonFromPlaceName(String placeName) {
        try {
            JSONArray jsonArray = getObjects(placeName);
            if (jsonArray != null && jsonArray.length() > 0) {
                org.json.JSONObject jsonObject = jsonArray.getJSONObject(0);
                double lat = jsonObject.getDouble("lat");
                double lon = jsonObject.getDouble("lon");
                return new double[]{lat, lon};
            } else {
                System.out.println("Place not found: " + placeName);
                return null; // Place not found
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static JSONArray getObjects(String placeName) throws IOException {
        String url = String.format("http://api.openweathermap.org/geo/1.0/direct?q=%s&limit=1&appid=%s", placeName, WeatherAPI.API_KEY);
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        Scanner scanner = new Scanner(connection.getInputStream());
        StringBuilder response = new StringBuilder();
        while (scanner.hasNext()) {
            response.append(scanner.nextLine());
        }
        scanner.close();

        return new JSONArray(response.toString());
    }
}
