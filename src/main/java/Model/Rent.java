package Model;

import java.util.Date;
import java.text.SimpleDateFormat;
public final class Rent {
    private Customer customer;
    private final Vehicle vehicle;
    private Date startDate;
    private Date endDate;

    public Rent(Customer customer, Vehicle vehicle, String startDate, String endDate) {
        this.customer = customer;
        this.vehicle = vehicle;
        try{
            this.startDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
            this.endDate = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
        }catch (Exception e){
            this.startDate = null;
            this.endDate = null;
        }

    }

    public Customer getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setFromDate(Date fromDate) {
        this.startDate = fromDate;
    }

    public void setToDate(Date toDate) {
        this.endDate = toDate;
    }

    @Override
    public String toString() {
        return "Rent{" +
                "customer=" + customer +
                ", vehicle=" + vehicle +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
