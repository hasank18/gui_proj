package gui_classes;

import javafx.scene.image.Image;

public class CustomData {
    String username,name1,phone1,address1,age1,email1,price_hour1;
    int rating1;
    Image image;

    public CustomData(String username,String name1, String phone1, String address1, String age1, String email1, String price_hour1, int rating1, Image image) {
        this.name1 = name1;
        this.phone1 = phone1;
        this.address1 = address1;
        this.age1 = age1;
        this.email1 = email1;
        this.price_hour1 = price_hour1;
        this.rating1 = rating1;
        this.image = image;
        this.username=username;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAge1() {
        return age1;
    }

    public void setAge1(String age1) {
        this.age1 = age1;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getPrice_hour1() {
        return price_hour1;
    }

    public void setPrice_hour1(String price_hour1) {
        this.price_hour1 = price_hour1;
    }

    public int getRating1() {
        return rating1;
    }

    public void setRating1(int rating1) {
        this.rating1 = rating1;
    }

    public Image getImage() {
        return image;
    }

    public String getUsername() {
        return username;
    }
}
