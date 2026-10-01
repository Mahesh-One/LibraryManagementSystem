package com.library.service;

import java.util.ArrayList;
import java.util.List;
import com.library.exception.BookNotFoundException;
import com.library.model.Book;

public class BookService {
    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book) { books.add(book); }

    public List<Book> getAllBooks() { return books; }

    public Book findBookById(int id) throws BookNotFoundException {
        for (Book book : books) {
            if (book.getBookId() == id) return book;
        }
        throw new BookNotFoundException("Book with ID " + id + " not found.");
    }

    public List<Book> searchByTitle(String keyword) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) result.add(book);
        }
        return result;
    }

    public List<Book> searchByAuthor(String keyword) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(keyword.toLowerCase())) result.add(book);
        }
        return result;
    }

    public void updateBook(int id, String title, String author, String category) throws BookNotFoundException {
        Book book = findBookById(id);
        book.setTitle(title);
        book.setAuthor(author);
        book.setCategory(category);
    }

    public void deleteBook(int id) throws BookNotFoundException {
        Book book = findBookById(id);
        if (!book.isAvailable()) throw new IllegalStateException("Cannot delete an issued book.");
        books.remove(book);
    }
}
