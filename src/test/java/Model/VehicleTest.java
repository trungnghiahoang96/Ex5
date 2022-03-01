package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {
    private Vehicle vehicleTest;


    @BeforeEach
    void setUp() {
        vehicleTest = new Vehicle("27", "Porsche", "Porsche 914-2", 5, "78K8-34248", "Ready");
    }

    @Test
    void getStatus() {
        assertEquals("Ready", vehicleTest.getStatus());
    }

    @Test
    void getLicensePlate() {
        assertEquals("78K8-34248", vehicleTest.getLicensePlate());
    }

    @Test
    void getNumberOfSeats() {
        assertEquals(5, vehicleTest.getNumberOfSeats());
    }

    @Test
    void getBrand() {
        assertEquals("Porsche", vehicleTest.getBrand());
    }

}