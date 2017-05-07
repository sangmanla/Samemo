import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import sam.util.Util;
import sam.vo.RouteList;

public class GatherTTCInformation {
	private static String DB_URL, DB_USER, DB_PW, URL_ROUTE_LIST, URL_ROUTE_CONFIG, URL_PREDICTIONS;

	public static void main(String args[]) throws Exception {
		readEnvInformation();
		RouteList routeList = (RouteList) Util.getParsingObject(RouteList.class,
				"http://webservices.nextbus.com/service/publicXMLFeed?command=routeList&a=ttc");
		System.out.println(routeList);
	}

	private static void readEnvInformation() {
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = GatherTTCInformation.class.getClassLoader().getResourceAsStream("env.properties");
			prop.load(input);
			DB_URL = prop.getProperty("DB_URL");
			DB_USER = prop.getProperty("DB_USER");
			DB_PW = prop.getProperty("DB_PW");
			
			URL_ROUTE_LIST = prop.getProperty("URL_ROUTE_LIST");
			URL_ROUTE_CONFIG = prop.getProperty("URL_ROUTE_CONFIG");
			URL_PREDICTIONS = prop.getProperty("URL_PREDICTIONS");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void dbProcess() throws Exception {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM BUS";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				int age = rs.getInt("age");
				String first = rs.getString("first");
				String last = rs.getString("last");

				System.out.print("ID: " + id);
				System.out.print(", Age: " + age);
				System.out.print(", First: " + first);
				System.out.println(", Last: " + last);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

	}
}