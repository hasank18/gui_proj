package controllers;

import gui_classes.Person;
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
import java.net.URLDecoder;
import java.sql.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignUpBabySitter implements Initializable {
    Connection con = null;
    @FXML
    AnchorPane switch_pane;
    @FXML
    VBox main;
    @FXML
    Label no_fname,no_username,no_email,no_pass,no_gender,no_birthdate,no_num,no_location,no_image;
    @FXML
    Button Continue,Back_Page;
    @FXML
    TextField fname,Email,location,date,num,username;
    @FXML
    PasswordField pass;
    @FXML
    ComboBox<String> gender;
    @FXML
    ImageView image;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gender.getItems().addAll("Male", "Female");

    }

    @FXML
    private void Back_Page(ActionEvent event)  throws Exception {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/ChoiceSignUp.fxml"));
        switch_pane.getChildren().setAll(pane);
    }

    @FXML
    private void continueHandleEvent(ActionEvent event) throws  Exception {
        if (checkInfo()) {
            sign_up_babysitter();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/Login.fxml"));
            switch_pane.getChildren().setAll(pane);
        }
    }

    private void sign_up_babysitter() {

        String Username = username.getText();
        String Name = fname.getText();
        String email=Email.getText();
        String Password = pass.getText();
        String Location =location.getText();
        String phone = num.getText();
        String Gender = gender.getValue();
        String Date=date.getText();
        String image=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_gui", "admin", "admin" + "");

            CallableStatement stmt = con.prepareCall("{call addPersonAsBabySitter(?, ?,?,?,?,?,?,?,?)}");
            stmt.setString(1,Username);
            stmt.setString(2,Name);
            stmt.setString(6,email);
            stmt.setString(8,Password);
            stmt.setString(4,Location);
            stmt.setString(3,phone);
            stmt.setString(7,Gender);
            stmt.setString(5,Date);
            stmt.setString(9,image);



            if(stmt.executeUpdate()>0) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION.INFORMATION);
                alert.setTitle(" information");
                alert.setHeaderText(null);
                alert.setContentText("succ added!");
                alert.showAndWait();
            }

        } catch (Exception e) {

            System.out.println("Error in Database:execQuery" +e.getLocalizedMessage());
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, e);
        }
    }



    private boolean checkInfo() {
        boolean flag = false;
        if (username.getText().isEmpty()) {
            no_username.setText("Please enter your username");
            no_username.setTextFill(Color.web("blue"));
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
        else
            no_pass.setText("");

        if (num.getText().isEmpty()) {
            no_num.setText("Please enter your phonenum");
            no_num.setTextFill(Color.web("blue"));
            flag = false;
        } else
            no_num .setText("");
        if (date.getText().isEmpty()) {
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
            no_location.setText("please enter your address");
            no_location.setTextFill(Color.web("blue"));
            flag = false;
        }
        else
            no_location.setText("");

        if (gender.getValue() !=null && !username.getText().isEmpty() && !fname.getText().isEmpty() && !Email.getText().isEmpty() && !num.getText().isEmpty() && !pass.getText().isEmpty()&&!date.getText().isEmpty()
                &&!location.getText().isEmpty());
        flag = true;
        return flag;
    }

}


