
package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao;
import model.Model;
@WebServlet("/ChangeServlet")
public class ChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChangeServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String old=request.getParameter("pass");		//oldpwd
		String newpass=request.getParameter("pass1");		//new pwd
		String cnfpass=request.getParameter("pass2");		//confirm pwd
		HttpSession session = request.getSession();
		String emailid = (String)session.getAttribute("email");
		String oldpass = (String)session.getAttribute("pass");		//old pwd in session/table
		String sql="update register set(password)=('"+newpass+"') where email='"+emailid+"'";
		String page="";
		//int a=0;
		String o=null;
		String p="select password from register where email='"+emailid+"'";
		try
		{
			ResultSet rs=Dao.change(p);
			while(rs.next())
			{
				o=rs.getString(1);
			}
		}catch(SQLException e) {e.printStackTrace();}
		System.out.println(old +" "+ newpass +" "+ cnfpass +" "+ emailid +" "+ oldpass+" "+o);
		if(old.equals("")||newpass.equals("") ||cnfpass.equals("")) {
			 
			page="error.jsp?msg=empty";  //URL Rewriting
			
		}
		else if (old.equals(o))
		{
			if(newpass.equals(cnfpass))
			{
				Model m = new Model();
				m.setEmail(emailid);
				m.setPass(newpass); 
				try
				{
					int i =Dao.register(m, sql);
					
					if(i!=0)
					{
						//a=1;
						page="error.jsp?msg=changed";
						/* page="login.jsp?msg=passwordchanged"; */
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
		
			else
			{
				page="error.jsp?msg=passwordmatch";
			}
		}
		else
		{
			page="error.jsp?msg=oldpass";
		}
			response.sendRedirect(page);
	}
}
