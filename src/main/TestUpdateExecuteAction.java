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
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            String classNum = request.getParameter("classNum");
            String subjectCd = request.getParameter("subjectCd");
            String studentNo = request.getParameter("studentNo");

            String NoStr = request.getParameter("No");
            int No = 0;
            if (NoStr != null && !NoStr.isEmpty()) {
                No = Integer.parseInt(NoStr);
            }

            String pointStr = request.getParameter("point");
            int point = 0;

            // バリデーション① 数値チェック
            try {
                point = Integer.parseInt(pointStr);
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "点数は数値で入力してください");

                // 🔁 DBから再取得して氏名なども含める
                TestDao dao = new TestDao();
                Test test = dao.find(classNum, subjectCd, studentNo, No);
                if (test == null) {
                    test = new Test(); // 万一該当データがなければ空オブジェクトでフォールバック
                    test.setClassNum(classNum);
                    test.setSubjectCd(subjectCd);
                    test.setStudentNo(studentNo);
                    test.setNo(No);
                }
                test.setPoint(0); // 無効値の場合でも0を表示させておく

                request.setAttribute("test", test);
                request.getRequestDispatcher("/scoremanager/main/test_update.jsp").forward(request, response);
                return;
            }

            // バリデーション② 0〜100の範囲チェック
            if (point < 0 || point > 100) {
                request.setAttribute("errorMessage", "点数は 0～100 の範囲で入力してください");

                // 🔁 DBから再取得して氏名なども含める
                TestDao dao = new TestDao();
                Test test = dao.find(classNum, subjectCd, studentNo, No);
                if (test == null) {
                    test = new Test();
                    test.setClassNum(classNum);
                    test.setSubjectCd(subjectCd);
                    test.setStudentNo(studentNo);
                    test.setNo(No);
                }
                test.setPoint(point); // 入力された値を表示用にセット

                request.setAttribute("test", test);
                request.getRequestDispatcher("/scoremanager/main/test_update.jsp").forward(request, response);
                return;
            }

            // 正常時：更新処理
            Test test = new Test();
            test.setClassNum(classNum);
            test.setSubjectCd(subjectCd);
            test.setStudentNo(studentNo);
            test.setNo(No);
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
