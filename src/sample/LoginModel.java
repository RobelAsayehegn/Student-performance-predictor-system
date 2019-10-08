package sample;

import java.sql.*;
import java.util.LinkedList;

public class LoginModel {
    Connection con;
    public LoginModel(){
        con = SqlConnection.Connector();
    }

    public Boolean isDbConnected(){
        try{
            return !con.isClosed();
        }catch (SQLException ex){
            System.out.println("Error num 2q3 : " +ex);
            return false;
        }
    }

    public Boolean isLoginAdmin(String id, String pass){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String Query = "select * from instractor where ins_id = ? and password = ?";
        try {
            preparedStatement = con.prepareStatement(Query);
            preparedStatement.setString(1,id);
            preparedStatement.setString(2,pass);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return true;
            else return false;
        }catch (Exception ex){
            System.out.println("Error num 3 : "+ex);
            return false;
        }
    }

    public String isLoginStudent(String id, String pass){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String Query = "select * from student where stu_id = ? and password = ?";
        try {
            preparedStatement = con.prepareStatement(Query);
            preparedStatement.setString(1,id);
            preparedStatement.setString(2,pass);
            resultSet = preparedStatement.executeQuery();
            String roll = "";
            while (resultSet.next()){
                roll = resultSet.getString("roll");
            }
            return roll;
        }catch (Exception ex){
            System.out.println("Error num 4 : "+ex);
            return null;
        }
    }

