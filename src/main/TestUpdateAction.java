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

        // 🔹 パラメータ取得
        String classNum = request.getParameter("classNum");
        String subjectCd = request.getParameter("subjectCd");
        String studentNo = request.getParameter("studentNo");

        // 🔹 `No` の取得と `null` チェック
        String noStr = request.getParameter("No");
        int No = 1; // 🔹 デフォルト値

        if (noStr != null && !noStr.isEmpty()) {
            try {
                No = Integer.parseInt(noStr);
            } catch (NumberFormatException e) {
                System.out.println("ERROR: 無効な試験回数の値 -> " + noStr);
            }
        }

        System.out.println("DEBUG: 取得した試験回数 = " + No);

        try {
            TestDao dao = new TestDao();
            Test test = dao.find(classNum, subjectCd, studentNo, No);

            System.out.println("DEBUG: 取得したテスト情報 -> 学生番号=" + studentNo
                + ", 科目コード=" + subjectCd + ", クラス番号=" + classNum + ", 試験回数=" + No);
            System.out.println("DEBUG: test = " + (test != null ? "データあり" : "データなし"));

            request.setAttribute("test", test);
            RequestDispatcher rd = request.getRequestDispatcher("/scoremanager/main/test_update.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}