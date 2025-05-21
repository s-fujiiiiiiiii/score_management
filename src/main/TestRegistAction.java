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
        StudentDao dao = new StudentDao();

        String entYear = request.getParameter("entYear");
        String classNum = request.getParameter("classNum");

        request.setAttribute("entYearList", dao.getYearList());
        request.setAttribute("classList", dao.getClassList());
        request.setAttribute("subjectList", dao.getSubjectList());

        if (entYear != null && !entYear.isEmpty() && classNum != null && !classNum.isEmpty()) {
            List<Student> testScores = dao.getStudents(entYear, classNum, "true");
            request.setAttribute("testScores", testScores);
        }

        return "test_regist.jsp";
    }
}
