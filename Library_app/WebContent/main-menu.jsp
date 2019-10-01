<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>

	<title>Library App</title>
	
	<link type="text/css" rel="stylesheet" href="css/style.css">
	
	<script type="text/javascript">
		function callservlet(param) {
			if(param=='DELETE-ACCOUNT' && !confirm("Are you sure you want to delete the account?")){
				return false;
			}
			//alert('param:'+param);
			document.forms[0].method = "POST";
			document.forms[0].action = "LibraryControllerServlet";
		    document.getElementById('command').value = param;
		    document.forms[0].submit();
		}
		
	</script>

</head>

<body >

	 <div id="wrapper">
		<div id="header">
			<h2>Welcome To Hello World Library</h2>
								
		</div>
	</div>
		
	<div id="container">
		<form>
		<input type="hidden" id="command" name="command" value="" />	
		
		<br>
		
		<div style="color:red">${Message}</div>
	
		<div id="content">
		
			<h3>Choose one of the following options</h3>
			
				
				
					<hr>Search Book:
							<input type="button" value="Search Book"
							onclick="window.location.href='search-book.jsp'; return false;"
							class="add-student-button"
				/> 
							
								
					<hr>View list of Books:
							
							<input type="button" value="View" 
								onclick="callservlet('LIST')"
								class="view-book-button"
								/>  
							
								
					<hr>Return Book:
							<input type="button" value="Return" 
								onclick="callservlet('ISSUED-LIST')"
								class="view-book-button"
								/>  
							 
					
					
					<hr>Issue Book:
							<input type="button" value="Issue" 
								onclick="callservlet('LIST')"
								class="view-book-button"
								/>  
							
												
					<hr>Logout:
							<input type="button" value="Logout" 
								onclick="callservlet('LOGOUT')"
								class="view-book-button"
								/>  
								
					<hr>Delete account:
							<input type="button" value="Delete" 
								onclick="callservlet('DELETE-ACCOUNT')"
								class="view-book-button"
									/>  
									
						
					</div>					
			</form>		
		</div>
	
	</body>

</html>