package Util;

import Model.Customer;
import Model.Rent;
import Model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Dataloader {



    /**
     * @param entry : is one line from csv file after split by separator
     * @return one Vehicle object
     */

    public static Vehicle vehicleFromLine(List<String> entry) {
        String id;
        id = entry.get(0);
        String brand = entry.get(1);
        String model = entry.get(2);
        int numberOfSeats = Integer.parseInt(entry.get(3));
        String licensePlate = entry.get(4);
        String status = entry.get(5);

        return new Vehicle(id, brand, model, numberOfSeats, licensePlate, status);

    }

    /**
     * @param fileParser : fileParse read csv file and give list of all line after split
     * @return List of Vehicle parsing from a CSV (rent.csv)
     */
    public static List<Vehicle> vehicleFromCSV(FileParser fileParser) {

        List<Vehicle> listOfVehicles = new ArrayList<>();
        List<List<String>> entriesList = fileParser.getRawList();

        for (List<String> entry : entriesList) {
            Vehicle vehicle = vehicleFromLine(entry);
            listOfVehicles.add(vehicle);
        }

        return listOfVehicles;

    }

    /**
     * @param entry : is one line of csv after split
     * @return : a Rent object parse from a line of CSV file
     */
    public static Rent rentFromLine(List<String> entry) {

        Vehicle vehicle = vehicleFromLine(entry);

        String customerID = entry.get(8);
        String customerName = entry.get(9);
        String fromDate = entry.get(6);
        String toDate = entry.get(7);

        Customer customer = new Customer(customerID, customerName);


        return new Rent(customer, vehicle, fromDate, toDate);

    }

    /**
     * @param fileParser : fileParse read csv file and give list of all line after split
     * @return  list of Rent created from CSV file
     */
    public static List<Rent> rentFromCSV(FileParser fileParser) {
        List<Rent> listOfRents = new ArrayList<>();
        List<List<String>> entriesList = fileParser.getRawList();

        for (List<String> entry : entriesList) {
            if (entry.size() > 6) {
                Rent rent = rentFromLine(entry);
                listOfRents.add(rent);
            }
        }

        return listOfRents;
    }

}
