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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	String schoolCd = request.getParameter("schoolCd");
        	String cd = request.getParameter("cd");

        	// デバッグログを追加
        	System.out.println("Request URL: " + request.getRequestURI());
        	System.out.println("Query Parameters: " + request.getQueryString());
        	System.out.println("schoolCd: " + schoolCd);
        	System.out.println("cd: " + cd);

        	if (schoolCd == null || cd == null || schoolCd.isEmpty() || cd.isEmpty()) {
        	    throw new ServletException("リクエストパラメータが不足しています");
        	}

            SubjectDao dao = new SubjectDao();
            int result = dao.delete(schoolCd, cd); // 削除処理を実行

            if (result > 0) {
                // 削除成功時に subject_delete_done.jsp へフォワード
                request.getRequestDispatcher("/scoremanager/main/subject_delete_done.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "削除対象のデータが存在しません");
                request.getRequestDispatcher("/score_management/main/SubjectListAction");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}