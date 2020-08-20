package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import model.Model;

/**
 * Servlet implementation class AddCampaign
 */
public class AddCampaign extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCampaign() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String party= request.getParameter("pname");
		String sn= request.getParameter("sname");
		String sub= request.getParameter("sub");
		String city= request.getParameter("city");
		String count= request.getParameter("country");
		String state= request.getParameter("state");	
		String page="";
		String sql="insert into campaign values('"+party+"','"+sn+"','"+sub+"','"+city+"','"+count+"','"+state+"')";
		
		if(party.equals("")||sn.equals("")||sub.equals("")||city.equals("")||count.equals("")||state.equals(""))
		{
			page="add_camp.jsp?msg=failure";
		}
		else
		{
			try
			{
				Model m = new Model();
				m.setParty(party);
				m.setSpeaker(sn);
				m.setSubject(sub);
				m.setCity(city);
				m.setCountry(count);
				m.setState(state);
				
				int i =Dao.register(m,sql);
				if(i!=0)
				{
					page="add_camp.jsp?msg=success";
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









