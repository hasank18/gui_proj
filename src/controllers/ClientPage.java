package controllers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import gui_classes.Client;
import gui_classes.CustomData;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
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

public class ClientPage implements Initializable {
    ObservableList<Custom> observableList;
    public JFXHamburger hamburger;
    public JFXDrawer drawer;
    public ListView<Custom> listView;
    public AnchorPane anchorPane;
    public AnchorPane anchorPane1;
    Connection con;
    Client client;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_gui", "root", "" + "");
            Statement stmt = con.createStatement();
            String test = "select * from Client,Person where Person.username='"+ Main.getUserName()+"' and Client.Person_username='"+Main.getUserName()+"'";
            ResultSet rs = stmt.executeQuery(test);
            String username="";
            if (rs.next()) {
                int ID = rs.getInt("idclient");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                Date birthdate = rs.getDate("birthdate");
                String email = rs.getString("email");
                String gender = rs.getString("gender");
                String password = rs.getString("password");
                String image = rs.getString("image");
                client = new Client(ID,username,name,address,phone,email,gender,password,birthdate,new Image(image));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_gui", "root", "" + "");
            Statement stmt = con.createStatement();
            String test = "select * from BabySitterView";
            ResultSet rs = stmt.executeQuery(test);
            while(rs.next()) {
                String username = rs.getString("Person_username");
                System.out.println(username);
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                Date birthdate = rs.getDate("birthdate");
                String email = rs.getString("email");
                String price_hour = rs.getString("price_hour");
                int rating = 2;
                Image image = new Image(rs.getString("image"));
                System.out.println(image.toString());
                Custom custom = new Custom(username);
                CustomData customData = new CustomData(username,name,phone,address,birthdate.toString(),email,price_hour,rating,image,OnClick(username));
                custom.updateItem(customData);
                listView.getItems().add(custom);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            VBox SidePane = FXMLLoader.load(getClass().getResource("../fxml/SidePaneClient.fxml"));
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
                    Stage stage = new Stage(),stage1 = (Stage)anchorPane1.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                    stage1.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
            SidePane.getChildren().get(3).setOnMouseClicked(e ->{
                try {
                    Parent parent = FXMLLoader.load(getClass().getResource("../fxml/checkBookingClient.fxml"));
                    Scene scene = new Scene(parent);
                    Stage stage = new Stage(),stage1 = (Stage)anchorPane1.getScene().getWindow();
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
                    Stage stage = new Stage(),stage1 = (Stage)anchorPane1.getScene().getWindow();
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

    }

    public ClientPage getController() {
        return this;
    }
    public EventHandler<MouseEvent> OnClick(String username){
        EventHandler<MouseEvent> eventEventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Connection con = DBconnection.getConnection();
                    Statement stmt = con.createStatement();
                    System.out.println(username);
                    ResultSet rs = stmt.executeQuery("select idBabySitter from BabySitter where Person_username = '" +username+ "'");
                    rs.next();
                    int idBabySitter = rs.getInt(1);
                    System.out.println(idBabySitter+"");
                    rs = stmt.executeQuery("select idclient from Client where Person_username = '" + Main.getUserName() + "'");
                    rs.next();
                    int cid = rs.getInt(1);
                    double paid = 0;
                    stmt.executeUpdate("insert into sitter_payment(paid,Client_idclient,admin_idadmin,BabySitter_idBabySitter) values(" + paid + "," + cid + "," + 1 + "," + idBabySitter + ")");
                    rs = stmt.executeQuery("select idsitter_payment from sitter_payment where Client_idclient=" + cid + " and BabySitter_idBabySitter=" + idBabySitter);
                    rs.next();
                    int idpayment = rs.getInt(1);
                    stmt.executeUpdate("insert SitterBooking(date,Client_idclient,BabySitter_idBabySitter,sitter_payment_idsitter_payment,answer) values('1999-12-12'," + cid + "," + idBabySitter + "," + idpayment + ",false);");
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/PaymentMethod.fxml"));
                    fxmlLoader.load();
                    PaymentMethod paymentMethod = fxmlLoader.getController();
                    paymentMethod.setId(idpayment);
                    Notifications.create().title("Booking Succseeded !!").text("now wait for Babysitter responce").showConfirm();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        return eventEventHandler;
    }
}
