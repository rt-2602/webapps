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
				
			<input type="hidden" name="command" value="SEARCH" />	
				
			<div style="color:red">${errorMessage}</div>
			 
			<table>
				<tbody>
				
					<tr>
						<td><label>Book Name:</label></td>
						<td><input type="text" name="book-name"/></td>
				
					</tr>
					
					<tr>
						<td><label>Author:</label></td>
						<td><input type="text" name="author-name"/></td>
				
					</tr>
					
	
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Submit" class="save" class="save"/></td>
						
						<td><input type="button" value="Back" class="save"  onclick="window.location.href='main-menu.jsp'; return false;"/></td>
						
					</tr>
					
				</tbody>
			
			</table>
		
		</form>
		
		<div style="clear: both;"></div>
		
		
		
	
	</div>

</body>
</html>