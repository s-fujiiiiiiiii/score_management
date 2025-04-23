package main;

import bean.Student;
import dao.StudentDao;

public class StudentCreateExecuteAction {

    public boolean registerStudent(int year, String studentNum, String studentName, String className) {
        // 学生オブジェクトの作成
        Student student = new Student();
        student.setEntYear(year);
        student.setStudentNumber(studentNum);
        student.setName(studentName);
        student.setClassNum(className);
        student.setAttend(true);

        // DAOを使用してデータベースに登録
        StudentDao dao = new StudentDao();
        return dao.insert(student);  // 成功か失敗かを返す
    }
}