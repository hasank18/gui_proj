package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    static String Username,type;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/LoginPage.fxml"));
        primaryStage.setTitle("service provider system");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void  setUserName(String username){
        Username = username;
    }
    public static String getUserName(){
        return Username;
    }

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        Main.type = type;
    }
}
