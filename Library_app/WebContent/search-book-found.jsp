<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
	<title>Library App</title>
	
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">
	
	<script type="text/javascript">
		function callservlet() {
			
			var radioBtn = document.getElementsByName("book");
			var selection;
			for(i=0; i<radioBtn.length; i++){
				if(radioBtn[i].checked){
					selection=radioBtn[i].id;
					break;
				}
			}
			//alert('param:'+param);
			document.forms[0].method = "POST";
			document.forms[0].action = "LibraryControllerServlet";
		    document.getElementById('command').value = selection;
		    document.forms[0].submit();
		}
		
	</script>
	

</head>

<body>
	

	<div id="wrapper">
		<div id="header">
			<h2>Hello World Library</h2>
		
		</div>	
	</div>
	
	
	
	<div id="container">
		<h3>Welcome to Hello World Library </h3>
			
			
		<form>
				
			<input type="hidden" id="command" name="command" value="" />		
				
			<div style="color:red">${errorMessage}</div>
			
			<div style="color:green" align="left">
					<h4>Book found with...</h4>
				</div>
			
			<table>
				
				<tr>
					<th>Book Name</th>
					<th>Author</th>
					<th align="center">Count available</th>
					<th align="center">Issue Book</th>
					<!-- <th>Return Book</th> -->
								
				</tr>
				
				
				<c:forEach var="tempBook" items="${foundBook}">
				
					<tr>
						<%-- <td> ${tempBook.id}</td>  --%>
						<td> ${tempBook.bookName}</td>
						<td> ${tempBook.authorName} </td>
						<td align="center"> ${tempBook.numberOfCopies}</td>
						
						<td> 
						
							 <input align="center" type="radio" value="${tempBook.id}" name="book" id="ISSUE"
								class="issue-book-button"
								/> 
						</td>
						
						<%-- <td>
								<input type="radio" value="${tempBook.id}" name="book" id="RETURN"
								class="return-book-button"
								/> 
						</td> --%>
						
					</tr>
					
				
			</c:forEach>
			</table> 
			
			<div>
				<input type="submit" value="Submit" 
								onclick="callservlet()"
								class="view-book-button"
								/> 			
				<input type="button" value="Back" class="save"  onclick="window.location.href='main-menu.jsp'; return false;"/>
			
			</div>
		</form>
		
		<div style="clear: both;"></div>
	
	
	</div>

</body>
</html>