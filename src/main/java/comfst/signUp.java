package comfst;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comfst.dao.usersDao;
import comfst.models.users;

public class signUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");

		users userModel = new users();
		userModel.setUsername(username);
		userModel.setPassword(password);
		userModel.setEmail(email);

		usersDao userDao = new usersDao();

		try {
			if (userDao.signUp(userModel)) {
				session.setAttribute("username", userModel.getUsername());
				res.sendRedirect("ListeEmail");
			} else {
				System.out.println("UserName Exists");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}