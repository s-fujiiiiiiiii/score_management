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
import bean.TestListStudent;
import dao.StudentDao;
import dao.SubjectDao;

@WebServlet("/main/TestListAction")
public class TestListAction extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentNo = request.getParameter("studentNo");
        String entYear = request.getParameter("entYear");
        String classNum = request.getParameter("classNum");
        String subjectCd = request.getParameter("subjectCd");

        List<TestListStudent> testScores = null;

        try {
            if (studentNo != null && !studentNo.isEmpty()) {
                //  学生番号で検索
                TestListStudentExecuteAction executeAction = new TestListStudentExecuteAction();
                testScores = executeAction.execute(studentNo);
            } else if (entYear != null && classNum != null && subjectCd != null) {
                //  入学年度・クラス・科目で検索
                TestListSubjectExecuteAction executeAction = new TestListSubjectExecuteAction();
                testScores = executeAction.execute(subjectCd, classNum, Integer.parseInt(entYear));
            }
        } catch (Exception e) {
            e.printStackTrace(); // サーバーコンソールにエラーログ出力
            request.setAttribute("errorMessage", "検索中にエラーが発生しました。");
        }

        // リスト取得も `try-catch` で保護
        try {
            StudentDao studentDao = new StudentDao();
            List<String> entYearList = studentDao.getYearList();
            List<String> classList = studentDao.getClassList();

            SubjectDao subjectDao = new SubjectDao();
            List<Subject> subjectList = subjectDao.search("");

            request.setAttribute("entYearList", entYearList);
            request.setAttribute("classList", classList);
            request.setAttribute("subjectList", subjectList);
            request.setAttribute("testScores", testScores);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "検索データの取得中にエラーが発生しました。");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/scoremanager/main/test_list.jsp");
        dispatcher.forward(request, response);
    }
}