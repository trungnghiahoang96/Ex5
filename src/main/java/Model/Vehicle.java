package Model;


public final class Vehicle {
    private final String id;
    private final String brand;
    private final String model;
    private final int numberOfSeats;
    private final String licensePlate;
    private String status;

    public Vehicle(String id, String brand, String model, int numberOfSeats, String licensePlate, String s) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.numberOfSeats = numberOfSeats;
        this.licensePlate = licensePlate;
        this.status = s;
    }

    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Vehicle" + '\n' +
                "       id= " + id + '\n' +
                "       brand= " + brand + '\n' +
                "       model= " + model + '\n' +
                "       numberOfSeats= " + numberOfSeats + '\n' +
                "       licensePlate= " + licensePlate + '\n' +
                "       status=" + status + '\n' +
                "";
    }
}
