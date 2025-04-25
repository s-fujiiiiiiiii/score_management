package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.TestListSubject;
import h2.DatabaseConnection;

public class TestListSubjectExecuteAction {
    public List<TestListSubject> execute(String subjectCd, String classNum, int entYear) throws Exception {
        String sql = "SELECT STUDENT_NO, NAME, CLASS_NUM, ENT_YEAR, NO, POINT " +
                     "FROM TEST " +
                     "JOIN STUDENT ON TEST.STUDENT_NO = STUDENT.ID " +
                     "WHERE SUBJECT_CD = ? AND CLASS_NUM = ? AND ENT_YEAR = ?";
        List<TestListSubject> results = new ArrayList<>();

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, subjectCd);
            stmt.setString(2, classNum);
            stmt.setInt(3, entYear);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TestListSubject subjectResult = new TestListSubject();
                subjectResult.setStudentNo(rs.getString("STUDENT_NO"));
                subjectResult.setName(rs.getString("NAME"));
                subjectResult.setClassNum(rs.getString("CLASS_NUM"));
                subjectResult.setEntYear(rs.getInt("ENT_YEAR"));
                subjectResult.setNo(rs.getInt("NO"));
                subjectResult.setPoint(rs.getInt("POINT"));
                results.add(subjectResult);
            }
        }
        return results;
    }
}