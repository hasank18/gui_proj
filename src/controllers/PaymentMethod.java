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
    static int id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void submit(ActionEvent event) throws SQLException {
        Connection con = DBconnection.getConnection();
        Statement stmt = con.createStatement();
//        String[] strings = Custom.price_hour1.split(":");
//        double paid = Double.parseDouble(hours.getText())*Double.parseDouble(strings[1]);
        stmt.executeUpdate("update sitter_payment set paid="+5+" where idsitter_payment="+id);
        stmt.executeUpdate("update SitterBooking set finished = true where sitter_payment_idsitter_payment="+id);
        Stage stage = (Stage)anchorPane.getScene().getWindow();
        stage.close();
        Notifications.create().title("Rating and Payment added successfully!!").showInformation();

    }

    public void setId(int id) {
        this.id = id;
    }
}
