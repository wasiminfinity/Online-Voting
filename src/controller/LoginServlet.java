
package controller;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
import model.Model;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String email=request.getParameter("email");
		String election = request.getParameter("election");
		String pass=request.getParameter("pass");
		String page="";
		String sql ="select * from register where email='"+email+"' and election='"+election+"' and password='"+pass+"'";
		String sql1 = "select * from register where email='"+email+"'";
		
		
		if(email.equals("")||election.equals("") ||pass.equals("")) {
		 
			page="error.jsp?msg=abc";  //URL Rewriting
			
		}
		else
		{
			Model m = new Model ();
			m.setEmail(email);
			m.setElection(election);
			m.setPass(pass);
			//Dao d = new Dao(); 
			try
			{
				
				ResultSet rs=Dao.validate(m,sql);
				
				 ResultSet rs1 = Dao.validate1(sql1);
					//set status in session
					if(rs1.next())
					{
						
						String s = rs1.getString(14);
						System.out.println("Status = "+s);
						
						if(s.equalsIgnoreCase("activate"))
						{
						HttpSession session1 = request.getSession();
						session1.setAttribute("s1",s);				
						}
						
						
					}
				if(rs.next())
				{
					//page="profile.jsp?msg=success";					
					String fn = rs.getString(1);
					HttpSession session = request.getSession();
					session.setAttribute("email",email);
					session.setAttribute("pass", pass);
					session.setAttribute("fname",fn);
					session.setMaxInactiveInterval(1800);
					/* page="profile.jsp?msg=success"; */
					page="generateotp.jsp";
				}
				else
				{
					page="error.jsp?msg=failed";
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		response.sendRedirect(page);
	}
}
