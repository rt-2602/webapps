<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>

	<title>Library App</title>
	
	<link type="text/css" rel="stylesheet" href="css/style.css">

</head>



<body>

	 <div id="wrapper">
		<div id="header">
			<h2>Welcome To Hello World Library</h2>
								
		</div>
	</div>
	
	<form action="LibraryControllerServlet" method="POST">
	<input type="hidden" name="command" value="RETURN"/>
		
	<div id="container">
	
		<div id="content">
		
			<h3>List of issued books</h3>
		
			<table>
				
				<tr>
					<!-- <th>User Id</th> -->
					<th>Book Name</th>
					<th>Author Name</th>
					<th>Status</th>
					<th>Return Book</th>
					<!-- <th>Return Book</th> -->
								
				</tr>
				
				
				<c:forEach var="tempBook" items="${BOOK_LIST}">
				
					<tr>
						 <%-- <td> ${tempBook.id}</td>  --%>
						 <td> ${tempBook.bookName}</td>
						 <td> ${tempBook.authorName} </td>
						<td> ${tempBook.status}</td>
						
						
						<td> 
						
							 <input type="radio" value="${tempBook.user_history_id}" name="book" 
								class="return-book-button" 
								/> 
						</td>
						
						<%-- <td>
								<input type="radio" value="${tempBook.id}" name="book"
								class="return-book-button"
								/> 
						</td> --%>
						
					</tr>
					
				
			</c:forEach>
			</table>
				<br><br>
				<div style="text-align: center;">
				  	<input type="submit" value="Submit" class="save" />
					<input type="button" value="Back" class="save"  onclick="window.location.href='main-menu.jsp'; return false;"/>
				</div>
				
		</div>
	
	</div> 
	</form>
</body>

</html>