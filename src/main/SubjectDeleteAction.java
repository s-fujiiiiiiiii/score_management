package main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SubjectDao;

@WebServlet("/SubjectDeleteAction")
public class SubjectDeleteAction extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	String schoolCd = request.getParameter("schoolCd");
            String cd = request.getParameter("cd");
            SubjectDao dao = new SubjectDao();
            dao.delete(schoolCd,cd); // 削除処理を実行

            request.getRequestDispatcher("subject_delete_done.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}