package ecommerce;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/showcart")
public class ShowCart extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			response.setHeader("Progma", "no-cache");
			response.setHeader("Expires", "0");

			
			HttpSession session = request.getSession(false);
			if (session != null) {
//			session.getAttribute("username");
				
			PrintWriter out = response.getWriter();
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");
			Statement st = con.createStatement();
			System.out.println("Show cart initiated: ......");
			displayCart(out);
			st.close();
			con.close();
			}else {
//				RequestDispatcher rd = request.getRequestDispatcher("loginPage.html");
				response.sendRedirect("loginPage.html");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void displayCart(PrintWriter printWriter) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(" select * from " + HomePageServlet.username);
//			System.out.println("Show cart : displayCart : start ......");

			int total_price = 0;
			int total_quantity = 0;
			printWriter.println("<html lang='en'>");
			printWriter.println("<head>");
			printWriter.println("<title>CART PAGE</title>");
			printWriter.println("<link rel='stylesheet' href='cart.css'>");
			printWriter.println("</head>");
			printWriter.println("<body>");
			printWriter.println("<div class='container'>");
			printWriter.println("<div class='header'><span id='c'>C<span>");
			printWriter.println("<span id='a'>A<span>");
			printWriter.println(" </span id='r'>R</span>");
			printWriter.println("</span id='t'>T</span>");
			printWriter.println("</div>");
//			System.out.println("In html code. .......");
			while (rs.next()) {
				String product_name = rs.getString(2);
				String product_img = rs.getString(3);
				int product_quantity = rs.getInt(4);
				int product_price = rs.getInt(5);
				total_price += product_price;
				total_quantity += product_quantity;
				printWriter.println(" <div class='flex-container'>");
				printWriter.println(" <div class='image' style='flex-grow: 1'><img src='" + product_img + "' alt="
						+ product_name + ">");
//				System.out.println("All clear in while loop of display cart");
				printWriter.println(" </div>");
				printWriter.println(" <div class='content c1' style='flex-grow: 5'>" + product_name + "</div>");
				printWriter.println(" <div class='content' style='flex-grow: 2'>" + product_quantity + "</div>");
				printWriter.println("<div class='content' style='flex-grow: 2'>" + product_price + "</div>");
				printWriter.println(" </div>");
			}
			printWriter.println("<div class='footer'>");
			printWriter.println("<div class='content' style='flex-grow: 2'>TOTAL PRICE: " + total_price + " </div>");
			printWriter.println(
					"<div class='content c2' style='flex-grow: 2'>TOTAL QUANTITY: " + total_quantity + "</div>");
			printWriter.println("</div>");
			printWriter.println("<div class='checkout'>");
			printWriter.println("<a href='paymentServlet'>");
			printWriter.println("CHECKOUT</a>");
			printWriter.println("</div>");
			printWriter.println("</div>");
			printWriter.println("</body>");
			printWriter.println("</html>");


		} catch (Exception e) {
			System.out.println(e);
		}
//		System.out.println("Show cart : displayCart : end ......");

	}

}
