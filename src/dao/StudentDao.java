package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;

public class StudentDao extends Dao {



private static final String URL = "jdbc:h2:~/test"; // データベースURL
private static final String USER = "sa"; // データベースユーザー名
private static final String PASSWORD = ""; // データベースパスワード



    public List<Student> getStudents() throws Exception {
        List<Student> students = new ArrayList<>();
        System.out.println("DAOメソッド 'getStudents()' が呼び出されました。");

        // JNDI接続でデータベースに接続
        Connection connection = getConnection();

        PreparedStatement statement = connection.prepareStatement(
            "SELECT ENT_YEAR, NO, NAME, CLASS_NUM, IS_ATTEND FROM STUDENT"
        );
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Student student = new Student();
            student.setEntYear(resultSet.getInt("ENT_YEAR"));
            student.setStudentNumber(resultSet.getString("NO"));
            student.setName(resultSet.getString("NAME"));
            student.setClassNum(resultSet.getString("CLASS_NUM"));
            student.setAttend(resultSet.getBoolean("IS_ATTEND"));
            students.add(student);
        }

        resultSet.close();
        statement.close();
        connection.close();

        System.out.println("取得した学生データの件数: " + students.size());
        return students;
    }



        public boolean insert(Student student) {
            String sql = "INSERT INTO STUDENT (ENT_YEAR, NO, NAME, CLASS_NUM) VALUES (?, ?, ?, ?)";

            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                // SQLにパラメータを設定
                statement.setInt(1, student.getEntYear());
                statement.setString(2, student.getStudentNumber());
                statement.setString(3, student.getName());
                statement.setString(4, student.getClassNum());

                statement.setBoolean(6, student.isAttend());


                // SQL実行
                int rowsAffected = statement.executeUpdate();

                // 挿入成功ならtrueを返す
                return rowsAffected > 0;

            } catch (SQLException e) {
                System.err.println("データベースに学生情報を挿入中にエラーが発生しました: " + e.getMessage());
                e.printStackTrace();
                return false;  // 失敗した場合はfalseを返す
            }
    }
}
