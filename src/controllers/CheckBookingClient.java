package controllers;

import gui_classes.CustomData;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.DBconnection;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class CheckBookingClient implements Initializable {
    public ListView<Custom2> listView;
    public AnchorPane anchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Connection con = DBconnection.getConnection();
        try {
            Statement stmt  = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from BookingData where username ='"+Main.getUserName()+"'");
            while(rs.next()){
                String name =rs.getString("name");
                boolean answer = rs.getBoolean("answer");
                Custom2 custom2 = new Custom2();
                if(answer == false)
                custom2.updateItem(name,"Waiting Responce");
                else
                    custom2.updateItem(name,"Accepted");
                listView.getItems().add(custom2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void Return(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../fxml/ClientPage.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage(),stage1 = (Stage)anchorPane.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage1.close();
    }
}
