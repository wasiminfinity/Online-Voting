
package controller; 
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import model.Model;  
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private String;
       
    public UpdateServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	} 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fn = request.getParameter("fname");
		String mn = request.getParameter("mname");
		String ln = request.getParameter("lname");
		String con = request.getParameter("con");
		String add = request.getParameter("add");
		String email= request.getParameter("email");
		String dob= request.getParameter("dob");
		String page="";		
		String sql="update register set first_name='"+fn+"',middle_name='"+mn+"',last_name='"+ln+"',contact='"+con+"',address='"+add+"',dob='"+dob+"' where email='"+email+"'";	
		
	try{
		Model m = new Model();
		m.setFn(fn);
		m.setMn(mn);
		m.setLn(ln);
		m.setCon(con);
		m.setAdd(add);
		m.setEmail(email);
		m.setDob(dob);
			
		int i =Dao.common(m, sql, "update");
			if(i!=0){System.out.println("hi");
				page="error.jsp?msg=up";
			}
			else{
				page="update.jsp?msg=failed";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	System.out.println("hi");
	response.sendRedirect(page);
	}
}
