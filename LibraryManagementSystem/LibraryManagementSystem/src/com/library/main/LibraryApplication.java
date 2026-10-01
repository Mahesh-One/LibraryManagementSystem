package com.library.main;

import java.util.List;
import com.library.exception.BookNotAvailableException;
import com.library.exception.BookNotFoundException;
import com.library.exception.StudentNotFoundException;
import com.library.model.Book;
import com.library.model.IssueRecord;
import com.library.model.Student;
import com.library.service.BookService;
import com.library.service.IssueService;
import com.library.service.StudentService;
import com.library.util.InputUtil;

public class LibraryApplication {
    private static final BookService bookService = new BookService();
    private static final StudentService studentService = new StudentService();
    private static final IssueService issueService = new IssueService();

    public static void main(String[] args) {
        loadSampleData();
        int choice;
        do {
            printMenu();
            choice = InputUtil.readInt("Enter choice: ");
            try {
                switch (choice) {
                    case 1 -> addBook();
                    case 2 -> viewBooks();
                    case 3 -> searchBook();
                    case 4 -> updateBook();
                    case 5 -> deleteBook();
                    case 6 -> addStudent();
                    case 7 -> viewStudents();
                    case 8 -> updateStudent();
                    case 9 -> deleteStudent();
                    case 10 -> issueBook();
                    case 11 -> returnBook();
                    case 12 -> viewIssueRecords();
                    case 0 -> System.out.println("Thank you for using Library Management System.");
                    default -> System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (choice != 0);
        InputUtil.close();
    }

    private static void printMenu() {
        System.out.println("\n========== LIBRARY MANAGEMENT SYSTEM ==========");
        System.out.println("1. Add Book");
        System.out.println("2. View All Books");
        System.out.println("3. Search Book");
        System.out.println("4. Update Book");
        System.out.println("5. Delete Book");
        System.out.println("6. Add Student");
        System.out.println("7. View All Students");
        System.out.println("8. Update Student");
        System.out.println("9. Delete Student");
        System.out.println("10. Issue Book");
        System.out.println("11. Return Book");
        System.out.println("12. View Issue Records");
        System.out.println("0. Exit");
        System.out.println("===============================================");
    }

    private static void addBook() {
        int id = InputUtil.readInt("Book ID: ");
        String title = InputUtil.readString("Title: ");
        String author = InputUtil.readString("Author: ");
        String category = InputUtil.readString("Category: ");
        bookService.addBook(new Book(id, title, author, category));
        System.out.println("Book added successfully.");
    }

    private static void viewBooks() {
        if (bookService.getAllBooks().isEmpty()) { System.out.println("No books found."); return; }
        for (Book book : bookService.getAllBooks()) book.displayBook();
    }

    private static void searchBook() {
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Title");
        System.out.println("3. Search by Author");
        int choice = InputUtil.readInt("Choice: ");
        if (choice == 1) {
            try { bookService.findBookById(InputUtil.readInt("Book ID: ")).displayBook(); }
            catch (BookNotFoundException e) { System.out.println(e.getMessage()); }
        } else {
            String keyword = InputUtil.readString("Enter keyword: ");
            List<Book> result = choice == 2 ? bookService.searchByTitle(keyword) : bookService.searchByAuthor(keyword);
            if (result.isEmpty()) System.out.println("No books found.");
            else for (Book book : result) book.displayBook();
        }
    }

    private static void updateBook() throws BookNotFoundException {
        int id = InputUtil.readInt("Book ID: ");
        String title = InputUtil.readString("New Title: ");
        String author = InputUtil.readString("New Author: ");
        String category = InputUtil.readString("New Category: ");
        bookService.updateBook(id, title, author, category);
        System.out.println("Book updated successfully.");
    }

    private static void deleteBook() throws BookNotFoundException {
        bookService.deleteBook(InputUtil.readInt("Book ID: "));
        System.out.println("Book deleted successfully.");
    }

    private static void addStudent() {
        int id = InputUtil.readInt("Student ID: ");
        String name = InputUtil.readString("Name: ");
        String email = InputUtil.readString("Email: ");
        String phone = InputUtil.readString("Phone: ");
        studentService.addStudent(new Student(id, name, email, phone));
        System.out.println("Student added successfully.");
    }

    private static void viewStudents() {
        if (studentService.getAllStudents().isEmpty()) { System.out.println("No students found."); return; }
        for (Student student : studentService.getAllStudents()) student.displayStudent();
    }

    private static void updateStudent() throws StudentNotFoundException {
        int id = InputUtil.readInt("Student ID: ");
        String name = InputUtil.readString("New Name: ");
        String email = InputUtil.readString("New Email: ");
        String phone = InputUtil.readString("New Phone: ");
        studentService.updateStudent(id, name, email, phone);
        System.out.println("Student updated successfully.");
    }

    private static void deleteStudent() throws StudentNotFoundException {
        studentService.deleteStudent(InputUtil.readInt("Student ID: "));
        System.out.println("Student deleted successfully.");
    }

    private static void issueBook() throws BookNotFoundException, StudentNotFoundException, BookNotAvailableException {
        int bookId = InputUtil.readInt("Book ID: ");
        int studentId = InputUtil.readInt("Student ID: ");
        issueService.issueBook(bookId, studentId, bookService, studentService);
        System.out.println("Book issued successfully.");
    }

    private static void returnBook() throws BookNotFoundException {
        int issueId = InputUtil.readInt("Issue ID: ");
        issueService.returnBook(issueId, bookService);
        System.out.println("Book returned successfully.");
    }

    private static void viewIssueRecords() {
        if (issueService.getAllRecords().isEmpty()) { System.out.println("No issue records found."); return; }
        for (IssueRecord record : issueService.getAllRecords()) record.displayIssueRecord();
    }

    private static void loadSampleData() {
        bookService.addBook(new Book(101, "Java Programming", "James Gosling", "Programming"));
        bookService.addBook(new Book(102, "Clean Code", "Robert C. Martin", "Programming"));
        bookService.addBook(new Book(103, "The Alchemist", "Paulo Coelho", "Novel"));
        studentService.addStudent(new Student(1, "Rahul", "rahul@gmail.com", "9876543210"));
        studentService.addStudent(new Student(2, "Priya", "priya@gmail.com", "9876501234"));
    }
}
