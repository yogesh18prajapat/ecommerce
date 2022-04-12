package ecommerce;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@WebFilter("/homePageServlet")
public class LoginFilter implements Filter {
//	static boolean login_user;

    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		

		response.setContentType("text/html");
		String u_name = request.getParameter("user_name");
		String password = request.getParameter("user_password");
		RequestDispatcher rd;
		
		try {
			PrintWriter out = response.getWriter();
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select * from users");
				
				while (rs.next()) {
					String rs_userName = rs.getString(5);
					String rs_password = rs.getString(6);
					if (rs_userName.equals(u_name) && rs_password.equals(password) ) {
//						  login_user = true;
						chain.doFilter(request, response);
						 return;
					}
				}
//				  login_user = false;
				rd = request.getRequestDispatcher("loginPage.html");
				 rd.include(request,response);
				rs.close();
				st.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}


}
