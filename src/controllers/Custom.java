package controllers;

import gui_classes.CustomData;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.controlsfx.control.Rating;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Custom extends AnchorPane {
    @FXML
    Label name,phone,address,age,price_hour;
    @FXML
    Rating rating;
    @FXML
    ImageView circle;

    public Custom() {
        loadFXML();
    }

    public void loadFXML(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Custom.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    protected void updateItem(CustomData customData) {
        circle.setImage(customData.getImage());
        name.setText(name.getText()+customData.getName1());
        phone.setText(phone.getText()+customData.getPhone1());
        address.setText(address.getText()+customData.getAddress1());
        age.setText(age.getText()+customData.getAge1());
        price_hour.setText(price_hour.getText()+customData.getPrice_hour1());
        rating.setRating(customData.getRating1());
    }

}
