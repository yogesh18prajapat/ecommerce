package com.ecommerceJsp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ecommerceJsp.bean.Product;
import com.ecommerceJsp.bean.User;

public class UserDao {

	public static Connection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");
		return con;
	}

	public static void createTable(String username) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");
//		String table_name = username+"_orders";
		Class.forName("com.mysql.jdbc.Driver");

		Statement st = con.createStatement();
		String cart_table = "Create table " + username + "(id int(15) not null primary key ,"
				+ " name varchar(100) not null, img varchar(200), quantity int(15), price int(15) not null) ";
		String orders_table = "CREATE TABLE   " + username + "_orders"
				+ "(id int(50) primary key not null auto_increment, productid int(50) not null, name varchar(100) not null,  img varchar(100) not null, quantity int(50)  not null,  price int(50)  not null )";
		st.execute(cart_table);
		st.execute(orders_table);
		st.close();
		con.close();
	}

	public static boolean containsUsername(String username) throws Exception {
		Connection con = getConnection();

		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from users");
		while (rs.next()) {
			String rs_userName = rs.getString(5);
			if (rs_userName.equals(username)) {
				st.close();
				rs.close();
				return true;
			}
		}
		return false;
	}

	public static boolean containsuseremail(String email) throws Exception {
		Connection con = getConnection();

		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from users");
		while (rs.next()) {
			String rs_email = rs.getString(3);
			if (rs_email.equals(email)) {
				st.close();
				rs.close();
				return true;
			}
		}
		return false;
	}

	public static boolean adduser(User user_object) throws Exception {
		String name = user_object.getName();
		String email = user_object.getEmail();
		String phone_no = user_object.getPhone_no();
		String u_name = user_object.getUsername();
		String password = user_object.getPassword();

		String query = "INSERT into users(name,email,ph_no,userName,password) values(?,?,?,?,?)";
		Connection con = getConnection();
		PreparedStatement pst = con.prepareStatement(query);
		pst.setString(1, name);
		pst.setString(2, email);
		pst.setString(3, phone_no);
		pst.setString(4, u_name);
		pst.setString(5, password);
		int count = pst.executeUpdate();
		if (count > 1) {
			return true;
		}
		return false;
	}

	public static boolean validatePassword(String username, String user_password) throws Exception {
		boolean containsUsername = containsUsername(username);
		Connection con = getConnection();

		Statement st = con.createStatement();
		if (containsUsername) {
			ResultSet rs = st.executeQuery("select * from users where userName = '"+ username+"'");
			if (rs.next()) {
				String password = rs.getString("password");
				if (password.equals(user_password)) {
					st.close();
					rs.close();
					return true;
				}
			}
		}
		return false;
	}
	
	public static User getUser(String user_name) throws Exception {
		Connection con = getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(" select * from users where userName= '"+user_name+"'");
		
			if(rs.next()) {
				User user = new User();
				String name = rs.getString(2);
				String email = rs.getString(3);
				String phone_no = rs.getString(4);
				String userName = rs.getString(5);
				String password= rs.getString(6);
				user.setName(name);
				user.setEmail(email);
				user.setPhone_no(phone_no);
				user.setUsername(userName);
				user.setPassword(password);
			return user;
			}
	
		rs.close();
		st.close();
		con.close();
		return null;
	}

	
}
