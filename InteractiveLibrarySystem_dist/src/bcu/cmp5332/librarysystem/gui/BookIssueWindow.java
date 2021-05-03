package bcu.cmp5332.librarysystem.gui;

import bcu.cmp5332.librarysystem.commands.AddBook;
import bcu.cmp5332.librarysystem.commands.BorrowBook;
import bcu.cmp5332.librarysystem.commands.Command;
import bcu.cmp5332.librarysystem.main.LibraryException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class BookIssueWindow extends JFrame implements ActionListener {

    private MainWindow mw;
    private JTextField bookIDField = new JTextField();
    private JTextField patronIDField = new JTextField();

    private JButton addBtn = new JButton("Issue");
    private JButton cancelBtn = new JButton("Cancel");

    public BookIssueWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Issue a Book");

        setSize(300, 200);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(6, 2));
        topPanel.add(new JLabel("Book ID : "));
        topPanel.add(bookIDField);
        topPanel.add(new JLabel("Patron ID : "));
        topPanel.add(patronIDField);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(addBtn);
        bottomPanel.add(cancelBtn);

        addBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addBtn) {
            issueBook();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }

    private void issueBook() {
        try {
            int bookID = Integer.parseInt(bookIDField.getText());
            int patronID = Integer.parseInt(patronIDField.getText());
            Command borrowBook = new BorrowBook(bookID, patronID);
            borrowBook.execute(mw.getLibrary(), LocalDate.now());
            mw.displayBooks();
            this.setVisible(false);
        } catch (LibraryException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
