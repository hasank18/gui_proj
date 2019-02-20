package controllers;

import gui_classes.CustomData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookingSitter implements Initializable {
    String name,phone,address,age,email,price_hour;
    int rating=2;
    Connection con;
    int ID;
    @FXML
    ListView<String> listView;
    @FXML
    ImageView home_choice,settings,help,start,home;
    @FXML
    SplitPane pane1;
    @FXML
    AnchorPane switchpane,container;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<CustomData> arrayList = new ArrayList<>();
        final ObservableList<String> data = FXCollections.observableArrayList();
        //arrayList.add(new CustomData("hasan","12312312","bchamoun","18","example@gmail.com","10",2));

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_gui", "admin", "admin" + "");
            Statement stmt = con.createStatement(),stmt2=con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from babysitter"),rs2;
            while(rs.next()){
                rs2 = stmt2.executeQuery("select * from Person where username = '"+rs.getString("Person_username")+"'");
                if(rs2.next()){
                    name = rs2.getString("name");
                    phone = rs2.getString("phone");
                    address = rs2.getString("address");
                    age = rs2.getDate("birthdate").toString();
                    email = rs2.getString("email");
                    price_hour = rs.getString("price_hour");
                    rating = 2;
                }
            }
            listView.setItems(data);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
        @FXML
    private void home_choiceHandler(MouseEvent mouseEvent)throws Exception{
        pane1.setDividerPosition(0,0.2);
//        pane1.setDividerPosition(0,0.2);

    }
    @FXML
    private void home_choiceHandler2(MouseEvent mouseEvent)throws Exception{
        pane1.setDividerPosition(0,0.01);

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

    void setID(int ID){
        this.ID = ID;
    }
    public void setData(){

    }


    public void startHandler(MouseEvent mouseEvent) {
    }
}
