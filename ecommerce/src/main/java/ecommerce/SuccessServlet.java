package ecommerce;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/successServlet")
public class SuccessServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");

		try {
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			response.setHeader("Progma", "no-cache");
			response.setHeader("Expires", "0");

			
			PrintWriter out = response.getWriter();
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Success servlet Invoked");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");
			Statement st = con.createStatement();
			
			
			System.out.println(HomePageServlet.username);
//			String homePage = "homePageServlet?user_name="+HomePageServlet.username+"& user_password="+HomePageServlet.password;
			String cart_table_name = HomePageServlet.username;
			String order_table_name =cart_table_name+"_orders";

			addToRecentOrder(cart_table_name, order_table_name);
			st.execute("truncate table " + cart_table_name);
			System.out.println("Successfully deleted " + HomePageServlet.username);
			out.println("<html lang='en'>");
			out.println("<head>");
			out.println("<title>Success Page</title>");
			out.println("<link rel='stylesheet' href='successpage.css'>");
			out.println("</head>");

			out.println("<body>");
			
			
			
			out.println("<form id='myform' method='POST' action='homePageServlet'>");
			out.println(" <input type='text' name='user_name' value='"+HomePageServlet.username+"' hidden />");
			out.println(" <input type='text' name='user_password' value='"+HomePageServlet.password+"' hidden />");
			out.println(" </form>");
			
			
			out.println("<div class='container'>");
			out.println("<div class='header flex'>");
			out.println("<p  class='top_bottom'>PAYMENT SUCCESSFULL</p>");
			out.println("</div>");
			out.println("<div class='middle'>");
			out.println("<div class='thank'>");
			out.println("<p>THANK YOU</p>");
			out.println("<p>FOR</p>");
			out.println("<p>SHOPPING</p>");
			out.println("</div>");
			out.println("</div>");
			out.println("<div class='footer flex'>");
			
			
			
//			out.println("<input type='submit' form='myform' value= 'Go to home' >");
			out.println("<input type='submit' form='myform' id='home' value='Return to Home page'");

			
			
	
//			out.println("<a href='"+homePage+"'> <p class='top_bottom'>  Go to home </p></a>");
			out.println("</div>");
			out.println("</div>");
			out.println("</body>");

			out.println("</html>");
			st.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void addToRecentOrder(String cart_table_name, String order_table_name) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ecommerce", "root", "root");
		
		//query to read data from cart
		String readquery = "select * from "+cart_table_name;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(readquery);
		
		//query to write data into orders
		String writequery = "INSERT INTO " + order_table_name + "(productid,name,img,quantity,price) values(?,?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(writequery);
		
		while(rs.next()) {
			//reading data from cart
		int productid =	rs.getInt(1);
		String name = rs.getString(2);
		String img = rs.getString(3);
		int quantity =	rs.getInt(4);
		int price =	rs.getInt(5);

		//writing data into orders
				pst.setInt(1, productid);
				pst.setString(2, name);
				pst.setString(3, img);
				pst.setInt(4, quantity);
				pst.setInt(5, price);
				pst.executeUpdate();
				System.out.println("Successfully uploaded into: "+order_table_name);
				
		}
		st.close();
		rs.close();
		pst.close();
		con.close();
		
	}
}