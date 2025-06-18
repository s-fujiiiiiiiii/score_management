package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import h2.DatabaseConnection;

@WebServlet("/main/TestListAction")
public class TestListAction extends HttpServlet {
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 🔹 パラメータ取得
        String studentNo = request.getParameter("studentNo");
        String entYear = request.getParameter("entYear");
        String classNum = request.getParameter("classNum");
        String subjectCd = request.getParameter("subjectCd");

        // 🔹 studentNoの取得状態をログ出力
        System.out.println("DEBUG: 取得した studentNo=" + studentNo);

        List<?> testScores = null;
        String studentName = null;
        String subjectName = null;
        int maxNo = 1;

        try {
            // 🔹 入学年度リストとクラスリストを取得
            StudentDao studentDao = new StudentDao();
            List<String> entYearList = studentDao.getEntYearList();
            request.setAttribute("entYearList", entYearList);
            List<String> classList = studentDao.getClassList();
            request.setAttribute("classList", classList);

            System.out.println("DEBUG: 取得した入学年度リストサイズ=" + entYearList.size());
            System.out.println("DEBUG: 取得したクラスリストサイズ=" + classList.size());

            if (studentNo != null && !studentNo.trim().isEmpty()) {
                System.out.println("生徒別検索を開始...");
                studentName = studentDao.getStudentName(studentNo);
                TestListStudentExecuteAction executeAction = new TestListStudentExecuteAction();
                testScores = executeAction.execute(studentNo);

                request.setAttribute("studentName", studentName);
                request.setAttribute("studentNo", studentNo);

            } else if (entYear != null && classNum != null && subjectCd != null
                    && !entYear.isEmpty() && !classNum.isEmpty() && !subjectCd.isEmpty()) {
                System.out.println("科目別検索を開始...");
                SubjectDao subjectDao = new SubjectDao();
                subjectName = subjectDao.getSubjectName(subjectCd);

                try (Connection con = DatabaseConnection.getConnection();
                     PreparedStatement stmt = con.prepareStatement("SELECT MAX(NO) FROM TEST WHERE SUBJECT_CD = ?")) {
                    stmt.setString(1, subjectCd);
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            maxNo = rs.getInt(1);
                            if (maxNo < 1) maxNo = 1;
                        }
                    }
                }

                TestListSubjectExecuteAction executeAction = new TestListSubjectExecuteAction();
                testScores = executeAction.execute(subjectCd, classNum, Integer.parseInt(entYear));

                request.setAttribute("subjectName", subjectName);
                request.setAttribute("maxNo", maxNo);
            } else {
                System.out.println("検索条件が不足しているため、検索をスキップします。");
            }

            // 🔹 科目リストを取得してリクエストへセット
            SubjectDao subjectDao = new SubjectDao();
            List<Subject> subjectList = subjectDao.getAllSubjects();
            request.setAttribute("subjectList", subjectList);
            System.out.println("DEBUG: 取得した科目リストのサイズ=" + subjectList.size());

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "検索中にエラーが発生しました。");
        }

        request.setAttribute("testScores", testScores);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/scoremanager/main/test_list.jsp");
        dispatcher.forward(request, response);
    }
}