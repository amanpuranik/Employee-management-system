package employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import dbUtil.Database;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    @FXML
    private TextField idNumber;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField email;
    @FXML
    private DatePicker dob;

    @FXML
    private TableView<EmployeeData> employeeTable;

    @FXML
    private TableColumn<EmployeeData,String> idcolumn;
    @FXML
    private TableColumn<EmployeeData,String> firstnamecolumn;
    @FXML
    private TableColumn<EmployeeData,String> lastnamecolumn;
    @FXML
    private TableColumn<EmployeeData,String> emailcolumn;
    @FXML
    private TableColumn<EmployeeData,String> birthdaycolumn;

    private Database database;
    private ObservableList<EmployeeData> data;



    public void initialize(URL url, ResourceBundle rb){

        this.database = new Database();

    }

    @FXML
    private void loadEmployeeData(ActionEvent event){
        try{
            Connection connection = Database.connect();
            this.data = FXCollections.observableArrayList();

            //Typing query
            ResultSet resultSet = connection.createStatement().executeQuery("Select * From employee");
            //checks if there is a next value
            while (resultSet.next()){
                this.data.add(new EmployeeData(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3), resultSet.getString(4),resultSet.getString(4)));
            }
        }

        catch(SQLException sqlException){
            sqlException.printStackTrace();

        }
        //need to display data to table
        this.idcolumn.setCellValueFactory(new PropertyValueFactory<EmployeeData,String>("ID"));
        this.firstnamecolumn.setCellValueFactory(new PropertyValueFactory<EmployeeData,String>("firstName"));
        this.lastnamecolumn.setCellValueFactory(new PropertyValueFactory<EmployeeData,String>("lastName"));
        this.emailcolumn.setCellValueFactory(new PropertyValueFactory<EmployeeData,String>("email"));

        this.employeeTable.setItems(null);
        this.employeeTable.setItems(this.data);
    }

    //add data to the DB

    @FXML
    public void addEmployee(javafx.event.ActionEvent event){
        String sqlInsert = "INSERT INTO employee(id,fname,lname,email) VALUES(?,?,?,?)";

        try{
            Connection connection = Database.connect();
            PreparedStatement statement= connection.prepareStatement(sqlInsert);

            statement.setString(1,this.idNumber.getText());
            statement.setString(2,this.firstname.getText());
            statement.setString(3,this.lastname.getText());
            statement.setString(4,this.email.getText());

            statement.execute();
            connection.close();


        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    public void clear(javafx.event.ActionEvent actionEvent){
        this.idNumber.setText("");
         this.firstname.setText("");
        this.lastname.setText("");
        this.email.setText("");
    }



}
