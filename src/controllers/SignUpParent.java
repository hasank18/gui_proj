package controllers;
import gui_classes.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignUpParent implements Initializable {
    String server = "localhost";
    int port = 3306;
    String user = "admin";
    String password = "admin";
    String database = "project_gui";
    String jdbcurl;
    Connection con = null;
//         private FileChooser fileChooser;
//
//        fileChooser = new FileChooser();
//        fileChooser.getExtensionFilters().addAll(
//                new ExtensionFilter("Text Files", "*txt"),
//                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
//                new ExtensionFilter("Audio Files", "*wav", "*.mp3", "*.aac"),
//                new ExtensionFilter("All Files", "*.*")
//        );

    @FXML
    Button Back_Page,Continue;
    @FXML
    AnchorPane switch_pane;
    @FXML
    Label no_name,no_username,no_email,no_pass,no_gender,no_num,no_address,no_date;
    @FXML
    TextField fname,Email,address,num,username;
    @FXML
    PasswordField pass;
    @FXML
    DatePicker date;
    @FXML
    ComboBox<String> gender;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gender.getItems().addAll("Male", "Female");

    }
    @FXML
    private void Back_Page(ActionEvent event)  throws Exception {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/ChoiceSignUp.fxml"));
        switch_pane.getChildren().setAll(pane);
    }

    //    BrowseAction.setOnAction(e ->
//
//    {
//        //Single File Selection
//        file = fileChooser.showOpenDialog(primaryStage);
//        if (file != null) {
//            textArea.setText(file.getAbsolutePath());
//            image = new Image(file.toURI().toString(), 100, 150, true, true);
//            ImageView = new ImageView(image);
//            ImageView.setFitWidth(100);
//            ImageView.setFitHeight(150);
//            ImageView.setPreserveRatio(true);
//
//            layout.setCenter(imageView);
//            BorderPane.setAlignment(imageView, Pos.TOP_LEFT);
//        }
//    }
    @FXML
    private void continueHandleEvent(ActionEvent event) throws Exception{
        if (checkInfo())
            sign_up_parent();

        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxml/Login.fxml"));
        switch_pane.getChildren().setAll(pane);


    }

    private void sign_up_parent() {
        String Username = username.getText();
        String Name = fname.getText();
        String email=Email.getText();
        String Password = pass.getText();
        String Location =address.getText();
        String phone = num.getText();
        String Gender = gender.getValue();
        LocalDate Date=date.getValue();
        String image=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_gui", "admin", "admin" + "");
            CallableStatement stmt = con.prepareCall("{call addPersonAsClient(?, ?,?,?,?,?,?,?,?)}");


            stmt.setString(1,Username);
            stmt.setString(2,Name);
            stmt.setString(6,email);
            stmt.setString(8,Password);
            stmt.setString(4,Location);
            stmt.setString(3,phone);
            stmt.setString(7,Gender);
            stmt.setDate(5, java.sql.Date.valueOf(Date));
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
            no_username.setTextFill(javafx.scene.paint.Color.web("blue"));
            flag = false;
        } else
            no_username.setText("");
        if (fname.getText().isEmpty()) {
            no_name.setText("Please enter your name");
            no_name.setTextFill(javafx.scene.paint.Color.web("blue"));
            flag = false;
        } else
            no_name.setText("");
        if (Email.getText().isEmpty()) {
            no_email.setText("Please enter your Email");
            no_email.setTextFill(javafx.scene.paint.Color.web("blue"));
            flag = false;
        } else
            no_email.setText("");
        if (pass.getText().isEmpty()) {
            no_pass.setText("Please enter your password");
            no_pass.setTextFill(javafx.scene.paint.Color.web("blue"));
            flag = false;
        }else{
            no_pass.setText("");
        }

        if (num.getText().isEmpty()) {
            no_num.setText("Please enter your phonenum");
            no_num.setTextFill(javafx.scene.paint.Color.web("blue"));
            flag = false;
        } else
            no_num.setText("");
        if (gender.getValue() == null) {
            no_gender.setText("Please enter your gender");
            no_gender.setTextFill(javafx.scene.paint.Color.web("blue"));
            flag = false;
        } else
            no_gender.setText("");
        if(address.getText().isEmpty()) {
            no_address.setText("please enter your address");
            no_address.setTextFill(Color.web("blue"));
            flag = false;
        }
        else
            no_address.setText("");

        if(date.getEditor().getText().isEmpty()) {
            no_date.setText("please enter your birthdate");
            no_date.setTextFill(Color.web("blue"));
            flag = false;
        }
        else
            no_date.setText("");
        if (gender.getValue() != null &&date.getValue()!=null  && !username.getText().isEmpty() && !fname.getText().isEmpty() && !Email.getText().isEmpty() && !num.getText().isEmpty() && !pass.getText().isEmpty()
                &&!address.getText().isEmpty())
            flag = true;
        return flag;
    }


}
