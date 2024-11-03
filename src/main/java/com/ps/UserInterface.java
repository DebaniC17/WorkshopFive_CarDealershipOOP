package com.ps;

import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private static Dealership dealership;
    private static Scanner scanner = new Scanner(System.in);

    public static void init() {
        DealershipFileManager fileManager = new DealershipFileManager();
        dealership = fileManager.getDealership();

    }

    public static void display() {
        init();

        boolean running = true;

        while (running) {
            System.out.println("Welcome to the " + dealership.getName() + " screen");
            System.out.println("1) Show all vehicles");
            System.out.println("2) Search by price range");
            System.out.println("3) Search by make and model");
            System.out.println("4) Search by year");
            System.out.println("5) Search by color");
            System.out.println("6) Search by mileage");
            System.out.println("7) Search by vehicle type");
            System.out.println("8) Add new vehicle");
            System.out.println("9) Remove a vehicle");
            System.out.println("10) Sell/Lease a vehicle");
            System.out.println("0) Exit");
            System.out.print("Command: ");

            int command = scanner.nextInt();

            switch (command) {
                case 1:
                    processShowAllVehiclesRequest();
                    break;

                case 2:
                    processGetByPriceRequest();
                    break;

                case 3:
                    processGetByMakeModelRequest();
                    break;

                case 4:
                    processGetByYearRequest();
                    break;

                case 5:
                    processGetByColorRequest();
                    break;

                case 6:
                    processGetByMileageRequest();
                    break;

                case 7:
                    processGetByVehicleTypeRequest();
                    break;

                case 8:
                    processAddVehicleRequest();
                    break;

                case 9:
                    processRemoveVehicleRequest();
                    break;

                case 10:
                    sellLeaseVehicle();
                    break;

                case 0:
                    running = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Command not found, please try again.");

            }


        }
        scanner.close();
    }

        private static void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicle found");
        } else {
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle);
            }
        }
    }
//    private static void displayVehicles(Vehicle vehicle) {
//        System.out.printf("Make: %s, Model: %s, Year: %d, Odometer: %d",
//                vehicle.getMake(),
//                vehicle.getModel(),
//                vehicle.getYear(),
//                vehicle.getOdometer()
//        );
//    }

    private static void processShowAllVehiclesRequest() {
        List<Vehicle> allVehicles = dealership.getAllVehicles();
        displayVehicles(allVehicles);
    }

    private static void processGetByPriceRequest() {
        System.out.print("Enter minimum price:");
        double minPrice = scanner.nextDouble();
        System.out.print("Enter maximum price: ");
        double maxPrice = scanner.nextDouble();

        List<Vehicle> vehicles = dealership.getVehicleByPrice(minPrice, maxPrice);
        displayVehicles(vehicles);

    }

    private static void processGetByMakeModelRequest() {
        System.out.print("Enter make: ");
        String make = scanner.next();
        System.out.print(" Enter model: ");
        String model = scanner.next();

        List<Vehicle> vehicles = dealership.getVehicleByMakeModel(make, model);
        displayVehicles(vehicles);

    }

    private static void processGetByYearRequest() {
        System.out.print("Enter minimum year: ");
        int minYear = scanner.nextInt();
        System.out.print("Enter maximum year: ");
        int maxYear = scanner.nextInt();

        List<Vehicle> vehicles = dealership.getVehicleByYear(minYear, maxYear);
        displayVehicles(vehicles);

    }

    private static void processGetByColorRequest() {
        System.out.print("Enter color: ");
        String color = scanner.next();

        List<Vehicle> vehicles = dealership.getVehicleByColor(color);
        displayVehicles(vehicles);

    }

    private static void processGetByMileageRequest() {
        System.out.print("Enter minimum mileage: ");
        int minMileage = scanner.nextInt();
        System.out.print("Enter maximum mileage: ");
        int maxMileage = scanner.nextInt();

        List<Vehicle> vehicles = dealership.getVehicleByMileage(minMileage, maxMileage);
        displayVehicles(vehicles);

    }

    private static void processGetByVehicleTypeRequest() {
        System.out.print("Enter vehicle type: ");
        String type = scanner.next();

        List<Vehicle> vehicles = dealership.getVehicleByType(type);
        displayVehicles(vehicles);


    }

    private static void processAddVehicleRequest() {
        System.out.print("Enter VIN: ");
        int vin = scanner.nextInt();
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        System.out.print("Enter make: ");
        String make = scanner.next();
        System.out.print("Enter model: ");
        String model = scanner.next();
        System.out.print("Enter vehicle type: ");
        String vehicleType = scanner.next();
        System.out.print("Enter color: ");
        String color = scanner.next();
        System.out.print("Enter odometer reading: ");
        int odometer = scanner.nextInt();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();

        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        dealership.addVehicle(vehicle);

        DealershipFileManager fileManager = new DealershipFileManager();
        fileManager.saveDealership(dealership);

        System.out.println("Vehicle added successfully.");

    }

    private static void processRemoveVehicleRequest() {
        System.out.println("Enter the VIN of the vehicle your wanting to remove: ");
        int vin = scanner.nextInt();


    }

    private static void sellLeaseVehicle() {
        System.out.println("Enter your name: ");
        scanner.nextLine();
        System.out.println("Enter your email: ");
        scanner.nextLine();
        System.out.println("Enter vehicle VIN: ");
        scanner.nextLine();
        System.out.println("Do you want to sell or lease the vehicle: ");
        scanner.hasNextBoolean();
    }

}
