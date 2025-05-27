package main;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Test;
import dao.TestDao;

public class TestUpdateExecuteAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            String classNum = request.getParameter("classNum");
            String subjectCd = request.getParameter("subjectCd");
            String studentNo = request.getParameter("studentNo");

            String examRoundStr = request.getParameter("examRound");
            int examRound = 0;
            if (examRoundStr != null && !examRoundStr.isEmpty()) {
                examRound = Integer.parseInt(examRoundStr);
            }

            String pointStr = request.getParameter("point");
            int point = 0;
            if (pointStr != null && !pointStr.isEmpty()) {
                point = Integer.parseInt(pointStr);
            }

            // もし点数の範囲チェックを入れる場合（例）
            if(point < 0 || point > 100) {
                throw new ServletException("点数は0から100の範囲で入力してください。");
            }

            Test test = new Test();
            test.setClassNum(classNum);
            test.setSubjectCd(subjectCd);
            test.setStudentNo(studentNo);
            test.setNo(examRound);
            test.setPoint(point);

            TestDao dao = new TestDao();
            try (Connection con = dao.getConnection()) {
                boolean success = dao.update(test, con);
                System.out.println("更新成功？: " + success);
            }

            response.sendRedirect("TestListAction");

        } catch (Exception e) {
            // ここでログ出力やエラーページに遷移させる処理を追加しても良いです
            throw new ServletException(e);
        }
    }
}
