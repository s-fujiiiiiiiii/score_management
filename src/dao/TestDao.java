package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Test;

public class TestDao extends Dao {

    // 点数登録（INSERT）
	public boolean save(Test test, Connection con) throws Exception {
	    // 🔹 `NULL` の場合はデフォルト値を設定
	    String classNum = test.getClassNum();
	    if (classNum == null || classNum.isEmpty()) {
	        classNum = "未登録";  // ← 例えば「未登録」というデフォルト値を設定
	    }

	    String sql = "INSERT INTO TEST (CLASS_NUM, SUBJECT_CD, STUDENT_NO, NO, POINT) VALUES (?, ?, ?, ?, ?)";
	    try (PreparedStatement stmt = con.prepareStatement(sql)) {
	        stmt.setString(1, classNum);
	        stmt.setString(2, test.getSubjectCd());
	        stmt.setString(3, test.getStudentNo());
	        stmt.setInt(4, test.getNo());
	        stmt.setInt(5, test.getPoint());

	        int rowsInserted = stmt.executeUpdate();
	        System.out.println("DEBUG: 挿入された行数 = " + rowsInserted);
	        return rowsInserted > 0;
	    }
	}

    // 点数更新（UPDATE）
	public boolean update(Test test, Connection con) throws Exception {
	    String sql = "UPDATE TEST SET POINT = ? WHERE (CLASS_NUM IS NULL OR CLASS_NUM = ?) AND SUBJECT_CD = ? AND NO = ? AND STUDENT_NO = ?";

	    try (PreparedStatement stmt = con.prepareStatement(sql)) {
	        stmt.setInt(1, test.getPoint());
	        stmt.setString(2, test.getClassNum());
	        stmt.setString(3, test.getSubjectCd());
	        stmt.setInt(4, test.getNo());
	        stmt.setString(5, test.getStudentNo());

	        int rowsUpdated = stmt.executeUpdate();
	        System.out.println("DEBUG: 更新された行数 = " + rowsUpdated);
	        return rowsUpdated > 0;
	    }
	}

    // 点数削除（POINTをNULLに更新）
    public boolean deletePoint(Test test, Connection con) throws Exception {
        String sql = "UPDATE TEST SET POINT = NULL WHERE CLASS_NUM = ? AND SUBJECT_CD = ? AND NO = ? AND STUDENT_NO = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, test.getClassNum());
            stmt.setString(2, test.getSubjectCd());
            stmt.setInt(3, test.getNo());
            stmt.setString(4, test.getStudentNo());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    // 1件検索（主キーで検索）
    public Test find(String classNum, String subjectCd, String studentNo, int No) throws Exception {
        Test test = null;

        try (Connection con = getConnection()) {
            String sql = "SELECT t.*, s.NAME AS STUDENT_NAME FROM TEST t " +
                         "JOIN STUDENT s ON t.STUDENT_NO = s.NO " +
                         "WHERE (t.CLASS_NUM IS NULL OR t.CLASS_NUM = ?) " +
                         "AND t.SUBJECT_CD = ? AND t.STUDENT_NO = ? AND t.NO = ?";

            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, classNum);
                stmt.setString(2, subjectCd);
                stmt.setString(3, studentNo);
                stmt.setInt(4, No);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        test = new Test();
                        test.setClassNum(rs.getString("CLASS_NUM"));
                        test.setSubjectCd(rs.getString("SUBJECT_CD"));
                        test.setStudentNo(rs.getString("STUDENT_NO"));
                        test.setNo(rs.getInt("NO"));
                        test.setPoint(rs.getInt("POINT"));
                        test.setStudentName(rs.getString("STUDENT_NAME"));
                    }
                }
            }
        }

        System.out.println("DEBUG: TestDao.find() -> 検索結果: " + (test != null ? "データあり" : "データなし"));
        return test;
    }

    // 成績レコードを削除するメソッド（主キー4項目指定）
    public boolean delete(String classNum, String subjectCd, String studentNo, int No) throws Exception {
        boolean result = false;

        try (Connection con = getConnection()) {
            String sql = "DELETE FROM TEST WHERE (CLASS_NUM IS NULL OR CLASS_NUM = ?) AND SUBJECT_CD = ? AND STUDENT_NO = ? AND NO = ?";

            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, classNum);
                stmt.setString(2, subjectCd);
                stmt.setString(3, studentNo);
                stmt.setInt(4, No);

                int rowsDeleted = stmt.executeUpdate();
                System.out.println("DEBUG: 削除された行数 = " + rowsDeleted);
                result = (rowsDeleted > 0);
            }
        }
        return result;
    }
}