package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

import javax.swing.*;
import java.time.LocalDate;

public class RenewBook implements Command {
    private int bookID;
    private int patronID;

    public RenewBook(int bookID, int patronID) {
        this.bookID = bookID;
        this.patronID = patronID;
    }

    /**
     * Renew the book for another loan period.
     * @param library
     * @param currentDate
     * @throws LibraryException
     */

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        Book book = library.getBookByID(bookID);
        if (book.isOnLoan()) {
            Patron patron = library.getPatronByID(patronID);
            patron.renewBook(book, currentDate, currentDate.plusDays(library.getLoanPeriod()));
        } else {
            JOptionPane.showMessageDialog(null, "Book is not on a loan.");
        }
    }
}
