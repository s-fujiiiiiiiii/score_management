package main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDao;

@WebServlet("/main/SubjectUpdateConfirmAction")
public class SubjectUpdateConfirmAction extends HttpServlet {
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String schoolCd = request.getParameter("schoolCd");
            String cd = request.getParameter("cd");

            if (schoolCd == null || cd == null || schoolCd.isEmpty() || cd.isEmpty()) {
                throw new ServletException("必要なパラメータが不足しています");
            }

            SubjectDao dao = new SubjectDao();
            Subject subject = dao.findBySchoolCdAndCd(schoolCd, cd); // `schoolCd` も含めて検索

            if (subject != null) {
                request.setAttribute("subject", subject);
                request.getRequestDispatcher("/scoremanager/main/subject_update.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "該当する科目が見つかりません");
                request.getRequestDispatcher("/scoremanager/main/subject_list.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}