package bean;

import java.io.Serializable;

public class Student implements Serializable {
    private int enrollmentYear; // 入学年度
    private String studentNumber; // 学生番号
    private String name; // 氏名
    private String className; // クラス名
    private int score; // 得点
    private boolean isEnrolled; // 在学状況

    // コンストラクタ
    public Student() {}

    public Student(int enrollmentYear, String studentNumber, String name, String className, int score, boolean isEnrolled) {
        this.enrollmentYear = enrollmentYear;
        this.studentNumber = studentNumber;
        this.name = name;
        this.className = className;
        this.score = score;
        this.isEnrolled = isEnrolled;
    }

    // GetterとSetterメソッド
    public int getEnrollmentYear() { return enrollmentYear; }
    public void setEnrollmentYear(int enrollmentYear) { this.enrollmentYear = enrollmentYear; }

    public String getStudentNumber() { return studentNumber; }
    public void setStudentNumber(String studentNumber) { this.studentNumber = studentNumber; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public boolean isEnrolled() { return isEnrolled; }
    public void setEnrolled(boolean isEnrolled) { this.isEnrolled = isEnrolled; }

    @Override
    public String toString() {
        return "Student{" +
               "enrollmentYear=" + enrollmentYear +
               ", studentNumber='" + studentNumber + '\'' +
               ", name='" + name + '\'' +
               ", className='" + className + '\'' +
               ", score=" + score +
               ", isEnrolled=" + isEnrolled +
               '}';
    }
}