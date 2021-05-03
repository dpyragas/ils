package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

import java.time.LocalDate;

public class ShowPatron implements Command {
    private int patronID;

    public ShowPatron(int patronID) {
        this.patronID = patronID;
    }

    /**
     * Show one patron.
     * @param library
     * @param currentDate
     * @throws LibraryException
     */

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        Patron patron = library.getPatronByID(patronID);
        System.out.println(patron.getDetailsShort());
    }
}
