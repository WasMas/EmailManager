package comfst;

import java.sql.*;

public class SqlQuery {
	public ResultSet Results;

	public void ExecuteQuery(String SqlQuery) throws SQLException {
		String url = "jdbc:postgresql://localhost/EmailManager?user=EmailManager&password=1234";
		Connection connect = DriverManager.getConnection(url);
		Results = connect.createStatement().executeQuery(SqlQuery);
		connect.close();
	}

	public void Execute(String SqlQuery) throws SQLException {
		String url = "jdbc:postgresql://localhost/EmailManager?user=EmailManager&password=1234";
		Connection connect = DriverManager.getConnection(url);
		connect.createStatement().execute(SqlQuery);
		connect.close();
	}
}