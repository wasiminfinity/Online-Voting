
package controller; 

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;

public class Vote extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Vote() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get user's email from the session for status update
	HttpSession session = request.getSession();
	//converting email value and storing it in string
	String email = session.getAttribute("email").toString();
		
	String pname= request.getParameter("vote");
	System.out.println(pname);
	
	
	
	if(pname != null)
	{System.out.println("here");
		//Dao d = new Dao();
		
		try {
			
			String sql = "update register set status = 'activate' where email='"+email+"'";
			//get email id from session here and use it to update status of specific user 
			
			int i = Dao.register1(sql);
			//get status of user and store it in session
			
			 String sql1 = "select * from register where email='"+email+"'";
			 System.out.println(email);
			 ResultSet rs = Dao.validate1(sql1);
			//set status in session
			/*if(rs.next())
			{
				String s = rs.getString(15);
				System.out.println("Status = "+s);
				HttpSession session1 = request.getSession();
				session1.setAttribute("s1",s);				
			}
			*/
			if(i==1)
			{
				System.out.println("status updated successfully");
				try{
					
				String sql2 = "update candidate set vote=vote + 1 where pn='"+pname+"'";
				String s=null;
				if(rs.next()) {
				s = rs.getString(15);
				System.out.println("Status = "+s);
				HttpSession session1 = request.getSession();
				session1.setAttribute("s1",s);
				}
				int k = Dao.register1(sql2);
				if(k==1)
				{
					System.out.println("vote submitted");
				}
				
			}
				catch(Exception e)
				{System.out.println("inside vote first try block");
					e.printStackTrace();
					
				}
			}
		
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("inside vote second try block");
			e.printStackTrace();
		}
	//redirect user back to home page with the session continued and not expired
	//disable the vote button on jsp page
		System.out.println("successfully voted");
		response.sendRedirect("profile.jsp");
	}
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
	}

}
