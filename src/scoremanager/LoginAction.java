package scoremanager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;

@WebServlet("/login")
public class LoginAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String id = request.getParameter("id").trim();
        String password = request.getParameter("password").trim();

        TeacherDao dao = new TeacherDao();
        Teacher teacher = null;
		try {
			teacher = dao.search(id, password);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

        if (teacher != null) {
            session.setAttribute("teacher", teacher);
            request.getRequestDispatcher("login-out.jsp").forward(request, response);
        } else if (id == null || id.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("errorMessage", "このフィールドを入力してください");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
        	request.setAttribute("errorMessage", "IDまたはパスワードが確認できませんでした");
            request.setAttribute("enteredId", id); // 入力されたIDを保持
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }


}