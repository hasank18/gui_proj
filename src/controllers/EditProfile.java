package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

public class EditProfile implements Initializable {
    public AnchorPane anchorPane;
    @FXML
    private ImageView image;

    @FXML
    private TextField password;

    @FXML
    private TextField profilePicture;

    @FXML
    private TextField name;

    @FXML
    private TextField email;

    @FXML
    private TextField phone;

    @FXML
    private TextField address;

    @FXML
    private TextField birthdate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DBconnection dBconnection = new DBconnection();
        Connection con = dBconnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Person where username = '"+ Main.getUserName()+"'");
            if(rs.next()){
                name.setText(rs.getString("name"));
                address.setText(rs.getString("address"));
                phone.setText(rs.getString("phone"));
                birthdate.setText(rs.getDate("birthdate").toString());
//                Image image1 = new Image(String.valueOf(getClass().getResource(rs.getString("image"))));
//                if(image1!=null)
//                    image.setImage(image1);
//                else
//                    image.setImage(new Image("file:../resources/default_profile_picture.png"));
                profilePicture.setText(rs.getString("image"));
                password.setText(rs.getString("password"));
                email.setText(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void getData(){
    }

    public void Update(ActionEvent event) {
        DBconnection dBconnection = new DBconnection();
        Connection con = dBconnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            String data = "update Person set name='"+name.getText()+"',address='"+address.getText()+"',phone='"+phone.getText()+"',birthdate='"+birthdate.getText()+"',image='"+profilePicture.getText()+"',password='"+password.getText()+"',email='"+email.getText()+"' where username='"+Main.getUserName()+"'";
            stmt.executeUpdate(data);
            System.out.println(data);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void Return(ActionEvent event) throws IOException {
        if(Main.getType().equals("Client")) {
            Parent parent = FXMLLoader.load(getClass().getResource("../fxml/ClientPage.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage(),stage1 = (Stage)anchorPane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            stage1.close();
        }
        else if (Main.getType().equals("BabySitter")) {
            Parent parent = FXMLLoader.load(getClass().getResource("../fxml/BabySitterPage.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage(),stage1 = (Stage)anchorPane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            stage1.close();
        }
        else{
            Parent parent = FXMLLoader.load(getClass().getResource("../fxml/AdminPage.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage(),stage1 = (Stage)anchorPane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            stage1.close();
        }

    }
}
