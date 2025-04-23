package bean;

import java.io.Serializable;

public class TestListStudent implements Serializable {
    private String studentNo;
    private String studentName;
    private String subjectCd;
    private int point;

    // ゲッターとセッター
    public String getStudentNo() { return studentNo; }
    public void setStudentNo(String studentNo) { this.studentNo = studentNo; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getSubjectCd() { return subjectCd; }
    public void setSubjectCd(String subjectCd) { this.subjectCd = subjectCd; }

    public int getPoint() { return point; }
    public void setPoint(int point) { this.point = point; }
}