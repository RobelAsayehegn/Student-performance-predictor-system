package sample;

import java.sql.Connection;
import java.sql.DriverManager;

public class Generic<T> implements The_Interface{
    T element;
    Generic(){}
    //here is the constractor
    Generic(T element){
        this.element = element;
    }

    public  <T extends String> T getLocation(){
        Generic generic = new Generic();
        return (T) generic.element;
    }
    @Override//overriding the method in the inteface
    public<T> T Connector(){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/data","root","");
            return (T) con;
        }catch (Exception ex){
            System.out.println("error : "+ex);
            return null;
        }
    }
}
