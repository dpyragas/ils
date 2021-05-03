package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

import javax.swing.*;
import java.time.LocalDate;

public class DeleteBook implements Command {
    private int bookID;

    public DeleteBook(int bookID) {
        this.bookID = bookID;
    }

    /**
     * Delete the book from the library. Book will not be deleted if it is already on a loan.
     * @param library
     * @param currentDate
     * @throws LibraryException
     */

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        Book book = library.getBookByID(bookID);
        if (book.isOnLoan()) {
            JOptionPane.showMessageDialog(null, "Could not delete the book because the book is currently on a loan");
        } else {
            book.setDeleted(true);
        }
    }
}
