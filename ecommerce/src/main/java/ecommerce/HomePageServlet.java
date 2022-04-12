package ecommerce;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/homePageServlet")
public class HomePageServlet extends HttpServlet {
	
	
	public static String username = "";
	public static String password = "";


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//	    LoginFilter.login_user = true;
		response.setContentType("text/html");
		username = request.getParameter("user_name");
		password = request.getParameter("user_password");

		HttpSession session = request.getSession();
		session.setAttribute("username", username);
		PrintWriter out = response.getWriter();
//		out.println("<h1>HOME PAGE</h1>");
		String servlet = "mensProductList";

		out.println("<html lang='en'><head>");
		out.println("<title>HOME PAGE</title>");
		out.println("<link rel='stylesheet' href='homepage.css'>");
		out.println("</head><body>");

		out.println("<div>");
		out.println("<h1 class='nav'>");
		out.println("<a href='logout'>");
		out.println(" <button class='logout'>LOGOUT</button></a>");
		out.println(" Summer <br> sales");
		out.println(" </h1>");
		out.println("</div>");
		out.println(" <div class='col'>");
		out.println(" <div>");
		out.println(" <a href='"+servlet+"'>");
		out.println(" <img src='https://m.media-amazon.com/images/I/81ktialOdLL._UX569_.jpg' alt='Men Section'></a>");
		out.println("  <div class='btm'><a href='"+servlet+"'>Men's</a></div>");
		out.println(" </div><div>");
		out.println("  <a href='Link to mens product list'>");
		out.println(
				"  <img src='https://40plusstyle.com/wp-content/uploads/2019/08/monikahandbag.jpg' alt='Women Section'></a>");
		out.println(" <div class='btm'><a href='Link to mens product list'>Womens</a></div>");
		out.println(" </div><div>");
		out.println(" <a href='Link to mens product list'>");
		out.println(" <img src='https://m.media-amazon.com/images/I/81T9k3QhvpL._UY741_.jpg' alt='Kid Section'></a>");
		out.println("  <div class='btm s'><a href='Link to mens product list'>Childern's</a></div>");
		out.println(" </div></div>");

		out.println("</body></html>");
		System.out.println("HOME PAGE");
//		out.println("<h1><a href='mensProductList'>mensProductList</a></h1>");
		out.close();
	}

}
