import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// Simplified classes for demo
class SimplePerson {
    protected String id, name, email, phone, address;
    
    public SimplePerson(String id, String name, String email, String phone, String address) {
        this.id = id; this.name = name; this.email = email; this.phone = phone; this.address = address;
    }
    
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
}

class SimpleMember extends SimplePerson {
    private String membershipDate, membershipType;
    private List<String> issuedBooks;
    private int maxBooksAllowed;
    
    public SimpleMember(String id, String name, String email, String phone, String address, 
                       String membershipDate, String membershipType) {
        super(id, name, email, phone, address);
        this.membershipDate = membershipDate;
        this.membershipType = membershipType;
        this.issuedBooks = new ArrayList<>();
        
        switch (membershipType.toLowerCase()) {
            case "premium": this.maxBooksAllowed = 10; break;
            case "student": this.maxBooksAllowed = 5; break;
            default: this.maxBooksAllowed = 3; break;
        }
    }
    
    public String getMembershipDate() { return membershipDate; }
    public String getMembershipType() { return membershipType; }
    public List<String> getIssuedBooks() { return new ArrayList<>(issuedBooks); }
    public int getMaxBooksAllowed() { return maxBooksAllowed; }
    public int getCurrentBooksCount() { return issuedBooks.size(); }
    
    public boolean addIssuedBook(String bookId) {
        if (issuedBooks.size() < maxBooksAllowed && !issuedBooks.contains(bookId)) {
            issuedBooks.add(bookId);
            return true;
        }
        return false;
    }
}

class SimpleLibrary {
    private List<SimpleMember> members = new ArrayList<>();
    
    public boolean addMember(SimpleMember member) {
        if (findMemberById(member.getId()) == null) {
            members.add(member);
            return true;
        }
        return false;
    }
    
    public SimpleMember findMemberById(String memberId) {
        return members.stream().filter(m -> m.getId().equals(memberId)).findFirst().orElse(null);
    }
    
    public List<SimpleMember> getAllMembers() { return new ArrayList<>(members); }
}

