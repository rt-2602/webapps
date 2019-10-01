package com.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class LibraryDbUtil {
	
	static Logger LOG = Logger.getLogger(LibraryControllerServlet.class);
	
	private DataSource dataSource;
	
	
	public LibraryDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Book> getBooksAvailableInLibrary() throws Exception{
		
		LOG.info("Calling getBooksAvailableInLibrary method in LibraryDbUtil..");
				
		List<Book> books = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			
			String getBookListQuery = "select * from books order by book_name";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(getBookListQuery);
			
			// process result set
			while(myRs.next()) {
				
				//retrieve data from result set
				int id = myRs.getInt("id");
				String bookName = myRs.getString("book_name");
				String authorName = myRs.getString("author");
				int numOfCopies = myRs.getInt("number_of_copies");
				
				//create student object
				Book tempBook = new Book(id, bookName, authorName,numOfCopies);
							
				//add it to list of books
				books.add(tempBook);
				
				LOG.info("Returning list of books from getBooksAvailableInLibrary method in LibraryDbUtil. With size.." +books.size());
				
			}	
		
			LOG.info("Exiting getBooksAvailableInLibrary method in LibraryDbUtil..");
			
			//Collections.sort(books);
			return books;
		}
		finally {
			
			LOG.info("Entering finally block in getBooksAvailableInLibrary() method in LibraryDbUtil..");
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
				
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		
		LOG.info("Entering close(myConn, myStmt, myRs) method in LibraryDbUtil..");
		
		try {
			
			LOG.info("Entering try block in close(myConn, myStmt, myRs) method in LibraryDbUtil..");
			
			if(myRs != null) {
				myRs.close();
			}
			
			if(myStmt != null) {
				myStmt.close();
				
			}
			
			if(myConn != null) {
				myConn.close();
			}
			
		}catch(Exception exc) {
			
			LOG.info("Entering catch block in close(myConn, myStmt, myRs) method in LibraryDbUtil..");
			
			exc.printStackTrace();
		}
		
		LOG.info("Exiting close(myConn, myStmt, myRs) method in LibraryDbUtil..");
		
	}

	public User verifyUser(String userId, String password) throws Exception{
		
		LOG.info("Entering verifyUser method in LibraryDbUtil for.." +userId);
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			
			LOG.info("Entering try block in verifyUser method in LibraryDbUtil..");
			
			myConn = dataSource.getConnection();
			
			String getUserQuery = "select * from users where user_id = ? and password = ?";
			
			myStmt = myConn.prepareStatement(getUserQuery);
			
			myStmt.setString(1, userId);
			myStmt.setString(2, password);
			
			myRs = myStmt.executeQuery();
			
			if(myRs.next()) {
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				//String userId = myRs.getString("user_id");
				//String password = myRs.getString("password");
				
				LOG.info("Exiting verifyUser method in LibraryDbUtil with new User object with.." +id + firstName + lastName);
				
				User user = new User(id, firstName, lastName, email, userId, password);
				return user;
				
			}else {
				
				LOG.info("Exiting verifyUser method in LibraryDbUtil with null..");
				
				return null;
			}	
			
		
		}
			finally {
				
				LOG.info("Entering finally block in verifyUser method in LibraryDbUtil..");
				
				close(myConn, myStmt, null);
					}
	}

	public List<Book> searchBook(Book theBook) throws Exception {
		
		LOG.info("Entering searchBook method in LibraryDbUtil for.." +theBook.getBookName());
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		ResultSet myRs = null;
		
		List<Book> foundBooks = new ArrayList<>(); 
		
		
		try {
			
			LOG.info("Entering try block in searchBook method in LibraryDbUtil..");
			
			
			myConn = dataSource.getConnection();
			
			String checkIfBookExistsQuery = "select * from books where book_name like ? or author like ? ";
			
			
			myStmt = myConn.prepareStatement(checkIfBookExistsQuery);
			
			
			myStmt.setString(1, theBook.getBookName()+"%");
			myStmt.setString(2, theBook.getAuthorName()+"%");
			
			
			myRs = myStmt.executeQuery();
			
			while(myRs.next()) {
				
				//retrieve data from result set
				int id = myRs.getInt("id");
				String bookName = myRs.getString("book_name");
				String authorName = myRs.getString("author");
				int numOfCopies = myRs.getInt("number_of_copies");
				
				//create book object
				Book tempBook = new Book(id, bookName, authorName,numOfCopies);
							
				//add it to list of books
				foundBooks.add(tempBook);
				
				LOG.info("Returning foundBooks from searchBook method in LibraryDbUtil with size.." +foundBooks.size());
				
			}	
			
		LOG.info("Exiting searchBook method in LibraryDbUtil..");
			
		return foundBooks;		
		
	}
	finally {
		
		LOG.info("Entering finally block in searchBook method in LibraryDbUtil..");
		
		close(myConn, myStmt, null);
		}
}
	

