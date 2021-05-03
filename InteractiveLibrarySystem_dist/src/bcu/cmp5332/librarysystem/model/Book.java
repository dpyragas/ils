package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.LibraryException;

import java.time.LocalDate;
import java.util.stream.Stream;

public class Book {

    private int id;
    private String title;
    private String author;
    private String publicationYear;
    private String publisher;

    private boolean deleted;

    private Loan loan;

    public Book(int id, String title, String author, String publicationYear, String publisher) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getDetailsShort() {
        return "Book #" + id + " - " + title;
    }

    public boolean isOnLoan() {
        return (loan != null) && (!loan.isTerminated());
    }

    public LocalDate getDueDate() {
        return loan.getDueDate();
    }

    public void setDueDate(LocalDate dueDate) throws LibraryException {
        loan.setDueDate(dueDate);
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * Status changed according to the availability of the books.
     * @return
     */

    public String getStatus() {
        if (isOnLoan()) {
            return "On Loan.";
        } else {
            return "Available.";
        }
    }

    public boolean isDeleted() {
        return deleted;
    }

    public String getPublisher() {
        return publisher;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    /**
     * Terminate the loan when returning to the library.
     * @param currentDate
     */

    public void returnToLibrary(LocalDate currentDate) {
        loan.setTerminated(true, currentDate);
    }
}
