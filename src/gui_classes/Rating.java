package gui_classes;

import java.util.Observable;

public class Rating extends Observable {
    private int ID,stars;
    private String comment;

    public Rating(int ID, int stars, String comment) {
        this.ID = ID;
        this.stars = stars;
        this.comment = comment;
        setChanged();
        notifyObservers();
    }

    public int getID() {
        return ID;
    }

    public int getStars() {
        return stars;
    }

    public String getComment() {
        return comment;
    }

    public void setStars(int stars) {
        this.stars = stars;
        setChanged();
        notifyObservers();
    }

    public void setComment(String comment) {
        this.comment = comment;
        setChanged();
        notifyObservers();
    }
}
