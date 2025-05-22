package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.TestListStudent;
import h2.DatabaseConnection;

public class TestListStudentExecuteAction {
    public List<TestListStudent> execute(String studentNo) throws Exception {
        String sql = "SELECT SUBJECT.NAME AS SUBJECT_NAME, SUBJECT.CD AS SUBJECT_CD, TEST.NO, TEST.POINT, " +
                     "STUDENT.ENT_YEAR, STUDENT.CLASS_NUM " +
                     "FROM TEST " +
                     "JOIN SUBJECT ON TEST.SUBJECT_CD = SUBJECT.CD " +
                     "JOIN STUDENT ON TEST.STUDENT_NO = STUDENT.NO " +
                     "WHERE TEST.STUDENT_NO = ?";
        List<TestListStudent> results = new ArrayList<>();

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, studentNo);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TestListStudent studentResult = new TestListStudent();
                studentResult.setSubjectName(rs.getString("SUBJECT_NAME"));
                studentResult.setSubjectCd(rs.getString("SUBJECT_CD"));
                studentResult.setNo(rs.getInt("NO"));
                studentResult.setPoint(rs.getInt("POINT"));
                studentResult.setEntYear(rs.getInt("ENT_YEAR"));
                studentResult.setClassNum(rs.getString("CLASS_NUM"));
                results.add(studentResult);
            }
        }
        return results;
    }
}
