package comfst.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import comfst.SqlQuery;
import comfst.models.emails;

public class emailsDao {
	SqlQuery Sql = new SqlQuery();

	public void addEmail(emails emailModel) throws SQLException {
		String email = emailModel.getAddress();
		Sql.Execute("INSERT INTO emails (address) VALUES ('" + email + "')");

	}

	public void removeEmail(emails emailModel) throws SQLException {
		String email = emailModel.getAddress();
		Sql.Execute("DELETE FROM emails WHERE address='" + email + "'");

	}

	public List<String> getEmails() throws SQLException {
		List<String> listMail = new ArrayList<String>();
		Sql.ExecuteQuery("SELECT * FROM emails");
		while (Sql.Results.next()) {
			listMail.add(Sql.Results.getString(3));
		}
		return listMail;
	}
}
