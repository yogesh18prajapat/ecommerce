package ecommerce;

import java.io.IOException;
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
import javax.servlet.http.HttpSession;

@WebServlet("/productDescription")
public class ProductDescriptionServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		int id = Integer.parseInt(request.getParameter("id"));
		
		
		try {
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			response.setHeader("Progma", "no-cache");
			response.setHeader("Expires", "0");

			HttpSession session = request.getSession(false);
			if(session != null) {
//			session.getAttribute("username");
			PrintWriter out = response.getWriter();
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(" select * from products where id = " + id);
			out.println("<!DOCTYPE html>");
			out.println("<html lang='en'>");
			out.println("<head>");
			out.println("<title>Product Details</title>");
			out.println("<link href=\"https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap\" rel=\"stylesheet\">\r\n");
			out.println("<link rel='stylesheet' href='productDetailPage.css'>");
			out.println("</head>");
			out.println("<body>");
			out.println(" <div class='container'>");

			while (rs.next()) {
				String name = rs.getString(2);
				int price = rs.getInt(3);
				String img = rs.getString(4);
				int oldPrice = rs.getInt(6);
				String description = "PRODUCT ID: "+id;
				String cart = "cart?id="+id;
				String c = "showcart";
				out.println("<div class='left'>");
				out.println("<img id='image' src='"+img+"' alt='"+name+"'>");
				out.println("<div class='content'>");
				out.println("<p id='name'>"+name+"</p>");
				out.println("<p id='price'><del id='oldprice'>"+oldPrice+"</del>&nbsp;&nbsp;"+price+"</p>");
				out.println("<div class='buttons'>");
				out.println("<a href='"+cart+"'>");
				out.println("<button class='btn1'>ADD TO CART</button></a>");
				out.println("<a href='"+cart+"'>");
				System.out.println("sending data : "+id);
				out.println("<button class='btn2'>BUY NOW</button></a>");
				out.println("</div>");
				out.println("</div>");
				out.println("</div>");
				out.println("<div class='right'>");
				out.println("<h1>DESCRIPTION</h1>");
				out.println("<hr/>");
				out.println(" <div class='description'>");
				out.println("<p>"+description+"</p>");
				out.println("<a href='"+c+"'>");
				out.println("<p class='bottom_btn'>GO TO CART</p></a>");
				out.println("</div>");
				out.println("</div>");
			}
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");

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
}
