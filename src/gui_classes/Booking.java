package gui_classes;

import java.sql.Date;
import java.util.Observable;

public class Booking {
    private int ID;
    private Date date;
    private Payment payment;
    private boolean accepted = false;

    public Booking(int ID, Date date, Payment payment) {
        this.ID = ID;
        this.date = date;
        this.payment = payment;
    }

    public int getID() {
        return ID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
