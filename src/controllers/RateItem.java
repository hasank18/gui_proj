package controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Rating;

import java.io.IOException;

public class RateItem extends AnchorPane {
    public Label name;
    public Rating rating;
    public TextArea textArea;
    public Button button;
    public RateItem() {
        loadFXML();
    }

    public void loadFXML(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/RateItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void updateItem(String name, EventHandler<MouseEvent> eventEventHandler){
        this.name.setText(name);
        button.setOnMouseClicked(eventEventHandler);
    }

    public double getRating() {
        return rating.getRating();
    }

    public String getComment(){
        return textArea.getText();
    }
}
