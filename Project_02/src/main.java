
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

/**
 Runnable entry point for the code
 */
public class main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("city_temperature.csv");
        GlobalWeatherManager gm = new GlobalWeatherManager(file);
        System.out.println(gm.getCityListStats("US", "Washington", "Seattle"));
        System.out.println(gm.getReading(0));
//        int b = gm.getCityListStats("US","Washington","Seattle").count();
//
//        WeatherReading[] n = gm.getReadings(a,b, 6,9);
//        System.out.println(gm.getTemperatureLinearRegressionSlope(n));


    }
}