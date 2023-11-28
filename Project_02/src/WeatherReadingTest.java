import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeatherReadingTest {

    @org.junit.jupiter.api.Test
    void compareTo() {
        WeatherReading testReading = new WeatherReading("North America","US",
                "Washington", "Seattle", 1, 1,1995, 73.8);
        WeatherReading testReading2 = new WeatherReading("North America","US",
                "Washington", "Seattle", 1, 2,1995, 73.8);
        assertEquals(-1,testReading.compareTo(testReading2));
    }



}