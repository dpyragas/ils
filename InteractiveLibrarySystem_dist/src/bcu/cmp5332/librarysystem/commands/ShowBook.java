package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;

import java.time.LocalDate;
import java.util.List;

public class ShowBook implements Command {
    private int bookID;

    public ShowBook(int bookID) {
        this.bookID = bookID;
    }

    /**
     * Show one book.
     * @param library
     * @param currentDate
     * @throws LibraryException
     */

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        Book book = library.getBookByID(bookID);
        System.out.println(book.getDetailsShort());
    }
}
