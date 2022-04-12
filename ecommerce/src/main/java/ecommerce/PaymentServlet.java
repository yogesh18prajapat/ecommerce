package ecommerce;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/paymentServlet")
public class PaymentServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP:1.1
		response.setHeader("Progma", "no-cache");//HTTP:1.0
		response.setHeader("Expires", "0"); //Proxy

		try {
			PrintWriter out = response.getWriter();
			String successServlet = "successServlet";

			out.println("<html lang='en'>");
			out.println("<head>");
			out.println("<link href=\"https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap\" rel=\"stylesheet\">\r\n");
			out.println("<link rel='stylesheet' href='paymentsPage.css'>");
			out.println("</head>");
			out.println("<body>");
//			out.println("<form>");
			out.println("<div class='container'>");
			out.println("<h1>Confirm Your Payment</h1>");
			out.println("<div class='first-row'>");
			out.println("<div class='owner'>");
			out.println("<h3>Owner</h3>");
			out.println("<div class='input-field'>");
			out.println("<input type='text' required > ");
			out.println("</div>");
			out.println("</div>");
			out.println("<div class='cvv'>");
			out.println("<h3>CVV</h3>");
			out.println("<div class='input-field'>");
			out.println("<input type='password' required >");
			out.println("</div>");
			out.println("</div>");
			out.println("</div>");
			out.println("<div class='second-row'>");
			out.println("<div class='card-number'>");
			out.println("<h3>Card Number</h3>");
			out.println(" <div class='input-field'>");
			out.println("<input type='text' required >");
			out.println("</div>");
			out.println("</div>");
			out.println("</div>");
			out.println("<div class='third-row'>");
			out.println("<div class='cards'>");
			out.println("<img src='mc.png' alt='master card'>");
			out.println("<img src='vi.png' alt='visa card'>");
			out.println(" <img src='pp.png' alt='PayPal'>");
			out.println(" </div>");
			out.println("</div>");
			out.println("<a href='"+successServlet+"'>Confirm</a>");
			out.println("</div>");
//			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
