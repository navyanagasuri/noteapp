import java.io.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;
public class Reg extends HttpServlet
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

 String user_name=request.getParameter("username");
 String pwd=request.getParameter("password");

 try {
 // Register JDBC driver
 Class.forName("com.mysql.jdbc.Driver");
 // Open a connection
 Connection conn = DriverManager.getConnection(DB_URL, USER,
PASS);
 PreparedStatement st = conn.prepareStatement("insert into log(username,password) values(?, ?)");
st.setString(1, request.getParameter("username"));
st.setString(2, request.getParameter("password"));
 // Execute SQL query
 st.executeUpdate();
 out.println("<h1>"+
"</center>"+
"<script>"+
"window.alert('Registration Successful!');"+"setTimeout(view, 500);"+"function view()"+
"{window.location.replace('http://localhost:8080/noteapp-main/index.html');}"+"</script>"+
"</body>"+
"</html>");
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

