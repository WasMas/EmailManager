package comfst.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comfst.error;
import comfst.dao.usersDao;
import comfst.models.users;

public class login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	int loginAttempts = 3;
	error error = new error();
	users userModel = new users();
	usersDao userDao = new usersDao();

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		HttpSession session = req.getSession();

		userModel.setUsername(username);
		userModel.setPassword(password);

		try {
			if (userDao.login(userModel)) {
				session.setAttribute("username", userModel.getUsername());
				res.sendRedirect("ListeEmail");
			} else {
				if (loginAttempts !=0) {
					loginAttempts--;
					error.errorMessage(req, res, "Nom d'utilisateur ou mot de passe incorrect, tentatives de connexion restantes: "+loginAttempts);
				}
				else
					error.errorMessage(req, res, "Aucune tentative de connexion restante, revenez plus tard");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
