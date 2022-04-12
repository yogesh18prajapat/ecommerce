package com.ecommerceJsp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

//Product Bean import
import com.ecommerceJsp.bean.Product;

public class ProductDao {
	
	public static Connection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce","root","root");
		return con;
	}
	
	public static Product createProduct(String name, int price, String img, String section, int oldPrice) {
		Product p = new Product();
		p.setName(name);
		p.setPrice(price);
		p.setImg(img);
		p.setSection(section);
		p.setOldprice(oldPrice);
		return p;
	}
	
	public static void addProduct(Product product) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");
			Statement st = con.createStatement();
			String query = "INSERT into products(name,price,img,section,oldprice) values(?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, product.getName());
			pst.setInt(2, product.getPrice());
			pst.setString(3, product.getImg());
			pst.setString(4, product.getSection());
			pst.setInt(5, product.getOldprice());
//			int count = pst.executeUpdate();
			pst.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void addToCart(int id, String cart_table_name) throws Exception {
//		System.out.println("add To Cart: started:.............");
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");
		
			Statement st = con.createStatement();
			Product product = getProductById(id);
			
			if(containsProduct(id, cart_table_name)) {
				int producyt_quantity = getQuantity(id, cart_table_name);
				updateQunatity(cart_table_name,id,true);
			}else {
			String query = "INSERT into "+cart_table_name+"(id,name,img,quantity,price) values(?,?,?,?,?)";
//			System.out.println("add To Cart: before pst:.............");
			PreparedStatement pst = con.prepareStatement(query);
//			System.out.println("add To Cart: after pst:.............");
			pst.setInt(1, product.getId());
			pst.setString(2, product.getName());
			pst.setString(3, product.getImg());
			pst.setInt(4, (product.getQuantity())+1);
			pst.setInt(5, product.getPrice());
			int count = pst.executeUpdate();
//			System.out.println("add To Cart: after execution:.............");
			pst.close();
			con.close();
//			System.out.println("add To Cart: finished:.............");

			}
		}
		
	
