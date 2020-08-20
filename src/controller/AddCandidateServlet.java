
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import dao.Dao;
import model.Model;
 
import javax.servlet.http.*;

public class AddCandidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddCandidateServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String party = request.getParameter("party");
		String fn = request.getParameter("fname");
		String age = request.getParameter("age");
		/*
		 * String con = request.getParameter("con"); String add =
		 * request.getParameter("add");
		 */
		String gen = request.getParameter("gen");
		int vote=0;
		String page="";
		
		String sql = "insert into candidate(pn,fn,age,gender,vote) values('"+party+"', '"+fn+"', '"+age+"','"+gen+"', '"+vote+"')"; 
		if (party.equals("")||fn.equals("")||age.equals("")||gen.equals("") )
		{
			page="add_Candidate.jsp?msg=failure";
		}
		else{
			
			Model m = new Model();
			m.setParty(party);
			m.setFn(fn);
			m.setAge(age);
			/*
			 * m.setCon(con); m.setAdd(add);
			 */
			m.setGend(gen);
			try
			{	
				int i = Dao.register(m, sql);
				HttpSession session = request.getSession();
				session.setAttribute("party",party);
				if(i!=0)
				{
					page="add_Candidate.jsp?msg=success";
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			response.sendRedirect(page);	

		}
	}
}
