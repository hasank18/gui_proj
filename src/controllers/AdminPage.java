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
import java.net.MalformedURLException;
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
        menuItemData = new MenuItemData("file:/resources/my_settings.png","hasan");
        customMenuItem = new CustomMenuItem();
        try {
            customMenuItem.updateItem(menuItemData);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        vBox.getChildren().add(customMenuItem);
    }
}