    public Boolean RegisterStudent(String fname,String lname,String gender,String gpa, String pass){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String Query = "insert into student (f_name,l_name,password,gender,gpa) values (?,?,?,?,?)";
        try {
            preparedStatement = con.prepareStatement(Query);
            preparedStatement.setString(1,fname);
            preparedStatement.setString(2,lname);
            preparedStatement.setString(3,pass);
            preparedStatement.setString(4,gender);
            preparedStatement.setString(5,gpa);
            int RowAffected = preparedStatement.executeUpdate();
            if (RowAffected > 0)
                return true;
            else return false;
        }catch (Exception ex){
            System.out.println("Error num 6 : "+ex);
            return false;
        }
    }
    public int getId(){
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = con.prepareStatement("select * from student");
            rs = statement.executeQuery("select stu_id from student");
            int id = 0;
            while (rs.next()) {
                id = rs.getInt("Stu_id");
            }return id;
        }catch (Exception ex){
            System.out.println("Error num 7: "+ex);
        }return 0;
    }

    public LinkedList FetchFromDatabase(){
        LinkedList list = new LinkedList();
        Statement statement = null;
        ResultSet rs = null;
        String Query = "SELECT * FROM exam";
        try {
            statement = con.createStatement();
            //int row = statement.executeUpdate();System.out.println(row);
            rs = statement.executeQuery(Query);
            while (rs.next()){
                String Question = rs.getString("question");
                String a = rs.getString("choice_a");
                String b = rs.getString("choice_b");
                String c = rs.getString("choice_c");
                String d = rs.getString("choice_d");
                String ans = rs.getString("answer");
                list.add(Question);list.add(a);list.add(b);list.add(c);list.add(d);list.add(ans);
            }return list;
        }catch (Exception ex){System.out.println("here is the problem");
            System.out.println("Error num 8 :"+ex);
            return null;
        }
    }

    public String setquestionary(String part,String attend,String hour,String id){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String Query = "insert into questionary (id,participation,study_hour_in_aday,class_attend) values (?,?,?,?)";
        try {
            preparedStatement = con.prepareStatement(Query);
            preparedStatement.setInt(1,Integer.parseInt(id));
            preparedStatement.setString(3,hour);
            preparedStatement.setString(2,part);
            preparedStatement.setString(4,attend);
            int RowAffected = preparedStatement.executeUpdate();
            if (RowAffected > 0)
                return " SUBMITTED ";
            else return "NOT SUBMITTED";
        }catch (Exception ex){
            System.out.println("Error num 6 : "+ex);
            return "NOT SUBMITTED";
        }
    }
    public void setquestionary(int point1,int point2,double hour,String id){
        PreparedStatement preparedStatement ;
        //ResultSet resultSet = null;
        int idd = Integer.parseInt(id);
        String Query = "UPDATE student SET score_in_questionaries = ? WHERE Stu_id = ?";
        try {double score = ((point1+point2+hour)/15.0)*100;
            preparedStatement = con.prepareStatement(Query);
            preparedStatement.setDouble(1,score);
            preparedStatement.setInt(2,idd);
            int RowAffected = preparedStatement.executeUpdate();
        }catch (Exception ex){
            System.out.println("Error num 6 : "+ex);
        }
    }

    public String Submission(int score,int id){
        PreparedStatement preparedStatement ;
        //ResultSet resultSet = null;
        String Query = "UPDATE student SET score_in_exam = ? WHERE Stu_id = ?";
        try {double percent = ((score)/10.0)*100;
            preparedStatement = con.prepareStatement(Query);
            preparedStatement.setDouble(1,percent);
            preparedStatement.setInt(2,id);
            int RowAffected = preparedStatement.executeUpdate();
            if (RowAffected>0){

                return retriveScore(id);
            }

            else return retriveScore(id);
        }catch (Exception ex){
            System.out.println("Error num 6 : "+ex);
            return retriveScore(id);
        }
    }
    double exam_score=0,que_score=0;
    public String retriveScore(int idnum){

        try {
            PreparedStatement st = con.prepareStatement("select * from student where stu_id = ?");
            st.setInt(1,idnum);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                exam_score= rs.getDouble("score_in_exam");
                que_score = rs.getDouble("score_in_questionaries");
            }ReturnScore();
            if (totalScore(idnum,exam_score,que_score).equals("SUBMITTED")) {
                return "SUBMITTED";
            }

        } catch (SQLException e) {
            System.out.println(e);
            return "NOT SUBMITTED";
        }return "NOT SUBMITTED";
    }
    public double ReturnScore(){
        double result = exam_score*0.5 + que_score*0.5;
        return result;
    }
    public String totalScore(int id,double exam_score,double que_score){
        PreparedStatement preparedStatement ;
        String Query = "UPDATE student SET total_score = ((?*0.5)+(?*0.5)) WHERE Stu_id = ?";
        try {
            preparedStatement = con.prepareStatement(Query);
            preparedStatement.setInt(3,id);
            preparedStatement.setDouble(2,exam_score);
            preparedStatement.setDouble(1,que_score);
            int RowAffected = preparedStatement.executeUpdate();
            if (RowAffected>0){
                return "SUBMITTED";
            }
            else return "NOT SUBMITTED";
        }catch (Exception ex){
            System.out.println("Error num 6 : "+ex);
        }return "NOT SUBMITTED";
    }

    public String EnterQuestion(String q,String a,String b,String c,String d,String answer){
        PreparedStatement preparedStatement ;
        //ResultSet resultSet = null;
        String Query = "insert into exam (question,choice_a,choice_b,choice_c,choice_d,answer) values (?,?,?,?,?,?)";
        try {
            preparedStatement = con.prepareStatement(Query);
            preparedStatement.setString(1,q);
            preparedStatement.setString(2,a);
            preparedStatement.setString(3,b);
            preparedStatement.setString(4,c);
            preparedStatement.setString(5,d);
            preparedStatement.setString(6,answer);
            int RowAffected = preparedStatement.executeUpdate();
            if (RowAffected > 0)
                return "SUBMISSION SUCCESS";
            else return "SUBMISSION NOT SUCCESS";
        }catch (Exception ex){
            System.out.println("Error num 6 : "+ex);
            return "SUBMISSION NOT SUCCESS";
        }
    }

    public String UpdateQuestion(String q,String a,String b,String c,String d,String answer){
        PreparedStatement preparedStatement ;
        //ResultSet resultSet = null;
        String Query = "update exam set question = ?,choice_a = ?,choice_b = ?,choice_c = ?,choice_d = ?,answer = ? WHERE id = ?";
        try {
            preparedStatement = con.prepareStatement(Query);
            preparedStatement.setString(1,q);
            preparedStatement.setString(2,a);
            preparedStatement.setString(3,b);
            preparedStatement.setString(4,c);
            preparedStatement.setString(5,d);
            preparedStatement.setString(6,answer);
            int RowAffected = preparedStatement.executeUpdate();
            if (RowAffected > 0)
                return "UPDATED SUCCESSFULLY";
            else return "UPDATE NOT SUCCESSFUL";
        }catch (Exception ex){
            System.out.println("Error num 6 : "+ex);
            return "SUBMISSION NOT SUCCESS";
        }
    }
}
