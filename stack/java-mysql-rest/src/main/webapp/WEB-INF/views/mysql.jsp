<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.lang.*"%>
<%@ page import="java.sql.*"%>

<html>
<head>
<title>MySQL JDBC Execution JSP</title>
</head>
<body BGCOLOR=white>

	<%
		String jdbcurl = request.getParameter("jdbcurl");
		String dbuser = request.getParameter("dbuser");
		String dbpassword = request.getParameter("dbpassword");
		String sqlstmt = request.getParameter("sqlstmt");
		if (jdbcurl != null && dbuser != null && dbpassword != null && sqlstmt != null) {
	%>
	<h3>
		Execution results for : <i> <%=sqlstmt%>
		</I>
	</h3>
	<%=executeStmt(jdbcurl, dbuser, dbpassword, sqlstmt)%>
	<hr>
	<br>
	<%
		}
	%>

	<b>Enter parameters:</b>
	<form METHOD=get>
		<table>
			<tr>
				<th>Parameter</th>
				<th>Value</th>
			</tr>
			<tr>
				<td>JDBC connection string</td>
				<td><input type="text"
					value="jdbc:mysql://<%=System.getenv("MYSQLCS_CONNECT_STRING")%>?verifyServerCertificate=false&useSSL=false&requireSSL=false"
					size="120" name="jdbcurl"></td>
			</tr>
			<tr>
				<td>Database User</td>
				<td><input value="<%=System.getenv("MYSQLCS_USER_NAME")%>"
					type="text" size="120" name="dbuser"></td>
			</tr>
			<tr>
				<td>Database Password</td>
				<td><input type="text"
					value="<%=System.getenv("MYSQLCS_USER_PASSWORD")%>" size="120"
					name="dbpassword"></td>
			</tr>
			<tr>
				<td>SQL Statement</td>
				<td><input type="text" size="120" name="sqlstmt"></td>
			</tr>
		</table>
		<input type="submit" value="Execute">
	</form>
</body>
</html>
<%!private String executeStmt(String jdbcURL, String dbuser, String dbpassword, String sqlStmt)
			throws ClassNotFoundException {

		StringBuffer sbResult = new StringBuffer();

		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("MySQL DRIVER found");

		try (Connection conn = DriverManager.getConnection(jdbcURL, dbuser, dbpassword);
				Statement stmt = conn.createStatement();) {

			if (stmt.execute(sqlStmt)) {
				System.out.println("STMT query executed");
				ResultSet rset = stmt.getResultSet();
				sbResult.append("Query result:<br>");
				sbResult.append(formatResult(rset));
			} else {
				System.out.println("STMT update executed");
				sbResult.append("Number of rows affected: ");
				sbResult.append(stmt.getUpdateCount());
				sbResult.append("<br>");
			}

			return sbResult.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return ("<p> SQL error: <pre> " + e + " </pre> </p>\n");
		}
	}

	private String formatResult(ResultSet rset) throws SQLException {
		StringBuffer sb = new StringBuffer();
		if (!rset.next())
			sb.append("<p> No matching rows.<p>\n");
		else {
			sb.append("<ul><b>");
			do {
				sb.append("<li>" + rset.getString(1) + "</li>\n");
			} while (rset.next());
			sb.append("</b></ul>");
		}
		return sb.toString();
	}%>