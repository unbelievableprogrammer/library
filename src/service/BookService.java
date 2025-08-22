package service;

import dao.BookDAO;
import model.Book;

import java.util.List;

public class BookService {
    private final BookDAO dao;

    public BookService(BookDAO dao) {
        this.dao = dao;
    }

    public void addBook(String title, String author) {
        dao.addBook(new Book(title, author));
    }

    public List<Book> listBooks() {
        return dao.getAllBooks();
    }

    public Book getBook(int id) {
        return dao.getBookById(id);
    }

    public void deleteBook(int id) {
        dao.deleteBookById(id);
    }


}
