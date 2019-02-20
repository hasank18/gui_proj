package controllers;

import gui_classes.MenuItemData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class CustomMenuItem extends HBox {
    @FXML
    ImageView imageView;
    @FXML
    Label label;

    public CustomMenuItem() {
        loadFXML();
    }

    public void loadFXML(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/CustomMenuItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    protected void updateItem(MenuItemData customData) throws FileNotFoundException {
        imageView.setImage(new Image(new FileInputStream(customData.getImageUrl())));
        label.setText(customData.getLabel());
    }

}
