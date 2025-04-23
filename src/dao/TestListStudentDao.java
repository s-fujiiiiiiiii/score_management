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

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, studentNo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                TestListStudent studentScore = new TestListStudent();
                studentScore.setStudentNo(studentNo);
                studentScore.setSubjectCd(rs.getString("SUBJECT_CD"));
                studentScore.setPoint(rs.getInt("POINT"));
                testList.add(studentScore);
            }

            rs.close();
            stmt.close();
            con.close();
        }
        return testList;
    }
}