package loginapp;

import employee.EmployeeController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import manager.ManagerController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//controller class add functionality to fxml files
public class Controller implements Initializable {

    Loginmodel loginmodel = new Loginmodel(); //now we can use methods in loginmodel class


    //this part matches the stuff in the scene with their ids
    @FXML //this is a tag
    private Label dbStatus; //this tag binds the label to the dbUtil.database status label through the fieldID
    @FXML
    private Button loginButton;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    ComboBox<Option> combobox; //I made it not private
    @FXML
    private Label loginStatus;



    // this method will check if db is connected and update the db label
    @FXML
    public void initialize(URL url, ResourceBundle RB){

        if (this.loginmodel.isDBconnected()){
            this.dbStatus.setText("Db is connected");
        }
        else{
            this.dbStatus.setText("Not connected");
        }

        this.combobox.setItems(FXCollections.observableArrayList(Option.values())); //this piece sets the options of the combo box

        //final ComboBox combobox = new ComboBox(options);
    }

    public void setTopText(String text){
        loginStatus.setText(text);
    }

    //method for the login functionality
    @FXML
    public void Login(ActionEvent event) throws Exception {
        System.out.println(this.loginmodel.isLogin("aman","puranik","Employee"));


        //checks if correct credentials based on data that was input
            try {
                if(this.loginmodel.isLogin(this.username.getText(),this.password.getText(),((Option)this.combobox.getValue()).toString())) {
                    System.out.println("correct");
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/employee/employeeFXML.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();

                    stage.setTitle("Employees");
                    stage.setScene(new Scene(root1));
                    stage.show();
                }

            } catch (Exception exception) {
                exception.printStackTrace();

            }
        }


    //login process for an employee
    public void employeeLogin(){
        try{
            Stage userstage = new Stage(); //creates a new stage
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(getClass().getResource("employeeFXML.fxml").openStream());

            EmployeeController employeeController = (EmployeeController)loader.getController(); //attatches the controller file to its respective fxml file
            Scene scene = new Scene(root); //creating a new scene
            userstage.setScene(scene);
            userstage.setTitle("employee time");
            userstage.show();
        }
        catch (IOException exception)
        {
            exception.printStackTrace(); //will print the error to console if one occurs
        }
    }

    //login process for a manager
    public void managerLogin(){
        try{
            Stage Managerstage = new Stage(); //creates a new stage
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/manager/manager.fxml").openStream());

            ManagerController managerController = (ManagerController)loader.getController();
            Scene scene = new Scene(root);   //creating a new scene
            Managerstage.setScene(scene);
            Managerstage.setTitle("manager time");
            Managerstage.show();
        }
        catch (IOException exception)
        {
            exception.printStackTrace(); //will print the error to console if one occurs
        }
    }

}
