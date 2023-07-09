//package Rent_a_Ride;

import java.util.Scanner;

public class RideBookingSystem {
    public static void main(String[] args) {
        Driver[] drivers = new Driver[5];
        drivers[0] = new Driver("Driver A", "Sedan", 4.0, 500);
        drivers[1] = new Driver("Driver B", "HatchBack", 4.3, 400);
        drivers[2] = new Driver("Driver C", "5 Seater", 4.8, 200);
        drivers[3] = new Driver("Driver D", "Sedan", 4.1, 700);
        drivers[4] = new Driver("Driver E", "HatchBack", 4.7, 430);

        Scanner input = new Scanner(System.in);
        System.out.print("Customer Ride Distance: ");
        String ride = input.nextLine();
        String distanceStr;
        if (ride.endsWith("km")) {
            distanceStr = ride.substring(0, ride.length() - 2);
        }
        else{
            distanceStr = ride;
        }
        double rideDistance = Double.parseDouble(distanceStr);

        System.out.print("Car Requested: ");
        String requestedCar = input.nextLine();

        System.out.println("List of Drivers with Details:");
        int count = 70;
        while(count > 0){
            System.out.print("-");
            count -=1;
        }
        System.out.println();

        //System.out.printf("%-20s%-20s%-20s%-20s\n", "Name", "Car Model", "Rating", "Distance");
        for (Driver driver : drivers) {
            System.out.printf("   "+"%-20s%-20s%-20s%-20s\n", driver.getName(), driver.getCarModel(), driver.getRating(), driver.getDistanceFromCustomer());
            count = 70;
            while(count > 0){
                System.out.print("-");
                count -=1;
            }
            System.out.println();
        }

        Driver[] eligibleDrivers = filterEligibleDrivers(drivers, rideDistance, requestedCar);

        if (eligibleDrivers.length == 0) {
            System.out.println("No drivers available for the requested car model. Please select a different car.");
        } else {
            Driver closestDriver = getClosestDriver(eligibleDrivers);
            System.out.println(closestDriver.getName() + " will get you to the destination.");
            System.out.println("Your charge will be Rs "+(rideDistance*8)+" ("+rideDistance+"*8).");
        }
        
        input.close();
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
