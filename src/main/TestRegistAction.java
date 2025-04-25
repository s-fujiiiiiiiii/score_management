package main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Test;
import dao.TestDao;

@WebServlet("/TestRegistAction")
public class TestRegistAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String studentNo = request.getParameter("studentNo");
        String subjectCd = request.getParameter("subjectCd");
        String classNum = request.getParameter("classNum");
        String entYear = request.getParameter("entYear");
        String noStr = request.getParameter("no");
        String pointStr = request.getParameter("point");

        boolean success = false;

        try {
            int no = Integer.parseInt(noStr);
            int point = Integer.parseInt(pointStr);
            int year = Integer.parseInt(entYear);

            TestDao dao = new TestDao();
            Test test = new Test(studentNo, subjectCd, classNum, no, point, year);
            success = dao.insertTestRecord(test);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "登録中にエラーが発生しました。");
        }

        if (success) {
            response.sendRedirect("/scoremanager/main/test_regist_done.jsp");
        } else {
            request.getRequestDispatcher("/scoremanager/main/test_regist.jsp").forward(request, response);
        }
    }
}