package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class BorrowBook implements Command {
    private int bookID;
    private int patronID;

    public BorrowBook(int bookID, int patronID) {
        this.bookID = bookID;
        this.patronID = patronID;
    }
    /**
     * Issue the book for the patron. In here several checks are made to verify the patron did not reach the maximum
     * limit of books and also whether the book or patron has been deleted or already on the loan.
     * @param library
     * @param currentDate
     * @throws LibraryException
     */

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        Patron patron = library.getPatronByID(patronID);
        if (patron.getBooks().size() >= library.getMaximumBooksForPatron()) {
            JOptionPane.showMessageDialog(null, "Maximum limit of book for the patron has reached.");
        } else {
            Book book = library.getBookByID(bookID);
            if (book.isDeleted()) {
                JOptionPane.showMessageDialog(null, "Book is deleted from the library.");
            }
            if (patron.isDeleted()) {
                JOptionPane.showMessageDialog(null, "Patron is deleted from the library.");
            } else {
                if (book.isOnLoan()) {
                    JOptionPane.showMessageDialog(null, "Book is already on loan.");
                } else
                    patron.borrowBook(book, currentDate, currentDate.plusDays(library.getLoanPeriod()));
            }
        }

    }
}
