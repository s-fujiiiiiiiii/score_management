package bean;

import java.util.Map;

public class TestListSubject {
    private String subjectCd;            // 科目コード
    private String subjectName;          // 科目名
    private Map<Integer, Integer> points; // 得点データ (キー: 学生番号, 値: 得点)

    // コンストラクタ
    public TestListSubject() {}

    public TestListSubject(String subjectCd, String subjectName, Map<Integer, Integer> points) {
        this.subjectCd = subjectCd;
        this.subjectName = subjectName;
        this.points = points;
    }

    // ゲッターとセッター
    public String getSubjectCd() {
        return subjectCd;
    }

    public void setSubjectCd(String subjectCd) {
        this.subjectCd = subjectCd;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Map<Integer, Integer> getPoints() {
        return points;
    }

    public void setPoints(Map<Integer, Integer> points) {
        this.points = points;
    }

    // デバッグ用 toString()
    @Override
    public String toString() {
        return "TestListSubject [subjectCd=" + subjectCd + ", subjectName=" + subjectName + ", points=" + points + "]";
    }
}