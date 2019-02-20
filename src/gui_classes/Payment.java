package gui_classes;

public class Payment {
    private int ID;
    private double paid,rec_sitter,rec_admin;

    public Payment(int ID, double paid) {
        this.ID = ID;
        this.paid = paid;
        rec_admin = (10*paid)/100;
        rec_sitter = paid - rec_admin ;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }

    public double getRec_sitter() {
        return rec_sitter;
    }

    public double getRec_admin() {
        return rec_admin;
    }

    public int getID() {
        return ID;
    }
}
