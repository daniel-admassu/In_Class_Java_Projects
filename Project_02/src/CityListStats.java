/**
 *
 * @param startingIndex the first index in which a specific city is found
 * @param count the amount of weather readings there are for the city
 * @param years an array of years in which weather readings have been collected for that city
 */
public record CityListStats(int startingIndex, int count, int[] years) {
}
