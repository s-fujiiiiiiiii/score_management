package main;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.StudentDao;
import dao.SubjectDao;

@WebServlet("/main/TestListAction")
public class TestListAction extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentNo = request.getParameter("studentNo");
        String entYear = request.getParameter("entYear");
        String classNum = request.getParameter("classNum");
        String subjectCd = request.getParameter("subjectCd");

        List<?> testScores = null;

        System.out.println("リクエスト URL: " + request.getRequestURL());
        System.out.println("リクエスト QueryString: " + request.getQueryString());
        System.out.println("リクエストから受け取った studentNo: " + studentNo);

        try {
            if (studentNo != null && !studentNo.trim().isEmpty()) {
                System.out.println("学生番号による検索を開始...");
                TestListStudentExecuteAction executeAction = new TestListStudentExecuteAction();
                testScores = executeAction.execute(studentNo);
            } else if (entYear != null && classNum != null && subjectCd != null
                        && !entYear.isEmpty() && !classNum.isEmpty() && !subjectCd.isEmpty()) {
                System.out.println("科目別検索を開始...");
                TestListSubjectExecuteAction executeAction = new TestListSubjectExecuteAction();
                testScores = executeAction.execute(subjectCd, classNum, Integer.parseInt(entYear));
            } else {
                System.out.println("初回アクセス時のため、検索を実行しません。");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "検索中にエラーが発生しました。");
        }

        request.setAttribute("testScores", testScores);

        try {
            StudentDao studentDao = new StudentDao();
            List<String> entYearList = studentDao.getYearList();
            List<String> classList = studentDao.getClassList();

            SubjectDao subjectDao = new SubjectDao();
            List<Subject> subjectList = subjectDao.search("");

            request.setAttribute("entYearList", entYearList);
            request.setAttribute("classList", classList);
            request.setAttribute("subjectList", subjectList);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "検索データの取得中にエラーが発生しました。");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/scoremanager/main/test_list.jsp");
        dispatcher.forward(request, response);
        System.out.println("検索結果サイズ: " + (testScores != null ? testScores.size() : "NULL"));
        System.out.println("testScoresがJSPへ渡される: " + (testScores != null ? testScores.size() : "NULL"));
    }
}
