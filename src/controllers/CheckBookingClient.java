package controllers;

import gui_classes.CustomData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
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
    public ListView listView;
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
                boolean finished = rs.getBoolean("finished");
                int cid = rs.getInt("Client_idclient");
                int bid = rs.getInt("BabySitter_idBabySitter");
                if(answer == false) {
                    Custom2 custom2 = new Custom2();
                    custom2.updateItem(name, "Waiting Responce");
                    listView.getItems().add(custom2);
                }
                else if (finished == false){
                    RateItem rateItem = new RateItem();
                    rateItem.updateItem(name,OnClick(rateItem.getRating(),rateItem.getComment(),cid,bid));
                    listView.getItems().add(rateItem);
                }
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

    public EventHandler<MouseEvent> OnClick(double rating,String comment,int cid,int bid) throws SQLException {

        EventHandler<MouseEvent> eventEventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Connection con = DBconnection.getConnection();
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate("insert SitterRating(stars,comment,Client_idclient,BabySitter_idBabySitter) values("+(int)rating+",'"+comment+"',"+cid+","+bid+")");
                    Parent parent = FXMLLoader.load(getClass().getResource("../fxml/PaymentMethod.fxml"));
                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        return eventEventHandler;
    }

    public void Delete(MouseEvent mouseEvent) {
        listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
    }
}
