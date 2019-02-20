package gui_classes;

import org.controlsfx.control.Notifications;

import java.sql.Blob;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class BabySitter extends Person implements Observer {
    private double price_hour;
    private ArrayList<Notifications> notifications;
    private int ID;
    private ArrayList<Booking> bookings;
    private ArrayList<Rating> ratings;
    private ArrayList<Payment> payments;
    private boolean online = false;

    public BabySitter(int ID, String username, String name, String phone, String address, String email, String gender, String password, Date birthdate, Blob image, double price_hour) {
        super(username, name, phone, address, email, gender, password, birthdate, image);
        this.price_hour = price_hour;
        bookings = new ArrayList<>();
        ratings = new ArrayList<>();
        payments = new ArrayList<>();
        notifications = new ArrayList<>();
        this.ID=ID;
    }

    public double getPrice_hour() {
        return price_hour;
    }

    public void setPrice_hour(double price_hour) {
        this.price_hour = price_hour;
    }
    public void addRating(Rating rating){
        ratings.add(rating);
    }
    public void addBooking(Booking booking){
        bookings.add(booking);
    }

    public void addPayment(Payment payment){
        payments.add(payment);
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public ArrayList<Rating> getRatings() {
        return ratings;
    }

    public ArrayList<Payment> getPayments() {
        return payments;
    }

    public int getID() {
        return ID;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("worked");
    }

    public void addNotification(Notifications notifications){
        this.notifications.add(notifications);
    }
    public ArrayList<Notifications> getNotifications() {
        return notifications;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}




