package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String USER = "databaseuser";
    private static final String SQLITE = "jdbc:sqlite:employee.sqlite";

    //start the connection here
    public static Connection connect() throws SQLException{

        try{
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(SQLITE);
        }
        catch(ClassNotFoundException ex){
            System.out.println("no");
            ex.printStackTrace();

        }

        return null;

    }

}
