package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import sample.DBconnection;
import sample.Main;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class PaymentMethod implements Initializable {


    public TextField hours;
    public AnchorPane anchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void submit(ActionEvent event) throws SQLException {
        Connection con = DBconnection.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select idBabySitter from BabySitter where Person_username = '"+ Custom.clientUsername+"'");
        rs.next();
        int idBabySitter = rs.getInt(1);
        rs = stmt.executeQuery("select idclient from Client where Person_username = '"+Main.getUserName()+"'");
        rs.next();
        int cid = rs.getInt(1);
        String[] strings = Custom.price_hour1.split(":");
        double paid = Double.parseDouble(hours.getText())*Double.parseDouble(strings[1]);
        stmt.executeUpdate("insert into sitter_payment(paid,Client_idclient,admin_idadmin,BabySitter_idBabySitter) values("+paid+","+cid+","+1+","+idBabySitter+")");
        rs = stmt.executeQuery("select idsitter_payment from sitter_payment where Client_idclient="+cid+" and BabySitter_idBabySitter="+idBabySitter);
        rs.next();
        int idpayment = rs.getInt(1);
        stmt.executeUpdate("insert into SitterBooking(date,Client_idclient,BabySitter_idBabySitter,sitter_payment_idsitter_payment,answer) values('1999-12-12',"+cid+","+idBabySitter+","+idpayment+",false);");
        Stage stage = (Stage)anchorPane.getScene().getWindow();
        stage.close();
        Notifications.create().title("Booking Succseeded !!").text("now wait for Babysitter responce").showConfirm();

    }
}
