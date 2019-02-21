package controllers;

import gui_classes.MenuItemData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;


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
    protected void updateItem(MenuItemData customData) throws  MalformedURLException {
        try {
            Image image = new Image(customData.getImageUrl());
            imageView.setImage(image);
            imageView.setCache(true);
            imageView.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        label.setText(customData.getLabel());
    }

}
