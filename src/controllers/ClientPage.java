package controllers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import gui_classes.Client;
import gui_classes.CustomData;
import javafx.event.ActionEvent;
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

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ClientPage implements Initializable {
    public JFXHamburger hamburger;
    public JFXDrawer drawer;
    public ListView<Custom> listView;
    public AnchorPane anchorPane;
    public AnchorPane anchorPane1;
    Connection con;
    Client client;
    private int ID;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            VBox SidePane = FXMLLoader.load(getClass().getResource("../fxml/SidePane.fxml"));
            drawer.setSidePane(SidePane);
            SidePane.getChildren().get(1).setOnMouseClicked(event -> {

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

    void setID(int ID) {
        this.ID = ID;
        System.out.println(ID + "");
    }

    public ClientPage getController() {
        return this;
    }

    public void setData() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_gui", "root", "" + "");
            Statement stmt = con.createStatement();
            String test = "select * from Client where idclient=" + ID;
            ResultSet rs = stmt.executeQuery(test);
            String username="";
            if (rs.next())
                username = rs.getString("Person_username");
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
                client = new Client(ID,username,name,address,phone,email,gender,password,birthdate,image);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getData(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_gui", "admin", "admin" + "");
            Statement stmt = con.createStatement();
            String test = "select * from BabySitterView";
            ResultSet rs = stmt.executeQuery(test);
            while(rs.next()) {
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                Date birthdate = rs.getDate("birthdate");
                String email = rs.getString("email");
                String price_hour = rs.getString("price_hour");
                int rating = 2;
                Image image = new Image(rs.getString("image"));
                System.out.println(rs.getString("image"));
                Custom custom = new Custom();
                custom.updateItem(new CustomData(name,phone,address,birthdate.toString(),email,price_hour,rating,image));
                listView.getItems().add(custom);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void MainPage(MouseEvent event) {

    }

    public void GoToSettings(MouseEvent event) {
    }

    public void GoToBooking(MouseEvent event) {
    }

    public void LogOut(MouseEvent event) throws IOException {
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
    }
}
