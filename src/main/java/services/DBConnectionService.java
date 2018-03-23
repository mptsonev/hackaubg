package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionService {

	private Connection conn = null;

	
	public DBConnectionService() {
		try {
			this.conn = DriverManager.getConnection(
					"jdbc:postgres://K231CHL2mVF0yYum:l1yswwHrudHbMOo8@10.11.241.22:49971/uNzE4kBu9otGp4QJ",
					"K231CHL2mVF0yYum", "l1yswwHrudHbMOo8");
		} catch (SQLException e) {
			System.out.println("Error connection to db " + e);
		}		
	}
	

	public String getExampleDataFromDB() {
		String result = null;
		Statement st;
		try {
			st = conn.createStatement();

			ResultSet rs = st.executeQuery("SELECT name FROM test WHERE id = 1");
			while (rs.next()) {
				result = rs.getString(1);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			result = "Error trying to query the DB";
		}
		return result;
	}
}
