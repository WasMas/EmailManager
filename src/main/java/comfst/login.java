package comfst;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comfst.dao.usersDao;
import comfst.models.users;

public class login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		HttpSession session = req.getSession();
		users userModel = new users();
		userModel.setUsername(username);
		userModel.setPassword(password);

		usersDao userDao = new usersDao();

		try {
			if (userDao.login(userModel)) {
				session.setAttribute("username", userModel.getUsername());
				res.sendRedirect("ListeEmail");
			} else {
				System.out.println("Maheyesh");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
