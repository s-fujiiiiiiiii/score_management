package main;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDao;

@WebServlet("/SubjectListAction")
public class SubjectListAction extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            SubjectDao dao = new SubjectDao();
            List<Subject> subjects = dao.search(""); // 空文字で全科目を検索
            request.setAttribute("subjects", subjects); // 科目リストをリクエストに保存
            request.getRequestDispatcher("/scoremanager/main/subject_list.jsp").forward(request, response); // JSPへ遷移
        } catch (Exception e) {
        	e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
