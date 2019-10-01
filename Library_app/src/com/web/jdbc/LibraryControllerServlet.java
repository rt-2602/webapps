package com.web.jdbc;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Servlet implementation class LibraryControllerServlet
 */
@WebServlet("/LibraryControllerServlet")
public class LibraryControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static Logger LOG = Logger.getLogger(LibraryControllerServlet.class);
	
	private LibraryDbUtil libraryDbUtil;
	
	@Resource(name="jdbc/library_app")
	private DataSource dataSource;
	

	@Override
	public void init() throws ServletException {
		
		super.init();
		
		//create our library db util and pass in the conn pool/datasource
		try {
			LOG.info("Initializing datasource..");
			libraryDbUtil = new LibraryDbUtil(dataSource);
			
			String log4jConfigFile = "C:\\Users\\rashm\\rashmi-workspace\\Library_app\\WebContent\\WEB-INF\\log4j.properties";
			PropertyConfigurator.configure(log4jConfigFile);
			
		}
		catch(Exception exc) {
			throw new ServletException(exc);
		}
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibraryControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LOG.info("Calling doPost method from doGet method..");
		doPost(request,response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LOG.info("Entered doPost method. Entering try block..");
		try {
			
			//read the command parameter
			
			String theCommand = request.getParameter("command");
			
			LOG.info("Checking command entered for.." +theCommand);
			
			//if the command is missing, then default to log-in page
			if(theCommand == null) {
				LOG.info("No command entered so showing login page..");
				theCommand = "LOGIN";
			}/*else if(!theCommand.equals("LOGIN")) {
				User user = (User) request.getSession().getAttribute("USER_DETAILS");
				if(user==null) {
					response.sendRedirect(request.getContextPath() + "/User-login-form.jsp");
				}
			}*/
			
			//route to the appropriate method
			LOG.info("Entering switch..");
			
			switch(theCommand) {
			
			case "LOGIN":
				
				LOG.info("Login button clicked. Calling verifyUser method from doPost for command.." +theCommand);
				
				verifyUser(request,response);
				break;
				
			case "LIST": 
				
				LOG.info("Calling viewListOfBooksAvailable method from doPost for command.." + theCommand);
				
				viewListOfBooksAvailableInLibrary(request,response);
				break;
				
			case "SEARCH":
				
				LOG.info("Calling searchBook method from doPost for command.." +theCommand);
				
				searchBook(request,response);
				break;
				
			case "ISSUE":
				
				LOG.info("Calling issueBook method from doPost for command.." +theCommand);
				
				issueBook(request,response);
				break;
				
			case "RETURN":
				
				LOG.info("Calling returnBook method from doPost for command.." +theCommand);
				
				returnBook(request,response);
				break;
				
			case "ISSUED-LIST":
				
				LOG.info("Calling listOfBooksIssuedByUser method from doPost for command.." +theCommand);
				
				listOfBooksIssuedByUser(request,response);
				break;
							
			case "LOGOUT":
				
				LOG.info("Calling logOut method from doPost for command.." +theCommand);
				
				logOut(request,response);
				break;
				
			case "CREATE-ACCOUNT":
				
				LOG.info("Calling createAccount method from doPost for command.." +theCommand);
				
				createAccount(request,response);
				break;
				
			case "DELETE-ACCOUNT":
				
				LOG.info("Calling deleteAccount method from doPost for command.." +theCommand);
				
				deleteAccount(request,response);
				break;
							
			default:
				
				LOG.info("Calling default switch statement, login method from doPost for command.." +theCommand);
				
				login(request,response);
				break;
				
				
			
			}
			
			//list the students in MVC fashion
			//listBooks(request,response);
		}
		catch(Exception exc){
			
			LOG.info("Throwing exception from catch block in doPost..");
			
			throw new ServletException(exc);
		}
	}

	private void deleteAccount(HttpServletRequest request, HttpServletResponse response) 
				throws Exception{
		
		LOG.info("Entering deleteAccount method in LibraryControllerServlet..");
		
		User userDetails = (User) request.getSession().getAttribute("USER_DETAILS");
		String userId = userDetails.getUserId();
		int id = userDetails.getId();	
		
		LOG.info("Calling deleteUser method in LibraryDbUtil from LibraryControllerServlet..");
							
		libraryDbUtil.deleteUser(id,userId);
				
		RequestDispatcher dispatcher = request.getRequestDispatcher("/User-login-form.jsp");
		request.setAttribute("errorMessage", "Account deleted successfully!!");
		dispatcher.forward(request, response);
		
		LOG.info("Exiting deleteAccount method in LibraryControllerServlet..");		
		
	}

	private void createAccount(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		LOG.info("Entering createAccount method in LibraryControllerServlet..");
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		User theUser = new User(firstName,lastName,email,userId,password);
		
		LOG.info("Calling addNewUser method in LibraryDbUtil from LibraryControllerServlet..");
		
		boolean result = libraryDbUtil.addNewUser(theUser);
		
		if(result) {
			request.setAttribute("Message", "Account successfully created!!");
		}else {
			request.setAttribute("Message", "Account already exists!!");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/User-login-form.jsp");
		dispatcher.forward(request, response);
		
		LOG.info("Exiting createAccount method in LibraryControllerServlet..");
		//listBooks(request, response);
	}

	private void logOut(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		LOG.info("Entering logout method in LibraryControllerServlet..");
		
		request.getSession().invalidate();
	    response.sendRedirect(request.getContextPath() + "/User-login-form.jsp");
	    
	    LOG.info("Exiting logOut method in LibraryControllerServlet..");
		
	}

	private void listOfBooksIssuedByUser(HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		LOG.info("Entering listOfBooksIssuedByUser method in LibraryControllerServlet..");
			
		User userDetails = (User) request.getSession().getAttribute("USER_DETAILS");
		String userId = userDetails.getUserId();
			 
		LOG.info("Calling getlistOfBooksIssuedByUser method in LibraryDbUtil from LibraryControllerServlet..");
			//get books from db util
		List<Book> books = libraryDbUtil.getListOfBooksIssuedByUser(userId);
			
			// add books to the request
		request.setAttribute("BOOK_LIST", books);
		
			
			//send to JSP page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-issued-books.jsp");
		dispatcher.forward(request, response);
		
		LOG.info("Exiting listOfBooksIssuedByUser method in LibraryControllerServlet..");
			
			/*if(libraryDbUtil.returnBook(true)) {
				RequestDispatcher dispatcher1 = request.getRequestDispatcher("/main-menu.jsp");
				request.setAttribute("Message", "Book returned!!");
				dispatcher.forward(request, response); 
			}else {
				RequestDispatcher dispatcher1 = request.getRequestDispatcher("/main-menu.jsp");
				request.setAttribute("Message", "Book selected not issued. Book not returned!!");
				dispatcher.forward(request, response); 
			}
		*/
		
			
	}

	private void returnBook(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LOG.info("Entering returnBook method in LibraryControllerServlet..");
		
		String user_history_id = request.getParameter("book");
		
		User userDetails = (User) request.getSession().getAttribute("USER_DETAILS");
		String userId = userDetails.getUserId();
		
		LOG.info("Calling returnBook method in LibraryDbUtil from LibraryControllerServlet..");
			
		if(libraryDbUtil.returnBook(user_history_id,userId)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/main-menu.jsp");
			request.setAttribute("Message", "Book returned!!");
			dispatcher.forward(request, response); 
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/main-menu.jsp");
			request.setAttribute("Message", "Book selected not issued. Book not returned!!");
			dispatcher.forward(request, response); 
		}
		
		LOG.info("Exiting returnBook method in LibraryControllerServlet..");
		
	}

	private void issueBook(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		LOG.info("Entering issueBook method in LibraryControllerServlet..");
		
		String bookId = request.getParameter("book");
		
		User userDetails = (User) request.getSession().getAttribute("USER_DETAILS");
		String userId = userDetails.getUserId();
		
		LOG.info("Calling issueBook method in LibraryDbUtil from LibraryControllerServlet..");
		
		//boolean issue = true;
		if(libraryDbUtil.issueBook(bookId,userId)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/main-menu.jsp");
			request.setAttribute("Message", "Book isssued!!");
			dispatcher.forward(request, response); 
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/main-menu.jsp");
			request.setAttribute("Message", "Book selected not available. Book not isssued!!");
			dispatcher.forward(request, response); 
		}
		
		LOG.info("Entering issueBook method in LibraryControllerServlet..");
				
}

	private void searchBook(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		LOG.info("Entering searchBook method in LibraryControllerServlet..");
		
		String bookName = request.getParameter("book-name");
		String authorName = request.getParameter("author-name");
		
				
		//create new boo object
		Book theBook = new Book(bookName,authorName);
		
		LOG.info("Calling serachBook method in LibraryDbUtil from LibraryControllerServlet..");
		
		//check with database if book exists
		List<Book> foundBook = libraryDbUtil.searchBook(theBook);
		
		//send back to login page
		if(foundBook.size() > 0) {
			
			request.setAttribute("foundBook", foundBook );
						
			RequestDispatcher dispatcher = request.getRequestDispatcher("/search-book-found.jsp");
			dispatcher.forward(request, response);
			
		}else {
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/search-book.jsp");
			request.setAttribute("errorMessage", "Book not found!!");
            
            dispatcher.forward(request, response); 
			
		}
		
		LOG.info("Exiting searchBook method in LibraryControllerServlet..");
		
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		LOG.info("Entering login method in LibraryControllerServlet..");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/User-login-form.jsp");
		dispatcher.forward(request, response);
		
		LOG.info("Exiting login method in LibraryControllerServlet..");
		
	}

	private void verifyUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		LOG.info("Entering verifyUser method in LibraryControllerServlet..");
		
		//read user info from form data
		String userId = request.getParameter("user-id");
		String password = request.getParameter("password");
		
		/* Encoding password
		String encode=new String(Base64.getEncoder().encode(password.getBytes()));
		System.out.println(encode);
		String decode =new String(Base64.getDecoder().decode(encode));
		System.out.println(decode);*/
		
		LOG.info("Calling verifyUser method in LibraryDbUtill from LibraryControllerServlet..");
		
		//create new user object
		//User theUser = new User(userId,password);
		
		User userDB = libraryDbUtil.verifyUser(userId,password);
		
		//send back to login page
		if(userDB != null) {
			
			request.getSession().setAttribute("USER_DETAILS", userDB);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/main-menu.jsp");
			dispatcher.forward(request, response);
		}else {
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/User-login-form.jsp");
			request.setAttribute("errorMessage", "Invalid user name or password!! Please enter valid credentials!!");
            
            dispatcher.forward(request, response); 
			
		}
		
		LOG.info("Exiting verifyUser method in LibraryControllerServlet..");
	}
	
	private void viewListOfBooksAvailableInLibrary(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		LOG.info("Entering viewListOfBooksAvailableInLibrary method in LibraryControllerServlet..");
		
		LOG.info("Calling getBooksAvailableInLibrary method in LibraryControllerServlet..");
		
		//get books from db util
		List<Book> books = libraryDbUtil.getBooksAvailableInLibrary();
		
		// add students to the request
		request.setAttribute("BOOK_LIST", books);
		
		//send to JSP page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-books.jsp");
		dispatcher.forward(request, response);
		
		LOG.info("Exiting viewListOfBooksAvailableInLibrary method in LibraryControllerServlet..");
		
	}

}
