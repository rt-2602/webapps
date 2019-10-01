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
				
			<input type="hidden" name="command" value="DELETE-ACCOUNT"  />	
					
				
			<div style="color:red">${errorMessage}</div>
			 
			 <%-- <%
				    if(request.getAttribute("errorMessage") != null && request.getAttribute("errorMessage") == "true"){
						%>
						 <div style="color:red">${errorMessage}</div>
						<%
						}
				%> --%>
		
			<div>Are you sure you want to delete your account?</div>
			
			<br><br>
				<div style="text-align: left;">
				  	<input type="submit" value="Yes" class="save" class="save"/>
					<!-- <input type="button" value="Back" class="save"  onclick="window.location.href='main-menu.jsp'; return false;"/> -->
				</div>
				
			
				<div style="text-align: center;">
				  	<!-- <input type="submit" value="Submit" class="save" /> -->
					<input type="button" value="No" class="save"  onclick="window.location.href='User-login-form.jsp'; return false;"/>
				</div>
			
			
		
		</form>
		
		
</body>
</html>