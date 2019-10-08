package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class ImportFile implements Initializable {
    @FXML private ProgressBar progress;
    @FXML private AnchorPane importpane;
    @FXML private Label label;

    class bg_thread implements Runnable{
        @Override
        public void run() {
            int i = 0;
            try {
                for (i = 0 ; i<=100 ; i++){
                    progress.setProgress(i/100.0);
                    Thread.sleep(20);
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }
    public void LoadHome(ActionEvent event)throws IOException {
        AnchorPane stpane = FXMLLoader.load(getClass().getResource("studentlogin.fxml"));
        importpane.getChildren().setAll(stpane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        progress.setProgress(0.0);
    }
    public void start(){
        Thread thread = new Thread(new bg_thread());
        thread.start();
    }
    String file = null;
    public void ChooseFile(ActionEvent actionEvent){
        FileChooser ff = new FileChooser();
        ff.setInitialDirectory(new File("C:\\Users\\Roba\\Downloads"));
        ff.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("csv files","*.csv"));
        File fil = ff.showOpenDialog(null);
        file = fil.getAbsolutePath();
        label.setText(file);
        Generic<String> generic= new Generic<>(file);
    }
    //and this one will import it in to the database
    public void Import(ActionEvent actionEvent){
        start();//starting the progress
        try {
            int i = 0;
            try {
                Generic<Connection> generic = new Generic<>();
                Connection con = generic.Connector();

                BufferedReader in = new BufferedReader(new FileReader(file));
                String line = null;
                while ((line = in.readLine())!=null){
                    String[] arr = line.split(",");//spliting the line in to array
                    //here is the query to insert in to database
                    String SQL = "insert into try(f_name,l_name,password,gender,gpa)" +
                            " values ('"+arr[0]+"','"+arr[1]+"','"+arr[2]+"','"+arr[3]+"',"+arr[4]+")";
                    PreparedStatement pr = con.prepareStatement(SQL);
                    pr.executeUpdate();//executing the sql
                    label.setText(line);
                    System.out.println(++i);
                }
                in.close();//closing the file
                /*
                 * this alert box will show us the number of students entered
                 * */
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("message");
                alert.setContentText("FILE ATACHMENT COMPLETED."+i+" Students Successfully\n" +
                        "Entered");
                alert.setHeaderText("Amount Of Student Entered");
                alert.showAndWait();
            }catch (Exception e){
                System.out.println("1--"+e);
            }
        }catch (Exception e){
            System.out.println("1--"+e);
        }
    }
}
