
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

class GlobalWeatherManagerTest {


    @Test
     void getReadingCount() throws FileNotFoundException {
        File file = new File("city_temperature.csv");
        Scanner input = new Scanner(file);
        int count = 0;
        while (input.hasNextLine()) {
            count++;
        }
        GlobalWeatherManager gm = new GlobalWeatherManager(file);
        assertEquals(count,gm.getReadingCount());

    }

    @Test
    void getReading() throws FileNotFoundException {
        File file = new File("city_temperature.csv");
        GlobalWeatherManager gm = new GlobalWeatherManager(file);
        //albania is the second country in the world alphabetically.
        assertEquals("Albania",gm.getReading(0).country());
        assertEquals(1,gm.getReading(0).month());
        assertEquals(1,gm.getReading(0).day());
        assertEquals(1995,gm.getReading(0).year());


    }

    @Test
    void getReadings() throws FileNotFoundException {
        File file = new File("city_temperature.csv");
        GlobalWeatherManager gm = new GlobalWeatherManager(file);

        int testCount = gm.getCityListStats("US", "Washington","Seattle").count();
        int testIndex = gm.getCityListStats("US", "Washington","Seattle").startingIndex();
        assertEquals( "Seattle",gm.getReadings(testIndex,testCount)[0].city() );
        assertEquals( "Seattle",gm.getReadings(testIndex,testCount)[testCount-1].city() );
    }

    @Test
    void testGetReadings() throws FileNotFoundException {
        File file = new File("city_temperature.csv");
        GlobalWeatherManager gm = new GlobalWeatherManager(file);
        int testCount = gm.getCityListStats("US", "Washington","Seattle").count();
        int testIndex = gm.getCityListStats("US", "Washington","Seattle").startingIndex();
        assertEquals(8,gm.getReadings(testIndex,testCount,8,1)[6].month());
        assertEquals(8,gm.getReadings(testIndex,testCount,8,1)[14].month());
    }

    @Test
    void getCityListStats() throws FileNotFoundException {
        File file = new File("city_temperature.csv");
        GlobalWeatherManager gm = new GlobalWeatherManager(file);
        int testCount = gm.getCityListStats("US", "Washington","Seattle").count();
        int testIndex = gm.getCityListStats("US", "Washington","Seattle").startingIndex();
        int[] testyears = gm.getCityListStats("US", "Washington","Seattle").years();
        assertEquals(1995, gm.getReading(testIndex).year());
        assertEquals(1, gm.getReading(testIndex).day());
        assertEquals("Seattle", gm.getReading(testIndex).city());
        assertEquals("Seattle", gm.getReading(testIndex+testCount-1).city());



    }

    @Test
    void iterator() throws FileNotFoundException {
        File file = new File("city_temperature.csv");
        GlobalWeatherManager gm = new GlobalWeatherManager(file);
        assertEquals(true,gm.iterator().hasNext());
    }

    @Test
    void getTemperatureLinearRegressionSlope() throws FileNotFoundException {
        File file = new File("city_temperature.csv");
        GlobalWeatherManager gm = new GlobalWeatherManager(file);
        CityListStats cityStats = gm.getCityListStats("US", "Washington", "Seattle");
        WeatherReading[] seattleData = gm.getReadings(cityStats.startingIndex(), cityStats.years().length, 7, 1);
        //This number obtained using =LineST in excel to calculate the slope
        assertEquals(0.272239316239316, gm.getTemperatureLinearRegressionSlope(seattleData), 0.000001);
    }

    @Test
    void calcLinearRegressionSlope() throws FileNotFoundException {
        File file = new File("city_temperature.csv");
        GlobalWeatherManager gm = new GlobalWeatherManager(file);
        assertEquals(1.13, gm.calcLinearRegressionSlope(
                new Integer[]{16, 12, 18, 4, 3, 10, 5, 12},
                new Double[]{87.0, 88.0, 89.0, 68.0, 78.0, 80.0, 75.0, 83.0}), 0.01);

    }
}