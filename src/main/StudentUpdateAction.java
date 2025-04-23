package main;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/student_update")
public class StudentUpdateAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // パラメータ受け取り
        request.setAttribute("studentNumber", request.getParameter("studentNumber"));
        request.setAttribute("entYear", request.getParameter("entYear"));
        request.setAttribute("name", request.getParameter("name"));
        request.setAttribute("classNum", request.getParameter("classNum"));
        request.setAttribute("attend", request.getParameter("attend"));

        // クラス一覧（仮で固定値。DBから取得する場合はDAOを使ってね）
        List<String> classList = List.of("A", "B", "C", "D");
        request.setAttribute("classList", classList);

        // フォームへフォワード
        request.getRequestDispatcher("/scoremanager/main/student_update.jsp").forward(request, response);
    }
}
