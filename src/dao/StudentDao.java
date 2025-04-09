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
             ResultSet resultSet = statement.executeQuery("SELECT * FROM students")) {

            while (resultSet.next()) {
                Student student = new Student();
                student.setEnrollmentYear(resultSet.getInt("enrollment_year"));
                student.setStudentNumber(resultSet.getString("student_number"));
                student.setName(resultSet.getString("name"));
                student.setClassName(resultSet.getString("class_name"));
                student.setScore(resultSet.getInt("score"));
                student.setEnrolled(resultSet.getBoolean("is_enrolled"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}