public boolean ifBookIsIssued(String bookId, String userId) throws Exception{
		
		LOG.info("Entering ifBookIsIssued method in LibraryDbUtil for.." +bookId +userId);
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
				
		int id = Integer.parseInt(bookId);
		
		Boolean result = true;
		
		try {
				
			LOG.info("Entering try block in ifBookIsIssued in LibraryDbUtil..");
			
			myConn = dataSource.getConnection();
			
			String checkIfBookIsIssuedForUserIDQuery = "select * from user_books where user_id = ? and status = 'ISSUED' ";
			myStmt = myConn.prepareStatement(checkIfBookIsIssuedForUserIDQuery);
	
			myStmt.setString(1, userId);
	
			// execute query
			myRs = myStmt.executeQuery();
			
			int count=0;
			while (myRs.next()) {
				 
				count++;
			
				String userIdDb = myRs.getString("user_id");
				int bookIdDb = myRs.getInt("book_id");
	
				if(userIdDb.equals(userId) ){
					
					if (bookIdDb == id) {
						result = false;
					}
				}
			}
			if(count>=2) {
				result = false;
			}
			
		}
		
		finally {
			
			LOG.info("Entering finally block in ifBookIsIssued in LibraryDbUtil..");

			close(myConn, myStmt,null);
		}
		
		LOG.info("Exiting ifBookIsIssued method in LibraryDbUtil..");
		
		return result;
	}

	@SuppressWarnings("resource")
	public boolean issueBook(String bookId, String userId) throws Exception {

		LOG.info("Entering issueBook method in LibraryDbUtil for.." + bookId +userId);
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int id;
		int numOfCopies = 0;
		boolean result = false;
		
		
		try {
			
			id = Integer.parseInt(bookId);

			myConn = dataSource.getConnection();
						
			String getBookWithBookIdQuery = "select * from books where id = ?";

			myStmt = myConn.prepareStatement(getBookWithBookIdQuery);

			myStmt.setInt(1, id);

			// execute query
			myRs = myStmt.executeQuery();

			// process result set
			if (myRs.next()) {

				// retrieve data from result set
								
					numOfCopies = myRs.getInt("number_of_copies");
					
				    if (numOfCopies > 0 && numOfCopies<=5 ) {
				    						    		
				    	if(ifBookIsIssued(bookId,userId)){
								
							numOfCopies = numOfCopies - 1;
		
							String insertDetailsIntouser_booksTableQuery = "insert into user_books (user_id, book_id, status) values ( ?, ?, ? )";
							
							myStmt = myConn.prepareStatement(insertDetailsIntouser_booksTableQuery);
		
							myStmt.setString(1, userId);
							myStmt.setString(2, bookId);
							myStmt.setString(3, "ISSUED");
																												
							myStmt.execute();
														
							String sql3 = "update books set number_of_copies=? where id=?";
									
							myStmt = myConn.prepareStatement(sql3);
		
							myStmt.setInt(1, numOfCopies);
							myStmt.setString(2, bookId);
							
							myStmt.execute();
							result = true;
							
							myStmt.close();
							}
					
				    }else {
						result = false;
						
					}
			}
		}	
		
		finally {
			
			LOG.info("Entering finally block in issueBook in LibraryDbUtil..");

			close(myConn, myStmt,null);
		}
		
		LOG.info("Exiting issueBook method in LibraryDbUtil..");
		
		return result;
	}
	

	@SuppressWarnings("resource")
	public boolean returnBook(String tableId, String userId) throws Exception {
		
		LOG.info("Entering returnBook method in LibraryDbUtil for.." +userId);
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		ResultSet myRs1 = null;
		int id;
		int numOfCopies = 0;
		boolean result = false;
		int issuedId = 0;

		try {
			
			id = Integer.parseInt(tableId);

			myConn = dataSource.getConnection();

			String getBookWithBookIDFromUser_BooksTableQuery = "select * from user_books where id = ?";

			myStmt = myConn.prepareStatement(getBookWithBookIDFromUser_BooksTableQuery);

			myStmt.setInt(1, id);

			// execute query
			myRs = myStmt.executeQuery();

			// process result set
			if (myRs.next()) {

				// retrieve data from result set
				
				issuedId = myRs.getInt("book_id");
								
				String getBookWithIdFromBooksTableQuery = "select * from books where id = ?";
				
				myStmt = myConn.prepareStatement(getBookWithIdFromBooksTableQuery);

				myStmt.setInt(1, issuedId);

				// execute query
				myRs1 = myStmt.executeQuery();
				
				if (myRs1.next()) {
				
					numOfCopies = myRs1.getInt("number_of_copies");
					
					if (numOfCopies >= 0 ) {
						
							numOfCopies = numOfCopies + 1;
		
							String deleteBookWithIdFromUser_booksTableQuery = "delete from user_books where id = ?";
							
							myStmt = myConn.prepareStatement(deleteBookWithIdFromUser_booksTableQuery);
		
							myStmt.setString(1, tableId);
		
							myStmt.execute();
		
							String updateNumber_of_booksInBooksTableQuery = "update books set number_of_copies=? where id=?";
							
		
							myStmt = myConn.prepareStatement(updateNumber_of_booksInBooksTableQuery);
		
							myStmt.setInt(1, numOfCopies);
							myStmt.setInt(2, issuedId);
							
							myStmt.execute();
							result = true;
					}

				}else {
					
					result = false;
				}
			}
			
			LOG.info("Exiting returnBook method in LibraryDbUtil..");
			
			return result;
		
		} finally {

			LOG.info("Entering finally block in returnBook in LibraryDbUtil..");
			
			close(myConn, myStmt,null);
		}
				
	}

	public List<Book> getListOfBooksIssuedByUser(String userId) throws Exception{
		
		LOG.info("Entering getListOfBooksIssuedByUser method in LibraryDbUtil for.." +userId);
		
		List<Book> books = new ArrayList<>();
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int user_history_id = 0;
				
		try {
			
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String getIssuedBooksListFromUsser_booksAndBooksTableQuery = "select ub.id, b.book_name, b.author, ub.status, ub.book_id from books b, user_books ub where b.id = ub.book_id and ub.user_id = ?";
			
			myStmt = myConn.prepareStatement(getIssuedBooksListFromUsser_booksAndBooksTableQuery);
			
			myStmt.setString(1, userId);
			
			myRs = myStmt.executeQuery();
			
			// process result set
			while(myRs.next()) {
				
				//retrieve data from result set
				user_history_id = myRs.getInt("id");
				String book_name = myRs.getString("book_name");
				String author_name = myRs.getString("author");
				String status = myRs.getString("status");
				
				//create book object
				Book tempBook = new Book(book_name, author_name, status, user_history_id);
							
				//add it to list of books
				books.add(tempBook);
							
			}	
			
		
			LOG.info("Exiting getListOfBooksIssuedByUser method in LibraryDbUtil..");
			
			return books;
		}
		finally {
			
			LOG.info("Entering finally block in getListOfBooksIssuedByUser method in LibraryDbUtil..");
			
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
				
	}

	@SuppressWarnings("resource")
	public boolean addNewUser(User theUser) throws Exception{
		
		LOG.info("Entering addNewUser method in LibraryDbUtil for.." +theUser.getFirstName() +theUser.getLastName());
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet  myRs = null;
		boolean result = false;
		
		try {
			
			myConn = dataSource.getConnection();
			
			String addNewUserToUsersTableQuery = "select * from users where first_name = ? and last_name = ? and email = ? and user_id = ? and password = ? ";
			
			myStmt = myConn.prepareStatement(addNewUserToUsersTableQuery);
			
			myStmt.setString(1, theUser.getFirstName());
			myStmt.setString(2, theUser.getLastName());
			myStmt.setString(3, theUser.getEmail());
			myStmt.setString(4, theUser.getUserId());
			myStmt.setString(5, theUser.getPassword());
			
			 //myStmt.execute();
			 
			 myRs = myStmt.executeQuery();
				
				if(!myRs.next()) {
					
					String insertNewUsertoUsersTableQuery = "insert into users "
							+ " (first_name, last_name, email, user_id, password)"
							+ "values (?, ?, ?, ?, ?)";
					
					myStmt = myConn.prepareStatement(insertNewUsertoUsersTableQuery);
					
					myStmt.setString(1, theUser.getFirstName());
					myStmt.setString(2, theUser.getLastName());
					myStmt.setString(3, theUser.getEmail());
					myStmt.setString(4, theUser.getUserId());
					myStmt.setString(5, theUser.getPassword());
					
					myStmt.execute();
					
					result = true;
									
				} else {
					
					result = false;
			
				}
			
			LOG.info("Exiting addNewUser method in LibraryDbUtil..");
				
			return result;
			
		}
		finally {
			
			LOG.info("Entering finally block in addNewUser method in LibraryDbUtil..");
			
			close(myConn, myStmt, null);
		}
	}

	@SuppressWarnings("resource")
	public void deleteUser(int id, String userId) throws Exception{
		
		LOG.info("Entering deleteUser method in LibraryDbUtil for.." +userId);
		
		Connection myConn =null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		boolean returnFlag=false;
		
		try {
						
			myConn = dataSource.getConnection();
			
			// get the books issued from user_books table and add to no. of books available
			
			String getBookIssuedForUserIdQuery = "select * from user_books where user_id=?";
			
			myStmt = myConn.prepareStatement(getBookIssuedForUserIdQuery);
			
			myStmt.setString(1, userId);
			
			myRs = myStmt.executeQuery();
			
			// process result set
			while(myRs.next()) {
				
				//retrieve data from result set
				
				String userBooksId = myRs.getString("id");
				System.out.println("Calling returnBook() for "+userBooksId+" and "+userId);
				returnFlag = returnBook(userBooksId, userId);
				
			}
			
			// delete from user table
			if(returnFlag) {
				String deleteUserFromUsersTableQuery = "delete from users where id=?";
				
				myStmt = myConn.prepareStatement(deleteUserFromUsersTableQuery);
				
				myStmt.setInt(1, id);
				
				myStmt.execute();
			}
			
			LOG.info("Exiting deleteUser method in LibraryDbUtil..");
		}
		finally {
			
			LOG.info("Entering finally block in LibraryDbUtil..");
			
			close(myConn, myStmt, null);
			
		}
		
	}
}

