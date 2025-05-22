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
            throw new ServletException(e);
        }
    }
}
