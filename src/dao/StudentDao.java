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
}
