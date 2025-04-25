package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static List<Book> inventory = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeInventory();
        showMainMenu();
    }

    private static void initializeInventory() {
        // You can change the titles and ISBNs as you wish
        for (int i = 1; i <= 20; i++) {
            inventory.add(new Book(i, "ISBN-" + (1000 + i), "Book " + i));
        }
    }

    private static void showMainMenu() {
        while (true) {
            System.out.println("\n=== Neighborhood Library ===");
            System.out.println("1. Show available books");
            System.out.println("2. Show checked out books");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    showAvailableBooks();
                    break;
                case "2":
                    showCheckedOutBooks();
                    break;
                case "3":
                    System.out.println("Thank you for using the library!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void showAvailableBooks() {
        System.out.println("\n--- Available Books ---");
        boolean hasAvailable = false;
        for (Book book : inventory) {
            if (!book.isCheckedOut()) {
                System.out.printf("ID: %d | ISBN: %s | Title: %s\n", book.getId(), book.getIsbn(), book.getTitle());
                hasAvailable = true;
            }
        }
        if (!hasAvailable) {
            System.out.println("There are no available books.");
        }
        System.out.println("\nEnter the ID of the book to check out or X to return to the main menu:");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("X")) {
            return;
        }
        try {
            int id = Integer.parseInt(input);
            Book book = findBookById(id);
            if (book != null && !book.isCheckedOut()) {
                System.out.print("Enter your name to check out the book: ");
                String name = scanner.nextLine();
                book.checkOut(name);
                System.out.println("Book successfully checked out to " + name + ".");
            } else {
                System.out.println("Invalid ID or the book is already checked out.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid ID number or X.");
        }
    }

    private static void showCheckedOutBooks() {
        System.out.println("\n--- Checked Out Books ---");
        boolean hasCheckedOut = false;
        for (Book book : inventory) {
            if (book.isCheckedOut()) {
                System.out.printf("ID: %d | ISBN: %s | Title: %s | Checked out to: %s\n", book.getId(), book.getIsbn(), book.getTitle(), book.getCheckedOutTo());
                hasCheckedOut = true;
            }
        }
        if (!hasCheckedOut) {
            System.out.println("There are no checked out books.");
        }
        System.out.println("\nC - Check in a book | X - Return to the main menu");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("X")) {
            return;
        } else if (input.equalsIgnoreCase("C")) {
            System.out.print("Enter the ID of the book you want to check in: ");
            String idStr = scanner.nextLine();
            try {
                int id = Integer.parseInt(idStr);
                Book book = findBookById(id);
                if (book != null && book.isCheckedOut()) {
                    book.checkIn();
                    System.out.println("Book successfully checked in.");
                } else {
                    System.out.println("Invalid ID or the book is not checked out.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid ID number.");
            }
        } else {
            System.out.println("Invalid option.");
        }
    }

    private static Book findBookById(int id) {
        for (Book book : inventory) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    private static Book[] getPopulatedBooks() throws IOException {
        FileReader fr = new FileReader("books.txt");
        BufferedReader reader = new BufferedReader(fr);

        Book[] booksTemp = new Book[1000];
        int size = 0;
        String dataString;

        while((dataString = reader.readLine()) != null){
            booksTemp[size] = getBookFromEncodedString(dataString);
            size++;
        }
        reader.close();

        Book[] booksFinal = Arrays.copyOf(booksTemp, size);

        return booksFinal;
    }

    private static Book getBookFromEncodedString(String encodedBook) {
        String[] temp = encodedBook.split(Pattern.quote("|"));

        int id = Integer.parseInt(temp[0]);
        String isbn = temp[1];
        String title = temp[2];

        Book result = new Book(id, isbn, title);
        return result;
    }
}