//	public static void updateQunatity(String table_name, int product_id, boolean increment) throws SQLException, ClassNotFoundException {
//		Class.forName("com.mysql.jdbc.Driver");
//		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");
//		int quantity = getQuantity(product_id, table_name);
//		int price = getPrice(product_id);
//		if(increment) {
//			quantity++;
//		}else {
//			quantity --;
//		}
//		String query = "UPDATE " + table_name + " SET quantity =" + quantity +",price = "+price*quantity+" WHERE id=" + product_id;
//		Statement st = con.createStatement();
//		st.execute(query);
////		System.out.println("Cart : updateQunatity : end");
//		st.close();
//		con.close();
//	}
	
	public static void updateQunatity(String table_name, int product_id, boolean increment) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");
		int quantity = getQuantity(product_id, table_name);
		int price = getPrice(product_id);
		
		if(increment) {
			quantity++;
		}else {
			quantity --;
		}
		Statement st = con.createStatement();
		if(quantity == 0) {
			String query = "delete from "+table_name+" where id="+product_id; 
			st.execute(query);
		}else {
		String query = "UPDATE " + table_name + " SET quantity =" + quantity +",price = "+price*quantity+" WHERE id=" + product_id;
		st.execute(query);
//		System.out.println("Cart : updateQunatity : end");
		}
		st.close();
		con.close();
	}

	public static boolean containsProduct(int id, String table_name) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");
		Statement st = con.createStatement();
		int product_id = 0;
		ResultSet rs = st.executeQuery(" select * from " + table_name);
		while (rs.next()) {
			product_id = rs.getInt(1);
			if (product_id == id) {
				rs.close();
				st.close();
				con.close();
				return true;
			}
		}
		rs.close();
		st.close();
		con.close();
		return false;
	}
	
	public static int getQuantity(int id,  String table_name) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");
		Statement st = con.createStatement();
		int product_id = 0;
		ResultSet rs = st.executeQuery(" select * from " + table_name);
		while (rs.next()) {
			product_id = rs.getInt(1);
			int product_quantity = rs.getInt(4);
			if (product_id == id) {
				rs.close();
				st.close();
				con.close();
				return product_quantity;
			}
		}
		rs.close();
		st.close();
		con.close();
		return 1;
	}
	
	public static int getPrice(int id) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");
		Statement st = con.createStatement();
		int product_id = 0;
		ResultSet rs = st.executeQuery(" select * from products");
		while (rs.next()) {
			product_id = rs.getInt(1);
			int product_price = rs.getInt(3);
			if (product_id == id) {
				rs.close();
				st.close();
				con.close();
				return product_price;
			}
		}
		rs.close();
		st.close();
		con.close();
		return 1;
	}

	public static List<Product> getProducts(String table_name) throws SQLException, ClassNotFoundException {
//		System.out.println("getting products from: "+table_name);
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(" select * from " + table_name);
		List<Product> list =new LinkedList<>();
		while(rs.next()) {
			int id = rs.getInt(1);
			int quantity = rs.getInt(4);
			Product product = getProductById(id);
			product.setQuantity(quantity);
			list.add(product);
		}
		rs.close();
		st.close();
		con.close();
		return list;
	}

	public static int getTotal(String table_name, boolean quantity) throws ClassNotFoundException, SQLException {
		System.out.println("getting products from: "+table_name);
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(" select * from " + table_name);
		int total_quantity = 0;
		int total_price = 0;
		while(rs.next()) {
			int product_id = rs.getInt(1);
			int product_quantity = rs.getInt(4);
			int product_price = rs.getInt(5);
			total_quantity += product_quantity;
			total_price += product_price;
		}
		if(quantity) {
			rs.close();
			st.close();
			con.close();
			return total_quantity;
		}
		else {
			rs.close();
			st.close();
			con.close();
			return total_price;
		}
		
	}
	
	public static List getProducts(String table_name, String section) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(" select * from " + table_name +" where section= '"+section+"'");
		List<Product> product_list = new LinkedList();
		while(rs.next()) {
			Product p = new Product();
			p.setId(rs.getInt(1));
			p.setName(rs.getString(2));
			p.setPrice(rs.getInt(3));
			p.setImg(rs.getString(4));
			p.setSection(rs.getString(5));
			p.setOldprice(rs.getInt(6));
			
			product_list.add(p);
		}
		rs.close();
		st.close();
		con.close();
		return product_list;
	}

	public static Product getProductById(int id) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(" select * from products where id= "+id);
		
			if(rs.next()) {
				Product product = new Product();
				int p_id = rs.getInt(1);
				String name = rs.getString(2);
				int price = rs.getInt(3);
				rs.getString(4);
				String sec = rs.getString(5);
				int oldprice = rs.getInt(6);
//				System.out.println(p_id+": "+name+": "+price+": "+sec+": "+oldprice);
				product.setId(rs.getInt(1));
				product.setName(rs.getString(2));
				product.setPrice(rs.getInt(3));
				product.setImg(rs.getString(4));
				product.setSection(rs.getString(5));
				product.setOldprice(rs.getInt(6));
			return product;
			}
	
		rs.close();
		st.close();
		con.close();
		return null;
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
	
	
	public static void addToOrderThroughId(int product_id, String order_table_name) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ecommerce", "root", "root");
		
		Product product = getProductById(product_id);
		int productid =	product.getId();
		String name = product.getName();
		String img = product.getImg();
		int quantity =	1;
		int price =	product.getPrice();

		String writequery = "INSERT INTO " + order_table_name + "(productid,name,img,quantity,price) values(?,?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(writequery);
		
				pst.setInt(1, productid);
				pst.setString(2, name);
				pst.setString(3, img);
				pst.setInt(4, quantity);
				pst.setInt(5, price);
				pst.executeUpdate();
				System.out.println("Successfully uploaded into: "+order_table_name);
				
		pst.close();
		con.close();
		
	}

	public static List getProductsFromOrders(String orders_table_name) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(" select * from " + orders_table_name);
		List<Product> product_list = new LinkedList();
		while(rs.next()) {
			Product p = new Product();
			p.setId(rs.getInt(2));
			p.setName(rs.getString(3));
			p.setImg(rs.getString(4));
			p.setQuantity(rs.getInt(5));
			p.setPrice(rs.getInt(6));
			product_list.add(p);
		}
		rs.close();
		st.close();
		con.close();
		return product_list;
	}
	
	public static void truncateTable(String table_name) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");
		Statement st = con.createStatement();
		String query = "TRUNCATE TABLE "+table_name;
		st.execute(query);
		st.close();
		con.close();
	}
}

