package ecommerce;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/cart")
public class Cart extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));

		try {
			HttpSession session = request.getSession(false);
			
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			response.setHeader("Progma", "no-cache");
			response.setHeader("Expires", "0");

			if (session != null) {

				PrintWriter out = response.getWriter();
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");
				Statement st = con.createStatement();
				System.out.println("Adding data into cart : " + id);
				int product_id = 0;
				String product_name = "";
				int product_price = 0;
				String product_img = "";
				int product_quantity = 1;
				ResultSet rs = st.executeQuery(" select * from products where id =" + id);
				while (rs.next()) {
					product_id = rs.getInt(1);
					product_name = rs.getString(2);
					product_price = rs.getInt(3);
					product_img = rs.getString(4);
				}
				rs.close();
				st.close();

				boolean contains = containsProduct(con, product_id);
				
				if(contains) {
					int newQuntity = getQuantity(con, product_id);
					updateQunatity(con, product_id, newQuntity);
				}else {
					addProductToCart(con, product_id, product_name, product_img, product_quantity, product_price);
				}
		
				con.close();
//				ShowCart.displayCart(out);
//				RequestDispatcher rd = request.getRequestDispatcher("showcart");
//				rd.include(request, response);
				response.sendRedirect("showcart");
				out.close();
			} else {
				response.sendRedirect("loginPage.html");
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static void addProductToCart(Connection con, int id, String name, String img, int quantity, int price) {
		try {
			System.out.println("Cart :addProductToCart : start");

			String query = "INSERT into " + HomePageServlet.username
					+ "(id,name, img, quantity, price) values(?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, id);
			pst.setString(2, name);
			pst.setString(3, img);
			pst.setInt(4, quantity);
			pst.setInt(5, price);
			pst.executeUpdate();
			pst.close();
			System.out.println("Cart :addProductToCart : end");

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void updateQunatity(Connection con, int product_id, int quantity) throws SQLException {
		System.out.println("Cart :updateQunatity : start");
		String table_name = HomePageServlet.username;
		quantity ++;
		
		String query = "UPDATE " + table_name + " SET quantity =" + quantity + " WHERE id=" + product_id;
		Statement st = con.createStatement();
		st.execute(query);
		System.out.println("Cart : updateQunatity : end");
		st.close();
	}

	public static boolean containsProduct(Connection con, int id) throws SQLException {
		Statement st = con.createStatement();
		int product_id = 0;
		ResultSet rs = st.executeQuery(" select * from " + HomePageServlet.username);
		while (rs.next()) {
			product_id = rs.getInt(1);
			if (product_id == id) {
				rs.close();
				st.close();
				return true;
			}
		}
		rs.close();
		st.close();
		return false;
	}
	
	public static int getQuantity(Connection con, int id) throws SQLException {
		Statement st = con.createStatement();
		int product_id = 0;
		ResultSet rs = st.executeQuery(" select * from " + HomePageServlet.username);
		while (rs.next()) {
			product_id = rs.getInt(1);
			int product_quantity = rs.getInt(4);
			if (product_id == id) {
				rs.close();
				st.close();
				return product_quantity;
			}
		}
		rs.close();
		st.close();
		return 1;
	}

//	public static void addProductToCart(Connection con, int productid, String name, String img, int quantity,
//	int price) {
//try {
//	String query = "INSERT into " + HomePageServlet.username
//			+ "(id,name, img, quantity, price) values(?,?,?,?,?)";
//	Statement st = con.createStatement();
//	System.out.println("Cart : addProductToCart : start");
//	ResultSet rs = st.executeQuery(" select * from " + HomePageServlet.username);
//	PreparedStatement pst = con.prepareStatement(query);
//	while (rs.next()) {
//		int product_id = rs.getInt(1);
//		int product_quantity = rs.getInt(4);
//		if (product_id == productid) {
//			product_quantity++;
//			updateQunatity(con, product_id, product_quantity);
//			System.out.println("added product to cart after updated: ");
////			pst.setInt(1, productid);
////			pst.setString(2, name);
////			pst.setString(3, img);
////			pst.setInt(4, product_quantity);
////			pst.setInt(5, price);
//			return;
//		} else {
//			pst.setInt(1, productid);
//			pst.setString(2, name);
//			pst.setString(3, img);
//			pst.setInt(4, quantity);
//			pst.setInt(5, price);
//			int count = pst.executeUpdate();
//			System.out.println("cart : else statement : added product tio cart: ");
//		}
//	}
//	pst.close();
//	con.close();
//	System.out.println("Cart : addProductToCart : end");
//
//} catch (Exception e) {
//	System.out.println(e);
//}
//}

}
