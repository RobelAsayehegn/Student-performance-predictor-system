package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.*;

public class AdminPageController implements Initializable{
    LoginModel loginModel = new LoginModel();
    @FXML private AnchorPane adpagepane;
    @FXML private TextField a;
    @FXML private TextArea question;
    @FXML private TextField b;
    @FXML private TextField answer;
    @FXML private TextField d;
    @FXML private TextField c;
    @FXML private Label label1;
    @FXML private TableView<TableModel> table;
    @FXML private TableColumn<TableModel,String> col_id;
    @FXML private TableColumn<TableModel,String> col_question;
    @FXML private TableColumn<TableModel,String> col_a;
    @FXML private TableColumn<TableModel,String> col_b;
    @FXML private TableColumn<TableModel,String> col_c;
    @FXML private TableColumn<TableModel,String> col_d;
    @FXML private TableColumn<TableModel,String> col_answer;


    public void changequestionCellEvent(TableColumn.CellEditEvent event){
        try {
            TableModel tableModel = table.getSelectionModel().getSelectedItem();
            tableModel.setQuestion(event.getNewValue().toString());
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @FXML
    void New(ActionEvent event) throws IOException {
        try {
            AnchorPane adpagepan = load(getClass().getResource("adminpage.fxml"));
            adpagepane.getChildren().setAll(adpagepan);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    @FXML
    void LoadHome(ActionEvent event) throws IOException {
        AnchorPane stpane = load(getClass().getResource("studentlogin.fxml"));
        adpagepane.getChildren().setAll(stpane);
    }
    @FXML
    void Submit(ActionEvent event) throws IOException {
        String stutes = "";
        if (question.getText().equals("")||a.getText().equals("")||b.getText().equals("")||
                c.getText().equals("")||d.getText().equals("")||answer.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setContentText("you must fill all the entities.");
            alert.setHeaderText(null);
            alert.showAndWait();
        }else {
            stutes = loginModel.EnterQuestion(question.getText(),a.getText(),b.getText(),c.getText(),d.getText(),answer.getText());
        }

        label1.setText(stutes);
        AnchorPane adpagepan = load(getClass().getResource("adminpage.fxml"));
        adpagepane.getChildren().setAll(adpagepan);
    }
    ObservableList list = FXCollections.observableArrayList();
    @FXML
    void Update(ActionEvent event) {
        ObservableList<TableModel> allData,selectedData;
        allData = table.getItems();
        selectedData = table.getSelectionModel().getSelectedItems();
        for (TableModel tableModel : selectedData){
            String id = String.valueOf(allData.get(0));
            String ques = allData.get(1).toString();
            String a1 = String.valueOf(allData.get(2));
            String b1 = String.valueOf(allData.get(3));
            String c1 = String.valueOf(allData.get(4));
            String d1 = String.valueOf(allData.get(5));
            String ans = String.valueOf(allData.get(6));
            System.out.println(id);
            question.setText(ques);a.setText(a1);b.setText(b1);c.setText(c1);d.setText(d1);answer.setText(ans);
        }
        /*String stutes = loginModel.UpdateQuestion(question.getText(),a.getText(),b.getText(),c.getText(),d.getText(),answer.getText());
        label1.setText(stutes);*/
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connection con = SqlConnection.Connector();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from exam");
            while (rs.next()){
                list.add(new TableModel(rs.getString("id"),rs.getString("question"),
                        rs.getString("choice_a"),rs.getString("choice_b"),rs.getString("choice_c"),
                        rs.getString("choice_d"),rs.getString("answer")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_question.setCellValueFactory(new PropertyValueFactory<>("question"));
        col_a.setCellValueFactory(new PropertyValueFactory<>("a"));
        col_b.setCellValueFactory(new PropertyValueFactory<>("b"));
        col_c.setCellValueFactory(new PropertyValueFactory<>("c"));
        col_d.setCellValueFactory(new PropertyValueFactory<>("d"));
        col_answer.setCellValueFactory(new PropertyValueFactory<>("answer"));
        table.setItems(list);
        table.setEditable(true);
        col_id.setCellFactory(TextFieldTableCell.forTableColumn());
        col_question.setCellFactory(TextFieldTableCell.forTableColumn());
        col_a.setCellFactory(TextFieldTableCell.forTableColumn());
        col_b.setCellFactory(TextFieldTableCell.forTableColumn());
        col_c.setCellFactory(TextFieldTableCell.forTableColumn());
        col_d.setCellFactory(TextFieldTableCell.forTableColumn());
        col_answer.setCellFactory(TextFieldTableCell.forTableColumn());
    }
}
