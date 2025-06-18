package main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;

@WebServlet("/student_update_done")
public class StudentUpdateExecuteAction extends HttpServlet {
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // パラメータ取得
        String studentNumber = request.getParameter("studentNumber");
        String name = request.getParameter("name");
        String classNum = request.getParameter("classNum");
        boolean attend = Boolean.parseBoolean(request.getParameter("attend"));

        // DB更新処理（StudentDaoを使うと仮定）
        StudentDao dao = new StudentDao();
        dao.updateStudent(studentNumber, name, classNum, attend);

        // 完了画面へのリダイレクト
        response.sendRedirect(request.getContextPath() + "/scoremanager/main/student_update_done.jsp");
    }
}
