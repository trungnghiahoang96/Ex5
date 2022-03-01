import Controller.Query;
import Model.Customer;
import Model.Rent;
import Model.Vehicle;
import Util.Dataloader;
import Util.FileParser;
import Util.IO;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<Vehicle> vehicles = new ArrayList<>();
    private static List<Rent> rents = new ArrayList<>();


    public static void main(String[] args) {

        loadData();

        start();

    }

    public static void start() {
        while (true) {

            displayChoices();

            try {
                int choice = IO.inputInt();

                if (isTerminationChoice(choice)) {
                    System.out.println("Program Exiting..... ");
                    break;
                } else {
                    executeChoice(choice);
                }
            } catch (NumberFormatException e) {
                System.out.println("Not a number! Enter another number option please!");

            }

        }
    }

    public static void displayChoices() {
        System.out.println("Welcome to the magic library:");
        System.out.println("Menu");
        System.out.println("0 - Quit\n" +
                "1 - Add new rental \n" +
                "2 - Display list of all current rental deal \n"+
                "3 - Display list of all vehicles of car rental fleet\n" +
                "4 - Display list of all vehicles available for rental in time range\n" +
                "5 - Adding a new vehicle to the car rental fleet.\n" +
                "6 - Cancellation of a vehicle from the car rental fleet.\n" +
                "7 - Export of all car rental vehicles in a CSV file.\n");
    }

    public static boolean isTerminationChoice(int i) {
        return i == 0;
    }

    public static void executeChoice(int choice) {

        switch (choice) {
            case 1:
                System.out.println("(example input for this option:      date range 01/03/2022   05/03/2022\n" +
                        "         id  6   customerId   10    customerName    Nghia)\n");
                Query.addNewRental(rents, vehicles);
                break;
            case 2:
                Query.showListRents(rents);
                break;
            case 3:
                Query.showListVehicles(vehicles);
                break;
            case 4:
                System.out.println("(example input for option 3:       start  21/02/2022    end  28/02/2022\n" +
                        "         start 15/02/2022   end 17/02/2022 ) \n");
                Query.reviewAvailableForRent(rents, vehicles);
                break;
            case 5:
                System.out.println("(example input: 19  Honda Honda Civic    4  22A6-78794) \n");
                Query.addNewVehicle(vehicles);
                break;
            case 6:
                Query.cancelAVehicleFromFleet(vehicles);
                break;
            case 7:
                try {
                    Query.exportCarsFleetToCSV(vehicles);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                String warning = "";

                warning += "XXXXXXXXXXXXXXXXXXXXXXXX\n";
                warning += "Invalid option!! Enter valid option:\n";
                warning += "XXXXXXXXXXXXXXXXXXXXXXXX\n";

                System.out.println(warning);

        }
    }


    /**
     * this function load data from cars.csv and rent.csv to create list of vehicle and rent status
     */
    private static void loadData() {
        String RESOURCES = "src/main/resources/";
        String pathOfVehicles = RESOURCES + "input/cars.csv";
        String pathOfRent = RESOURCES + "input/rent.csv";

        FileParser vehicleParser = new FileParser(pathOfVehicles, ",");
        FileParser rentParser = new FileParser(pathOfRent, ",");

        vehicles = Dataloader.vehicleFromCSV(vehicleParser);
        rents = Dataloader.rentFromCSV(rentParser);


    }
}
