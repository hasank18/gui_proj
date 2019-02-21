package gui_classes;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import org.controlsfx.control.Notifications;

import java.sql.Blob;
import java.sql.Date;
import java.util.ArrayList;

public class Client extends Person {
    private int ID;
    private ArrayList<Booking> bookings;
    private ArrayList<Rating> ratings;
    private ArrayList<Payment> payments;
    public Client(int ID,String username, String name,String address, String phone, String email, String gender, String password, Date birthdate, Image image) {
        super(username, name, phone,address, email, gender, password, birthdate, image);
        bookings = new ArrayList<>();
        ratings = new ArrayList<>();
        payments = new ArrayList<>();
        this.ID=ID;
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

}


