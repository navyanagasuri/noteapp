import java.io.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;
public class Login extends HttpServlet
{
	String username;
 public void doPost(HttpServletRequest request, HttpServletResponse
response) throws ServletException, IOException
 {
 String JDBC_DRIVER = "com.mysql.jdbc.Driver";
 String DB_URL="jdbc:mysql://localhost/login";

 String USER = "root";
 String PASS = "varshi14";
 response.setContentType("text/html");
 PrintWriter out = response.getWriter();

 username=request.getParameter("username");
 String password=request.getParameter("password");

 try {
 // Register JDBC driver
 Class.forName("com.mysql.jdbc.Driver");
 // Open a connection
 Connection conn = DriverManager.getConnection(DB_URL, USER,
PASS);
 // Execute SQL query
 Statement stmt = conn.createStatement();
 String sql="SELECT username,password FROM log";
 ResultSet rs = stmt.executeQuery(sql);
out.println("<!DOCTYPE html>"+
 "<html>"+
"<head>"+
"<meta charset='utf-8'>"+
"<meta name='viewport' content='width=device-width, initial-scale=1.0'>"+
"<link rel='stylesheet' href='style.css'>"+
"<title>Login</title>"+
"</head>"+
"<body><center><br>");
 // Extract data from result set
int flag=0;
 while(rs.next())
{
 //Retrieve by column name
String uname= rs.getString("username");
String pass= rs.getString("password");
//out.print(uname);
//out.print(pass);
if(uname.equals(username) &&
pass.equals(password))
{
out.println("<h1></center>"+
"<script>"+
"setTimeout(view, 500);"+
"function view()"+
"{window.location.replace('http://localhost:8080/noteapp-main/home.html');}"+"</script>"+
"</body>"+
"</html>");
HttpSession session = request.getSession();
session.setAttribute("uname",username);
session.setAttribute("pass",password);
flag=1;
break;
}
 }
if(flag==0)
{
out.println("<h1>Invalid Login<br><br>Register"+
"</center>"+
"<script>"+
"setTimeout(view, 1500);"+
"function view()"+
"{window.location.replace('http://localhost:8080/noteapp-main/index.html');}"+
 "</script>"+
"</body>"+
"</html>");
}
 // Clean-up environment
 rs.close();
 stmt.close();
 conn.close();
out.close();
 } catch(SQLException se) {
 //Handle errors for JDBC
 se.printStackTrace();
 } catch(Exception e) {
 //Handle errors for Class.forName
 e.printStackTrace();
 }
}
}


class Notes extends HttpServlet
{
 public void doPost(HttpServletRequest request, HttpServletResponse
response) throws ServletException, IOException
 {
 String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
 String DB_URL="jdbc:mysql://localhost/login";

 String USER = "root";
 String PASS = "varshi14";
 response.setContentType("text/html");
 PrintWriter out = response.getWriter();

 /*String user_name=request.getParameter("username");
 String pwd=request.getParameter("password");*/

 Login l= new Login();
 String u=l.username;
 out.print(u);
 String note=request.getParameter("title-field");

out.println("<h1>"+
"</center>"+
"<script>"+
"window.alert("+u+note+");"+"setTimeout(view, 500);</script>"+
"</body>"+
"</html>");


 try {
 // Register JDBC driver
 Class.forName("com.mysql.jdbc.Driver");
 // Open a connection
 Connection conn = DriverManager.getConnection(DB_URL, USER,
PASS);
 PreparedStatement st = conn.prepareStatement("update log set notes=? where username="+u);
st.setString(1, note);
/*st.setString(2, request.getParameter("password"));*/
 // Execute SQL query
 st.executeUpdate();
 /*out.println("<h1>"+
"</center>"+
"<script>"+
"window.alert('Registration Successful!');"+"setTimeout(view, 500);"+"function view()"+
"{window.location.replace('http://localhost:8080/noteapp-main/login.html');}"+"</script>"+
"</body>"+
"</html>");*/
 st.close();
 // Clean-up environment
 conn.close();
out.close();
 } catch(SQLException se) {
 //Handle errors for JDBC
 se.printStackTrace();
 } catch(Exception e) {
 //Handle errors for Class.forName
 e.printStackTrace();
 }
}
}