package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

import java.time.LocalDate;

public class AddPatron implements Command {

    private final String name;
    private final String phone;

    public AddPatron(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
    /**
     * Add the patron to the library by given name and phone.
     * @param library
     * @param currentDate
     * @throws LibraryException
     */
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        int lastIndex = library.getPatrons().size() - 1;
        int maxId = 0;
        if (lastIndex != -1) {
            maxId = library.getPatrons().get(lastIndex).getId();
        }
        Patron patron = new Patron(++maxId, name, phone);
        library.addPatron(patron);
        System.out.println("Patron #" + patron.getId() + " added.");
    }
}
