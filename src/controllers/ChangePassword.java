package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ChangePassword implements Initializable {
    @FXML
    Button changebtn;
    @FXML
    Label no_username,no_pass1,no_pass2,lbl;
    @FXML
    TextField txt1;
    @FXML
    PasswordField pass1,pass2;
    @FXML
    ImageView visible1,visible2;
    @FXML
    Connection con=null;

    PreparedStatement pstmt=null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    private void updateHandler(ActionEvent event)throws Exception{
        if (checkInfo()&&checkconfirmation())
           try{

               String query="update password "
                       + " set password=? "
                       + " where username=? and password=?";


               pstmt=con.prepareCall(query);

               pstmt.setString(1,pass2.getText());
               pstmt.setString(2,txt1.getText());
               pstmt.setString(3,pass1.getText());


               int count=pstmt.executeUpdate();

               if(count>0)
               {
                   System.out.println("password successfully changed");
               }
               else {
                   System.out.println("unable to change password, provide right credential");
               }}
        catch (Exception e){
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        }


    @FXML
    private boolean checkconfirmation() {
        String UserName = txt1.getText();
        String Password = pass1.getText();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_gui", "admin", "admin" + "");
            Statement stmt = con.createStatement();
            String data = "select UserName,Password  from Person where username='" + UserName + "'and password='" + Password + "'";
            ResultSet rs2 = stmt.executeQuery(data);
            if (rs2.next()) {
                return true;

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return false;
    }
    private boolean checkInfo () {
        boolean flag = false;
        if (txt1.getText().isEmpty()) {
            no_username.setText("Please enter your username");
            no_username.setTextFill(Color.web("#00CED1"));
            flag = false;
        }
        if (pass1.getText().isEmpty()) {
            no_pass1.setText("Please enter your Pass");
            no_pass1.setTextFill(Color.web("#00CED1"));
            flag = false;
        }
        if (pass2.getText().isEmpty()) {
            no_pass2.setText("Please reenter your new Pass");
            no_pass2.setTextFill(Color.web("#00CED1"));
            flag = false;
        }

        if (!txt1.getText().isEmpty() && !pass1.getText().isEmpty() &&!pass2.getText().isEmpty() );
            flag = true;
        return flag;
    }
    @FXML
    public void visibleHandler1(javafx.scene.input.MouseEvent mouseEvent) throws Exception
        {
            pass1.setText(pass1.getText());
        }
        @FXML
        public void visibleHandler2(javafx.scene.input.MouseEvent mouseEvent) throws Exception{

                pass2.setText(pass2.getText());
            }




}
