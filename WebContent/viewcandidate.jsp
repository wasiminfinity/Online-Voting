
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    
    pageEncoding="ISO-8859-1"%>
       	<%@page import="model.Model, dao.Dao, java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Candidate</title>
</head>

<% 
if(session.getAttribute("email")!=null)
{
	Model m = new Model();
	ResultSet rs = Dao.validate(m,"select * from candidate");

%>
<body>

<jsp:include page="header_admin.jsp"></jsp:include>
<br><br><br><br><br><br>
<center><a href="add_Candidate.jsp"><button>Add</button></a>
    	<a href="edit_Candidate.jsp"><button>Edit</button></a>
    	<a href="delete_Candidate.jsp"><button>Delete</button></a>
    	<a href="viewcandidate.jsp"><button>View</button></a>
</center>

<center>
<br>
<br>
<table border="2">
<tr>
<th>Party Name</th>
<th>First Name</th>
<th>Age</th>
<th>Gender</th>
<th>Vote</th>

</tr>

<%
while(rs.next())
{
  %>
	<tr>
	<td><%=rs.getString(1)%></td>
	<td><%=rs.getString(2)%></td>
	<td><%=rs.getString(3)%></td>
	<td><%=rs.getString(4)%></td>
	<td><%=rs.getString(5)%></td>
	</tr>
<% }%>
</table>
<%} 
else
{
	response.sendRedirect("viewcandidate.jsp");
}
%>
</center>
</body>
</html>
