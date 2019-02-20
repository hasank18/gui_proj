package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.sql.*;

import java.time.LocalDate;
import java.util.ResourceBundle;

public class SignUpBabysitter implements Initializable {
    String server = "localhost";
    int port = 3306;
    String user = "admin";
    String password = "admin";
    String database = "project_gui";
    String jdbcurl;
    Connection con = null;
    @FXML
    AnchorPane switch_pane;
    @FXML
    VBox main;
    @FXML
    Label no_fname,no_username,no_email,no_pass,no_pass2,no_gender,no_birthdate,no_num,no_location,no_image;
    @FXML
    Button Continue,Back_Page;
    @FXML
    TextField fname,Lname,Email,location,birthdate,num,username;
    @FXML
    PasswordField pass,confirm_pass;
    @FXML
    ComboBox<String> gender,service;
    @FXML
    ImageView image,imageback;
    @FXML
    DatePicker date;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gender.getItems().addAll("Male", "Female");

    }

    @FXML
    private void Back_Page(ActionEvent event)  throws Exception {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("fxml/ChoiceSignUp.fxml"));
        switch_pane.getChildren().setAll(pane);
    }

    @FXML
    private void continueHandleEvent(ActionEvent event) {
        if (checkInfo())
            sign_up_babysitter();

    }

    private void sign_up_babysitter() {

        String Username = username.getText();
        String Name = fname.getText();
        String email=Email.getText();
        String Password = pass.getText();
        String Location =location.getText();
        String phone = num.getText();
        String Gender = gender.getValue();
        LocalDate Date=date.getValue();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_gui", "admin", "admin" + "");
            Statement stmt = con.createStatement();
            String test = "call addPerson("+"'"+username+"',"+"'"+fname+"','"+num+"'"+"''"+location+"','"+date+"','"+Email+"'"+"'"+gender+"','"+pass+"','"+image+"')";

            ResultSet rs = stmt.executeQuery(test);

         }catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error in Database:execQuery" +e.getLocalizedMessage());
        }
    }



    private boolean checkInfo() {
        boolean flag = false;
        if (username.getText().isEmpty()) {
            no_fname.setText("Please enter your username");
            no_fname.setTextFill(Color.web("blue"));
            flag = false;
        }
        else
            no_username.setText("");
        if (fname.getText().isEmpty()) {
            no_fname.setText("Please enter your name");
            no_fname.setTextFill(Color.web("blue"));
            flag = false;
        }else
            no_fname.setText("");

        if (Email.getText().isEmpty()) {
            no_email.setText("Please enter your Email");
            no_email.setTextFill(Color.web("blue"));
            flag = false;
        }else
            no_email.setText("");
        if (pass.getText().isEmpty()) {
            no_pass.setText("Please enter your password");
            no_pass.setTextFill(Color.web("blue"));
            flag = false;
        }

        if (num.getText().isEmpty()) {
            no_num.setText("Please enter your phonenum");
            no_num.setTextFill(Color.web("blue"));
            flag = false;
        } else
            no_num .setText("");
        if (date.getEditor().getText().isEmpty()) {
            no_birthdate.setText("Please enter date");
            no_birthdate.setTextFill(Color.web("blue"));
            flag = false;
        } else
            no_birthdate.setText("");
        if (gender.getValue() == null) {
            no_gender.setText("Please select your gender");
            no_gender.setTextFill(Color.web("blue"));
            flag = false;
        } else
            no_gender.setText("");
        if(location.getText().isEmpty()) {
            no_location.setText("please enter your location");
            no_location.setTextFill(Color.web("blue"));
            flag = false;
        }
        else
            no_location.setText("");

        if (gender.getValue() !=null && !username.getText().isEmpty() && !fname.getText().isEmpty() && !Email.getText().isEmpty() && !num.getText().isEmpty() && !pass.getText().isEmpty()&&!birthdate.getText().isEmpty()
        &&!location.getText().isEmpty());
            flag = true;
        return flag;
    }

}


