package com.web.jdbc;

public class Book implements Comparable<Book>{
	
	public Book(int id, String userId, String status) {
		super();
		this.id = id;
		this.userId = userId;
		this.status = status;
	}

	private int id;
	private String bookName;
	private String authorName;
	private int numberOfCopies;
	private String userId;
	private String status;
	private int user_history_id;
	
	public Book() {
		super();
	}
	

	public Book(String bookName, String authorName, String status, int user_history_id) {
		super();
		this.bookName = bookName;
		this.authorName = authorName;
		this.status = status;
		this.user_history_id = user_history_id;
	}




	public Book(String bookName, String userId, String status) {
		super();
		this.bookName = bookName;
		this.userId = userId;
		this.status = status;
	}

	public Book(int id) {
		super();
		this.id = id;
	}

	public Book(String bookName, String authorName) {
		super();
		this.bookName = bookName;
		this.authorName = authorName;
	}

	public Book(int id, String bookName, String authorName,int numberOfCopies) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.authorName = authorName;
		this.numberOfCopies = numberOfCopies;
	}

	public Book(String bookName, String authorName, int numberOfCopies) {
		super();
		this.bookName = bookName;
		this.authorName = authorName;
		this.numberOfCopies = numberOfCopies;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public int getNumberOfCopies() {
		return numberOfCopies;
	}

	public void setNumberOfCopies(int numberOfCopies) {
		this.numberOfCopies = numberOfCopies;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", bookName=" + bookName + ", authorName=" + authorName + "]";
	}

	@Override
	public int compareTo(Book book) {
		return bookName.compareTo(book.getBookName());
	}



	public int getUser_history_id() {
		return user_history_id;
	}



	public void setUser_history_id(int user_history_id) {
		this.user_history_id = user_history_id;
	}

}
