package ecommerce;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/recoverPassword")
public class RecoverPassword extends HttpServlet {
		public void doPost(HttpServletRequest request, HttpServletResponse response) {
			response.setContentType("text/html");

			String email = request.getParameter("user_email");
//			 RequestDispatcher rd = null;
			try {
				PrintWriter out = response.getWriter();
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("select * from users");
					while (rs.next()) {
						String rs_email = rs.getString(3);
						if (email.equals(rs_email)) {
							String rs_userName = rs.getString(5);
							String rs_password = rs.getString(6);
							out.println("<h1>"+"username: "+rs_userName+"</h1>");	
							out.println("<h1>"+"password: "+rs_password+"</h1>");	
				//process to send a mail with user_name and password
							return;
						} 
					}
					out.println("<h1>email doesn't exists</h1>");
//					rd = request.getRequestDispatcher("forgotPassword.html");
//					 rd.include(request, response);
					st.close();
					con.close();	
			} catch (Exception e) {
				System.out.println(e);
			}
		
		}
}
