package main;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import dao.SubjectDao;

public class TestRegistAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nextPage = execute(request, response);
            if (nextPage != null) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/scoremanager/main/" + nextPage);
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        StudentDao studentDao = new StudentDao();
        SubjectDao subjectDao = new SubjectDao();

        // フォームからの値を取得
        String entYear = request.getParameter("entYear");
        String classNum = request.getParameter("classNum");
        String subject = request.getParameter("subject");
        String noParam = request.getParameter("no");

        // セレクトボックス用データをセット（常に設定）
        request.setAttribute("entYearList", studentDao.getEntYearList());
        request.setAttribute("classList", studentDao.getClassList());
        request.setAttribute("subjectList", subjectDao.getAllSubjects());

        // **検索ボタン (`name="search"`) が押された場合のみ処理を実行**
        if (request.getParameter("search") != null) {
            // 入学年度、クラス、科目、回数のいずれかが未入力の場合
            if (entYear == null || entYear.isEmpty() ||
                classNum == null || classNum.isEmpty() ||
                subject == null || subject.isEmpty() ||
                noParam == null || noParam.isEmpty()) {

                request.setAttribute("message", "入学年度とクラスと科目と回数を選択してください");
                return "test_regist.jsp";
            }

            // 成績リストを取得
            List<Student> testScores = studentDao.getStudents(entYear, classNum, "true");
            if (testScores == null || testScores.isEmpty()) {
                request.setAttribute("message", "該当する成績がありません");
            } else {
                request.setAttribute("testScores", testScores);
            }
        }

        return "test_regist.jsp";
    }
}