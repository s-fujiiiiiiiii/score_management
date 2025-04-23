package main;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Test;
import dao.TestDao;

@WebServlet("/scoremanager/main/TestRegisterAction")
public class TestRegistAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentNo = request.getParameter("studentNo");
        String subjectCd = request.getParameter("subjectCd");
        String schoolCd = request.getParameter("schoolCd");
        int no = Integer.parseInt(request.getParameter("no"));
        int point = Integer.parseInt(request.getParameter("point"));
        String classNum = request.getParameter("classNum");

        Test test = new Test(studentNo, subjectCd, schoolCd, no, point, classNum);
        TestDao dao = new TestDao();
        boolean isRegistered = false;

        try {
            isRegistered = dao.save(test, dao.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (isRegistered) {
            request.setAttribute("message", "成績が登録されました！");
        } else {
            request.setAttribute("message", "成績登録に失敗しました。");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/scoremanager/main/test_regist_done.jsp");
        dispatcher.forward(request, response);
    }
}