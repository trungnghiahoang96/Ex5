package Model;

import java.util.ArrayList;
import java.util.List;

public final class Customer {
    private final String customerId;
    private final String customerName;
    private final List<Rent> rents = new ArrayList<>();

    public Customer(String customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    /**
     *
     * @param rent
     * Return of the vehicle to the car rental
     */
    public void updateRents(Rent rent){
        rents.add(rent);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}