public class MemberDemo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║            🏛️ LIBRARY MANAGEMENT SYSTEM 🏛️             ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println();
        
        SimpleLibrary library = new SimpleLibrary();
        
        System.out.println("┌────────────────────────────────────────────────┐");
        System.out.println("│         📋 OPTION 2: MEMBER MANAGEMENT        │");
        System.out.println("└────────────────────────────────────────────────┘");
        System.out.println();
        
        // Initialize with sample members
        initializeSampleMembers(library);
        
        // Demo all member management operations
        demonstrateMemberOperations(library);
    }
    
    private static void initializeSampleMembers(SimpleLibrary library) {
        String today = LocalDate.now().format(DATE_FORMAT);
        
        library.addMember(new SimpleMember("M001", "John Doe", "john@email.com", 
                                          "123-456-7890", "123 Main St", today, "Regular"));
        library.addMember(new SimpleMember("M002", "Jane Smith", "jane@email.com", 
                                          "098-765-4321", "456 Oak Ave", today, "Premium"));
        library.addMember(new SimpleMember("M003", "Bob Johnson", "bob@email.com", 
                                          "555-123-4567", "789 Pine Rd", today, "Student"));
        
        // Add some issued books for demo
        library.findMemberById("M002").addIssuedBook("B001");
        library.findMemberById("M002").addIssuedBook("B003");
        library.findMemberById("M003").addIssuedBook("B002");
    }
    
    private static void demonstrateMemberOperations(SimpleLibrary library) {
        // 1. Display All Members
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                1️⃣ DISPLAY ALL MEMBERS                   ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        displayAllMembers(library);
        
        System.out.println("\n" + "═".repeat(60) + "\n");
        
        // 2. Add New Member
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                  2️⃣ ADD NEW MEMBER                      ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        addNewMemberDemo(library);
        
        System.out.println("\n" + "═".repeat(60) + "\n");
        
        // 3. Search Member by ID
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║               3️⃣ SEARCH MEMBER BY ID                    ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        searchMemberDemo(library);
        
        System.out.println("\n" + "═".repeat(60) + "\n");
        
        // 4. Updated Member List
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║              4️⃣ UPDATED MEMBER LIST                     ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        displayAllMembers(library);
        
        System.out.println("\n" + "═".repeat(60));
        System.out.println("✅ Member Management Demo Completed Successfully!");
        System.out.println("🎯 All OOP concepts demonstrated: Classes, Inheritance, Encapsulation");
    }
    
    private static void displayAllMembers(SimpleLibrary library) {
        List<SimpleMember> members = library.getAllMembers();
        
        if (members.isEmpty()) {
            System.out.println("❌ No members found in the library.");
            return;
        }
        
        System.out.println("┌──────────┬──────────────────┬──────────────┬────────────┐");
        System.out.println("│ ID       │ Name             │ Type         │ Books      │");
        System.out.println("├──────────┼──────────────────┼──────────────┼────────────┤");
        
        for (SimpleMember member : members) {
            System.out.printf("│ %-8s │ %-16s │ %-12s │ %2d/%-8d│%n", 
                member.getId(), 
                member.getName().length() > 16 ? member.getName().substring(0, 16) : member.getName(),
                member.getMembershipType(),
                member.getCurrentBooksCount(),
                member.getMaxBooksAllowed());
        }
        
        System.out.println("└──────────┴──────────────────┴──────────────┴────────────┘");
        System.out.println("📊 Total Members: " + members.size());
    }
    
    private static void addNewMemberDemo(SimpleLibrary library) {
        String today = LocalDate.now().format(DATE_FORMAT);
        
        System.out.println("➕ Adding new Premium member...");
        System.out.println();
        
        SimpleMember newMember = new SimpleMember(
            "M004", 
            "Sarah Wilson", 
            "sarah.wilson@email.com", 
            "555-987-6543", 
            "456 University Ave", 
            today, 
            "Premium"
        );
        
        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│                NEW MEMBER DETAILS               │");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.printf("│ ID: %-43s │%n", newMember.getId());
        System.out.printf("│ Name: %-41s │%n", newMember.getName());
        System.out.printf("│ Email: %-40s │%n", newMember.getEmail());
        System.out.printf("│ Phone: %-40s │%n", newMember.getPhone());
        System.out.printf("│ Address: %-38s │%n", newMember.getAddress());
        System.out.printf("│ Type: %-41s │%n", newMember.getMembershipType());
        System.out.printf("│ Max Books: %-36d │%n", newMember.getMaxBooksAllowed());
        System.out.printf("│ Join Date: %-36s │%n", newMember.getMembershipDate());
        System.out.println("└─────────────────────────────────────────────────┘");
        
        if (library.addMember(newMember)) {
            System.out.println("✅ Member added successfully!");
        } else {
            System.out.println("❌ Failed to add member (ID already exists)");
        }
    }
    
    private static void searchMemberDemo(SimpleLibrary library) {
        String searchId = "M002";
        System.out.println("🔍 Searching for member with ID: " + searchId);
        System.out.println();
        
        SimpleMember member = library.findMemberById(searchId);
        
        if (member != null) {
            System.out.println("✅ Member found!");
            System.out.println();
            
            System.out.println("┌─────────────────────────────────────────────────────────┐");
            System.out.println("│                    MEMBER DETAILS                      │");
            System.out.println("├─────────────────────────────────────────────────────────┤");
            System.out.printf("│ ID: %-51s │%n", member.getId());
            System.out.printf("│ Name: %-49s │%n", member.getName());
            System.out.printf("│ Email: %-48s │%n", member.getEmail());
            System.out.printf("│ Phone: %-48s │%n", member.getPhone());
            System.out.printf("│ Address: %-46s │%n", member.getAddress());
            System.out.printf("│ Membership Type: %-38s │%n", member.getMembershipType());
            System.out.printf("│ Member Since: %-41s │%n", member.getMembershipDate());
            System.out.printf("│ Books Issued: %d/%-41d │%n", 
                member.getCurrentBooksCount(), member.getMaxBooksAllowed());
            
            if (!member.getIssuedBooks().isEmpty()) {
                System.out.printf("│ Issued Book IDs: %-36s │%n", 
                    String.join(", ", member.getIssuedBooks()));
            }
            System.out.println("└─────────────────────────────────────────────────────────┘");
        } else {
            System.out.println("❌ Member not found with ID: " + searchId);
        }
    }
}
