package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public boolean exists(String studentNumber) {
        String sql = "SELECT 1 FROM STUDENT WHERE NO = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, studentNumber);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next(); // データが1件でもあれば重複

        } catch (Exception e) {
            e.printStackTrace();
            return false;  // 念のため false を返しておく
        }
    }

    // 登録
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

    public void updateStudent(String studentNumber, String name, String classNum, boolean attend) {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(
                 "UPDATE student SET name=?, class_num=?, attend=? WHERE student_number=?")) {

            ps.setString(1, name);
            ps.setString(2, classNum);
            ps.setBoolean(3, attend);
            ps.setString(4, studentNumber);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
