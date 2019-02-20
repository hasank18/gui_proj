//package controllers;
//
//import gui_classes.ClientPage;
//import javafx.beans.property.ReadOnlyStringWrapper;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Button;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.TextFieldTableCell;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.input.KeyEvent;
//import javafx.event.EventHandler;
//import javafx.scene.paint.Color;
//import javafx.scene.control.Label;
//
//import javax.swing.*;
//import java.awt.*;
//import java.io.IOException;
//import java.net.URL;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.ResourceBundle;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class Client_info_Controller implements Initializable {
//
//    String server = "localhost";
//    int port = 3306;
//    String user = "admin";
//    String password = "admin";
//    String database = "project_gui";
//    String jdbcurl;
//    Connection con = null;
//    private PreparedStatement pst = null;
//    Image format=null;
//    @FXML
//    TextField Client_fname_field, Client_lname_field;
//    @FXML
//    AnchorPane switch_pane, container;
//    @FXML
//    Button search;
//    @FXML
//    TableView<gui_classes.ClientPage> table;
//    @FXML
//    TableColumn<gui_classes.ClientPage, String> client_id, client_fname, client_lname, client_pass, client_email, client_phonenum, client_location, client_gender;
//    @FXML
//    TableColumn<ClientPage, Image> client_image;
//
//    @FXML
//    Label not_found, label;
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        int cid;
//        String fname, lname, pass, email,
//                phonenum, Clocation, gender;
//        ArrayList<byte[]> Image= new ArrayList<>();
//
//        client_id.setCellValueFactory(data -> {
//            gui_classes.ClientPage value = data.getValue();
//            String string = "" + value.getId();
//            return new ReadOnlyStringWrapper(string);
//        });
//        client_fname.setCellValueFactory(data -> {
//            ClientPage value = data.getValue();
//            String string = value.getfName();
//            return new ReadOnlyStringWrapper(string);
//        });
//        client_lname.setCellValueFactory(data -> {
//            ClientPage value = data.getValue();
//            return new ReadOnlyStringWrapper(value.getlName());
//        });
//        client_email.setCellValueFactory(data -> {
//            ClientPage value = data.getValue();
//            return new ReadOnlyStringWrapper(((ClientPage) value).getEmail());
//        });
//        client_pass.setCellValueFactory(data -> {
//            ClientPage value = data.getValue();
//            return new ReadOnlyStringWrapper(((ClientPage) value).getPassword());
//        });
//
//        client_phonenum.setCellValueFactory(data -> {
//            ClientPage value = data.getValue();
//            return new ReadOnlyStringWrapper(((ClientPage) value).getPhonenum());
//        });
//        client_gender.setCellValueFactory(data -> {
//            ClientPage value = data.getValue();
//            return new ReadOnlyStringWrapper(((ClientPage) value).getGender());
//        });
//        client_location.setCellValueFactory(data -> {
//            ClientPage value = data.getValue();
//            return new ReadOnlyStringWrapper(((ClientPage) value).getLocation());
//        });
////        client_image.setCellValueFactory(data->{
////            ClientPage value=data.getValue();
////            return new ReadOnlyStringWrapper(value.getimage());
////        });
//
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_gui", "admin", "admin" + "");
//            Statement stmt = con.createStatement();
//            String get_data = "select * from Client_info";
//            ResultSet rs = stmt.executeQuery(get_data);
//            while (rs.next()) {
//                cid = rs.getInt(1);
//                fname = rs.getString(2);
//                lname = rs.getString(3);
//                email = rs.getString(4);
//                pass = rs.getString(5);
//                phonenum = rs.getString(6);
//                gender = rs.getString(7);
//                Clocation = rs.getString(8);
//                try {
//                    Blob imageBlob = rs.getBlob(11);
//                    if(imageBlob!=null)
//                        Image.add(imageBlob.getBytes(1, (int) imageBlob.length()));
//                    else
//                        Image.add(null);
//                } catch(Exception e){
//                    e.printStackTrace();
//                }
//
//
////                table.getItems().add(new ClientPage(cid, fname, lname, email, pass, phonenum, gender, Clocation,image));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @FXML
//    private void updatefNameHandler(TableColumn.CellEditEvent event) {
//        ClientPage clientController = table.getSelectionModel().getSelectedItem();
//        String edit = "call updateCFname('" + clientController.getId() + "','" + event.getNewValue() + "')";
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_gui", "admin", "admin" + "");
//            Statement stmt = con.createStatement();
//            stmt.executeQuery(edit);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @FXML
//    private void updatelNameHandler(TableColumn.CellEditEvent event) {
//        ClientPage clientController = table.getSelectionModel().getSelectedItem();
//        String edit = "call updateCLname('" + clientController.getId() + "','" + event.getNewValue() + "')";
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_gui", "admin", "admin" + "");
//            Statement stmt = con.createStatement();
//            stmt.executeQuery(edit);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @FXML
//    private void updateemailHandler(TableColumn.CellEditEvent event) {
//        ClientPage clientController = table.getSelectionModel().getSelectedItem();
//        String edit = "call update_email('" + clientController.getId() + "','" + event.getNewValue() + "')";
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_gui", "admin", "admin" + "");
//            Statement stmt = con.createStatement();
//            stmt.executeQuery(edit);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @FXML
//    private void updatepassHandler(TableColumn.CellEditEvent event) {
//        ClientPage clientController = table.getSelectionModel().getSelectedItem();
//        String edit = "call updatePass('" + clientController.getId() + "','" + event.getNewValue() + "')";
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_gui", "admin", "admin" + "");
//            Statement stmt = con.createStatement();
//            stmt.executeQuery(edit);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @FXML
//    private void updategenderHandler(TableColumn.CellEditEvent event) {
//        ClientPage clientController = table.getSelectionModel().getSelectedItem();
//        String edit = "call update_gender('" + clientController.getId() + "','" + event.getNewValue() + "')";
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_gui", "admin", "admin" + "");
//            Statement stmt = con.createStatement();
//            stmt.executeQuery(edit);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @FXML
//    private void updatelocationHandler(TableColumn.CellEditEvent event) {
//        ClientPage clientController = table.getSelectionModel().getSelectedItem();
//        String edit = "call update_location('" + clientController.getId() + "','" + event.getNewValue() + "')";
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_gui", "admin", "admin" + "");
//            Statement stmt = con.createStatement();
//            stmt.executeQuery(edit);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @FXML
//    private void updatephonenumHandler(TableColumn.CellEditEvent event) {
//        ClientPage clientController = table.getSelectionModel().getSelectedItem();
//        String edit = "call update_phonenum('" + clientController.getId() + "','" + event.getNewValue() + "')";
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_gui", "admin", "admin" + "");
//            Statement stmt = con.createStatement();
//            stmt.executeQuery(edit);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @FXML
//    private void editClientInfo(ActionEvent event) {
//        table.setEditable(true);
//        client_id.setCellFactory(TextFieldTableCell.forTableColumn());
//        client_fname.setCellFactory(TextFieldTableCell.forTableColumn());
//        client_lname.setCellFactory(TextFieldTableCell.forTableColumn());
//        client_email.setCellFactory(TextFieldTableCell.forTableColumn());
//        client_pass.setCellFactory(TextFieldTableCell.forTableColumn());
//        client_phonenum.setCellFactory(TextFieldTableCell.forTableColumn());
//        client_gender.setCellFactory(TextFieldTableCell.forTableColumn());
//        client_location.setCellFactory(TextFieldTableCell.forTableColumn());
//    }
//
//
//    @FXML
//    private void deleteClient(ActionEvent event) {
//        int id = table.getSelectionModel().getSelectedItem().getId();
//        String delete_Client = "call delete_client('" + id + "')";
//        table.getItems().remove(table.getSelectionModel().getSelectedItem());
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_gui", "admin", "admin" + "");
//            Statement stmt = con.createStatement();
//            stmt.executeQuery(delete_Client);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//    @FXML
//    private void searchclient() {
//            if (search.getText().equals("")) {
////                try {
////                    pst = con.prepareStatement("select *from Client_info");
////                    ResultSet rs = pst.executeQuery();
////                    while (rs.next()) {
////                        table.getItems().add(new ClientPage (rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getByte(10)));
////                    }
////                } catch (SQLException e1) {
////                    System.out.println("h");
////                    Logger.getLogger(Client_info_Controller.class.getName()).log(Level.SEVERE ,null,e1);
////                }
//            }
//            else {
//                table.getItems().clear();
//                String sql = "select * from Client_info where Client_FirstName like '"  + search.getText() + "%'";
//                try {
//                    pst = con.prepareStatement(sql);
//                    ResultSet rs = pst.executeQuery();
//                    while (rs.next()) {
//                        int cid = rs.getInt(1);
//                        String fname = rs.getString(2);
//                        String lname = rs.getString(3);
//                        String email = rs.getString(4);
//                        String pass = rs.getString(5);
//                        String phonenum = rs.getString(6);
//                        String gender = rs.getString(7);
//                        String Clocation = rs.getString(8);
//                        Blob imageBlob = rs.getBlob(9);
//                        byte[] image=imageBlob.getBytes(1, (int) imageBlob.length());
//                        table.getItems().add(new ClientPage(cid, fname, lname, email, pass, phonenum, gender, Clocation,image));
//                        }
//                } catch (SQLException e1) {
//                    Logger.getLogger(Client_info_Controller.class.getName()).log(Level.SEVERE, null, e1);
//                }
//
//            }
//        }
//        public void show_image(ActionEvent event){
//        try {
//            String sql = "select C_image from Client_info where Client_id = 10";
//            pst = con.prepareStatement(sql);
//            ResultSet rs = pst.executeQuery();
//            if (rs.next()) {
//                byte[] imagedata = rs.getBytes("image");
//                System.out.println(imagedata);
//
//
//            }
//        }
//        catch(Exception e){
//                e.printStackTrace();
//            }
//        }
//    }
//
