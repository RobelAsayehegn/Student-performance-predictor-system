package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO
    }
    @FXML
    private AnchorPane rootpane;

    @FXML
    public void LoadAdmin(ActionEvent event)throws IOException{
        AnchorPane adpane = FXMLLoader.load(getClass().getResource("adminlogin.fxml"));
        rootpane.getChildren().setAll(adpane);
    }
    @FXML
    public void LoadStudent(ActionEvent event)throws IOException{
        try {
            AnchorPane stpane = FXMLLoader.load(getClass().getResource("studentlogin.fxml"));
            rootpane.getChildren().setAll(stpane);
        }catch (Exception e){
            System.out.println(e);
        }

    }
    @FXML
    public void LoadRegistration(ActionEvent event)throws IOException {
        AnchorPane regpane = FXMLLoader.load(getClass().getResource("registration.fxml"));
        rootpane.getChildren().setAll(regpane);
    }
}