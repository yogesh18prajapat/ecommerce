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

@WebServlet("/mensProductList")
public class MensProductList extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");
		try {
			PrintWriter out = response.getWriter();
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");
				Statement st = con.createStatement();

				ResultSet rs = st.executeQuery(" select * from products where section = 'men'");
				out.println("<!DOCTYPE html>");
				out.println("<html lang='en'>");
				out.println("<head>");
				out.println("<title>Mens Product</title>");
				out.println("<link rel='stylesheet' href='cards.css'>");
				out.println("</head>");
				out.println("<body>");
				out.println(" <div class='cards_container'>");

				
				while (rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					int price = rs.getInt(3);
					String img = rs.getString(4);
					int oldPrice = rs.getInt(6);
					String servlet = "productDescription?id="+id;
					
					out.println("<div class='card'>");
					out.println("<a href='"+servlet+"'><img src='"+img+"' alt='"+name+"' style='width:100%'></a>");
					out.println("<div class='container'><a href='"+servlet+"'");
					out.println("<h4><b>"+name+"</b></h4>");
					out.println("<p><del>"+oldPrice+"</del></p>");
					out.println("<p><b>"+price+"</b></p>");
					out.println("</a></div>");
					out.println("</div>");
				}
				out.println("</div>");
				out.println("</body>");
				out.println("</html>");

				st.close();
				con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

