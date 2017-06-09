package test.adonis.weatherapp.utils;

/**
 * Created by Paola on 09/06/2017.
 */

public class MathUtils {

    public static String KelvinToCelsiusConverter(String kelvin){
        String celsius = "";

        double kelvinDegrees = Float.valueOf(kelvin);
        double celsiusDegrees = kelvinDegrees - 273.15D;
        celsiusDegrees = round(celsiusDegrees , 2 , true);
        celsius = String.valueOf(celsiusDegrees) + " °C";

        return celsius;
    }

    private static double round (double value, int precision, boolean up) {
        int scale = (int) Math.pow(10, precision);
        if (up) {
            return (double) Math.ceil(value * scale) / scale;
        } else {
            return (double) Math.floor(value * scale) / scale;
        }
    }
}
