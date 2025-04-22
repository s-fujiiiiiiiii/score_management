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
            // パラメータ取得
            String entYear = request.getParameter("entYear");
            String classNum = request.getParameter("classNum");
            String isAttend = request.getParameter("isAttend");

            // DAO呼び出し
            StudentDao studentDao = new StudentDao();
            List<Student> students = studentDao.getStudents(entYear, classNum, isAttend);
            List<String> classList = studentDao.getClassList(); // クラス一覧取得
            List<String> yearList = studentDao.getYearList();   // 年度一覧取得

            // リクエストスコープに設定
            request.setAttribute("studentList", students);
            request.setAttribute("classList", classList);
            request.setAttribute("yearList", yearList);  // 年度リストを追加

            // フォワード
            request.getRequestDispatcher("/scoremanager/main/student_list.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "データ取得中にエラーが発生しました");
        }
    }
}
