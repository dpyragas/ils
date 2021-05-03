package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

import javax.swing.*;
import java.time.LocalDate;

public class ReturnBook implements Command {
    private int bookID;
    private int patronID;

    public ReturnBook(int bookID, int patronID) {
        this.bookID = bookID;
        this.patronID = patronID;
    }

    /**
     * Return the book to the library.
     * @param library
     * @param currentDate
     * @throws LibraryException
     */

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        Book book = library.getBookByID(bookID);
        if (book.isOnLoan()) {
            Patron patron = library.getPatronByID(patronID);
            patron.returnBook(book, currentDate);
        } else {
            JOptionPane.showMessageDialog(null, "Book is not on a loan.");
        }
    }
}
