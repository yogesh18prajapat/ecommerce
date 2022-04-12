package ecommerce;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddProductsToWebsite{
	public static  void main(String[] args) {
	
//			addProduct("blue full T-shirt", 799, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRATl3iw0kis2Yt_Yq5Krp02wZl6yMv7cInBg&usqp=CAU", "men", 999);
//			addProduct("half-half shirt", 999, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQNZXHvKBIbPlmX15Bd3995csu02yzOncE2Xg&usqp=CAU", "men", 1099);
//			addProduct("printed T-shirt", 549,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSvSS-CYbwlXK5SrR2XgILM6K805qobdanqNg&usqp=CAU", "men", 699);
//			addProduct("Stylish check shirt", 699, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTf5ekfnEZ-vk_gxTHCYdlqFMvO-aiZnbnV1w&usqp=CAU", "men", 899);
//			addProduct("plain shirt", 999, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQyo_826fxSCftsJsoNXu5gmIa2cWkp398ncQ&usqp=CAU", "men", 1599);
//			addProduct("printed T-shirt", 499, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQe4J4ZrQhPhnmd2rD1DxBQkr_GxWFNQk-xHg&usqp=CAU", "men", 799);
//			addProduct("white shirt", 1999, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQSOh2bWmgrcvCo3z2xqKiGvKYi0Vh4dwcXEw&usqp=CAU", "men", 2599);
//			addProduct("premium shirt", 2599, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT-Pmz0dumq4cnQ84Nf0cRLsEBISAZ5KNWbTQ&usqp=CAU", "men", 2999);
//			addProduct("half-sleves shirt", 799, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTz9iJwV6suh9uSv-D3zLe325mY2_sN9YHhBw&usqp=CAU", "men", 1099);
//			addProduct("classic full-sleves shirt", 1599, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSLJnPYjFngCzcInN07Qpe9z-U8wapsar5hGw&usqp=CAU", "men", 1999);
		
			
	}

	public static void addProduct(String name, int price, String img, String section, int oldPrice) {
		try {
			String query = "INSERT into products(name,price,img,section,oldprice) values(?,?,?,?,?)";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery("select * from products");
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, name);
			pst.setInt(2, price);
			pst.setString(3, img);
			pst.setString(4, section);
			pst.setInt(5, oldPrice);
			int count = pst.executeUpdate();
			pst.close();
			con.close();
//			System.out.println("success......");

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}