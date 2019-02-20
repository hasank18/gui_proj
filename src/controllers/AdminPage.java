package controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Label;
public class AdminPage implements Initializable {
    @FXML
    AnchorPane switch_pane,container;
    @FXML
    Label client,employee,admin;
    @FXML
    SplitPane splitpane;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void handleclient_infoAction(Event actionEvent)throws Exception{

        AnchorPane pane = FXMLLoader.load(getClass().getResource("fxml/ParentProfile.fxml"));
        switch_pane.getChildren().setAll(pane);
    }
    @FXML
    public void handleemployee_infoAction(Event actionEvent)throws Exception{

        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/Babysitter_info.fxml"));
        switch_pane.getChildren().setAll(pane);
    }
    @FXML
    public void handleadmin_infoAction(Event actionEvent)throws Exception{

        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/admin.fxml"));
        switch_pane.getChildren().setAll(pane);
    }
    @FXML
    public void backHandler(MouseEvent actionEvent)throws Exception{
        AnchorPane pane=FXMLLoader.load(getClass().getResource("fxml/LoginPage.fxml"));
        container.getChildren().setAll(pane);
    }
    @FXML
    public void onMouseExit(MouseEvent mouseEvent) {
        splitpane.setDividerPosition(0,0.3);
    }

    public void onMouseEnter(MouseEvent mouseEvent) {
        splitpane.setDividerPosition(0,0);
    }

}
