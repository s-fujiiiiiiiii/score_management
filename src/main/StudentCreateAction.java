package main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class StudentCreateAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        HttpSession session = request.getSession();

        try {
            // 入力値取得
            int year = Integer.parseInt(request.getParameter("year"));
            String studentNum = request.getParameter("studentNum");
            String studentName = request.getParameter("studentName");
            String className = request.getParameter("className");

            // Execute に渡す
            StudentCreateExecuteAction execute = new StudentCreateExecuteAction();
            boolean result = execute.registerStudent(year, studentNum, studentName, className);

            // 登録成功時の処理
            if (result) {
                return "student_create_done.jsp"; // 成功画面へ
            } else {
                request.setAttribute("error", "登録に失敗しました。");
                return "studentCreate.jsp"; // 元の画面に戻す
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "エラーが発生しました。");
            return "studentCreate.jsp"; // エラー時も元の画面に戻す
        }
    }
}