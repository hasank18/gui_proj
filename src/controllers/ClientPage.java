package controllers;

import gui_classes.Client;
import gui_classes.CustomData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;

import java.io.FileInputStream;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ClientPage implements Initializable {
    public ListView<Custom> listView;
    Connection con;
    Client client;
    private int ID;
    @FXML
    SplitPane splitPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        listView.getItems().addAll(custom,custom1,custom2,custom3);
    }

    @FXML
    public void onMouseEnter(MouseEvent mouseEvent) {
        splitPane.setDividerPosition(0, 0.3);
    }

    public void onMouseEnter2(MouseEvent mouseEvent) {
        splitPane.setDividerPosition(0, 0.01);
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
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_gui", "admin", "admin" + "");
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
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_gui", "root", "" + "");
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
                Image image = new Image(new FileInputStream("default_profile_picture.png"));
                ImageView imageView = new ImageView(image);
                int rating = 2;
                Custom custom = new Custom();
                custom.updateItem(new CustomData(name,phone,address,birthdate.toString(),email,price_hour,rating,image));
                listView.getItems().add(custom);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
