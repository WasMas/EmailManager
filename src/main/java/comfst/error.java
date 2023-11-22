package comfst;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class error extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	public void errorMessage (HttpServletRequest req, HttpServletResponse res ,String errorMessage) throws IOException {
		HttpSession session = req.getSession();
		session.setAttribute("error", errorMessage);
		res.sendRedirect("error.jsp");
	}
}