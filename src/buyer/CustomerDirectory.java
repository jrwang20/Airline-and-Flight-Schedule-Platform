package buyer;

import java.util.ArrayList;
import java.util.List;

public class CustomerDirectory {

    private List<Customer> customerList;

    public CustomerDirectory() {
        this.customerList = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }


}
