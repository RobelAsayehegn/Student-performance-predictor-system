package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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

public class RegistrationController implements Initializable {
    LoginModel loginModel = new LoginModel();
    @FXML private AnchorPane regpane;
    @FXML private TextField studentfname;
    @FXML private TextField studentlname;
    @FXML private RadioButton male;
    @FXML private RadioButton female;
    @FXML private TextField studentgpa;
    @FXML private PasswordField studentpassword;
    @FXML private Label registrationlabel;
    @FXML private Label idlabel;
    @FXML private Button choose;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO
    }
    public void Choosefile(ActionEvent actionEvent)throws Exception{
        AnchorPane importpane = FXMLLoader.load(getClass().getResource("importfile.fxml"));
        regpane.getChildren().setAll(importpane);
    }
    @FXML
    public void LoadSample(ActionEvent event)throws IOException {
        AnchorPane stpane = FXMLLoader.load(getClass().getResource("studentlogin.fxml"));
        regpane.getChildren().setAll(stpane);
    }
    public void Register(ActionEvent actionEvent) throws IOException{
        String studentgender;
        if (male.isSelected()) studentgender = male.getText();
        else studentgender = female.getText();
        try {
            if (studentfname.getText().equals("")||studentlname.getText().equals("")||studentgender.equals("")||
                    studentgpa.getText().equals("")||studentpassword.getText().equals("")){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Alert");
                alert.setContentText("you must fill all the entities to register.");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
            else if (loginModel.isDbConnected() && loginModel.RegisterStudent(studentfname.getText(),studentlname.getText(),
                    studentgender,studentgpa.getText(),studentpassword.getText())){
                registrationlabel.setText("YOU ARE SUCCESSFULLY REGISTERED");
                idlabel.setText("Your Id is : "+loginModel.getId());
            }else registrationlabel.setText("YOU ARE NOT REGISTERED");
        }catch (Exception ex){
            registrationlabel.setText("YOU ARE NOT SUCCESSFULLY REGISTERED");
            System.out.println("Error num 5 : "+ex);
        }
    }
}
