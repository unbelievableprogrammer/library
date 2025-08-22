package dao;

import model.Book;

import java.util.List;

public interface BookDAO {
    void addBook(Book book);
    List<Book> getAllBooks();
    Book getBookById(int id);
    void deleteBookById(int id);
}
