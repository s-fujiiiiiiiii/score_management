package main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDao;

@WebServlet("/SubjectCreateAction")
public class SubjectCreateAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // フォームからデータを取得
            String schoolCd = request.getParameter("schoolCd");
            String cd = request.getParameter("cd");
            String name = request.getParameter("name");

            // Subjectオブジェクトを作成
            Subject subject = new Subject();
            subject.setSchoolCd(schoolCd);
            subject.setCd(cd);
            subject.setName(name);

            // データベースに保存
            SubjectDao dao = new SubjectDao();
            dao.insert(subject); // insertメソッドを使用

            // 登録完了画面へリダイレクト
            request.getRequestDispatcher("/scoremanager/main/subject_create_done.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}