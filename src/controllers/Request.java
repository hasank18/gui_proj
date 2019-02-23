package controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Request extends AnchorPane {
    public Label name;
    public Label address;
    public Label phone;
    public Button button;

    public Request() {
        loadFXML();
    }

    public void loadFXML(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Request.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void updateItem(String name, String address, String phone, EventHandler eventHandler){
        this.name.setText(name);
        this.address.setText(address);
        this.phone.setText(phone);
        button.setOnMouseClicked(eventHandler);
    }
}
