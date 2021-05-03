package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

import javax.swing.*;
import java.time.LocalDate;

public class DeletePatron implements Command {
    private int patronID;

    public DeletePatron(int patronID) {
        this.patronID = patronID;
    }

    /**
     * Delete the patron if the patron does not have books on loan.
     * @param library
     * @param currentDate
     * @throws LibraryException
     */

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        Patron patron = library.getPatronByID(patronID);
        if (patron.getBooks().size() > 0) {
            JOptionPane.showMessageDialog(null, "Could not delete the patron because the patron currently has books on loan.");
        } else {
            patron.setDeleted(true);
        }
    }
}
