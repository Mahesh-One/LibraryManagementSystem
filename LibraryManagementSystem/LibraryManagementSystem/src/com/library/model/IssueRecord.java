package com.library.model;

import java.time.LocalDate;

public class IssueRecord {
    private int issueId;
    private int bookId;
    private int studentId;
    private LocalDate issueDate;
    private LocalDate returnDate;
    private boolean returned;

    public IssueRecord(int issueId, int bookId, int studentId) {
        this.issueId = issueId;
        this.bookId = bookId;
        this.studentId = studentId;
        this.issueDate = LocalDate.now();
        this.returned = false;
    }
    public int getIssueId() { return issueId; }
    public int getBookId() { return bookId; }
    public int getStudentId() { return studentId; }
    public LocalDate getIssueDate() { return issueDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public boolean isReturned() { return returned; }

    public void returnBook() {
        returnDate = LocalDate.now();
        returned = true;
    }
    public void displayIssueRecord() {
        System.out.println("Issue ID: " + issueId + " | Book ID: " + bookId + " | Student ID: " + studentId
                + " | Issue Date: " + issueDate + " | Return Date: "
                + (returnDate == null ? "Not Returned" : returnDate)
                + " | Status: " + (returned ? "Returned" : "Issued"));
    }
}
