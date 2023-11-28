/**
 * crates a book record with all the information needed
 * @param ISBN the isbn of the book
 * @param Authors the author of the book
 * @param PublicationYear the year the book was published
 * @param OriginalTitle the original title of the book
 * @param Title the title of the book
 * @param AverageRating the average rating of the book
 */
public record Book(String ISBN, String Authors, int PublicationYear , String OriginalTitle,
                        String Title, double AverageRating) {

}


