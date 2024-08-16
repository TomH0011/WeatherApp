package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowCreator {
    private static boolean isCelsius = true; // Track if the current temperature is in Celsius
    private static double currentTemperature = 0.0; // Store the current temperature
    private static String currentWeatherDescription = ""; // Store the current weather description
    private static String currentForecast = ""; // Store the general forecast

    public static void createWindow() {
        JFrame f = new JFrame("Weather App"); // Creating instance of JFrame

        // Set the layout manager to null for absolute positioning
        f.setLayout(null);

        JLabel weatherLabelEmoji = new JLabel("Weather Emoji:");
        weatherLabelEmoji.setBounds(10, 110, 300, 100);
        f.add(weatherLabelEmoji);

        // Create a JPanel and set its bounds (position and size)
        JPanel panel = new JPanel();
        panel.setLayout(null); // Also set JPanel layout to null if you want to manually position components inside it
        panel.setBounds(5, 100, 300, 50); // x, y, width, height

        // Add a JTextField to the panel and set its bounds
        JTextField textField = new JTextField("Search...");
        textField.setBounds(10, 10, 180, 30); // Positioning within the panel
        panel.add(textField);

        // Add the panel to the frame
        f.add(panel);

        // Create a JLabel to display the temperature and description
        JLabel weatherLabel = new JLabel("Weather Info:");
        weatherLabel.setBounds(10, 70, 300, 30);
        f.add(weatherLabel);

        // Create a JButton and set its bounds
        JButton button = getSearchButton(panel);

        // Creating Unit Buttons
        UnitButtons unitbuttons = getUnitButtons(f);

        // Set the size and visibility of the JFrame
        f.setSize(600, 750); // 600 width and 750 height
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensures the app closes on exit
        f.setVisible(true); // Making the frame visible

        // Add action listener to the search button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String placeName = textField.getText().trim();

                if (placeName.isEmpty()) {
                    weatherLabel.setText("Please enter a location.");
                    weatherLabelEmoji.setText(null);
                    return;
                }

                // Retrieve the latitude and longitude
                double[] latLon = LongLatFromName.getLatLonFromPlaceName(placeName);
                if (latLon != null) {
                    // Get the weather data
                    TempPlusDescription weatherData = CallingWeatherData.getWeatherData(latLon[0], latLon[1]);

                    if (weatherData != null) {
                        // Store the current temperature and description
                        currentTemperature = weatherData.temperature();
                        currentWeatherDescription = weatherData.weatherDescription();
                        currentForecast = weatherData.forecast();
                        isCelsius = true; // Set the initial state to Celsius

                        // Update the weatherLabel with the temperature and weather description
                        String weatherInfo = String.format("Temp: %.2f°C, Description: %s", currentTemperature, currentWeatherDescription);
                        weatherLabel.setText(weatherInfo);

                        // Update the weatherLabelEmoji with the correct emoji using WeatherEmojiMapper
                        String weatherForecastEmoji = WeatherEmojiMapper.getWeatherEmoji(currentForecast);
                        weatherLabelEmoji.setText("Forecast: " + weatherForecastEmoji);
                    } else {
                        weatherLabel.setText("Failed to retrieve weather data.");
                        weatherLabelEmoji.setText(null);
                    }
                } else {
                    weatherLabel.setText("Location not found or invalid input.");
                    weatherLabelEmoji.setText(null);
                }
            }
        });

        // Add action listener for the Celsius button
        unitbuttons.celsiusButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isCelsius) { // Only convert if currently in Fahrenheit
                    double celsiusTemp = Temperatureconverter.getFaren(currentTemperature);
                    currentTemperature = celsiusTemp; // Update the current temperature to Celsius
                    weatherLabel.setText(String.format("Temp: %.2f°C, Description: %s", currentTemperature, currentWeatherDescription));
                    isCelsius = true;
                }
            }
        });

        // Add action listener for the Fahrenheit button
        unitbuttons.fahrenheitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isCelsius) { // Only convert if currently in Celsius
                    double fahrenheitTemp = Temperatureconverter.getCelc(currentTemperature);
                    currentTemperature = fahrenheitTemp; // Update the current temperature to Fahrenheit
                    weatherLabel.setText(String.format("Temp: %.2f°F, Description: %s", currentTemperature, currentWeatherDescription));
                    isCelsius = false;
                }
            }
        });

        // Making "Search..." go away when the text field gains focus
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                textField.setText(null); // Empty the text field when it receives focus
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText("Search...");
                }
            }
        });
    }

    private static UnitButtons getUnitButtons(JFrame f) {
        JButton celsiusButton = new JButton("Celsius");
        JButton fahrenheitButton = new JButton("Fahrenheit");
        fahrenheitButton.setBounds(125, 175, 100, 30); // Positioning relative to the panel's layout
        celsiusButton.setBounds(15, 175, 100, 30); // Positioning relative to the panel's layout
        f.add(celsiusButton);
        f.add(fahrenheitButton);
        UnitButtons unitbuttons = new UnitButtons(celsiusButton, fahrenheitButton);
        return unitbuttons;
    }

    private record UnitButtons(JButton celsiusButton, JButton fahrenheitButton) {
    }

    private static JButton getSearchButton(JPanel panel) {
        JButton button = new JButton("Search");
        button.setBounds(210, 10, 80, 30); // Positioning relative to the panel's layout
        panel.add(button);
        return button;
    }
}
