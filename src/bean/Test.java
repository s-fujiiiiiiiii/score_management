package bean;

public class Test {
    private String studentNo;  // 学生番号
    private String subjectCd;  // 科目コード
    private String classNum;   // クラス番号
    private int no;            // 試験回数
    private int point;         // 得点
    private int entYear;       // 入学年度

    // Getter and Setter for 入学年度
    public int getEntYear() {
        return entYear;
    }

    public void setEntYear(int entYear) {
        this.entYear = entYear;
    }

    // Getter and Setter for 学生番号
    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    // Getter and Setter for 科目コード
    public String getSubjectCd() {
        return subjectCd;
    }

    public void setSubjectCd(String subjectCd) {
        this.subjectCd = subjectCd;
    }

    // Getter and Setter for クラス番号
    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    // Getter and Setter for 試験回数
    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    // Getter and Setter for 得点
    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}