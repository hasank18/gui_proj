package gui_classes;

import javafx.scene.image.Image;

import java.sql.Blob;
import java.sql.Date;
import java.util.ArrayList;

 class Admin extends Person{
    private static Admin admin;
    private int ID;
    private ArrayList<Client> clients;
    private ArrayList<BabySitter> baby_sitters;
    private ArrayList<Payment> payments;

    private Admin(int ID,String username, String name, String phone, String address,String email, String gender, String password, Date birthdate, Image image) {
        super(username, name, phone,address ,email, gender, password, birthdate, image);
        payments = new ArrayList<>();
        baby_sitters = new ArrayList<>();
        clients = new ArrayList<>();
        this.ID=ID;
    }
    public Admin getAdmin(){
        if(admin == null){
            admin = new Admin(ID,username, name, phone, address, email, gender, password, birthdate, image);
        }
        return admin;
    }

     public ArrayList<Payment> getPayments() {
         return payments;
     }

     public ArrayList<Client> getClients() {
         return clients;
     }

     public void setClients(ArrayList<Client> clients) {
         this.clients = clients;
     }

     public ArrayList<BabySitter> getBaby_sitters() {
         return baby_sitters;
     }

     public void setBaby_sitters(ArrayList<BabySitter> baby_sitters) {
         this.baby_sitters = baby_sitters;
     }
     public void addBabySitter(int ID,String username, String name, String phone, String email, String gender, String password, Date birthdate, Image image,double price_hour){
        baby_sitters.add(new BabySitter(ID,username,name,phone,address,email,gender,password,birthdate,image,price_hour));
     }
     public void addClient(Client client){
        clients.add(client);
     }
     public void addPayment(Payment payment){
         payments.add(payment);
     }

     public int getID() {
         return ID;
     }
 }
