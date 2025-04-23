package main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDao;

@WebServlet("/main/SubjectUpdateAction")
public class SubjectUpdateAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String schoolCd = request.getParameter("schoolCd");
            String cd = request.getParameter("cd");
            String name = request.getParameter("name");

            Subject subject = new Subject();
            subject.setSchoolCd(schoolCd);
            subject.setCd(cd);
            subject.setName(name);

            SubjectDao dao = new SubjectDao();
            dao.update(subject); // 更新処理を実行

            request.getRequestDispatcher("/scoremanager/main/subject_update_done.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}