
package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import model.Model;


public class UpdateNominee extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UpdateNominee() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String party = request.getParameter("party");
		String page="";
		 
		String sql="update nominee set(pn) =('"+party+"') where pn='"+party+"'"; 	
		
	try{ 
		Model m = new Model();
		m.setParty(party);

		int i =Dao.common(m, sql, "update");
			if(i!=0){
				page="Edit_nomi.jsp?msg=success";
			}
			else{
				page="Edit_nomi.jsp?msg=failed";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}			
	response.sendRedirect(page);
	}
}
