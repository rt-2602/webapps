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
	<input type="hidden" name="command" value="SUBMIT"/>
		
	<div id="container">
	
		<div id="content">
		
			<h3>List of books</h3>
		
			<table>
				
				<tr>
					<th>Book Name</th>
					<th>Author</th>
					<th>Count available</th>
					<th>Issue Book</th>
					<!-- <th>Return Book</th> -->
								
				</tr>
				
				
				<c:forEach var="tempBook" items="${BOOK_LIST}">
				
					<tr>
						 <%-- <td> ${tempBook.id}</td>  --%>
						<td> ${tempBook.bookName}</td>
						<td> ${tempBook.authorName} </td>
						<td> ${tempBook.numberOfCopies}</td>
						<td> 
						
							 <input type="radio" value="${tempBook.id}" name="book"
								class="issue-book-button"
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