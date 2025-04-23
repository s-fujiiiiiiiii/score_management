package main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;
import tool.Action;

public class StudentCreateAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            // 入力値取得
            int year = Integer.parseInt(request.getParameter("year"));
            String studentNum = request.getParameter("studentNum");
            String studentName = request.getParameter("studentName");
            String className = request.getParameter("className");

            // StudentDao をインスタンス化
            StudentDao dao = new StudentDao();

            // 学生番号の重複チェック
            if (dao.exists(studentNum)) {
                // 重複している場合、エラーメッセージをリクエストにセット
                request.setAttribute("error", "学生番号が重複しています。");
                return "/scoremanager/main/student_create.jsp";  // 元の画面に戻す
            }

            // 重複していなければ登録処理を進める
            StudentCreateExecuteAction execute = new StudentCreateExecuteAction();
            boolean result = execute.registerStudent(year, studentNum, studentName, className);

            // 登録成功時の処理
            if (result) {
                return "/scoremanager/main/student_create_done.jsp";  // 成功画面へ
            } else {
                request.setAttribute("error", "登録に失敗しました。");
                return "/scoremanager/main/student_create.jsp";  // 元の画面に戻す
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "エラーが発生しました。");
            return "/scoremanager/main/student_create.jsp";  // エラー時も元の画面に戻す
        }
    }
}
