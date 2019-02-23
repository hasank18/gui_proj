package controllers;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import gui_classes.BabySitter;
import gui_classes.Booking;
import gui_classes.Client;
import gui_classes.Payment;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import sample.DBconnection;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class BabySitterPage implements Initializable {
    public AnchorPane container;
    public ListView<Comments> listView;
    public JFXDrawer drawer;
    public JFXHamburger hamburger;
    private int id;
    private BabySitter babySitter;
    Connection con = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Connection con = DBconnection.getConnection();
        try {
            Statement stmt = con.createStatement(),stmt2 = con.createStatement();
            ResultSet rs = stmt.executeQuery("select idBabySitter from BabySitter where Person_username = '"+Main.getUserName()+"'"),rs1;
            rs.next();
            int id = rs.getInt(1);
            rs = stmt.executeQuery("select * from SitterRating where BabySitter_idBabySitter="+id);
            while (rs.next()){
                rs1 = stmt2.executeQuery("select Person_username from Client where idclient = "+id);
                rs1.next();
                String username = rs1.getString(1);
                rs1 = stmt2.executeQuery("select name from Person where username = '"+username+"'");
                rs1.next();
                String name = rs1.getString(1);
               String comment = rs.getString("comment");
               int rating = rs.getInt("stars");
                Comments comments = new Comments();
               comments.updateItem(name,comment,rating);
               listView.getItems().add(comments);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
        VBox SidePane = FXMLLoader.load(getClass().getResource("../fxml/SidePaneBabySitter.fxml"));
        drawer.setSidePane(SidePane);
        SidePane.getChildren().get(1).setOnMouseClicked(event -> {
        });
        SidePane.getChildren().get(2).setOnMouseClicked(e ->{
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/EditProfile.fxml"));
                Parent parent= fxmlLoader.load();
                EditProfile editProfile = fxmlLoader.getController();
                editProfile.getData();
                Scene scene = new Scene(parent);
                Stage stage = new Stage(),stage1 = (Stage)container.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                stage1.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        SidePane.getChildren().get(3).setOnMouseClicked(e ->{
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("../fxml/AcceptRequest.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = new Stage(),stage1 = (Stage)container.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                stage1.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        SidePane.getChildren().get(4).setOnMouseClicked(e->{
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("../fxml/LoginPage.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = new Stage(),stage1 = (Stage)container.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                stage1.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    } catch (IOException e) {
        e.printStackTrace();
    }
    HamburgerBackArrowBasicTransition burgertask2 = new HamburgerBackArrowBasicTransition(hamburger);
        burgertask2.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED , (e) ->{
        burgertask2.setRate(burgertask2.getRate()*-1);
        burgertask2.play();
        if(drawer.isOpened()){
            drawer.close();
        }
        else
            drawer.open();
    });
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_gui", "admin", "admin" + "");
                Statement stmt = con.createStatement(), stmt2 = con.createStatement();
                String test = "select * from BabySitterView where Person_username='" + Main.getUserName()+"'";
                System.out.println(Main.getUserName());
                ResultSet rs = stmt.executeQuery(test), rs2;
                if (rs.next()) {
                    id = rs.getInt("idBabySitter");
                    System.out.println(id+"");
                    String name = rs.getString("name");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    Date birthdate = rs.getDate("birthdate");
                    String email = rs.getString("email");
                    String gender = rs.getString("gender");
                    String password = rs.getString("password");
                    String image = rs.getString("image");
                    double hour_price = rs.getDouble("price_hour");
                    double recMoney = rs.getDouble("recieved_money");
                    babySitter = new BabySitter(id, Main.getUserName(), name, phone, address, email, gender, password, birthdate, new Image(image), hour_price,recMoney);
                }
                rs = stmt.executeQuery("select * from SitterBooking where BabySitter_idBabySitter=" + id+" and answer = false");
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
}
