import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

/** GlobalWeatherManager implements GlobalWeatherManagerInterface for */
public class GlobalWeatherManager implements  GlobalWeatherManagerInterface {
     /** an ArrayList to store all the data read from the file */
    private final ArrayList<WeatherReading> readings = new ArrayList<>();
    /** to keep track of the total amount data read from the file */
    private int totalCount = 0;
    /**
     * reads the file, store the each line as weatherReading and add them into an ArrayList
     * @throws FileNotFoundException throws a helpful message when a file is not found
     */
    public GlobalWeatherManager(File fileName) throws FileNotFoundException {
        Scanner input = new Scanner(fileName);

        String firstLine = input.next();
        String secondLine = input.nextLine();


        while (input.hasNextLine()) {

            String word = input.nextLine();
            String[] parts = word.split(",", 8);
            String region = parts[0];
            String country = parts[1];
            String state = parts[2];
            String city = parts[3];
            String month = parts[4];
            int intmonth = Integer.parseInt(month);
            String day = parts[5];
            int intday = Integer.parseInt(day);
            String year = parts[6];
            int intyear = Integer.parseInt(year);
            String avgTemp = parts[7];
            double dubAvgTemp = Double.parseDouble(avgTemp);
            WeatherReading theCity = new WeatherReading(region, country, state, city,
                    intmonth, intday, intyear, dubAvgTemp);
            readings.add(theCity);
            totalCount++;


        }
        Collections.sort(readings);


    }


    @Override
    public int getReadingCount() {
        return totalCount;
    }

    @Override
    public WeatherReading getReading(int index) {
        if(index<0||index>totalCount)
            throw new IndexOutOfBoundsException("must input a valid index");


        return readings.get(index);
    }

    @Override
    public WeatherReading[] getReadings(int index, int count) {
        if(index<0||index>totalCount)
            throw new IndexOutOfBoundsException("must input a valid index");
        if(count<1||index+count>totalCount )
            throw new IndexOutOfBoundsException("must input a valid count");
        WeatherReading[] readArray = new WeatherReading[count];
        for (int i = 0; i < count; i++) {
            readArray[i] = readings.get(index + i);
        }
        return readArray;
    }

    @Override
    public WeatherReading[] getReadings(int index, int count, int month, int day) {

        if(month<1||month>12||day<1||day>31)
            throw new IllegalArgumentException("month and day need to be valid");
        if(index<0||index>totalCount)
            throw new IndexOutOfBoundsException("must input a valid index");
        if(count<1||index+count>totalCount )
            throw new IndexOutOfBoundsException("must input a valid count");
        WeatherReading[] readArray = new WeatherReading[count/365];
        int counter = 0;
        for (int i = 0; i < count; i++) {
            if (readings.get(index + i).month() == month) {
                if (readings.get(index + i).day() == day) {
                    readArray[counter] = readings.get(index + i);
                    counter++;
                }
            }

        }

        return readArray;
    }

    @Override
    public CityListStats getCityListStats(String country, String state, String city) {
        if(country==null||country.isEmpty())
            throw new IllegalArgumentException("country must not be null or empty");
        if(state==null||state.isEmpty())
            throw new IllegalArgumentException("state must not be null or empty");
        if(city==null||city.isEmpty())
            throw new IllegalArgumentException("city must not be null or empty");
        int startingIndex = 0;
        int count=0;
        int firstElement = 0;
        int lastElement = readings.size() - 1;

        while (firstElement <= lastElement) {
            int mid = (firstElement + lastElement) / 2;
            int compareCountry = readings.get(mid).country().compareTo(country);
            int compareState = readings.get(mid).state().compareTo(state);
            int compareCity = readings.get(mid).city().compareTo(city);
            if(compareCountry==0){
                if (compareState==0){
                    if(compareCity==0){
                        startingIndex=mid;
                        lastElement=0;
                    }else if(compareCity<0){
                        firstElement=mid+1;
                    } else if (compareCity>0) {
                        lastElement=mid-1;
                    }
                }else if(compareState<0){
                    firstElement = mid + 1;
                }else if(compareState>0){
                    lastElement=mid-1;
                }
            }else if (compareCountry<0){
                firstElement = mid + 1;
            } else if (compareCountry > 0) {
                lastElement =mid-1;
            }
        }
        while(readings.get(startingIndex).city().compareTo(city)==0 && startingIndex>0){
            startingIndex--;
        }

        startingIndex++;
        int i=0;
        while(readings.get(startingIndex+i).city().compareTo(city)==0){
            count++;
            i++;
        }
        if(startingIndex==1&&count==0){
            return null;
        }
        ArrayList<Integer> year = new ArrayList<>();
        for (int j = 0; j < count; j++) {
            year.add(Integer.parseInt(String.valueOf(readings.get(startingIndex+j).year())));
        }


        int[] years = new int[year.size()];
        for (int index = 0; index < count; index++) {
            years[index] = year.get(index);

        }

        return new CityListStats(startingIndex,count,years);

    }

    @Override
    public Iterator<WeatherReading> iterator() {
        return readings.iterator();
    }

    @Override
    public double getTemperatureLinearRegressionSlope(WeatherReading[] readings) {
        if(readings == null||readings.length<2)
            throw new IllegalArgumentException("array for readings must not be null or have less than 2 elements");
        ArrayList<Double> avgTemp = new ArrayList<>();
        ArrayList<Integer> years = new ArrayList<>();
        for (int i = 0; i < readings.length; i++) {
            if(readings[i].AvgTemperature()!=-99&&readings[i]!=null){
                avgTemp.add(readings[i].AvgTemperature());
                years.add(readings[i].year());
            }
        }
        Integer[] x = new Integer[avgTemp.size()];
        Double[] y = new Double[avgTemp.size()];
        for (int i = 0; i < avgTemp.size(); i++) {
            y[i]=avgTemp.get(i);
            x[i]= years.get(i);
        }

        return calcLinearRegressionSlope(x,y);
    }
    @Override
    public double calcLinearRegressionSlope(Integer[] x, Double[] y) {
        if(x == null||x.length<2)
            throw new IllegalArgumentException("array for \"x\" must not be null or have less than 2 elements");
        if (y== null|| x.length!= y.length)
            throw new IllegalArgumentException("array for \"y\" must not be null or have a different size with array \"x\" ");
        int xSum = 0;
        double ySum =0;
        double xySum = 0;
        int xsqr = 0;
        int n = x.length;
        for (int i = 0; i < n; i++) {
            xSum+=x[i];
            ySum+=y[i];
            xySum+=(x[i]*y[i]);
            xsqr+=(Math.pow(x[i],2));
        }

        return  ((n*xySum)-(xSum*ySum))/((n*xsqr)-(Math.pow(xSum,2)));
    }

}
