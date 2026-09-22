import java.util.ArrayList;
import java.util.List;

/**
 * Member class extending Person - demonstrates inheritance
 * Represents library members who can borrow books
 */
public class Member extends Person {
    private String membershipDate;
    private List<String> issuedBooks;
    private int maxBooksAllowed;
    private String membershipType; // Regular, Premium, Student
    
    // Constructor
    public Member(String id, String name, String email, String phone, String address, 
                  String membershipDate, String membershipType) {
        super(id, name, email, phone, address);
        this.membershipDate = membershipDate;
        this.membershipType = membershipType;
        this.issuedBooks = new ArrayList<>();
        
        // Set max books based on membership type
        switch (membershipType.toLowerCase()) {
            case "premium":
                this.maxBooksAllowed = 10;
                break;
            case "student":
                this.maxBooksAllowed = 5;
                break;
            default: // Regular
                this.maxBooksAllowed = 3;
                break;
        }
    }
    
    // Constructor for loading from file
    public Member(String id, String name, String email, String phone, String address,
                  String membershipDate, String membershipType, List<String> issuedBooks) {
        this(id, name, email, phone, address, membershipDate, membershipType);
        this.issuedBooks = issuedBooks != null ? issuedBooks : new ArrayList<>();
    }
    
    // Getters
    public String getMembershipDate() { return membershipDate; }
    public String getMembershipType() { return membershipType; }
    public List<String> getIssuedBooks() { return new ArrayList<>(issuedBooks); }
    public int getMaxBooksAllowed() { return maxBooksAllowed; }
    public int getCurrentBooksCount() { return issuedBooks.size(); }
    
    // Check if member can issue more books
    public boolean canIssueMoreBooks() {
        return issuedBooks.size() < maxBooksAllowed;
    }
    
    // Add issued book
    public boolean addIssuedBook(String bookId) {
        if (canIssueMoreBooks() && !issuedBooks.contains(bookId)) {
            issuedBooks.add(bookId);
            return true;
        }
        return false;
    }
    
    // Remove returned book
    public boolean removeIssuedBook(String bookId) {
        return issuedBooks.remove(bookId);
    }
    
    // Check if member has issued a specific book
    public boolean hasIssuedBook(String bookId) {
        return issuedBooks.contains(bookId);
    }
    
    @Override
    public String getRole() {
        return "Member (" + membershipType + ")";
    }
    
    @Override
    public void displayInfo() {
        System.out.println("═══════════════════════════════════════");
        System.out.println("👤 MEMBER INFORMATION");
        System.out.println("═══════════════════════════════════════");
        System.out.println("Member ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Address: " + address);
        System.out.println("Membership Type: " + membershipType);
        System.out.println("Member Since: " + membershipDate);
        System.out.println("Books Issued: " + issuedBooks.size() + "/" + maxBooksAllowed);
        
        if (!issuedBooks.isEmpty()) {
            System.out.println("Issued Book IDs: " + String.join(", ", issuedBooks));
        }
        System.out.println("═══════════════════════════════════════");
    }
    
    @Override
    public String toFileString() {
        String booksString = String.join(";", issuedBooks);
        return "MEMBER," + id + "," + name + "," + email + "," + phone + "," + 
               address + "," + membershipDate + "," + membershipType + "," + booksString;
    }
    
    // Create Member from file string
    public static Member fromFileString(String fileString) {
        String[] parts = fileString.split(",");
        if (parts.length >= 8 && parts[0].equals("MEMBER")) {
            String id = parts[1];
            String name = parts[2];
            String email = parts[3];
            String phone = parts[4];
            String address = parts[5];
            String membershipDate = parts[6];
            String membershipType = parts[7];
            
            List<String> issuedBooks = new ArrayList<>();
            if (parts.length > 8 && !parts[8].isEmpty()) {
                String[] bookIds = parts[8].split(";");
                for (String bookId : bookIds) {
                    if (!bookId.trim().isEmpty()) {
                        issuedBooks.add(bookId.trim());
                    }
                }
            }
            
            return new Member(id, name, email, phone, address, membershipDate, membershipType, issuedBooks);
        }
        return null;
    }
}
