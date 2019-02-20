package controllers;

import gui_classes.MenuItemData;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AdminPage implements Initializable {
    @FXML
    VBox vBox;
    CustomMenuItem customMenuItem;
    MenuItemData menuItemData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuItemData = new MenuItemData("@../resources/default_profile_picture.png","hasan");
        customMenuItem = new CustomMenuItem();
        try {
            customMenuItem.updateItem(menuItemData);
            vBox.getChildren().add(customMenuItem);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
