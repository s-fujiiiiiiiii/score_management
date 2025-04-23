package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.TestListStudent;

public class TestListStudentDao extends Dao { // Dao を継承
    public List<TestListStudent> findByStudentNo(String studentNo) throws Exception {
        Connection con = getConnection();
        String sql = "SELECT SUBJECT_CD, POINT FROM TEST WHERE STUDENT_NO = ?";
        List<TestListStudent> testList = new ArrayList<>();
        System.out.println("初期化した空リスト: " + testList);

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, studentNo);
            System.out.println("実行するSQL: " + sql + ", パラメータ: " + studentNo); // SQL確認用ログ

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                TestListStudent studentScore = new TestListStudent();
                studentScore.setStudentNo(studentNo);
                studentScore.setSubjectCd(rs.getString("SUBJECT_CD"));
                studentScore.setPoint(rs.getInt("POINT"));
                testList.add(studentScore);

                System.out.println("新規追加される成績データ: " + studentScore); // 追加確認ログ
            }
            System.out.println("取得した成績: " + testList); // クエリ結果確認ログ

            rs.close();
            stmt.close();
            con.close();
        }
        return testList;
    }
}