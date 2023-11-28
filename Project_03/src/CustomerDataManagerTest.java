import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDataManagerTest {

    @Test
    void getCustomerCount() throws FileNotFoundException {
        CustomerDataManager test = new CustomerDataManager();
        assertEquals(1000, test.getCustomerCount());
    }

    @Test
    void findCustomer() throws FileNotFoundException {
        CustomerDataManager test = new CustomerDataManager();
        Customer testCustomer = test.findCustomer("Dungey", "17847");
        Customer testCustomer2 = test.findCustomer("de Clerc", "11625");
        assertEquals("customer(17847) [id = 17847, firstName = Ben, lastName = Dungey, " +
                "email = bdungey44@google.com.au, balance = 14321.48]", testCustomer.toString());
        assertEquals("customer(11625) [id = 11625, firstName = Portia, lastName = de Clerc, " +
                "email = pdeclerckr@globo.com, balance = 3493.11]", testCustomer2.toString());

    }

    @Test
    void generateCsv() throws IOException {
        CustomerDataManager test = new CustomerDataManager();
        File file = new File("copyOfCustomers.txt");
        assertEquals(1000, test.generateCsv(file,true));
        assertEquals(true, file.exists());
        long bytes = file.length();
        assertEquals(true, bytes>0);
    }
}