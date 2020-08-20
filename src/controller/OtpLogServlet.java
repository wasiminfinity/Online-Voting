
package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/OtpLogServlet")
public class OtpLogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OtpLogServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String otp = request.getParameter("otp");
		HttpSession session = request.getSession();
		String otp1 = (String) session.getAttribute("otp");
		String page = "";

		if (otp.equals(otp1)) {

			page = "profile.jsp?msg=success";

		} else {
			page = "error.jsp?msg=otp";
		}
		response.sendRedirect(page);
	}
}
