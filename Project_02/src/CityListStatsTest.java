import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class CityListStatsTest {

    @Test
    void startingIndex() throws FileNotFoundException {
        File file = new File("city_temperature.csv");
        GlobalWeatherManager gm = new GlobalWeatherManager(file);
        int n = gm.getCityListStats("US","Washington","Seattle").startingIndex();
        assertEquals("Seattle", gm.getReading(n).city());
    }

    @Test
    void count() throws FileNotFoundException {
        File file = new File("city_temperature.csv");
        GlobalWeatherManager gm = new GlobalWeatherManager(file);
        int n = gm.getCityListStats("US","Washington","Seattle").count();
        int m = gm.getCityListStats("US","Washington","Seattle").startingIndex();
        assertEquals("Seattle", gm.getReading(n+m-1).city());
        assertFalse(Boolean.parseBoolean("Seattle"), gm.getReading(n+m).city());
    }

    @Test
    void years() throws FileNotFoundException {
        File file = new File("city_temperature.csv");
        GlobalWeatherManager gm = new GlobalWeatherManager(file);
        int[] n = gm.getCityListStats("US","Washington","Seattle").years();
        assertEquals(1995, n[0]);
        assertEquals(2020, n[n.length-1]);
    }
}