/**
 * cretes a customer with the required information
 * @param id Customers id
 * @param firstName Customers first name
 * @param lastName Customers last name
 * @param email Customers email
 * @param balance Customers balance
 */
public record Customer(String id, String firstName,
                       String lastName, String email, double balance)
                            implements Comparable<Customer>  {

    public Customer{

    }

    /**   String of customer with all their information   */
    public String toString(){
        return String.format("customer(%s) [id = %s, firstName = %s, lastName = %s, email = %s, " +
                "balance = %.2f]",id ,id ,firstName,lastName,email,balance);
    }
    /** an equals method based on the customers id */
    public boolean equals(Customer other) {
        return this.id.equals(other.id);
    }
    public int compareTo(Customer other){
        return this.lastName.compareTo(other.lastName);
    }
}
