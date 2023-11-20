package comfst.dao;

import java.sql.SQLException;

import comfst.SqlQuery;
import comfst.models.users;

public class usersDao {

	public boolean login(users user) throws SQLException {
		String username = user.getUsername();
		String password = user.getPassword();

		if (UserExist(username, password)) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean signUp(users user) throws SQLException {
		String username = user.getUsername();
		String password = user.getPassword();
		String email = user.getEmail();
		SqlQuery sql = new SqlQuery();
		if (usernameExist(username)) {
			return false;
		} else {
			sql.Execute("INSERT INTO users (email,username,password) VALUES ('" + email + "','" + username + "','"
					+ password + "')");
			return true;
		}
	}

	public Boolean UserExist(String username, String password) throws SQLException {
		String dbUsername;
		String dbPassword;
		SqlQuery sql = new SqlQuery();

		sql.ExecuteQuery("SELECT username, password FROM users where username='" + username + "'");

		if (!sql.Results.next()) {
			return false;
		} else {
			dbUsername = sql.Results.getString(1);
			dbPassword = sql.Results.getString(2);
			if (dbUsername.equals(username) && dbPassword.equals(password)) {
				return true;
			}
			return false;
		}
	}

	private Boolean usernameExist(String username) throws SQLException {
		SqlQuery sql = new SqlQuery();
		sql.ExecuteQuery("SELECT username FROM users where username='" + username + "'");
		if (sql.Results.next()) {
			return true;
		}
		return false;
	}
}
