package main;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.TestListStudent;
import dao.TestListStudentDao;

@WebServlet("/scoremanager/main/TestListAction")
public class TestListAction extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentNo = request.getParameter("studentNo");
        System.out.println("受け取った学生番号: " + studentNo);
        TestListStudentDao dao = new TestListStudentDao();
        List<TestListStudent> testScores = null;

        try {
            testScores = dao.findByStudentNo(studentNo);
            System.out.println("取得した成績リスト: " + testScores);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("testScores", testScores);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/scoremanager/main/test_list.jsp");
        dispatcher.forward(request, response);
    }
}