<!DOCTYPE html>

<html>

<head>
	<title>Library App</title>
	
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">

</head>

<body>
	

	<div id="wrapper">
		<div id="header">
			<h2>Hello World Library</h2>
		
		</div>	
	</div>
	
	
	
	<div id="container">
		<h3>Welcome to Hello World Library </h3>
		
		
		<form action="LibraryControllerServlet" method="POST">
				
			<input type="hidden" name="command" value="LOGIN"  />	
					
				
			<div style="color:red">${errorMessage}</div>
			 
			 <%-- <%
				    if(request.getAttribute("errorMessage") != null && request.getAttribute("errorMessage") == "true"){
						%>
						 <div style="color:red">${errorMessage}</div>
						<%
						}
				%> --%>
		
			<table>
				<tbody>
				
					<tr>
						<td><label>User Id:</label></td>
						<td><input type="text" name="user-id" value="Mary"/></td>
				
					</tr>
					
					<tr>
						<td><label>Password:</label></td>
						<td><input type="password" name="password" value="Mary123"/></td>
				
					</tr>
					
	
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="LOGIN" class="save" class="save"/></td>
						
					</tr>
					
				</tbody>
			
			</table>
		
		</form>
		
		<div style="clear: both;"></div>
	
		<p>
			<input type="hidden" name="command" value="CREATE-ACCOUNT" />	
			<a href="create-new-account.jsp">Create new account</a>
			
		</p>
			
	
	</div>
</body>
</html>