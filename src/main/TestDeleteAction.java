package main;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TestDao;

public class TestDeleteAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // POSTメソッドで削除処理を行う
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String entYear = request.getParameter("entYear");
        String classNum = request.getParameter("classNum");
        String subjectCd = request.getParameter("subjectCd");
        String studentNo = request.getParameter("studentNo");
        String examRound = request.getParameter("examRound");

        System.out.println("DEBUG: entYear=" + entYear);
        System.out.println("DEBUG: classNum=" + classNum);
        System.out.println("DEBUG: subjectCd=" + subjectCd);
        System.out.println("DEBUG: studentNo=" + studentNo);
        System.out.println("DEBUG: examRound=" + examRound);

        // パラメータチェック
        if (studentNo == null || studentNo.trim().isEmpty()) {
            request.setAttribute("errorMsg", "学生番号(studentNo)が指定されていません。");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }
        if (entYear == null || entYear.trim().isEmpty() ||
            classNum == null || classNum.trim().isEmpty() ||
            subjectCd == null || subjectCd.trim().isEmpty() ||
            examRound == null || examRound.trim().isEmpty()) {
            request.setAttribute("errorMsg", "必要なパラメータが不足しています。");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        try {
            TestDao dao = new TestDao();
            boolean deleted = false;

            // コミット制御を明示するため自前でConnectionを取得
            try (Connection con = dao.getConnection()) {
                con.setAutoCommit(false);  // トランザクション開始

                deleted = dao.delete(entYear, classNum, subjectCd, studentNo, Integer.parseInt(examRound));

                if (deleted) {
                    con.commit();
                } else {
                    con.rollback();
                }
            }

            if (!deleted) {
                request.setAttribute("errorMsg", "該当する成績情報が見つかりませんでした。");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMsg", "削除処理中にエラーが発生しました。");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        // 削除後は検索条件を保持して一覧に戻る
        String redirectUrl = String.format("%s/main/TestListAction?studentNo=%s&entYear=%s&classNum=%s&subjectCd=%s",
                request.getContextPath(),
                studentNo,
                entYear,
                classNum,
                subjectCd);

        response.sendRedirect(redirectUrl);
    }
}
