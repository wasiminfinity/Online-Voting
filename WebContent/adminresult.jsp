
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>     
  	<%@page import="model.Model, dao.Dao, java.sql.*, java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Give Vote</title>
</head>
<jsp:include page="header.jsp"></jsp:include> 
<body>
<br><br><br><br><br><br><br><br>
<center>

<table border="7px"  cellspacing="10px">
<%

String sql = "select * from candidate";
int i = 0;
Dao d = new Dao();

try
{
ArrayList<ArrayList> list = new ArrayList<ArrayList>(); 
	ResultSet rs = Dao.validate1(sql);
	while(rs.next()) 
	{
		String party = rs.getString(1);
		String vote = rs.getString(5);
		
		list.add(new ArrayList<String>());
		list.get(i).add(party);
		list.get(i).add(vote);	
		i++;
	}	
	HttpSession session1 = request.getSession();
	session1.setAttribute("nlist",list);
}
catch(Exception e)
{
	e.printStackTrace();
}

ArrayList<ArrayList> a = (ArrayList<ArrayList>)session.getAttribute("nlist");

for(int j=0;j<a.size();j++){
	
	%>
	
	<tr>
	<td>
		<b><p>Party Name:&nbsp; <%= a.get(j).get(0) %></p></b>
	</td>

	<td>
	 	<b>Number of Vote:<input type="text" value="<%= a.get(j).get(1)%>"></b>
	</td>
		
	</tr>
	<%
}
String quer="select vote from candidate";
ResultSet rs1=Dao.validate1(quer);
System.out.println("query executed ...");
ArrayList<Integer> al=new ArrayList<Integer>();
while(rs1.next()){
	al.add(rs1.getInt(1));
	System.out.println(rs1.getInt(1));
} 

String sql2 = "SELECT pn,vote FROM candidate where vote=(select max(vote) from candidate)";
ResultSet rs = Dao.validate1(sql2);
while(rs.next())
{
	String p=rs.getString(1);
 String l = rs.getString(2);
 HttpSession session2 = request.getSession();
 session2.setAttribute("p",p);
 session2.setAttribute("l",l);
}
String party = session.getAttribute("p").toString();
String votenum = session.getAttribute("l").toString();
al.remove(al.indexOf(Integer.parseInt(votenum)));
System.out.println(al);
%>
<%int flag=1;
	for(Integer x:al){
		if(x==Integer.parseInt(votenum))
	 		flag=0;
	 }%>
	 <%if(flag==0){ %>
		<p><font color="red"><b>" There is a "Tie" "</b></font></p>
		<%} 
	else{%>
		<p><font color="red"><b>"Winner is <%=party %> with number of votes <%= votenum %>"</b></font></p>
		
		<%}%>

</table>
<br><br><br>
</center>
</body>
<jsp:include page="footer.jsp"></jsp:include>
</html>
