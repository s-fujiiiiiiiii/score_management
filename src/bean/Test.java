package bean;

public class Test {
    private String studentNo;  // 学生番号
    private String subjectCd;  // 科目コード
    private String schoolCd;   // 学校コード
    private int no;            // テスト回数
    private int point;         // 得点
    private String classNum;   // クラス番号

    // コンストラクタ（デフォルト）
    public Test() {}

    // コンストラクタ（全フィールドを設定）
    public Test(String studentNo, String subjectCd, String schoolCd, int no, int point, String classNum) {
        this.studentNo = studentNo;
        this.subjectCd = subjectCd;
        this.schoolCd = schoolCd;
        this.no = no;
        this.point = point;
        this.classNum = classNum;
    }

    // ゲッターとセッター
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

    public String getSchoolCd() {
        return schoolCd;
    }

    public void setSchoolCd(String schoolCd) {
        this.schoolCd = schoolCd;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    // デバッグ用の toString メソッド
    @Override
    public String toString() {
        return "Test [studentNo=" + studentNo + ", subjectCd=" + subjectCd + ", schoolCd=" + schoolCd
                + ", no=" + no + ", point=" + point + ", classNum=" + classNum + "]";
    }
}