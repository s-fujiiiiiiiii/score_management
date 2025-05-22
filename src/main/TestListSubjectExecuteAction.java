package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.TestListSubject;
import h2.DatabaseConnection;

public class TestListSubjectExecuteAction {
    public List<TestListSubject> execute(String subjectCd, String classNum, int entYear) throws Exception {
        String sql = "SELECT t.STUDENT_NO, s.NAME, s.CLASS_NUM, s.ENT_YEAR, t.NO, t.POINT " +
                     "FROM TEST t " +
                     "JOIN STUDENT s ON t.STUDENT_NO = s.NO " +
                     "WHERE t.SUBJECT_CD = ? AND s.CLASS_NUM = ? AND s.ENT_YEAR = ? " +
                     "ORDER BY t.STUDENT_NO, t.NO";
        List<TestListSubject> results = new ArrayList<>();

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, subjectCd);
            stmt.setString(2, classNum);
            stmt.setInt(3, entYear);

            ResultSet rs = stmt.executeQuery();
            Map<String, TestListSubject> studentMap = new HashMap<>();

            while (rs.next()) {
                String studentNo = rs.getString("STUDENT_NO");
                int testNo = rs.getInt("NO");
                int point = rs.getInt("POINT");

                TestListSubject subjectResult = studentMap.get(studentNo);
                if (subjectResult == null) {
                    subjectResult = new TestListSubject();
                    subjectResult.setStudentNo(studentNo);
                    subjectResult.setName(rs.getString("NAME"));
                    subjectResult.setClassNum(rs.getString("CLASS_NUM"));
                    subjectResult.setEntYear(rs.getInt("ENT_YEAR"));
                    subjectResult.setPoints(new HashMap<>());
                    studentMap.put(studentNo, subjectResult);
                    results.add(subjectResult);
                }

                subjectResult.getPoints().put(testNo, point);
            }
        }
        return results;
    }
}
