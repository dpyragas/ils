package bcu.cmp5332.librarysystem.main;

import bcu.cmp5332.librarysystem.commands.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CommandParser {

    public static Command parse(String line) throws IOException, LibraryException {
        try {
            System.out.println(line);
            String[] parts = line.split(" ", 3);
            System.out.println(Arrays.toString(parts));
            String cmd = parts[0];


            if (cmd.equals("addbook")) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Title: ");
                String title = br.readLine();
                System.out.print("Author: ");
                String author = br.readLine();
                System.out.print("Publication Year: ");
                String publicationYear = br.readLine();
                System.out.print("Publisher: ");
                String publisher = br.readLine();

                return new AddBook(title, author, publicationYear, publisher);
            } else if (cmd.equals("addpatron")) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Name: ");
                String name = br.readLine();
                System.out.print("Phone: ");
                String phone = br.readLine();

                return new AddPatron(name, phone);
            } else if (cmd.equals("loadgui")) {
                return new LoadGUI();
            } else if (parts.length == 1) {
                if (line.equals("listbooks")) {
                    return new ListBooks();
                } else if (line.equals("listpatrons")) {
                    return new ListPatrons();
                } else if (line.equals("help")) {
                    return new Help();
                }
            } else if (parts.length == 2) {
                int id = Integer.parseInt(parts[1]);
                if (cmd.equals("showbook")) {
                    return new ShowBook(id);
                } else if (cmd.equals("showpatron")) {
                    return new ShowPatron(id);
                }
            } else if (parts.length == 3) {
                int patronID = Integer.parseInt(parts[1]);
                int bookID = Integer.parseInt(parts[2]);

                if (cmd.equals("borrow")) {
                    return new BorrowBook(bookID, patronID);
                } else if (cmd.equals("renew")) {
                    return new RenewBook(bookID, patronID);
                } else if (cmd.equals("return")) {
                    return new ReturnBook(bookID, patronID);
                }
            }
        } catch (NumberFormatException ex) {
            throw new LibraryException("Error in formatting.");
        }

        throw new LibraryException("Invalid command.");
    }
}
