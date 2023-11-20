package comfst.controllers;

import javax.servlet.http.HttpServlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comfst.dao.emailsDao;
import comfst.models.emails;

public class ListeEmail extends HttpServlet {

	private static final long serialVersionUID = 1L;

	emails emailModel = new emails();
	emailsDao emailDao = new emailsDao();

	private String chemin = "D:/Study/JEE/EmailManager/Text/EmailManager.txt";
	private List<String> listMail = new ArrayList<String>();
	private String erreur;

	public void init() {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(chemin));
			listMail = (List<String>) in.readObject();
			in.close();
		} catch (Exception e) {
			erreur = e.toString();
			System.out.println(erreur);
		}
	}

	private void save(List<String> addresses) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(chemin))) {
			for (String address : addresses) {
				writer.write(address);
				writer.newLine(); // Pour passer à la ligne suivante
			}
		}
	}

	public void PrintMailList(HttpServletResponse res) throws IOException {
		emailsDao emailsDao = new emailsDao();
		PrintWriter out = res.getWriter();
		listMail.clear();
		out.println("<h1>Membres: </h1>");
		out.println("<ul>");
		try {
			listMail = emailsDao.getEmails();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < listMail.size(); i++) {
			String element = listMail.get(i);
			out.println("<li>" + element + "</li>");
		}

		save(listMail);
		out.println("</ul>");
	}

	private void loggedIn(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("username") == null) {
			res.sendRedirect("index.jsp");
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		loggedIn(req, res);
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		out.println("<HTML>");
		out.println("<head><title>gerer mail</title></head>");
		out.println("<body>");
		out.println("<a href=\"logout\">Log Out</a>");
		out.println("<p>Entrer votre adresse email: </p><form method='POST'>");
		out.println("<input type='email' name='email' placeholder='Votre adresse e-mail' required>");
		out.println("<input type='submit' name='action' value='subscribe'>"); // Bouton "subscribe"
		out.println("<input type='submit' name='action' value='unsubscribe'>"); // Bouton "unsubscribe"
		out.println("<hr style=\"margin-top:30px\">");
		PrintMailList(res);
		out.println("</body>");
		out.println("</HTML>");
	}

	public Boolean MailDomainValid(String email) throws IOException {
		String domainsPath = "D:/Study/JEE/EmailManager/Text/Emails.txt";
		String domain = email.substring(email.indexOf("@") + 1);
		try (BufferedReader br = new BufferedReader(new FileReader(domainsPath))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (domain.equals(line)) {
					return true;
				}
			}
		}
		return false;
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		PrintWriter out = res.getWriter();

		String email = req.getParameter("email");
		String action = req.getParameter("action");

		emailModel.setAddress(email);

		res.setContentType("text/html");
		out.println("<HTML>");
		out.println("<head><title>Gerer mail</title></head>");
		out.println("<body>");

		if ("subscribe".equals(action)) {
			if (listMail.contains(email) || !MailDomainValid(email)) {
				out.println("Email Deja Utilisé/ Invalide!");
			} else {
				try {
					emailDao.addEmail(emailModel);
					out.println("l adresse email '" + email + "'  " + "est ajoutee avec succes");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		if ("unsubscribe".equals(action)) {
			if (!listMail.contains(email)) {
				out.println("Email Pas trouvé!");
			} else {
				try {
					emailDao.removeEmail(emailModel);
					out.println("l adresse email '" + email + "'  " + "est supprimer avec succes");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		out.println("<hr>");
		PrintMailList(res);
		out.println("<hr>");
		out.println("</body>");
		out.println("<a href='ListeEmail'>Retourner a la liste</a>");
	}
}