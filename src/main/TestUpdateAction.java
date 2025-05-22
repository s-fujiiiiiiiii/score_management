package main;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Test;
import dao.TestDao;

public class TestUpdateAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // パラメータ取得
        String classNum = request.getParameter("classNum");
        String subjectCd = request.getParameter("subjectCd");
        String studentNo = request.getParameter("studentNo");
        int examRound = Integer.parseInt(request.getParameter("examRound"));

        try {
            // TestDaoから対象のテスト情報を取得
            TestDao dao = new TestDao();
            Test test = dao.find(classNum, subjectCd, studentNo, examRound);

            // 取得したテスト情報をリクエストスコープにセット
            request.setAttribute("test", test);

            // 更新用JSPにフォワード
            RequestDispatcher rd = request.getRequestDispatcher("/scoremanager/main/test_update.jsp");

            rd.forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
