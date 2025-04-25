package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import bean.Test;

public class TestDao extends Dao {
    public boolean save(Test test, Connection con) throws Exception {
        String sql = "INSERT INTO TEST (ENT_YEAR, CLASS_NUM, SUBJECT_CD, EXAM_ROUND, POINT) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, test.getEntYear());
            stmt.setString(2, test.getClassNum());
            stmt.setString(3, test.getSubjectCd());
            stmt.setInt(4, test.getNo());
            stmt.setInt(5, test.getPoint());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        }
    }
}