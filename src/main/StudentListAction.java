package main;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;

@WebServlet("/student_list")
public class StudentListAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            System.out.println("サーブレット: 'StudentListAction' が呼び出されました。");

            // DAOのインスタンス化
            StudentDao studentDao = new StudentDao();

            // DAOメソッド呼び出し
            System.out.println("DAOメソッド 'getStudents()' を呼び出します...");
            List<Student> students = studentDao.getStudents();

            // デバッグログ: 学生データ
            System.out.println("取得した学生データの件数: " + students.size());
            System.out.println("学生データ内容: " + students);

            // JSPにデータを渡す
            request.setAttribute("studentList", students);

            // JSPへのフォワード
            System.out.println("JSP 'student_list.jsp' にフォワードします...");
            request.getRequestDispatcher("/scoremanager/main/student_list.jsp").forward(request, response);

        } catch (Exception e) {
            System.err.println("サーブレット処理中にエラーが発生しました: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "エラーが発生しました。");
        }
    }
}