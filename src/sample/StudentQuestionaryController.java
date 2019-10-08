package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentQuestionaryController implements Initializable {


    LoginController studentController=new LoginController();
    @FXML private AnchorPane questionary;
    @FXML private RadioButton active;
    @FXML private RadioButton medium;
    @FXML private RadioButton low;
    @FXML private RadioButton always;
    @FXML private RadioButton sometimes;
    @FXML private RadioButton none;
    @FXML private TextField study;
    @FXML private TextField id;
    @FXML private Label Display;
    @FXML private Button exam;


    int rob;
    /*String id_num;
    public StudentQuestionaryController(String id){
        this.id_num = id;
    }
    public void setid(String idd) {
        final int roba = Integer.parseInt(idd);
        System.out.println(roba);
    }*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exam.setDisable(true);
    }
    LoginModel loginModel = new LoginModel();
    public void Submit(ActionEvent actionEvent) throws IOException{
        exam.setDisable(false);
        String participation;
        int point1,point2;
        Double point3;
        if (active.isSelected()) {point1 = 5;
            participation = active.getText();
        } else if (medium.isSelected()) {point1 = 4;
            participation = medium.getText();
        } else {point1 = 2;
            participation = low.getText();
        }
        String attendance;
        if (always.isSelected()) {point2 = 5;
            attendance = active.getText();
        } else if (sometimes.isSelected()) {point2 = 4;
            attendance = medium.getText();
        } else {point2 = 2;
            attendance = low.getText();
        }
        if (participation.equals("")||attendance.equals("")||study.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setContentText("you must fill all the questionnaires.");
            alert.setHeaderText(null);
            alert.showAndWait();
        }float time = 0;
        try{
             time = Float.parseFloat(study.getText());
             if (time>5) point3 = 5.0;
            else if (time>3) point3 = 3.5;
            else point3 = 2.0;

            String display = loginModel.setquestionary(participation,attendance,study.getText(),id.getText());
            Display.setText(display);
            loginModel.setquestionary(point1,point2,point3,id.getText());
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void Exam(ActionEvent event)throws IOException{
        AnchorPane exampane = FXMLLoader.load(getClass().getResource("studentexam.fxml"));
        questionary.getChildren().setAll(exampane);
    }
    public void BacktoStudentPage(ActionEvent event)throws IOException{
        AnchorPane stpagepane = FXMLLoader.load(getClass().getResource("studentpage.fxml"));
        questionary.getChildren().setAll(stpagepane);
    }
    public void LoadHome(ActionEvent event)throws IOException{
        AnchorPane stpane = FXMLLoader.load(getClass().getResource("studentlogin.fxml"));
        questionary.getChildren().setAll(stpane);
    }
}