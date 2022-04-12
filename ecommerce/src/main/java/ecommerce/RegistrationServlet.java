package ecommerce;

import java.io.IOException;
import java.sql.Statement;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registrationServlet")
public class RegistrationServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");
		String name = request.getParameter("full_name");
		String email = request.getParameter("user_email");
		String phone_no = request.getParameter("phone_no");
		String u_name = request.getParameter("user_name");
		String password = request.getParameter("user_password");
		String confirmed_password = request.getParameter("confirmed_password");
		String gender = request.getParameter("gender");

		try {
			PrintWriter out = response.getWriter();
			if (!(password.equals(confirmed_password))) {
				out.println("password is not matching");
			} else if (password.length() < 5) {
				out.println("password is too short");
			} else {

				String query = "INSERT into users(name,email,ph_no,userName,password) values(?,?,?,?,?)";
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");
				Statement st = con.createStatement();

				ResultSet rs = st.executeQuery("select * from users");
				while (rs.next()) {
					String rs_email = rs.getString(3);
					String rs_userName = rs.getString(5);
					if (rs_email.equals(email)) {
						out.println("email already exists");
						st.close();
						return;
					} else if (rs_userName.equals(u_name)) {
						out.println("userName taken");
						st.close();
						return;
					}
				}
						PreparedStatement pst = con.prepareStatement(query);
						pst.setString(1, name);
						pst.setString(2, email);
						pst.setString(3, phone_no);
						pst.setString(4, u_name);
						pst.setString(5, password);
						int count = pst.executeUpdate();
						if (count > 0) {
							String createTable = "Create table "+u_name+"(id int(15) not null primary key ,"
									+ " name varchar(100) not null, img varchar(200), quantity int(15), price int(15) not null) ";
							st.execute(createTable);
							System.out.println("successfully created cart "+ u_name);
							createOrdersTable(con, u_name);
							System.out.println("successfully created orders table: "+ u_name);

							out.println("Successfully registered");
						} else {
							out.println("Failed to register");
						}
						pst.close();
					
					con.close();

			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void createOrdersTable(Connection con, String username)
			throws ClassNotFoundException, SQLException {
		String table_name = username+"_orders";
		Class.forName("com.mysql.jdbc.Driver");

		String query = "CREATE TABLE   " + table_name
				+ "(id int(50) primary key not null auto_increment, productid int(50) not null, name varchar(100) not null,  img varchar(100) not null, quantity int(50)  not null,  price int(50)  not null )";
		
		Statement st = con.createStatement();
		st.execute(query);
		st.close();
	}

}
