package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.LibraryException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Patron {

    private int id;
    private String name;
    private String phone;
    private final List<Book> books = new ArrayList<>();

    private boolean deleted;

    public Patron(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    /**
     * Helper function for issue book command.
     * @param book
     * @param currentDate
     * @param dueDate
     * @throws LibraryException
     */

    public void borrowBook(Book book, LocalDate currentDate, LocalDate dueDate) throws LibraryException {
        books.add(book);
        book.setLoan(new Loan(this, book, currentDate, dueDate));
    }

    /**
     * Helper function for renew book command.
     * @param book
     * @param currentDate
     * @param dueDate
     * @throws LibraryException
     */

    public void renewBook(Book book, LocalDate currentDate, LocalDate dueDate) throws LibraryException {
        book.setLoan(new Loan(this, book, currentDate, dueDate));
    }

    /**
     * Helper function for return book command.
     * @param book
     * @param currentDate
     * @throws LibraryException
     */

    public void returnBook(Book book,LocalDate currentDate) throws LibraryException {
        books.remove(book);
        book.returnToLibrary(currentDate);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getDetailsShort() {
        return "Patron #" + id + " - " + name;
    }

    public List<Book> getBooks() {
        return books;
    }
}
