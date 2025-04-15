package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.Student;

public class StudentDao {
    private static final String URL = "jdbc:h2:~/score"; // H2のデータベースパス
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM STUDENT")) {

            while (resultSet.next()) {
                Student student = new Student();
                student.setEntYear(resultSet.getInt("ENT_YEAR"));
                student.setStudentNumber(resultSet.getString("NO"));
                student.setName(resultSet.getString("NAME"));
                student.setClassNum(resultSet.getString("CLASS_NUM"));
                student.setAttend(resultSet.getBoolean("IS_ATTEND"));
                students.add(student);
            }
        } catch (SQLException e) {
            System.err.println("データベースから学生情報を取得中にエラーが発生しました: " + e.getMessage());
            e.printStackTrace();
        }
        return students;
    }
    }