package main;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/student_update")
public class StudentUpdateAction extends HttpServlet {
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // パラメータ受け取り
        String studentNumber = request.getParameter("studentNumber");
        String entYear = request.getParameter("entYear");
        String name = request.getParameter("name");
        String classNum = request.getParameter("classNum");
        String attend = request.getParameter("attend");

        // バリデーション
        boolean isValid = true;
        if (name == null || name.isEmpty()) {
            request.setAttribute("nameError", "氏名を入力してください");
            isValid = false;
        }

        if (isValid) {
            // クラス一覧（Java8対応：Arrays.asListを使用）
            List<String> classList = Arrays.asList("A", "B", "C", "D");

            // パラメータをリクエスト属性にセット
            request.setAttribute("studentNumber", studentNumber);
            request.setAttribute("entYear", entYear);
            request.setAttribute("name", name);
            request.setAttribute("classNum", classNum);
            request.setAttribute("attend", attend);
            request.setAttribute("classList", classList);

            // フォームへフォワード
            request.getRequestDispatcher("/scoremanager/main/student_update.jsp").forward(request, response);
        } else {
            // バリデーションエラーがあった場合、フォームを再表示
            request.getRequestDispatcher("/scoremanager/main/student_update.jsp").forward(request, response);
        }
    }
}
