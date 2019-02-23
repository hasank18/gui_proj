package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Rating;
import java.io.IOException;

public class Comments extends AnchorPane {
    @FXML
    Label comment,name;
    @FXML
    Rating rating;
    public Comments() {
        loadFXML();
    }

    public void loadFXML(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Comments.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    protected void updateItem(String name,String comment,int rating) {
        this.name.setText(name);
        this.comment.setText(comment);
        this.rating.setRating(rating);
    }
}
