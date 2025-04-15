package dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * DAOの基底クラス
 *
 * データベース接続を提供する。
 */
public class DAO {
    /**
     * データベースへの接続を取得する
     *
     * @return Connection データベース接続オブジェクト
     * @throws Exception 例外処理
     */
    protected Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver");

        // 🔹 H2のTCPモードに統一し、AUTO_SERVER を設定
//      return DriverManager.getConnection(
//          "jdbc:h2:tcp://localhost:9092/~/kouka2;AUTO_SERVER=TRUE",
//          "sa",
//          ""
//      );

        // H2データベースの組込モード（Embedded）
        return DriverManager.getConnection(
            "jdbc:h2:~/score", // H2データベースのローカルファイルを使用
            "sa", // ユーザー名
            ""    // パスワード（デフォルトは空）
        );
    }
}
