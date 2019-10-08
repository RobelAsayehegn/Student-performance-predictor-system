package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentPageController implements Initializable {
    /*String id;
    public StudentPageController(String id){
        this.id = id;
    }*/
    @FXML
    private AnchorPane stpagepane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //StudentQuestionaryController stu = new StudentQuestionaryController(id);
        //StudentExamController st = new StudentExamController(id);
    }
    public void Exam(ActionEvent actionEvent) throws IOException {
        try {
            AnchorPane exampane = FXMLLoader.load(getClass().getResource("studentexam.fxml"));
            stpagepane.getChildren().setAll(exampane);

            Object a = new Object();
        }catch (Exception e) {
            System.out.println(e);
        }
    }
    public void LoadHome(ActionEvent actionEvent) throws IOException {
        AnchorPane stpane = FXMLLoader.load(getClass().getResource("studentlogin.fxml"));
        stpagepane.getChildren().setAll(stpane);
    }
    public void Questionaries(ActionEvent event)throws Exception{
        AnchorPane questionary = FXMLLoader.load(getClass().getResource("studentquestionary.fxml"));
        stpagepane.getChildren().setAll(questionary);

    }
}