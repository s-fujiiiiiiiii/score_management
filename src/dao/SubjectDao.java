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

        String sql = keyword.isEmpty() ? "SELECT * FROM SUBJECT" : "SELECT * FROM SUBJECT WHERE NAME LIKE ?";

        // ③ SQL文の準備（商品名にキーワードが含まれるものを検索）
        PreparedStatement st = con.prepareStatement(
            "SELECT * FROM SUBJECT WHERE NAME LIKE ?");
        // ④ プレースホルダ `?` にキーワードをセット（% を付けて部分一致検索にする）
        st.setString(1, "%" + keyword + "%");

        // ⑤ SQL文を実行し、結果を取得
        ResultSet rs = st.executeQuery();

        // ⑥ 検索結果のデータを1件ずつ Product オブジェクトに変換してリストに追加
        while (rs.next()) {
        	Subject subject = new Subject();
            subject.setSchoolCd(rs.getString("school_cd"));       // 学校コード を設定
            subject.setCd(rs.getString("cd")); // コードを設定
            subject.setName(rs.getString("name"));  // 名前を設定
            list.add(subject);  // 作成したオブジェクトをリストに追加
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
    	//科目更新メソッド
    public int update(Subject subject) throws Exception {
    	Connection con = getConnection();
    	PreparedStatement st = con.prepareStatement(
    			"UPDATE SUBJECT SET NAME = ? WHERE SCHOOL_CD = ? AND CD = ?");
    	st.setString(1, subject.getName());
    	st.setString(2, subject.getSchoolCd());
    	st.setString(3, subject.getCd());

    	int line = st.executeUpdate();

    	st.close();
    	con.close();

    	return line;
    }

    	//科目削除メソッド
    public int delete(String schoolCd, String cd) throws Exception {
    	Connection con = getConnection();
    	PreparedStatement st = con.prepareStatement(
    			"DELETE FROM SUBJECT WHERE SCHOOL_CD = ? AND CD = ?");
    	st.setString(1, schoolCd);
    	st.setString(2, cd);
    	int line = st.executeUpdate();

    	System.out.println("削除された行数: " + line);

    	st.close();
    	con.close();

    	return line;
    }

    public Subject findByCd(String cd) throws Exception {
        // データベース接続を取得
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
            "SELECT * FROM SUBJECT WHERE CD = ?" // CD に一致する科目を取得
        );
        st.setString(1, cd); // プレースホルダに科目コードを設定

        ResultSet rs = st.executeQuery(); // SQL を実行
        Subject subject = null; // 科目オブジェクトを初期化

        // 結果が存在すれば科目データを設定
        if (rs.next()) {
            subject = new Subject();
            subject.setSchoolCd(rs.getString("SCHOOL_CD")); // 学校コードを取得
            subject.setCd(rs.getString("CD"));             // 科目コードを取得
            subject.setName(rs.getString("NAME"));         // 科目名を取得
            System.out.println("取得した科目名: " + rs.getString("NAME"));
        }else{
        	System.out.println("データベースに該当する科目がありません");
        }

        // リソースを解放
        rs.close();
        st.close();
        con.close();

        // 科目データを返す
        return subject;
    }

    public Subject findBySchoolCdAndCd(String schoolCd, String cd) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
            "SELECT * FROM SUBJECT WHERE SCHOOL_CD = ? AND CD = ?" // 学校コードも条件に追加
        );
        st.setString(1, schoolCd);
        st.setString(2, cd);

        ResultSet rs = st.executeQuery();
        Subject subject = null;

        if (rs.next()) {
            subject = new Subject();
            subject.setSchoolCd(rs.getString("SCHOOL_CD"));
            subject.setCd(rs.getString("CD"));
            subject.setName(rs.getString("NAME"));
        }

        rs.close();
        st.close();
        con.close();

        return subject;
    }

        public List<Subject> getAllSubjects() throws Exception {
            Connection con = getConnection();
            String sql = "SELECT SCHOOL_CD, CD, NAME FROM SUBJECT";
            List<Subject> subjects = new ArrayList<>();

            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Subject subject = new Subject();
                    subject.setSchoolCd(rs.getString("SCHOOL_CD")); // 学校コード
                    subject.setCd(rs.getString("CD")); // 科目コード
                    subject.setName(rs.getString("NAME")); // 科目名
                    subjects.add(subject);
                }
            }
            return subjects;
        }

        public String getSubjectName(String subjectCd) throws Exception {
            String subjectName = null;
            String sql = "SELECT name FROM subject WHERE cd = ?";
            try (Connection conn = getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, subjectCd);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        subjectName = rs.getString("name");
                    }
                }
            }
            return subjectName;
        }
 }

