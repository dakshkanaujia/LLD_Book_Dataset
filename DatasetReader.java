import java.io.*;
import java.util.*;

class DatasetReader {

    public static List<Book> readBooks(String fileName) {
        List<Book> books = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean isFirstLine = true;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;
                
                // Skip header row
                if (isFirstLine) {
                    isFirstLine = false;
                    System.out.println("Header: " + line);
                    continue;
                }

                // Skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }

                // Parse CSV line properly handling quoted fields
                String[] values = parseCSVLine(line);

                if (values.length < 7) {
                    System.out.println("Skipping line " + lineNumber + " - insufficient columns (" + values.length + "): " + line);
                    continue;
                }

                try {
                    // Extract fields with validation
                    String title = values[0].trim();
                    String author = values[1].trim();
                    
                    // Parse rating
                    String ratingStr = values[2].trim();
                    if (!isNumeric(ratingStr)) {
                        System.out.println("Skipping line " + lineNumber + " - invalid rating: '" + ratingStr + "'");
                        continue;
                    }
                    double userRating = Double.parseDouble(ratingStr);
                    
                    // Parse reviews
                    String reviewsStr = values[3].trim();
                    if (!isInteger(reviewsStr)) {
                        System.out.println("Skipping line " + lineNumber + " - invalid reviews: '" + reviewsStr + "'");
                        continue;
                    }
                    int reviews = Integer.parseInt(reviewsStr);
                    
                    // Parse price
                    String priceStr = values[4].trim();
                    if (!isInteger(priceStr)) {
                        System.out.println("Skipping line " + lineNumber + " - invalid price: '" + priceStr + "'");
                        continue;
                    }
                    double price = Double.parseDouble(priceStr);
                    
                    // Parse year
                    String yearStr = values[5].trim();
                    if (!isInteger(yearStr)) {
                        System.out.println("Skipping line " + lineNumber + " - invalid year: '" + yearStr + "'");
                        continue;
                    }
                    int year = Integer.parseInt(yearStr);
                    
                    String genre = values[6].trim();

                    // Create Book object
                    Book book = new Book(title, author, userRating, reviews, price, year, genre);
                    books.add(book);
                    
                } catch (NumberFormatException e) {
                    System.out.println("Skipping line " + lineNumber + " - number format error: " + e.getMessage());
                    System.out.println("Line content: " + Arrays.toString(values));
                } catch (Exception e) {
                    System.out.println("Skipping line " + lineNumber + " - unexpected error: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading CSV: " + e.getMessage());
        }

        System.out.println("Successfully loaded " + books.size() + " books.");
        return books;
    }
    
    /**
     * Properly parse a CSV line handling quoted fields that may contain commas
     */
    private static String[] parseCSVLine(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder currentField = new StringBuilder();
        boolean inQuotes = false;
        
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                // End of field
                fields.add(currentField.toString());
                currentField = new StringBuilder();
            } else {
                currentField.append(c);
            }
        }
        
        // Add the last field
        fields.add(currentField.toString());
        
        return fields.toArray(new String[0]);
    }
    
    // Helper method to check if a string is numeric
    private static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    // Helper method to check if a string is an integer
    private static boolean isInteger(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}


