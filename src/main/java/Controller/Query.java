package Controller;

import Model.Customer;
import Model.Rent;
import Model.Vehicle;
import Util.IO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Query {

    /**
     * @param vehicles : print out all current vehicle of Agency
     */
    public static void showListVehicles(List<Vehicle> vehicles) {
        System.out.println("All vehicles of Agency here: ");
        vehicles.forEach(System.out::println);
    }


    /**
     * @param rents : list of all current rental deal
     */
    public static void showListRents(List<Rent> rents) {
        System.out.println("All current rental deal here: ");
        rents.forEach(System.out::println);

    }

    /**
     * @param rents    : current rent deal of Agency
     * @param vehicles : list of vehicle
     *                 this function ask user enter date range to show all car available for that time range
     */
    public static void reviewAvailableForRent(List<Rent> rents, List<Vehicle> vehicles) {
        List<Vehicle> returnList = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter the date you want start to rent?  (format dd/MM/yyyy)");
        String enteredStartDate = sc.nextLine();
        System.out.println("Please enter the date you stop to rent?  (format dd/MM/yyyy)");
        String enteredEndDate = sc.nextLine();

        try {
            Date chosenStartDate = new SimpleDateFormat("dd/MM/yyyy").parse(enteredStartDate);
            Date chosenEndDate = new SimpleDateFormat("dd/MM/yyyy").parse(enteredEndDate);

            // get all vehicles in Ready status for rent
            returnList = vehicles.stream()
                    .filter(car -> car.getStatus().equals("Ready"))
                    .collect(Collectors.toList());

            // if chosen date range outside of current rent time of Vehicle --> still can rent
            // the condition is: chosenStartDate is after endDate of current rent - (startDate, endDate) (chosenStart, chosenEnd)
            // or chosenEndDate is before the startDate of current rent - (chosenStart, chosenEnd) (startDate, endDate)
            for (Rent rent : rents) {
                if (chosenStartDate.after(rent.getEndDate()) || chosenEndDate.before(rent.getStartDate())) {
                    returnList.add(rent.getVehicle());
                }
            }
            System.out.println("Here are the vehicles available for that date range: \n");
            returnList.forEach(System.out::println);

        } catch (ParseException e) {
            System.out.println("Wrong date format! \n");

        }

    }

    /**
     * @param rents    : current rental deal
     * @param vehicles : all vehicles of agency
     *                 add a rental to current list of rental deal (first review the current option for a specific time range)
     */
    public static void addNewRental(List<Rent> rents, List<Vehicle> vehicles) {
        try {
            reviewAvailableForRent(rents, vehicles);
            Scanner sc = new Scanner(System.in);

            System.out.println("Now give us some information about the vehicle you want to rent after referencing the list! \n");

            // get the vehicle from ID
            System.out.println("Enter vehicle id to choose the one you want to rent: \n");
            String vehicleId = sc.nextLine();
            Vehicle vehicle = vehicles.stream().filter(r -> r.getId().equals(vehicleId.trim())).findAny().orElse(null);
            if (vehicle == null) {
                System.out.println("Can not find vehicle with that ID. \n");
                return;
            }
            // enter date range again to create rental (after view available options) - date range should be ok
            System.out.println("Enter the date range again to create rental!");
            System.out.println("Please enter the date you want start to rent?  (format dd/MM/yyyy)");
            String enteredStartDate = sc.nextLine();
            System.out.println("Please enter the date you stop to rent?  (format dd/MM/yyyy)");
            String enteredEndDate = sc.nextLine();


            try {
                Date chosenStartDate = new SimpleDateFormat("dd/MM/yyyy").parse(enteredStartDate);
                Date chosenEndDate = new SimpleDateFormat("dd/MM/yyyy").parse(enteredEndDate);
                System.out.println("Enter your ID: ");
                String id = sc.nextLine();
                System.out.println("Enter your name: ");
                String name = sc.nextLine();
                Rent rent = new Rent(new Customer(id, name), vehicle, enteredStartDate, enteredEndDate);
                rents.add(rent);
                System.out.println("Rental done!");
                System.out.println("Your rental information here: \n" + rent.toString());
                System.out.println("\n Choose Option 2 - Display list of all current rental deal to check!\n");


            } catch (ParseException e) {
                System.out.println("Wrong date format! \n");

            }
        } catch (Exception e) {
            System.out.println("Wrong date format! \n");
        }

    }

    /**
     * @param vehicles : current vehicles of Agency
     *                 Function take information from user input to add new Vehicle to car rental fleet
     */
    public static void addNewVehicle(List<Vehicle> vehicles) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a new vehicle's ID: ");
        String id = sc.nextLine();
        System.out.println("Enter vehicle's brand : ");
        String brand = sc.nextLine();
        System.out.println("Enter vehicle's model: ");
        String model = sc.nextLine();

        try {
            System.out.println("Enter number of seats: ");
            int nbs = IO.inputInt();
            System.out.println("Enter license plate: ");
            String licensePlate = sc.nextLine();
            Vehicle v = new Vehicle(id, brand, model, nbs, licensePlate, "Ready");
            vehicles.add(v);
            System.out.println("Adding done! New vehicle is: \n" + v.toString());
            System.out.println("Choose Option 3 - Display list of all vehicles of car rental fleet to check! \n");
        } catch (NumberFormatException e) {
            System.out.println("Number of seat enter not a number! Exit this adding process.\n");
        }
    }

    /**
     * @param vehicles : current vehicles of Agency
     *                 function remove a vehicle for rent of Agency
     */
    public static void cancelAVehicleFromFleet(List<Vehicle> vehicles) {
        showListVehicles(vehicles);
        System.out.println("Enter vehicle's ID you want to cancel: \n");


        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();
        Vehicle vehicle = vehicles.stream().filter(car -> car.getId().equals(id)).findAny().orElse(null);
        if (vehicle == null) {
            System.out.println("Vehicle does not exit! \n");
        } else {
            vehicles.removeIf(car -> car.getId().equals(id));
            System.out.println("Remove/Cancel vehicle from car rental fleet done! \n" +
                    "\n Choose Option 3 - Display list of all vehicles of car rental fleet to check! \n");
        }
    }


    /**
     * @param vehicles - current all vehicles of Agency
     *                 function export all current vehicles to a csv file.
     */
    public static void exportCarsFleetToCSV(List<Vehicle> vehicles) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a name of file: ");
        String fileName = sc.nextLine();

        try (
                FileWriter fileWriter = new FileWriter("src/main/resources/output/" + fileName + ".csv");
                BufferedWriter writer = new BufferedWriter(fileWriter);

                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withDelimiter(',')
                        .withHeader("ID", "Brand", "Model", "Number of seat", "License plate", "Status"));
        ) {
            for (Vehicle v : vehicles) {
                csvPrinter.printRecord(v.getId(), v.getBrand(), v.getModel(), v.getNumberOfSeats(), v.getLicensePlate(), v.getStatus());
            }

            csvPrinter.flush();
        }
        System.out.println("Check src/main/resources/output/ folder for result. \n");
    }
}


