package Rent_a_Ride;

public class Driver {
    private String name;
    private String carModel;
    private double rating;
    private double distanceFromCustomer;

    public Driver(String name, String carModel, double rating, double distanceFromCustomer) {
        this.name = name;
        this.carModel = carModel;
        this.rating = rating;
        this.distanceFromCustomer = distanceFromCustomer;
    }

    public String getName() {
        return name;
    }

    public String getCarModel() {
        return carModel;
    }

    public double getRating() {
        return rating;
    }

    public double getDistanceFromCustomer() {
        return distanceFromCustomer;
    }
}
