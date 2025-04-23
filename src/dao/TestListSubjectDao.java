package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import bean.TestListSubject;

public class TestListSubjectDao extends Dao { // Dao を継承
    public TestListSubject findBySubjectCd(String subjectCd) throws Exception {
        Connection con = getConnection();
        String sql = "SELECT STUDENT_NO, POINT FROM TEST WHERE SUBJECT_CD = ?";
        TestListSubject subject = new TestListSubject();
        subject.setSubjectCd(subjectCd);
        subject.setPoints(new HashMap<>());

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, subjectCd);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                subject.getPoints().put(rs.getString("STUDENT_NO"), rs.getInt("POINT"));
            }

            rs.close();
            stmt.close();
            con.close();
        }
        return subject;
    }
}