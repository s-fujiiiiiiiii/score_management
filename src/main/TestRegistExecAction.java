package main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import dao.StudentDao;
import tool.Action;

public class TestRegistExecAction extends Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        String entYear = req.getParameter("entYear");
        String classNum = req.getParameter("classNum");
        String subject  = req.getParameter("subject");
        String noParam = req.getParameter("no");
        int no = (noParam == null || noParam.isEmpty()) ? 1 : Integer.parseInt(noParam);

        String[] studentNumbers = req.getParameterValues("studentNumber");
        String[] scores = req.getParameterValues("score");

        HttpSession session = req.getSession();
        String schoolCd = (String) session.getAttribute("school_cd");
        if (schoolCd == null) {
            schoolCd = "oom";
            session.setAttribute("school_cd", schoolCd);
        }

        StudentDao dao = new StudentDao();
        boolean hasError = false;

        for (int i = 0; i < studentNumbers.length; i++) {
            if (scores[i] != null && !scores[i].isEmpty()) {
                try {
                    int score = Integer.parseInt(scores[i]);

                    // ✅ 0～100 の範囲チェック
                    if (score < 0 || score > 100) {
                        req.setAttribute("message", "点数は 0～100 の範囲で入力してください");
                        req.setAttribute("testScores", dao.getStudents(entYear, classNum, "true")); // 戻る際にデータを渡す
                        req.setAttribute("entYearList", dao.getEntYearList());
                        req.setAttribute("classList", dao.getClassList());
                        req.setAttribute("subjectList", dao.getSubjectList());
                        return "/scoremanager/main/test_regist.jsp"; // `test_regist.jsp` に戻る
                    }
                } catch (NumberFormatException e) {
                    req.setAttribute("message", "点数は数値で入力してください");
                    hasError = true;
                    break;
                }
            }
        }

        req.setAttribute("testScores", dao.getStudents(entYear, classNum, "true"));
        req.setAttribute("entYearList", dao.getEntYearList());
        req.setAttribute("classList", dao.getClassList());

        // ✅ `subjectList` のデータ型が `List<Subject>` になっている
        List<Subject> subjectList = dao.getSubjectList();
        req.setAttribute("subjectList", subjectList);

        // ✅ エラーがある場合は `test_regist.jsp` に戻る
        if (hasError) {
            return "/scoremanager/main/test_regist.jsp";
        }

        // ✅ 正常時はデータを登録する
        for (int i = 0; i < studentNumbers.length; i++) {
            if (scores[i] != null && !scores[i].isEmpty()) {
                int score = Integer.parseInt(scores[i]);
                dao.insertScore(studentNumbers[i], subject, no, score, schoolCd);
            }
        }

        req.setAttribute("message", "登録が完了しました");
        return "/scoremanager/main/test_regist_done.jsp";
    }
}