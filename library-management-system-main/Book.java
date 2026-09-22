/**
 * Book class representing a book in the library
 * Demonstrates encapsulation and data modeling
 */
public class Book {
    private String bookId;
    private String title;
    private String author;
    private String category;
    private boolean isIssued;
    private String issuedTo;
    private String issueDate;
    private String returnDate;
    
    // Constructor
    public Book(String bookId, String title, String author, String category) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isIssued = false;
        this.issuedTo = null;
        this.issueDate = null;
        this.returnDate = null;
    }
    
    // Constructor for loading from file
    public Book(String bookId, String title, String author, String category, 
                boolean isIssued, String issuedTo, String issueDate, String returnDate) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isIssued = isIssued;
        this.issuedTo = issuedTo;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }
    
    // Getters
    public String getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }
    public boolean isIssued() { return isIssued; }
    public String getIssuedTo() { return issuedTo; }
    public String getIssueDate() { return issueDate; }
    public String getReturnDate() { return returnDate; }
    
    // Setters
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setCategory(String category) { this.category = category; }
    
    // Issue book method
    public boolean issueBook(String memberId, String issueDate, String returnDate) {
        if (!isIssued) {
            this.isIssued = true;
            this.issuedTo = memberId;
            this.issueDate = issueDate;
            this.returnDate = returnDate;
            return true;
        }
        return false;
    }
    
    // Return book method
    public boolean returnBook() {
        if (isIssued) {
            this.isIssued = false;
            this.issuedTo = null;
            this.issueDate = null;
            this.returnDate = null;
            return true;
        }
        return false;
    }
    
    // Convert to file format string
    public String toFileString() {
        return bookId + "," + title + "," + author + "," + category + "," + 
               isIssued + "," + (issuedTo != null ? issuedTo : "") + "," + 
               (issueDate != null ? issueDate : "") + "," + 
               (returnDate != null ? returnDate : "");
    }
    
    // Create Book from file string
    public static Book fromFileString(String fileString) {
        String[] parts = fileString.split(",");
        if (parts.length >= 4) {
            String bookId = parts[0];
            String title = parts[1];
            String author = parts[2];
            String category = parts[3];
            boolean isIssued = parts.length > 4 ? Boolean.parseBoolean(parts[4]) : false;
            String issuedTo = parts.length > 5 && !parts[5].isEmpty() ? parts[5] : null;
            String issueDate = parts.length > 6 && !parts[6].isEmpty() ? parts[6] : null;
            String returnDate = parts.length > 7 && !parts[7].isEmpty() ? parts[7] : null;
            
            return new Book(bookId, title, author, category, isIssued, issuedTo, issueDate, returnDate);
        }
        return null;
    }
    
    @Override
    public String toString() {
        String status = isIssued ? "Issued to: " + issuedTo : "Available";
        return String.format("ID: %s | Title: %s | Author: %s | Category: %s | Status: %s", 
                           bookId, title, author, category, status);
    }
    
    // Display detailed information
    public void displayDetails() {
        System.out.println("═══════════════════════════════════════");
        System.out.println("📚 BOOK DETAILS");
        System.out.println("═══════════════════════════════════════");
        System.out.println("Book ID: " + bookId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Category: " + category);
        System.out.println("Status: " + (isIssued ? "❌ Issued" : "✅ Available"));
        if (isIssued) {
            System.out.println("Issued To: " + issuedTo);
            System.out.println("Issue Date: " + issueDate);
            System.out.println("Return Date: " + returnDate);
        }
        System.out.println("═══════════════════════════════════════");
    }
}
