	package main;

	import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import dao.StudentDao;
import tool.Action;

	public class TestRegistExecuteAction extends Action {

	    @Override
	    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

	        String entYear = req.getParameter("entYear");
	        String classNum = req.getParameter("classNum");
	        String subject  = req.getParameter("subject");
	        int    no    = Integer.parseInt(req.getParameter("no"));

	        String[] studentNumbers = req.getParameterValues("studentNumber");
	        String[] scores         = req.getParameterValues("score");

	        // セッションから schoolCd 取得、なければ固定値セット
	        HttpSession session = req.getSession();
	        String schoolCd = (String) session.getAttribute("school_cd");
	        if (schoolCd == null) {
	            schoolCd = "SCHOOL001";
	            session.setAttribute("school_cd", schoolCd);
	        }

	        StudentDao dao = new StudentDao();

	        for (int i = 0; i < studentNumbers.length; i++) {
	            if (scores[i] != null && !scores[i].isEmpty()) {
	                int score = Integer.parseInt(scores[i]);
	                // schoolCd を含めて呼び出し
	                dao.insertScore(studentNumbers[i], subject, no, score, schoolCd);
	            }
	        }

	        // 次の画面表示用データをセット
	        req.setAttribute("entYearList", dao.getEntYearList());
	        req.setAttribute("classList", dao.getClassList());
	        req.setAttribute("subjectList", dao.getSubjectList());

	        List<Student> list = dao.getStudents(entYear, classNum, "true");
	        req.setAttribute("testScores", list);
	        req.setAttribute("message", "登録が完了しました");

	        return "/scoremanager/main/test_regist_done.jsp";
	    }
	}
