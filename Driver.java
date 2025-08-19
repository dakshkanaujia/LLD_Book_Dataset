import java.io.*;
import java.util.*;

public class Driver {
    public static void main(String[] args) {
        System.out.println("Loading books from CSV...");
        List<Book> books = DatasetReader.readBooks("data.csv");
        BookService service = new BookService(books);
        
        System.out.println("\nTotal books loaded: " + service.getTotalBooks());
        
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            printMenu();
            System.out.print("Enter your choice (1-7): ");
            
            try {
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline
                
                switch (choice) {
                    case 1:
                        // Test API 1: Total number of books by an author
                        System.out.print("Enter author name: ");
                        String author1 = sc.nextLine();
                        int count = service.countBooksByAuthor(author1);
                        System.out.println("Total books by " + author1 + ": " + count);
                        break;
                        
                    case 2:
                        // Test API 2: All the authors in the dataset
                        service.printAllAuthors();
                        break;
                        
                    case 3:
                        // Test API 3: Names of all the books by an author
                        System.out.print("Enter author name: ");
                        String author3 = sc.nextLine();
                        service.printBookTitlesByAuthor(author3);
                        break;
                        
                    case 4:
                        // Test API 4: Classify with a user rating
                        System.out.print("Enter rating (e.g., 4.7): ");
                        double rating = sc.nextDouble();
                        sc.nextLine(); // consume newline
                        service.printBooksByRating(rating);
                        break;
                        
                    case 5:
                        // Test API 5: Price of all the books by an author
                        System.out.print("Enter author name: ");
                        String author5 = sc.nextLine();
                        service.printBookPricesByAuthor(author5);
                        break;
                        
                    case 6:
                        // Show sample of loaded data
                        System.out.println("\n=== Sample Books (First 5) ===");
                        for (int i = 0; i < Math.min(5, books.size()); i++) {
                            books.get(i).printDetails();
                        }
                        break;
                        
                    case 7:
                        System.out.println("Goodbye!");
                        sc.close();
                        return;
                        
                    default:
                        System.out.println("Invalid choice. Please enter 1-7.");
                }
                
                System.out.println("\nPress Enter to continue...");
                sc.nextLine();
                
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); // clear the invalid input
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }
    
    private static void printMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("           BOOK MANAGEMENT SYSTEM");
        System.out.println("=".repeat(50));
        System.out.println("1. Count books by author");
        System.out.println("2. Show all authors");
        System.out.println("3. Show books by author");
        System.out.println("4. Find books by rating");
        System.out.println("5. Show book prices by author");
        System.out.println("6. Show sample data");
        System.out.println("7. Exit");
        System.out.println("=".repeat(50));
    }
}