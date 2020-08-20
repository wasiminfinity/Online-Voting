
package controller;

import java.io.IOException;
import model.Model;
import dao.Dao;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/AdminServlet")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminLogin() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		// String party=request.getParameter("party");
		String page = "";
		String sql = "select * from local where email='" + email + "'";

		System.out.println(email + " " + pass);
		System.out.println("hello");

		if (email.equals("") || pass.equals("")) {
			page = "error.jsp?msg=emp";
		} else {
			Model m = new Model();
			m.setEmail(email);
			m.setPass(pass);
			// Dao d = new Dao();
			try {
				ResultSet rs = Dao.validate(m, sql);

				if (rs.next()) {
					// page="profile.jps?msg=success";
					// String fn = rs.getString(1);
					HttpSession session = request.getSession();
					session.setAttribute("email", email);
					// session.setAttribute("party", party);
					// session.setAttribute("pass", pass);
					// session.setAttribute("fname",fn);
					// session.setMaxInactiveInterval(1800);
					page = "adminHome.jsp?msg=success";

				}

				else {
					page = "adminlogin.jsp?msg=failed";
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		response.sendRedirect(page);
	}
}
