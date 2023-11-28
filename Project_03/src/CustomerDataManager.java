import java.io.*;
import java.util.Scanner;

public class CustomerDataManager implements CustomerDataManagerInterface{
    /** a linked list of tagged linked linked lists  */
    private final LinkedList<TaggedLinkedList> customerList = new LinkedList<>();

    /**
     * The constructor reads the file, arranges customers by the first letter of there lastName
     * into a tagged linked list which are stored in a linked list.
     * @throws FileNotFoundException  throws a helpful message when a file is not found
     */
    @SuppressWarnings("unchecked")
    public CustomerDataManager() throws FileNotFoundException {
        File file = new File("Customers.txt");
        Scanner input = new Scanner(file);

        for (int i = 65; i < 91; i++) {
            customerList.add(new TaggedLinkedList<>((char)i));

        }
        String firstLine = input.nextLine();
        while(input.hasNextLine()) {
            String word = input.nextLine();
            String[] parts = word.split(",", 5);

            Customer customer = new Customer(parts[0], parts[1], parts[2], parts[3],
                    Double.parseDouble(parts[4]));
            int num = ((int)parts[2].charAt(0))-65;
            if(num<26){
                customerList.get(num).add(customer);
            }
            else{
                num+=65;
                num-=97;
                customerList.get(num).add(customer);
            }

        }

    }


    @Override
    public int getCustomerCount() {
        int count = 0;
        for (int i = 0; i < customerList.size(); i++) {
            count+=customerList.get(i).size();
        }
        return count;
    }

    @Override
    public Customer findCustomer(String lastName, String id) {
        int num = lastName.charAt(0);
        if(num<90){num-=65;}
        else {num-=97;}


        for (int i = 0; i < customerList.get(num).size(); i++) {
            Customer cus = (Customer)customerList.get(num).get(i);
            if(cus.id().equalsIgnoreCase(id)&& cus.lastName().equalsIgnoreCase(lastName)){
                return cus;
            }
        }


        return null;
    }

    @Override
    public int generateCsv(File csvFile, boolean wantHeader) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(csvFile);
        } catch (FileNotFoundException e) {
            return -1;
        }
        if(wantHeader) {
            writer.println("Id,firstName,lastName,email,balance");
        }
        int count =0;
        for (int i = 0; i < customerList.size(); i++) {
            for (int j = 0; j < customerList.get(i).size(); j++) {
                try {
                    writer.println(customerList.get(i).get(j));
                } catch (Exception e){
                    return -1;
                }
                count++;
            }
        }
        writer.close();
        //if(count==0){return -1;}   try/catch
        return count;
    }


}
