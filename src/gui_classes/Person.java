package gui_classes;

import java.sql.Blob;
import java.sql.Date;

public abstract class  Person {
    protected String username,name,phone,address,email,gender,password;
    protected Date birthdate;
    protected Blob image;

    public Person(String username, String name, String phone,String address, String email, String gender, String password, Date birthdate, Blob image) {
        this.username = username;
        this.name = name;
        this.phone = phone;
        this.address=address;
        this.email = email;
        this.gender = gender;
        this.password = password;
        this.birthdate = birthdate;
        this.image = image;


    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
