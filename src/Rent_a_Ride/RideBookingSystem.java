package Rent_a_Ride;

import java.util.Scanner;

public class RideBookingSystem {
    public static void main(String[] args) {
        Driver[] drivers = new Driver[5];
        drivers[0] = new Driver("Driver A", "Sedan", 4.0, 500);
        drivers[1] = new Driver("Driver B", "HatchBack", 4.3, 400);
        drivers[2] = new Driver("Driver C", "5 Seater", 4.8, 200);
        drivers[3] = new Driver("Driver D", "Sedan", 4.1, 700);
        drivers[4] = new Driver("Driver E", "HatchBack", 4.7, 430);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Customer Ride Distance: ");
        double rideDistance = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Car Requested: ");
        String requestedCar = scanner.nextLine();

        Driver[] eligibleDrivers = filterEligibleDrivers(drivers, rideDistance, requestedCar);

        if (eligibleDrivers.length == 0) {
            System.out.println("No drivers available for the requested car model. Please select a different car.");
        } else {
            Driver closestDriver = getClosestDriver(eligibleDrivers);
            System.out.println(closestDriver.getName() + " will get you to the destination.");
        }
        
        scanner.close();
    }

    public static Driver[] filterEligibleDrivers(Driver[] drivers, double rideDistance, String requestedCar) {
        Driver[] eligibleDrivers = new Driver[drivers.length];
        int count = 0;

        for (Driver driver : drivers) {
            if (driver.getRating() >= 4.0 && (requestedCar.isEmpty() || driver.getCarModel().equalsIgnoreCase(requestedCar))) {
                eligibleDrivers[count] = driver;
                count++;
            }
        }

        return trimArray(eligibleDrivers, count);
    }

    public static Driver getClosestDriver(Driver[] drivers) {
        Driver closestDriver = drivers[0];

        for (int i = 1; i < drivers.length; i++) {
            if (drivers[i].getDistanceFromCustomer() < closestDriver.getDistanceFromCustomer()) {
                closestDriver = drivers[i];
            }
        }

        return closestDriver;
    }

    public static Driver[] trimArray(Driver[] array, int size) {
        Driver[] trimmedArray = new Driver[size];
        System.arraycopy(array, 0, trimmedArray, 0, size);
        return trimmedArray;
    }
}
