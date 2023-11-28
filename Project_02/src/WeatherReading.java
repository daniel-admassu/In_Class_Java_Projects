/**
 *
 * @param region name of region the city is located in.
 * @param country name of country the city is located in.
 * @param state name of state the city is located in.
 * @param city name of the city for which the temperature is being collected
 * @param month month for which the temperature is being collected
 * @param day day for which the temperature is being collected
 * @param year year for which the temperature is being collected
 * @param AvgTemperature the average temperature of a city on a specific day
 */
public record WeatherReading(String region, String country, String state, String city,
                             int month, int day, int year, double AvgTemperature)
        implements Comparable<WeatherReading> {
    @Override
    public int compareTo(WeatherReading other){
            if(this.country.compareTo(other.country) == 0){
                if(this.state.compareTo(other.state) == 0){
                    if (this.city.compareTo(other.city)==0){
                        if ((((Integer) this.year).compareTo(other.year()))==0){
                            if (((Integer) this.month).compareTo(other.month)==0){
                                return ((Integer) this.day).compareTo(other.day);
                            }
                            return ((Integer) this.month).compareTo(other.month);
                        }
                        return (((Integer) this.year).compareTo(other.year()));
                    }
                    return this.city.compareTo(other.city);
                }
                return this.state.compareTo(other.state);
            }
            return this.country.compareTo(other.country);
    }


}
