package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;

public class StudentDao extends Dao {

    // 学生一覧（絞込あり）
    public List<Student> getStudents(String entYear, String classNum, String isAttend) throws Exception {
        List<Student> students = new ArrayList<>();
        Connection connection = getConnection();

        StringBuilder sql = new StringBuilder("SELECT ENT_YEAR, NO, NAME, CLASS_NUM, IS_ATTEND FROM STUDENT WHERE 1=1");

        if (entYear != null && !entYear.isEmpty()) {
            sql.append(" AND ENT_YEAR = ?");
        }
        if (classNum != null && !classNum.isEmpty()) {
            sql.append(" AND CLASS_NUM = ?");
        }
        if ("true".equals(isAttend)) {
            sql.append(" AND IS_ATTEND = TRUE");
        }

        PreparedStatement statement = connection.prepareStatement(sql.toString());

        int index = 1;
        if (entYear != null && !entYear.isEmpty()) {
            statement.setInt(index++, Integer.parseInt(entYear));
        }
        if (classNum != null && !classNum.isEmpty()) {
            statement.setString(index++, classNum);
        }

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

        return students;
    }

    // クラス一覧（重複なし）
    public List<String> getClassList() throws Exception {
        List<String> classList = new ArrayList<>();
        Connection connection = getConnection();

        String sql = "SELECT DISTINCT CLASS_NUM FROM STUDENT ORDER BY CLASS_NUM";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            classList.add(resultSet.getString("CLASS_NUM"));
        }

        resultSet.close();
        statement.close();
        connection.close();

        return classList;
    }

    // 年度一覧（重複なし）
    public List<String> getYearList() throws Exception {
        List<String> yearList = new ArrayList<>();
        Connection connection = getConnection();

        String sql = "SELECT DISTINCT ENT_YEAR FROM STUDENT ORDER BY ENT_YEAR";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            yearList.add(resultSet.getString("ENT_YEAR"));
        }

        resultSet.close();
        statement.close();
        connection.close();

        return yearList;
    }

    // 登録（このメソッドはいじらない）
    public boolean insert(Student student) {
        String sql = "INSERT INTO STUDENT (ENT_YEAR, NO, NAME, CLASS_NUM, IS_ATTEND) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = java.sql.DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, student.getEntYear());
            statement.setString(2, student.getStudentNumber());
            statement.setString(3, student.getName());
            statement.setString(4, student.getClassNum());
            statement.setBoolean(5, student.isAttend());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("挿入エラー: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}