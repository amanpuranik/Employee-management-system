package loginapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Login extends Application{

    Controller controller = new Controller();


    public void start (Stage stage) throws Exception{
        Parent root = (Parent)FXMLLoader.load(getClass().getResource("login.fxml"));//was /loginapp/login.fxml
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Employee management system");
        stage.show(); //showing the window
    }

    public static void main (String [] args){
        launch(args); //this line of code will run the application

    }

}
