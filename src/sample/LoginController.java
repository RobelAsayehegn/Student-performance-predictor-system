package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML private AnchorPane stpane;
    LoginModel loginModel = new LoginModel();
    @FXML private TextField studentid;
    @FXML private PasswordField studentpassword;
    @FXML private Label labelstudent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO
    }
    public LoginController(){
        //StudentPageController ss = new StudentPageController(studentid.getText());
    }
    @FXML
    public void Register(ActionEvent event)throws IOException {
        AnchorPane regpane = FXMLLoader.load(getClass().getResource("registration.fxml"));
        stpane.getChildren().setAll(regpane);
    }

    public void Login(ActionEvent actionEvent) {
        try {
            String idnum = studentid.getText();
            String pass = studentpassword.getText();
            String roll = loginModel.isLoginStudent(idnum,pass);
            if (idnum.equals("") || pass.equals("")){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Alert");
                alert.setContentText("Id or password is empty");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
            else if (roll.equals("student")){
                AnchorPane stpagepane = FXMLLoader.load(getClass().getResource("studentpage.fxml"));
                stpane.getChildren().setAll(stpagepane);
            }else if ( roll.equals("teacher")){
                AnchorPane adpagepane = FXMLLoader.load(getClass().getResource("adminpage.fxml"));
                stpane.getChildren().setAll(adpagepane);
            }
            else{
                labelstudent.setText("PASSWORD OR ID IS WRONG");
            }
        }catch (Exception ex){
            System.out.println("Error num 4 : "+ex);
            labelstudent.setText("PASSWORD OR ID IS WRONG");
        }

    }
}
