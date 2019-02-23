package controllers;

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

public class AcceptRequest implements Initializable {
    public ListView<Request> listView;
    public AnchorPane anchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connection con = DBconnection.getConnection();
            Statement stmt = con.createStatement(),stmt1 = con.createStatement();
            ResultSet rs = stmt.executeQuery("select idBabySitter from BabySitter where Person_username='"+ Main.getUserName()+"'"),rs1;
            rs.next();
            int id = rs.getInt(1);
            rs = stmt.executeQuery("select * from SitterBooking where BabySitter_idBabySitter="+id+" and answer = false");
            while(rs.next()) {
                int cid = rs.getInt("Client_idclient");
                int bid = rs.getInt("idSitterBooking");
                rs1 = stmt1.executeQuery("select Person_username from Client where idclient=" + cid);
                rs1.next();
                String username = rs1.getString(1);
                rs1 = stmt1.executeQuery("select * from Person where username = '"+username+"'");
                rs1.next();
                String name = rs1.getString("name");
                String address = rs1.getString("address");
                String phone = rs1.getString("phone");
                Request request = new Request();
                request.updateItem(name,address,phone,OnClick(bid));
                listView.getItems().add(request);
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public EventHandler<MouseEvent> OnClick(int bid){
        EventHandler<MouseEvent> eventEventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Connection con = DBconnection.getConnection();
                try {
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate("update SitterBooking set answer = true where idSitterBooking="+bid);
                    Notifications.create().title("Booking Accepted").showInformation();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        };
        return eventEventHandler;
    }

    public void Return(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../fxml/BabySitterPage.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage(),stage1 = (Stage)anchorPane.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        stage1.close();
    }
}
