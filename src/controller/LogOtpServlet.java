
package controller;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
import mail.PasswordMail;
import model.Model;

//@WebServlet("/LogOtpServlet")
public class LogOtpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LogOtpServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String emailid = request.getParameter("email");
		String page = "";
		String sql = "select * from register where email='" + emailid + "' ";
		System.out.println(emailid);

		if (emailid.equals("")) {
			page = "error.jsp?msg=abc";
		} else {
			Model m = new Model();
			m.setEmail(emailid);
			try {
				ResultSet rs = Dao.validate(m, sql);
				if (rs.next()) {
					String password = rs.getString(12);
					HttpSession session = request.getSession();
					session.setAttribute("email", emailid);
					session.setAttribute("pass", password);
					String data = "12345678901234567890123456789012345678901234567890";
					String otp = "";
					char ch[] = data.toCharArray();

					for (int i = 0; i < 4; i++) {
						int j = (int) ((Math.random()) * 50);
						otp = otp + ch[j];
					}
					session.setAttribute("otp", otp);
					System.out.println(otp);
					PasswordMail pm = new PasswordMail();
					//System.out.println("problem here");
					boolean status = pm.sendMail(emailid, otp);
					if (status) {
						page = "logotp.jsp?msg=send";
					}
				}
			} catch (Exception e) {
				System.out.println("otp");
				e.printStackTrace();
			}
		}
		response.sendRedirect(page);
	}
}
