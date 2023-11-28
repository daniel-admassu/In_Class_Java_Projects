import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Contract for CustomerDataManager
 */
public interface CustomerDataManagerInterface {

    // Constructor should have no parameters and set customer count to -1 if there were problems reading the file

    /**
     * Retrieves the count of customers in the customer list
     * @return      count of customers
     */
    public int getCustomerCount();

    /**
     * Retrieves the customer with the specified id, in the bucket determined by last name
     * @param lastName      used for its first character; the lower-cased letter is used to determine bucket
     * @param id            the customer id to look up
     * @return              the corresponding customer, or null if the customer isn't found
     */
    public Customer findCustomer(String lastName, String id);

    /**
     * Generates a CSV file containing all customers, in bucket order
     * @param   csvFile     a File reference to the output file to create
     * @param   wantHeader  whether a header line should be at the top of the file
     * @return              the count of customers written to the file; -1 if there was an error creating the file
     */
    public int generateCsv(File csvFile, boolean wantHeader);

}
