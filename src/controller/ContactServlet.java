
package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.Dao;
import model.Model;


@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ContactServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String from=request.getParameter("email");
		String subject=request.getParameter("sub");
		String message=request.getParameter("msg");
		String page="";
		Date d = new Date(); 
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		String s=sdf.format(d);
		
		if(from.equals("") ||subject.equals("")||message.equals(""))
		{
			page="error.jsp?msg=man";
		}
		else 
		{
			Model m =new Model();
			m.setEmail(from);
			m.setSubject(subject);
			m.setMessage(message);
			
			try {
				int i = Dao.register(m, "insert into feedback(femail,temail,subject,message,d) values('"+from+"' , 'wasim.akram547@gmail.com', '"+subject+"', '"+message+"', '"+s+"')");
				if(i!=0)
				{	
					page="error.jsp?msg=feedbacksent";
				}
				else
				{
					page="contact.jsp?msg=failed";
				}
			}
			catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		response.sendRedirect(page);
	}
}
