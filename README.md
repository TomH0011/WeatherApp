Weather App
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
This weather App uses the weather API to search for locations getting the latitude and lonitude from the entered place name
This then returns data such as:
-Temperture
-Forecast
-Short description
All this information can be entered on a GUI after running the main class
The information is then displayed on the GUI with a corresponsding emoji to match the forecast
Temperture may also be converted between Celsius and Fahrenheit
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
How this Code works:
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
The main class is simply used to run all the code from all other classes as it simply runs the WindowCreator class.
The WeatherAPI calls in the API from the URL and also feeds it the API key to allow a response back from the API.
CallingWeatherData class is responsible for getting the desired information from the API and in this case is Temperture, the "main" forecast and a short description of the weather.
For the LonLatFromName, this class is simply responsible for getting the latitude and longitude from a given place name.
The TempertureConverter class is used to swap between celsius and Fahrenheit and return a double of the temperture to 2 D.P.
WeatherEmojiMapper class is to give a corresponding emoji to the "main" forecast hidden in the weather API data
The WindowCreator class is the meat of the project which has the role of creating the GUI's frame as well as panels and is also for calling the information from all 
other classes in order to give actions to the buttons on the GUI.
Finally the TempPlusDescription class is just to allow the calling of the temperture and the short description from the CallingWeatherData class
