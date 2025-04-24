package main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/scoremanager/main/TestRegistAction")
public class TestRegistAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String entYear = request.getParameter("entYear");
        String classNum = request.getParameter("classNum");
        String subjectCd = request.getParameter("subjectCd");
        String examRound = request.getParameter("examRound");
        String pointStr = request.getParameter("point");

        // **未入力チェック**
        if (entYear == null || classNum == null || subjectCd == null || examRound == null) {
            request.setAttribute("errorMessage", "入学年度・クラス・科目・回数を選択してください。");
            request.getRequestDispatcher("/scoremanager/main/test_regist.jsp").forward(request, response);
            return;
        }

        // **得点のバリデーション**
        int point = -1;
        if (pointStr != null && !pointStr.isEmpty()) { // ✅ 得点が未入力なら登録しない
            try {
                point = Integer.parseInt(pointStr);
                if (point < 0 || point > 100) {
                    throw new IllegalArgumentException();
                }
            } catch (Exception e) {
                request.setAttribute("errorMessage", "得点は 0 〜 100 の範囲で入力してください。");
                request.getRequestDispatcher("/scoremanager/main/test_regist.jsp").forward(request, response);
                return;
            }
        }

        // **登録処理**
        boolean success = false;
        try {
            TestRegistExecuteAction executeAction = new TestRegistExecuteAction();
            success = executeAction.execute(entYear, classNum, subjectCd, examRound, point);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (success) {
            response.sendRedirect("/scoremanager/main/test_regist_done.jsp");
        } else {
            request.setAttribute("errorMessage", "成績登録中にエラーが発生しました。");
            request.getRequestDispatcher("/scoremanager/main/test_regist.jsp").forward(request, response);
        }
    }
}