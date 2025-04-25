package h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:h2:~/score"; // データベースURL
    private static final String USER = "sa";            // ユーザー名
    private static final String PASSWORD = "";          // パスワード

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}