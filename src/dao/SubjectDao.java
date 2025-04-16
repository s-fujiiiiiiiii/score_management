package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;

public class SubjectDao extends Dao {

    /**
     * 🔍 商品検索メソッド
     * キーワードを指定すると、データベースから商品名にそのキーワードを含む商品を検索し、
     * 取得した商品データをリストに詰め替えて返します。
     */
    public List<Subject> search(String keyword) throws Exception {
        // ① 検索結果を格納するリストを作成
        List<Subject> list = new ArrayList<>();

        // ② データベース接続を取得（DAOクラスの getConnection() を利用）
        Connection con = getConnection();

        // ③ SQL文の準備（商品名にキーワードが含まれるものを検索）
        PreparedStatement st = con.prepareStatement(
            "SELECT * FROM subject WHERE name LIKE ?");
        // ④ プレースホルダ `?` にキーワードをセット（% を付けて部分一致検索にする）
        st.setString(1, "%" + keyword + "%");

        // ⑤ SQL文を実行し、結果を取得
        ResultSet rs = st.executeQuery();

        // ⑥ 検索結果のデータを1件ずつ Product オブジェクトに変換してリストに追加
        while (rs.next()) {
        	Subject p = new Subject();
            p.setSchoolCd(rs.getString("schoolCd"));       // 学校コード を設定
            p.setCd(rs.getString("cd")); // コードを設定
            p.setName(rs.getString("name"));  // 名前を設定
            list.add(p);  // 作成したオブジェクトをリストに追加
        }

        // ⑦ 使用したリソースを解放（PreparedStatement と Connection を閉じる）
        st.close();
        con.close();

        // ⑧ 検索結果のリストを呼び出し元に返す
        return list;
    }

    /**
     * ➕ 商品追加メソッド
     * 受け取った Product オブジェクトの情報をデータベースに追加し、
     * 追加した行数を返します。
     */
    public int insert(Subject subject) throws Exception {
        // ① データベース接続を取得
        Connection con = getConnection();

        // ② SQL文の準備
        PreparedStatement st = con.prepareStatement(
            "INSERT INTO subject VALUES (?, ?, ?)");

        // ③ プレースホルダ `?` に `Product` オブジェクトのデータをセット
        st.setString(1, subject.getSchoolCd());  // 学校コードをセット
        st.setString(2, subject.getCd());    // コードをセット
        st.setString(3, subject.getName()); //名前をセット

        // ④ SQL文を実行し、追加された行数を取得
        int line = st.executeUpdate();

        // ⑤ 使用したリソースを解放（PreparedStatement と Connection を閉じる）
        st.close();
        con.close();

        // ⑥ 追加した行数を呼び出し元に返す
        return line;
    }
}
