package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.TestListStudent;

public class TestListSubjectDao extends Dao {
    public List<TestListStudent> findBySubjectCdClassNumAndEntYear(String subjectCd, String classNum, int entYear) throws Exception {
        Connection con = getConnection();
        String sql = "SELECT STUDENT_NO, POINT FROM TEST WHERE SUBJECT_CD = ? AND CLASS_NUM = ? AND STUDENT_NO IN (SELECT STUDENT_NO FROM STUDENT WHERE ENT_YEAR = ?)";
        List<TestListStudent> testList = new ArrayList<>();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, subjectCd);
            stmt.setString(2, classNum);
            stmt.setInt(3, entYear);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                TestListStudent studentScore = new TestListStudent();
                studentScore.setStudentNo(rs.getString("STUDENT_NO"));
                studentScore.setSubjectCd(subjectCd);
                studentScore.setClassNum(classNum);
                studentScore.setPoint(rs.getInt("POINT"));
                testList.add(studentScore);
            }
        }
        return testList;
    }
}