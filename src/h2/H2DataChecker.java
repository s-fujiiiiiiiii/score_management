package h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class H2DataChecker {
    // H2データベースをTCPモードで接続
    private static final String JDBC_URL = "jdbc:h2:~/kouka2";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            System.out.println("=== STUDENT テーブルのデータ ===");
            checkTable(stmt, "STUDENT"); // スキーマ名なし

            System.out.println("=== COURSE テーブルのデータ ===");
            checkTable(stmt, "COURSE"); // スキーマ名なし

            System.out.println("=== H2 データベースのテーブル確認 ===");
            checkDatabase(stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void checkTable(Statement stmt, String tableName) throws SQLException {
        System.out.println("=== " + tableName + " ===");

        // 🔹 スキーマ名を明示的に指定
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);

        while (rs.next()) {
            int columns = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= columns; i++) {
                System.out.print(rs.getMetaData().getColumnName(i) + ": " + rs.getString(i) + " | ");
            }
            System.out.println();
        }
        rs.close();
    }

    private static void checkDatabase(Statement stmt) throws SQLException {
        System.out.println("=== データベースのテーブル一覧 ===");
        ResultSet rs = stmt.executeQuery("SHOW TABLES");

        while (rs.next()) {
            System.out.println("テーブル: " + rs.getString(1));
        }
        rs.close();
    }

}
