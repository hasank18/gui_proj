package controllers;
import gui_classes.BabySitter;
import gui_classes.Booking;
import gui_classes.Client;
import gui_classes.Payment;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class BabySitterPage implements Initializable {
    public AnchorPane container;
    public SplitPane pane1;
    public ImageView start;
    public ImageView settings;
    public ImageView help;
    public ImageView home;
    public AnchorPane switchpane;
    public ImageView home_choice;
    public ListView listView;
    private int ID;
    private BabySitter babySitter;
    Connection con = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setData() {
        double hour_price=10;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_gui", "root", "" + "");
            Statement stmt = con.createStatement(), stmt2 = con.createStatement();
            String test = "select * from BabySitter where idBabySitter=" + ID;
            ResultSet rs = stmt.executeQuery(test), rs2;
            String username = "";
            if (rs.next()) {
                username = rs.getString("Person_username");
                hour_price = rs.getDouble("price_hour");
            }
            rs = stmt.executeQuery("select * from Person where username='" + username + "'");
            if (rs.next()) {
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                Date birthdate = rs.getDate("birthdate");
                String email = rs.getString("email");
                String gender = rs.getString("gender");
                String password = rs.getString("password");
                Blob image = rs.getBlob("image");
                babySitter = new BabySitter(ID, username, name, phone, address, email, gender, password, birthdate, image, hour_price);
            }
            rs = stmt.executeQuery("select * from SitterBooking where BabySitter_idBabySitter=" + ID);
            while (rs.next()) {
                int BookingID = rs.getInt("idSitterBooking");
                Date date = rs.getDate("date");
                int ClientID = rs.getInt("Client_idclient");
                int PaymentID = rs.getInt("sitter_payment_idsitter_payment");
                rs2 = stmt2.executeQuery("select * from sitter_payment where idsitter_payment=" + PaymentID);
                if (rs2.next()) {
                    double paid = rs2.getDouble("paid");
                    babySitter.addBooking(new Booking(BookingID, date, new Payment(PaymentID, paid)));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Notifications.create().title("Welcome").text("You have "+babySitter.getBookings().size()+" Bookings requests").show();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public BabySitter getBabySitter() {
        return babySitter;
    }

    public void setBabySitter(BabySitter babySitter) {
        this.babySitter = babySitter;
    }

    @FXML
    private void home_choiceHandler(MouseEvent mouseEvent) throws Exception {
        pane1.setDividerPosition(0, 0.2);
//        pane1.setDividerPosition(0,0.2);

    }

    @FXML
    private void home_choiceHandler2(MouseEvent mouseEvent) throws Exception {
        pane1.setDividerPosition(0, 0.01);

    }

    @FXML
    public void settingsHandler(javafx.scene.input.MouseEvent mouseEvent) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/Settings.fxml"));
        switchpane.getChildren().setAll(pane);

    }

    @FXML
    public void helpHandler(MouseEvent mouseEvent) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("fxml/HelpAndFeedback.fxml"));
        switchpane.getChildren().setAll(pane);

    }

    @FXML
    public void loginpageHandler(MouseEvent mouseEvent) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("fxml/LoginPage.fxml"));
        container.getChildren().setAll(pane);

    }

    public void startHandler(MouseEvent mouseEvent) {
    }
}
