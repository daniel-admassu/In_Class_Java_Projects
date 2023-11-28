import java.io.File;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CustomerDataManager customer = new CustomerDataManager();
        System.out.println(customer.getCustomerCount());
        System.out.println(customer.findCustomer("Glasebrook","25806"));
        System.out.println(customer.findCustomer("Admassu","12345"));
        File file = new File("copyOfCustomers.txt");
        // check copyOfCustomer.txt for results
        System.out.println(customer.generateCsv(file,true));
    }
}
