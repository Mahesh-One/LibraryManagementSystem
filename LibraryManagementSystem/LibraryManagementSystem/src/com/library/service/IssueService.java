package com.library.service;

import java.util.ArrayList;
import java.util.List;
import com.library.exception.BookNotAvailableException;
import com.library.exception.BookNotFoundException;
import com.library.exception.StudentNotFoundException;
import com.library.model.Book;
import com.library.model.IssueRecord;

public class IssueService {
    private final List<IssueRecord> records = new ArrayList<>();
    private int nextIssueId = 1;

    public IssueRecord issueBook(int bookId, int studentId, BookService bookService, StudentService studentService)
            throws BookNotFoundException, StudentNotFoundException, BookNotAvailableException {
        Book book = bookService.findBookById(bookId);
        studentService.findStudentById(studentId);
        if (!book.isAvailable()) throw new BookNotAvailableException("Book is already issued.");
        IssueRecord record = new IssueRecord(nextIssueId++, bookId, studentId);
        records.add(record);
        book.setAvailable(false);
        return record;
    }

    public void returnBook(int issueId, BookService bookService) throws BookNotFoundException {
        for (IssueRecord record : records) {
            if (record.getIssueId() == issueId) {
                if (record.isReturned()) throw new IllegalStateException("Book is already returned.");
                record.returnBook();
                bookService.findBookById(record.getBookId()).setAvailable(true);
                return;
            }
        }
        throw new IllegalArgumentException("Issue record with ID " + issueId + " not found.");
    }

    public List<IssueRecord> getAllRecords() { return records; }
}
