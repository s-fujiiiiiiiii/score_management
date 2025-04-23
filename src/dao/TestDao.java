package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import bean.Test;

public class TestDao extends Dao { // `Dao` を継承
    private static final String baseSql = "INSERT INTO TEST (STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM) VALUES (?, ?, ?, ?, ?, ?)";

    // ✅ 単一の成績登録
    public boolean save(Test test, Connection con) throws Exception {
        try (PreparedStatement stmt = con.prepareStatement(baseSql)) {
            stmt.setString(1, test.getStudentNo());
            stmt.setString(2, test.getSubjectCd());
            stmt.setString(3, test.getSchoolCd());
            stmt.setInt(4, test.getNo());
            stmt.setInt(5, test.getPoint());
            stmt.setString(6, test.getClassNum());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        }
    }

    // ✅ 複数の成績データを登録
    public boolean save(List<Test> list) throws Exception {
        Connection con = getConnection();
        boolean success = true;

        for (Test test : list) {
            if (!save(test, con)) {
                success = false;
                break;
            }
        }

        con.close();
        return success;
    }
}