package sample;

public class TableModel {
    String id,question,a,b,c,d,answer;
    public TableModel(String id,String question,String a,String b,String c,String d,String answer){
        this.setId(id);
        this.setQuestion(question);
        this.setA(a);
        this.setB(b);
        this.setC(c);
        this.setD(d);
        this.setAnswer(answer);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
