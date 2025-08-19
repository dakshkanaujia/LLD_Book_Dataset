import java.util.*;

public class BookService {
    private List<Book> books;

    public BookService(List<Book> books) {
        this.books = books;
    }

    public void printAllBooks() {
        System.out.println("\n=== All Books ===");
        for (Book book : books) {
            book.printDetails();
        }
    }
    
    public int getTotalBooks() {
        return books.size();
    }
    
    // 1. Total number of books by an author
    public int countBooksByAuthor(String author) {
        int count = 0;
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author.trim())) {
                count++;
            }
        }
        return count;
    }
    
    // 2. All the authors in the dataset
    public List<String> getAllAuthors() {
        Set<String> uniqueAuthors = new HashSet<>();
        for (Book book : books) {
            uniqueAuthors.add(book.getAuthor());
        }
        return new ArrayList<>(uniqueAuthors);
    }
    
    // 3. Names of all the books by an author
    public List<String> getBookTitlesByAuthor(String author) {
        List<String> bookTitles = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author.trim())) {
                bookTitles.add(book.getTitle());
            }
        }
        return bookTitles;
    }
    
    // 4. Classify with a user rating
    public List<Book> getBooksByRating(double rating) {
        List<Book> matchingBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getUserRating() == rating) {
                matchingBooks.add(book);
            }
        }
        return matchingBooks;
    }
    
    // 5. Price of all the books by an author
    public Map<String, Double> getBooksAndPricesByAuthor(String author) {
        Map<String, Double> bookPrices = new HashMap<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author.trim())) {
                bookPrices.put(book.getTitle(), book.getPrice());
            }
        }
        return bookPrices;
    }
    
    // Helper method to print authors
    public void printAllAuthors() {
        List<String> authors = getAllAuthors();
        Collections.sort(authors);
        System.out.println("\n=== All Authors ===");
        for (int i = 0; i < authors.size(); i++) {
            System.out.println((i + 1) + ". " + authors.get(i));
        }
        System.out.println("Total authors: " + authors.size());
    }
    
    // Helper method to print book titles by author
    public void printBookTitlesByAuthor(String author) {
        List<String> titles = getBookTitlesByAuthor(author);
        if (titles.isEmpty()) {
            System.out.println("No books found for author: " + author);
        } else {
            System.out.println("\n=== Books by " + author + " ===");
            for (int i = 0; i < titles.size(); i++) {
                System.out.println((i + 1) + ". " + titles.get(i));
            }
            System.out.println("Total books: " + titles.size());
        }
    }
    
    // Helper method to print books by rating
    public void printBooksByRating(double rating) {
        List<Book> books = getBooksByRating(rating);
        if (books.isEmpty()) {
            System.out.println("No books found with rating: " + rating);
        } else {
            System.out.println("\n=== Books with rating " + rating + " ===");
            for (int i = 0; i < books.size(); i++) {
                System.out.println((i + 1) + ". " + books.get(i).getTitle() + " by " + books.get(i).getAuthor());
            }
            System.out.println("Total books: " + books.size());
        }
    }
    
    // Helper method to print book prices by author
    public void printBookPricesByAuthor(String author) {
        Map<String, Double> bookPrices = getBooksAndPricesByAuthor(author);
        if (bookPrices.isEmpty()) {
            System.out.println("No books found for author: " + author);
        } else {
            System.out.println("\n=== Book Prices by " + author + " ===");
            double totalPrice = 0;
            int count = 1;
            for (Map.Entry<String, Double> entry : bookPrices.entrySet()) {
                System.out.println(count + ". " + entry.getKey() + " - $" + entry.getValue());
                totalPrice += entry.getValue();
                count++;
            }
            System.out.println("Total books: " + bookPrices.size());
            System.out.println("Total price: $" + totalPrice);
            System.out.println("Average price: $" + String.format("%.2f", totalPrice / bookPrices.size()));
        }
    }
}
