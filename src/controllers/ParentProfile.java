package controllers;


import gui_classes.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import sample.DBconnection;
import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ParentProfile implements Initializable {

    Connection con = null;
    private PreparedStatement pst = null;
    @FXML
    TextField search;
    @FXML
    TableView<Client> table;
    @FXML
    TableColumn<Client, String> id, name, username, email, password, gender, date, address, phone;
    @FXML
    TableColumn<Client, Image> image;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connection con = DBconnection.getConnection();
            Statement stm1 = con.createStatement();
            ResultSet rs1 = stm1.executeQuery("select * from Person,Client where Client.Person_username=Person.username");
            while (rs1.next()) {

                id.setCellValueFactory(new PropertyValueFactory<Client, String>("idclient"));
                username.setCellValueFactory(new PropertyValueFactory<Client, String>("Person_username"));
                name.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
                phone.setCellValueFactory(new PropertyValueFactory<Client, String>("phone"));
                address.setCellValueFactory(new PropertyValueFactory<Client, String>("address"));
                email.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
                date.setCellValueFactory(new PropertyValueFactory<Client, String>("birthdate"));



                table.getColumns().addAll(id, username, name, phone, address, email, date);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void updatename(TableColumn.CellEditEvent event) {
        Client client = table.getSelectionModel().getSelectedItem();
        String edit = "call updatename('" +client.getID() + "','" + event.getNewValue() + "')";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_gui", "admin", "admin" + "");
            Statement stmt = con.createStatement();
            stmt.executeQuery(edit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void updateemail(TableColumn.CellEditEvent event) {
        Client client = table.getSelectionModel().getSelectedItem();
        String edit = "call updateemail('" + client.getID() + "','" + event.getNewValue() + "')";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_gui", "admin", "admin" + "");
            Statement stmt = con.createStatement();
            stmt.executeQuery(edit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void updatepass(TableColumn.CellEditEvent event) {
        Client client   = table.getSelectionModel().getSelectedItem();
        String edit = "call updatepass('" + client.getID() + "','" + event.getNewValue() + "')";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_gui", "admin", "admin" + "");
            Statement stmt = con.createStatement();
            stmt.executeQuery(edit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void updategender(TableColumn.CellEditEvent event) {
        Client client = table.getSelectionModel().getSelectedItem();
        String edit = "call updategender('" + client.getID() + "','" + event.getNewValue() + "')";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_gui", "admin", "admin" + "");
            Statement stmt = con.createStatement();
            stmt.executeQuery(edit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void updateBirthdate(TableColumn.CellEditEvent event) {
        Client client = table.getSelectionModel().getSelectedItem();
        String edit = "call updateBirthdate('" + client.getID() + "','" + event.getNewValue() + "')";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_gui", "admin", "admin" + "");
            Statement stmt = con.createStatement();
            stmt.executeQuery(edit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void updateaddress(TableColumn.CellEditEvent event) {
        Client client = table.getSelectionModel().getSelectedItem();
        String edit = "call updateaddress('" + client.getID() + "','" + event.getNewValue() + "')";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_gui", "admin", "admin" + "");
            Statement stmt = con.createStatement();
            stmt.executeQuery(edit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void updatephonenumHandler(TableColumn.CellEditEvent event) {
        Client client= table.getSelectionModel().getSelectedItem();
        String edit = "call updatenum('" + client.getID() + "','" + event.getNewValue() + "')";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_gui", "admin", "admin" + "");
            Statement stmt = con.createStatement();
            stmt.executeQuery(edit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @FXML
//    private void updateimage (TableColumn.CellEditEvent event) {
//        BabySitter babysitter = table.getSelectionModel().getSelectedItem();
//        String edit = "call updateimage('" + babysitter.getID() + "','" + event.getNewValue() + "')";
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_gui", "admin", "admin" + "");
//            Statement stmt = con.createStatement();
//            stmt.executeQuery(edit);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }



    @FXML
    private void editEmployeeInfo(ActionEvent event) {
        table.setEditable(true);
        id.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        username.setCellFactory(TextFieldTableCell.forTableColumn());
        password.setCellFactory(TextFieldTableCell.forTableColumn());
        gender.setCellFactory(TextFieldTableCell.forTableColumn());
        address.setCellFactory(TextFieldTableCell.forTableColumn());
//        image.setCellFactory(TextFieldTableCell.forTableColumn());
        phone.setCellFactory(TextFieldTableCell.forTableColumn());
        date.setCellFactory(TextFieldTableCell.forTableColumn());

    }



    @FXML
    private void deleteHandler(ActionEvent event) {
        int id = table.getSelectionModel().getSelectedItem().getID();
        String deleteBabysitter = "call delete_client('" + id + "')";
        table.getItems().remove(table.getSelectionModel().getSelectedItem());
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_gui", "admin", "admin" + "");
            Statement stmt = con.createStatement();
            stmt.executeQuery(deleteBabysitter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void searchbabysitter() {
        if (search.getText().equals("")) {
            table.getItems().clear();
            try {
                pst = con.prepareStatement("select *from Person");
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    table.getItems().add(new Client(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getDate(9),rs.getBlob(10)));
                }
            } catch (SQLException e1) {

                Logger.getLogger(BabySitterProfile.class.getName()).log(Level.SEVERE ,null,pst);
            }
        }
        else {
            table.getItems().clear();
            String sql = "select * from Person where name like '"  + search.getText() + "%'";
            try {
                pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {

                    int pid = rs.getInt(1);
                    String username = rs.getString(2);
                    String fname = rs.getString(3);
                    String phonenum = rs.getString(4);
                    String address = rs.getString(5);
                    String email = rs.getString(6);
                    String gender = rs.getString(7);
                    String password = rs.getString(8);
                    Date date = rs.getDate(9);
                    Blob imageBlob = rs.getBlob(10);
                    double price=rs.getDouble(11);

                    table.getItems().add(new Client(pid, username, fname, phonenum, address, email, gender, password, date,imageBlob));
                }
            } catch(SQLException e1){
                Logger.getLogger(BabySitterProfile.class.getName()).log(Level.SEVERE, null, pst);
            }

        }
    }
}

