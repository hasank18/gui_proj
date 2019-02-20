package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;

public class HelpAndFeedback implements Initializable {
    @FXML
    Button change,delete,add,feedback;
    Connection con = null;
    @FXML
    AnchorPane switchpane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public void changepass_handler(ActionEvent event)throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("fxml/ChangePassword.fxml"));
        switchpane.getChildren().setAll(pane);

    }
    @FXML
    public void addaccountHandler(ActionEvent event)throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/SignUpParent.fxml"));
        switchpane.getChildren().setAll(pane);
    }
    @FXML
    public void deletehandler(ActionEvent event){
            String delete_client = "call deleteclient";
            try{
                Class.forName("com.mysql.jdbc.Driver");
                con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","admin","admin" + "" );
                Statement stmt=con.createStatement();
                stmt.executeQuery(delete_client);
            }catch (Exception e){
                e.printStackTrace();
            }
        }


}

