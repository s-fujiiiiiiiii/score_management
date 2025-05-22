package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Test;

public class TestDao extends Dao {

    // 点数登録（INSERT）
    public boolean save(Test test, Connection con) throws Exception {
        String sql = "INSERT INTO TEST (CLASS_NUM, SUBJECT_CD, STUDENT_NO, NO, POINT) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, test.getClassNum());
            stmt.setString(2, test.getSubjectCd());
            stmt.setString(3, test.getStudentNo());
            stmt.setInt(4, test.getNo());
            stmt.setInt(5, test.getPoint());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        }
    }

    // 点数更新（UPDATE）
    public boolean update(Test test, Connection con) throws Exception {
        String sql = "UPDATE TEST SET POINT = ? WHERE CLASS_NUM = ? AND SUBJECT_CD = ? AND NO = ? AND STUDENT_NO = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, test.getPoint());
            stmt.setString(2, test.getClassNum());
            stmt.setString(3, test.getSubjectCd());
            stmt.setInt(4, test.getNo());
            stmt.setString(5, test.getStudentNo());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    // 点数削除（POINTをNULLに更新） 主キー3項目のみで指定
    public boolean deletePoint(Test test, Connection con) throws Exception {
        boolean ownConnection = false;

        if (con == null) {
            con = getConnection(); // Dao クラスの getConnection() を使う
            ownConnection = true;
        }

        String sql = "UPDATE TEST SET POINT = NULL WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND NO = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, test.getStudentNo());
            stmt.setString(2, test.getSubjectCd());
            stmt.setInt(3, test.getNo());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } finally {
            if (ownConnection && con != null) {
                con.close(); // 自分で取得したコネクションなら閉じる
            }
        }
    }

    // 1件検索（主キーで検索）
    public Test find(String classNum, String subjectCd, String studentNo, int examRound) throws Exception {
        Test test = null;

        try (Connection con = getConnection()) {
            String sql = "SELECT t.*, s.NAME AS STUDENT_NAME FROM TEST t " +
                         "JOIN STUDENT s ON t.STUDENT_NO = s.NO " +
                         "WHERE t.CLASS_NUM = ? AND t.SUBJECT_CD = ? AND t.STUDENT_NO = ? AND t.NO = ?";

            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, classNum);
                stmt.setString(2, subjectCd);
                stmt.setString(3, studentNo);
                stmt.setInt(4, examRound);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        test = new Test();
                        test.setClassNum(classNum);
                        test.setSubjectCd(subjectCd);
                        test.setStudentNo(studentNo);
                        test.setNo(examRound);
                        test.setPoint(rs.getInt("POINT"));
                        test.setStudentName(rs.getString("STUDENT_NAME"));
                    }
                }
            }
        }
        return test;
    }

    // ←ここから追加↓
    // 成績レコードを削除するメソッド（ENT_YEARを含む主キー5項目指定）
    public boolean delete(String entYear, String classNum, String subjectCd, String studentNo, int examRound) throws Exception {
        boolean result = false;

        try (Connection con = getConnection()) {
            String sql = "DELETE FROM TEST WHERE ENT_YEAR = ? AND CLASS_NUM = ? AND SUBJECT_CD = ? AND STUDENT_NO = ? AND NO = ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, entYear);
                stmt.setString(2, classNum);
                stmt.setString(3, subjectCd);
                stmt.setString(4, studentNo);
                stmt.setInt(5, examRound);

                int rowsDeleted = stmt.executeUpdate();
                result = (rowsDeleted > 0);
            }
        }
        return result;
    }
}
