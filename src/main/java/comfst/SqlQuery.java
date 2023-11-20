package comfst;

import java.sql.*;

public class SqlQuery {
	public ResultSet Results;
	String url = "jdbc:postgresql://localhost/EmailManager?user=EmailManager&password=1234";
	Connection connect;

	public void init() throws SQLException {
		connect = DriverManager.getConnection(url);
	}

	public void ExecuteQuery(String SqlQuery) throws SQLException {

		Results = connect.createStatement().executeQuery(SqlQuery);
		connect.close();
	}

	public void Execute(String SqlQuery) throws SQLException {
		Connection connect = DriverManager.getConnection(url);
		connect.createStatement().execute(SqlQuery);
		connect.close();
	}
}