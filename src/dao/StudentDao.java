package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;

public class StudentDao extends Dao {

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
        String sql = "INSERT INTO STUDENT (NO, NAME, ENT_YEAR, CLASS_NUM, IS_ATTEND) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();  // ← JNDI経由に統一
             PreparedStatement statement = connection.prepareStatement(sql)) {

            System.out.println("学生の年度: " + student.getEntYear());

            statement.setString(1, student.getStudentNumber());
            statement.setString(2, student.getName());
            statement.setInt(3, student.getEntYear());
            statement.setString(4, student.getClassNum());
            statement.setBoolean(5, student.isAttend());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {  // SQLException ではなく Exception にしてるのは getConnection() が例外を投げるため
            System.err.println("データベースに学生情報を挿入中にエラーが発生しました: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}