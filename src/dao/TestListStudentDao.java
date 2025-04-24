package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.TestListStudent;

public class TestListStudentDao extends Dao {
    public List<TestListStudent> findByStudentNo(String studentNo) throws Exception {
        Connection con = getConnection();
        String sql = "SELECT SUBJECT_CD, CLASS_NUM, POINT FROM TEST WHERE STUDENT_NO = ?";
        List<TestListStudent> testList = new ArrayList<>();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, studentNo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                TestListStudent studentScore = new TestListStudent();
                studentScore.setStudentNo(studentNo);
                studentScore.setSubjectCd(rs.getString("SUBJECT_CD"));
                studentScore.setClassNum(rs.getString("CLASS_NUM"));
                studentScore.setPoint(rs.getInt("POINT"));
                testList.add(studentScore);
            }
        }
        return testList;
    }
}