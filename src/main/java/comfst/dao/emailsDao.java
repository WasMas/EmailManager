package comfst.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import comfst.SqlQuery;
import comfst.models.emails;

public class emailsDao {

	public Boolean addEmail(emails email) {

		return true;
	}

	public Boolean removeEmail() {
		return true;
	}

	public List<String> getEmails() throws SQLException {
		List<String> listMail = new ArrayList<String>();
		SqlQuery Sql = new SqlQuery();
		Sql.ExecuteQuery("SELECT * FROM emails");
		while (Sql.Results.next()) {
			listMail.add(Sql.Results.getString(3));
		}
		return listMail;
	}
}
