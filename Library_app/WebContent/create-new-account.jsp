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
		
		<h4>Please Enter </h4>
		
		
		<form action="LibraryControllerServlet" method="POST">
				
			<input type="hidden" name="command" value="CREATE-ACCOUNT"  />	
					
				
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
						<td><label>First Name:</label></td>
						<td><input type="text" name="firstName"/></td>
				
					</tr>
					
					<tr>
						<td><label>Last Name:</label></td>
						<td><input type="text" name="lastName"/></td>
				
					</tr>
					
					<tr>
						<td><label>Email Id:</label></td>
						<td><input type="text" name="email"/></td>
				
					</tr>
				
					<tr>
						<td><label>User Id:</label></td>
						<td><input type="text" name="userId"/></td>
				
					</tr>
					
					<tr>
						<td><label>Password:</label></td>
						<td><input type="password" name="password" /></td>
				
					</tr>
					
	
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Submit" class="save" class="save"/></td>
						
					</tr>
					
				</tbody>
			
			</table>
		
		</form>
		
		<div style="clear: both;"></div>
	
		<!-- <p>
			<input type="hidden" name="command" value="CREATE-ACCOUNT" />	
			<a href="StudentControllerServlet">Create new account</a>
			
		</p> -->
	
	</div>
</body>
</html>