package loginapp;

import dbUtil.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Loginmodel {

    Connection connection;

    public Loginmodel(){

        //this here will check if code is connected to my dbUtil.database
        try{
            this.connection = Database.connect();
        }
        catch(SQLException exception){
            exception.printStackTrace();
        }

        if(this.connection == null){
            System.exit(1);
        }
    }

    // Will return true if the DB is properly connected
    public boolean isDBconnected(){
        System.out.println("database is connected");
        return this.connection != null;
    }

    //Will see if login process is working fine
    public boolean isLogin(String user, String pass, String opt) throws Exception{


        PreparedStatement pr = null;
        ResultSet rs = null;

        String sqlquery = "SELECT * FROM login where username = ? and password = ? and division = ?";

        // this try statement checks if the login works fine by passing user string as the username
        try{
            pr = this.connection.prepareStatement(sqlquery); //will pass the query through
            pr.setString(1,user);
            pr.setString(2,pass);
            pr.setString(3,opt);

            rs = pr.executeQuery(); // executers the sqlquery

            boolean bool1;

            if(rs.next()){
                return true;
            }
            return false;
        }
        catch(SQLException exception){
            exception.printStackTrace();
            System.out.println("sql error");
            return false;

        }
        // anytime a connection to a db is opened, it must be closed after
        finally{
            {
                pr.close();
                rs.close();
            }
        }

    }

}
