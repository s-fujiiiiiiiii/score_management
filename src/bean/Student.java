package bean;

import java.io.Serializable;

public class Student implements Serializable {
    private int entYear; // 入学年度（ENT_YEAR）
    private String studentNumber; // 学生番号（NO）
    private String name; // 氏名（NAME）
    private String classNum; // クラス番号（CLASS_NUM）
    private String schoolCd; // 学校コード（SCHOOL_CD）
    private boolean isAttend; // 出席状況（IS_ATTEND）

    // デフォルトコンストラクタ
    public Student() {}

    // パラメータ付きコンストラクタ
    public Student(int entYear, String studentNumber, String name, String classNum, String schoolCd, boolean isAttend) {
        this.entYear = entYear;
        this.studentNumber = studentNumber;
        this.name = name;
        this.classNum = classNum;
        this.schoolCd = schoolCd;
        this.isAttend = isAttend;
    }

    // GetterとSetterメソッド
    public int getEntYear() { return entYear; }
    public void setEntYear(int entYear) { this.entYear = entYear; }

    public String getStudentNumber() { return studentNumber; }
    public void setStudentNumber(String studentNumber) { this.studentNumber = studentNumber; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getClassNum() { return classNum; }
    public void setClassNum(String classNum) { this.classNum = classNum; }

    public String getSchoolCd() { return schoolCd; }
    public void setSchoolCd(String schoolCd) { this.schoolCd = schoolCd; }

    public boolean isAttend() { return isAttend; }
    public void setAttend(boolean isAttend) { this.isAttend = isAttend; }

    // toStringメソッド
    @Override
    public String toString() {
        return "Student{" +
               "entYear=" + entYear +
               ", studentNumber='" + studentNumber + '\'' +
               ", name='" + name + '\'' +
               ", classNum='" + classNum + '\'' +
               ", schoolCd='" + schoolCd + '\'' +
               ", isAttend=" + isAttend +
               '}';
    }
}