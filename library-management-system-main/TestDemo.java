import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Demo class to test Library Management System - Option 2 (Member Management)
 */
public class TestDemo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║            🏛️ LIBRARY MANAGEMENT SYSTEM 🏛️             ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println();
        
        // Initialize library
        Library library = new Library();
        
        // Add sample data if empty
        initializeSampleData(library);
        
        System.out.println("┌────────────────────────────────────────────────┐");
        System.out.println("│              MEMBER MANAGEMENT DEMO            │");
        System.out.println("├────────────────────────────────────────────────┤");
        System.out.println("│ Demonstrating Option 2: Member Management     │");
        System.out.println("└────────────────────────────────────────────────┘");
        System.out.println();
        
        // Demo Member Management Operations
        demonstrateMemberManagement(library);
    }
    
    private static void demonstrateMemberManagement(Library library) {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                   👥 MEMBER MANAGEMENT                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println();
        
        // 1. Display all existing members
        System.out.println("📋 1. DISPLAYING ALL EXISTING MEMBERS:");
        System.out.println("═".repeat(50));
        displayAllMembers(library);
        
        System.out.println("\n" + "═".repeat(60) + "\n");
        
        // 2. Add a new member
        System.out.println("➕ 2. ADDING A NEW MEMBER:");
        System.out.println("═".repeat(50));
        addNewMemberDemo(library);
        
        System.out.println("\n" + "═".repeat(60) + "\n");
        
        // 3. Search for a specific member
        System.out.println("🔍 3. SEARCHING FOR A MEMBER:");
        System.out.println("═".repeat(50));
        searchMemberDemo(library);
        
        System.out.println("\n" + "═".repeat(60) + "\n");
        
        // 4. Display updated member list
        System.out.println("📋 4. UPDATED MEMBER LIST:");
        System.out.println("═".repeat(50));
        displayAllMembers(library);
        
        System.out.println("\n" + "═".repeat(60));
        System.out.println("✅ Member Management Demo Completed Successfully!");
        System.out.println("💾 All data has been saved to files automatically.");
    }
    
    private static void displayAllMembers(Library library) {
        List<Member> members = library.getAllMembers();
        
        if (members.isEmpty()) {
            System.out.println("❌ No members found in the library.");
            return;
        }
        
        System.out.println("┌────────────────────────────────────────────────────────────────────┐");
        System.out.printf("│ %-15s │ %-20s │ %-12s │ %-10s │%n", "Member ID", "Name", "Type", "Books");
        System.out.println("├────────────────────────────────────────────────────────────────────┤");
        
        for (Member member : members) {
            System.out.printf("│ %-15s │ %-20s │ %-12s │ %d/%-8d │%n", 
                member.getId(), 
                member.getName(), 
                member.getMembershipType(),
                member.getCurrentBooksCount(),
                member.getMaxBooksAllowed());
        }
        
        System.out.println("└────────────────────────────────────────────────────────────────────┘");
        System.out.println("Total Members: " + members.size());
    }
    
    private static void addNewMemberDemo(Library library) {
        String today = LocalDate.now().format(DATE_FORMAT);
        
        // Create a new Premium member
        Member newMember = new Member(
            "M004", 
            "Sarah Wilson", 
            "sarah.wilson@email.com", 
            "555-987-6543", 
            "456 University Ave", 
            today, 
            "Premium"
        );
        
        System.out.println("Creating new member with details:");
        System.out.println("┌────────────────────────────────────────┐");
        System.out.println("│ Member ID: M004                        │");
        System.out.println("│ Name: Sarah Wilson                     │");
        System.out.println("│ Email: sarah.wilson@email.com          │");
        System.out.println("│ Phone: 555-987-6543                    │");
        System.out.println("│ Address: 456 University Ave            │");
        System.out.println("│ Type: Premium (10 books max)           │");
        System.out.println("│ Join Date: " + today + "                │");
        System.out.println("└────────────────────────────────────────┘");
        
        if (library.addMember(newMember)) {
            System.out.println("✅ Member added successfully!");
        } else {
            System.out.println("❌ Failed to add member (ID might already exist)");
        }
    }
    
    private static void searchMemberDemo(Library library) {
        System.out.println("Searching for member with ID: M002");
        
        Member member = library.findMemberById("M002");
        
        if (member != null) {
            System.out.println("✅ Member found!");
            System.out.println("┌────────────────────────────────────────────────────────┐");
            System.out.println("│                    MEMBER DETAILS                     │");
            System.out.println("├────────────────────────────────────────────────────────┤");
            System.out.printf("│ ID: %-50s │%n", member.getId());
            System.out.printf("│ Name: %-48s │%n", member.getName());
            System.out.printf("│ Email: %-47s │%n", member.getEmail());
            System.out.printf("│ Phone: %-47s │%n", member.getPhone());
            System.out.printf("│ Address: %-45s │%n", member.getAddress());
            System.out.printf("│ Type: %-48s │%n", member.getMembershipType());
            System.out.printf("│ Member Since: %-40s │%n", member.getMembershipDate());
            System.out.printf("│ Books Issued: %d/%-38d │%n", 
                member.getCurrentBooksCount(), member.getMaxBooksAllowed());
            
            if (!member.getIssuedBooks().isEmpty()) {
                System.out.printf("│ Issued Books: %-40s │%n", 
                    String.join(", ", member.getIssuedBooks()));
            }
            System.out.println("└────────────────────────────────────────────────────────┘");
        } else {
            System.out.println("❌ Member not found with ID: M002");
        }
    }
    
    private static void initializeSampleData(Library library) {
        // Add sample books if none exist
        if (library.getAllBooks().isEmpty()) {
            library.addBook(new Book("B001", "Java Programming", "James Gosling", "Programming"));
            library.addBook(new Book("B002", "Data Structures", "Robert Lafore", "Computer Science"));
            library.addBook(new Book("B003", "Clean Code", "Robert Martin", "Programming"));
            library.addBook(new Book("B004", "Design Patterns", "Gang of Four", "Software Engineering"));
            library.addBook(new Book("B005", "Algorithms", "Thomas Cormen", "Computer Science"));
        }
        
        // Add sample members if none exist
        if (library.getAllMembers().isEmpty()) {
            String today = LocalDate.now().format(DATE_FORMAT);
            library.addMember(new Member("M001", "John Doe", "john@email.com", "123-456-7890", 
                                       "123 Main St", today, "Regular"));
            library.addMember(new Member("M002", "Jane Smith", "jane@email.com", "098-765-4321", 
                                       "456 Oak Ave", today, "Premium"));
            library.addMember(new Member("M003", "Bob Johnson", "bob@email.com", "555-123-4567", 
                                       "789 Pine Rd", today, "Student"));
        }
        
        // Add sample librarian if none exist
        if (library.getAllLibrarians().isEmpty()) {
            String today = LocalDate.now().format(DATE_FORMAT);
            library.addLibrarian(new Librarian("L001", "Alice Wilson", "alice@library.com", 
                                             "111-222-3333", "321 Library St", "EMP001", 
                                             "Main Library", today, 45000.0));
        }
    }
}
