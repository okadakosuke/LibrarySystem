package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.Book;
import dao.BookDao;

public class BookService {

	public void insert(Book book) {

		Connection connection = null;
		try {
			connection = getConnection();

			new BookDao().insert(connection, book);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public void update(Book book) {

		Connection connection = null;
		try {
			connection = getConnection();

			new BookDao().update(connection, book);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public void updateReserving(String bookId) {

		Connection connection = null;
		try {
			connection = getConnection();

			new BookDao().updateReserving(connection, bookId);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public Book selectBook(int bookId) {

		Connection connection = null;
		try {
			connection = getConnection();

			BookDao bookDao = new BookDao();
			Book book = bookDao.selectBook(connection, bookId);

			commit(connection);

			return book;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public List<Book> selectAll() {

		Connection connection = null;
		try {
			connection = getConnection();

			List<Book> books = new BookDao().selectAll(connection);

			commit(connection);

			return books;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public List<Book> selectRefinedBook(String selectBox, String freeWord, String condition, String selectedLibrary,
			String selectedShelfId, String isReserving, String delay, String bookStatus) {

		Map<String, String> columnMap = getMapData();
		Connection connection = null;
		try {
			connection = getConnection();

			List<Book> bookList = new BookDao().selectRefinedBook(connection, columnMap.get(selectBox), freeWord, condition,
					selectedLibrary, selectedShelfId, isReserving, delay, bookStatus);

			commit(connection);

			return bookList;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public List<Book> selectShelfId() {

		Connection connection = null;
		try {
			connection = getConnection();

			List<Book> books = new BookDao().selectShelfId(connection);

			commit(connection);

			return books;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public List<Book> getSelectedBooks(String selectBox, String freeWord, String condition, String sort, String bookStatus,
			List<String> newBooks, List<String> libraries, List<String> categories, List<String> types) {

		Map<String, String> columnMap = getMapData();
		Connection connection = null;

		try {
			connection = getConnection();
			List<Book> books = new BookDao().getSelectedBooks(connection, columnMap.get(selectBox), freeWord, condition, sort, bookStatus,
					newBooks, libraries, categories, types);
			commit(connection);

			if(books == null) return getDefaultValue();
			else return books;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public Map<String, String> getMapData(){

		Map<String, String> map = new HashMap<>();
		map.put("1", "");
		map.put("2", "name");
		map.put("3", "author");
		map.put("4", "publisher");
		map.put("5", "isbn_id");

		return map;
	}

	public Map<String, String> getMapCategory(){

		Map<String, String> map = new HashMap<>();
		map.put("1", "全て");
		map.put("2", "本");
		map.put("3", "著者");
		map.put("4", "出版社");
		map.put("5", "ISBN番号");

		return map;
	}

	public List<Book> getDefaultValue(){

		List<Book> defaultBooks = new ArrayList<>();
		Book defaultBook = new Book();

		defaultBook.setId(0);
		defaultBook.setAuthor("");
		defaultBook.setCategory("");
		defaultBook.setDisposing("");
		defaultBook.setIsbnId("");
		defaultBook.setKeeping("");
		defaultBook.setLending("");
		defaultBook.setLibraryId("");
		defaultBook.setName("");
		defaultBook.setPublishedDate("");
		defaultBook.setPublisher("");
		defaultBook.setReserving("");
		defaultBook.setShelfId("");
		defaultBook.setType("");

		defaultBooks.add(defaultBook);

		return defaultBooks;
	}


	public void lendingBook(String bookId) {

		Connection connection = null;
		try {
			connection = getConnection();

			new BookDao().lendingBook(connection, bookId);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public void returningBook(String lending) {

		Connection connection = null;
		try {
			connection = getConnection();

			new BookDao().returningBook(connection, lending);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}




	public void deliveringBook(int bookId, int num, String time) {

		Connection connection = null;
		try {
			connection = getConnection();

			new BookDao().deliveringBook(connection, bookId, num, time);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public void cancelingBook(int bookId, int num, String time) {

		Connection connection = null;
		try {
			connection = getConnection();

			new BookDao().cancelingBook(connection, bookId, num, time);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
	public List<Book> selectAdmin() {

		Connection connection = null;
		try {
			connection = getConnection();

			List<Book> books = new BookDao().selectAdmin(connection);

			commit(connection);

			return books;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
	public void status(int bookId) {

		Connection connection = null;
		try {
			connection = getConnection();

			new BookDao().status(connection, bookId);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
}