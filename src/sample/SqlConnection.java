package sample;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqlConnection {
    public static Connection Connector(){
        try {//this helps us to get the connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data","root","");
            return con;
        }catch (Exception ex){
            System.out.println("error : "+ex);
            return null;
        }
    }
}
