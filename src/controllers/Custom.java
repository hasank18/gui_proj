package controllers;

import gui_classes.CustomData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

import java.io.IOException;

public class Custom extends AnchorPane {
    static String clientUsername;
    static String price_hour1;
    @FXML
    Label name,phone,address,age,price_hour;
    @FXML
    Rating rating;
    @FXML
    ImageView image;
    @FXML
    Button button;

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
//        image.setImage(customData.getImage());
        name.setText(name.getText()+customData.getName1());
        phone.setText(phone.getText()+customData.getPhone1());
        address.setText(address.getText()+customData.getAddress1());
        age.setText(age.getText()+customData.getAge1());
        price_hour.setText(price_hour.getText()+customData.getPrice_hour1());
        rating.setRating(customData.getRating1());
        button.setOnMouseClicked(e->{
            Parent parent = null;
            try {
                parent = FXMLLoader.load(getClass().getResource("../fxml/PaymentMethod.fxml"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            clientUsername = customData.getUsername();
            price_hour1 = price_hour.getText();
        });
    }
}
