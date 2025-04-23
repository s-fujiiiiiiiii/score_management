package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import bean.TestListSubject;

public class TestListSubjectDao extends Dao {
    public TestListSubject findBySubjectCd(String subjectCd) throws Exception {
        Connection con = getConnection();
        String sql = "SELECT STUDENT_NO, POINT FROM TEST WHERE SUBJECT_CD = ?";
        TestListSubject subject = new TestListSubject();
        subject.setSubjectCd(subjectCd);
        subject.setPoints(new HashMap<>()); // Map の初期化

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, subjectCd);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // 学生番号を Integer に変換して挿入
                subject.getPoints().put(Integer.parseInt(rs.getString("STUDENT_NO")), rs.getInt("POINT"));
            }
        }

        return subject;
    }
}