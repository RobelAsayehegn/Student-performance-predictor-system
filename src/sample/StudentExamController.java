package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class StudentExamController implements Initializable {


    LoginModel loginModel = new LoginModel();
    LinkedList list = loginModel.FetchFromDatabase();
    int num = 0, score = 0;
    @FXML private AnchorPane exampane;
    @FXML private Label questionlabel;
    @FXML private Label labelscore;
    @FXML private RadioButton radioa;
    @FXML private RadioButton radiob;
    @FXML private RadioButton radioc;
    @FXML private RadioButton radiod;
    @FXML private Label statuslabel;
    @FXML private Label starts;
    @FXML private Label resultlabel;
    @FXML private Button start;
    @FXML private Button next;
    @FXML private TextField jfield;

    /*String id_num;
    public StudentExamController(String id_num) {
        this.id_num = id_num;
    }*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {}
    int gg = (int)(Math.random()*3);
    int hh = 0;
    public void Answer(ActionEvent actionEvent)throws IOException{
        try {

            if (radioa.isSelected() || radiob.isSelected() || radioc.isSelected()||radiod.isSelected()){
                if (radioa.isSelected()&& radioa.getText().equals(list.get(5+num))){
                    labelscore.setText(""+ ++score+"/10");
                    statuslabel.setText(" RIGHT ANSWER ");
                }
                else if (radiob.isSelected()&& radiob.getText().equals(list.get(5+num))){
                    labelscore.setText(""+ ++score+"/10");
                    statuslabel.setText(" RIGHT ANSWER ");
                }
                else if (radioc.isSelected()&& radioc.getText().equals(list.get(5+num))){
                    labelscore.setText(""+ ++score+"/10");
                    statuslabel.setText(" RIGHT ANSWER ");
                }
                else if (radiod.isSelected()&& radiod.getText().equals(list.get(5+num))){
                    labelscore.setText(""+ ++score+"/10");
                    statuslabel.setText(" RIGHT ANSWER ");
                }
                else statuslabel.setText(" WRONG ANSWER ");
                num+=6;
            }

        }catch (Exception e){ System.out.println(e);}
    }

    public void Start(ActionEvent actionEvent)throws IOException{
        try{
            if (gg == 0){hh = 0;}
            else if(gg == 1){hh = 60;}
            else if (gg == 2){hh = 120;}System.out.println(hh);
            questionlabel.setText((String) list.get(hh+0));
            radioa.setText((String) list.get(hh+1));
            radiob.setText((String) list.get(hh+2));
            radioc.setText((String) list.get(hh+3));
            radiod.setText((String) list.get(hh+4));
            num +=hh;
            labelscore.setText(""+ score);
            starts.setText("CLICK SUBMIT AFTER YOU ANSWER 10 QUESTIONS");

        }catch (Exception e){
            System.out.println("Error : "+e);
        }
    }

    public void NextQuestion()throws IOException {
        try {
            //num +=hh;
            questionlabel.setText((String) list.get(num));
            radioa.setText((String) list.get(1+num));
            radiob.setText((String) list.get(2+num));
            radioc.setText((String) list.get(3+num));
            radiod.setText((String) list.get(4+num));
            statuslabel.setText("");
            start.setDisable(true);//this helps us to disable the start button
            /*This will help us to limit the number of questions to 10*/
            if (radiod.getText().equals(list.get(hh + 58)) || radiod.getText().equals(list.get(118))||
                    radiod.getText().equals(list.get(178))){
                starts.setText("NOW CLICK SUBMIT IN ORDER TO PROCEED");
                next.setDisable(true);//this helps us to disable the next button
            }
        }catch (Exception ex){
            System.out.println("Error num 93 :"+ex);
        }
    }
    public void Submit(ActionEvent event){
        try{
            if (jfield.getText().equals("")){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Alert");
                alert.setContentText("you must enter id number.");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
            String sub = loginModel.Submission(score,Integer.parseInt(jfield.getText()));
            starts.setText(sub);
            questionlabel.setVisible(false);radiod.setVisible(false);radioc.setVisible(false);radiob.setVisible(false);
            radioa.setVisible(false);statuslabel.setVisible(false);

            double result = loginModel.ReturnScore();
            if (result > 90){resultlabel.setText(" YOU HAVE PERFORMED EXCELLENT RESULT WHICH IS : "+result+"%");}
            else if(result > 75){resultlabel.setText("YOU HAVE PERFORMED VERY GOOD RESULT WHICH IS : "+result+"%");}
            else if(result > 60){resultlabel.setText("YOU HAVE PERFORMED GOOD RESULT WHICH IS : "+result+"%");}
            else resultlabel.setText("YOU HAVEN'T PERFORMED WELL AND YOUR RESULT IS : "+result+"%");
        }catch (Exception ex){
            System.out.println("Error num 9 :"+ex);
        }
    }
    public void LoadHome(ActionEvent event)throws IOException{
        AnchorPane stpane = FXMLLoader.load(getClass().getResource("studentlogin.fxml"));
        exampane.getChildren().setAll(stpane);
    }

    public void BacktoStudentPage(ActionEvent event)throws IOException{
        AnchorPane stpagepane = FXMLLoader.load(getClass().getResource("studentpage.fxml"));
        exampane.getChildren().setAll(stpagepane);
    }
}
