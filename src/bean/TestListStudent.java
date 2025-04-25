package bean;

public class TestListStudent {
    private String studentNo;
    private String subjectCd;
    private String classNum;
    private int point;
    private String subjectName;
    private int no;

    //  ゲッターとセッターを定義
    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getSubjectCd() {
        return subjectCd;
    }

    public void setSubjectCd(String subjectCd) {
        this.subjectCd = subjectCd;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getSubjectName(){
    	return subjectName;
    }

    public void setSubjectName(String subjectName){
    	this.subjectName = subjectName;
    }

    public int getNo(){
    	return no;
    }

    public void setNo(int no) {
    	this.no = no;
    }
}