import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Library class - Main business logic class
 * Manages books, members, and librarians with file persistence
 */
public class Library {
    private List<Book> books;
    private List<Member> members;
    private List<Librarian> librarians;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    // Constructor
    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.librarians = new ArrayList<>();
        loadAllData();
    }
    
    // Load all data from files
    public void loadAllData() {
        books = FileHandler.loadBooks();
        members = FileHandler.loadMembers();
        librarians = FileHandler.loadLibrarians();
        
        System.out.println("📚 Data loaded successfully!");
        System.out.println("Books: " + books.size() + " | Members: " + members.size() + " | Librarians: " + librarians.size());
    }
    
    // Save all data to files
    public boolean saveAllData() {
        boolean success = true;
        success &= FileHandler.saveBooks(books);
        success &= FileHandler.saveMembers(members);
        success &= FileHandler.saveLibrarians(librarians);
        
        if (success) {
            System.out.println("✅ All data saved successfully!");
        } else {
            System.out.println("❌ Error saving some data!");
        }
        return success;
    }
    
    // Book Management Methods
    public boolean addBook(Book book) {
        if (findBookById(book.getBookId()) == null) {
            books.add(book);
            saveAllData();
            System.out.println("✅ Book added successfully: " + book.getTitle());
            return true;
        } else {
            System.out.println("❌ Book with ID " + book.getBookId() + " already exists!");
            return false;
        }
    }
    
    public Book findBookById(String bookId) {
        return books.stream()
                .filter(book -> book.getBookId().equals(bookId))
                .findFirst()
                .orElse(null);
    }
    
    public List<Book> searchBooks(String query) {
        String lowerQuery = query.toLowerCase();
        return books.stream()
                .filter(book -> 
                    book.getTitle().toLowerCase().contains(lowerQuery) ||
                    book.getAuthor().toLowerCase().contains(lowerQuery) ||
                    book.getCategory().toLowerCase().contains(lowerQuery) ||
                    book.getBookId().toLowerCase().contains(lowerQuery))
                .collect(Collectors.toList());
    }
    
    public List<Book> getAvailableBooks() {
        return books.stream()
                .filter(book -> !book.isIssued())
                .collect(Collectors.toList());
    }
    
    public List<Book> getIssuedBooks() {
        return books.stream()
                .filter(Book::isIssued)
                .collect(Collectors.toList());
    }
    
    // Member Management Methods
    public boolean addMember(Member member) {
        if (findMemberById(member.getId()) == null) {
            members.add(member);
            saveAllData();
            System.out.println("✅ Member added successfully: " + member.getName());
            return true;
        } else {
            System.out.println("❌ Member with ID " + member.getId() + " already exists!");
            return false;
        }
    }
    
    public Member findMemberById(String memberId) {
        return members.stream()
                .filter(member -> member.getId().equals(memberId))
                .findFirst()
                .orElse(null);
    }
    
    // Librarian Management Methods
    public boolean addLibrarian(Librarian librarian) {
        if (findLibrarianById(librarian.getId()) == null) {
            librarians.add(librarian);
            saveAllData();
            System.out.println("✅ Librarian added successfully: " + librarian.getName());
            return true;
        } else {
            System.out.println("❌ Librarian with ID " + librarian.getId() + " already exists!");
            return false;
        }
    }
    
    public Librarian findLibrarianById(String librarianId) {
        return librarians.stream()
                .filter(librarian -> librarian.getId().equals(librarianId))
                .findFirst()
                .orElse(null);
    }
    
    // Book Issue/Return Methods
    public boolean issueBook(String bookId, String memberId) {
        Book book = findBookById(bookId);
        Member member = findMemberById(memberId);
        
        if (book == null) {
            System.out.println("❌ Book not found with ID: " + bookId);
            return false;
        }
        
        if (member == null) {
            System.out.println("❌ Member not found with ID: " + memberId);
            return false;
        }
        
        if (book.isIssued()) {
            System.out.println("❌ Book is already issued to: " + book.getIssuedTo());
            return false;
        }
        
        if (!member.canIssueMoreBooks()) {
            System.out.println("❌ Member has reached maximum book limit (" + member.getMaxBooksAllowed() + ")");
            return false;
        }
        
        // Calculate dates
        LocalDate issueDate = LocalDate.now();
        LocalDate returnDate = issueDate.plusDays(14); // 2 weeks loan period
        
        // Issue the book
        if (book.issueBook(memberId, issueDate.format(DATE_FORMAT), returnDate.format(DATE_FORMAT))) {
            member.addIssuedBook(bookId);
            saveAllData();
            
            System.out.println("✅ Book issued successfully!");
            System.out.println("📖 Book: " + book.getTitle());
            System.out.println("👤 Member: " + member.getName());
            System.out.println("📅 Issue Date: " + issueDate.format(DATE_FORMAT));
            System.out.println("📅 Return Date: " + returnDate.format(DATE_FORMAT));
            return true;
        }
        
        return false;
    }
    
    public boolean returnBook(String bookId, String memberId) {
        Book book = findBookById(bookId);
        Member member = findMemberById(memberId);
        
        if (book == null) {
            System.out.println("❌ Book not found with ID: " + bookId);
            return false;
        }
        
        if (member == null) {
            System.out.println("❌ Member not found with ID: " + memberId);
            return false;
        }
        
        if (!book.isIssued()) {
            System.out.println("❌ Book is not currently issued!");
            return false;
        }
        
        if (!book.getIssuedTo().equals(memberId)) {
            System.out.println("❌ Book is issued to different member: " + book.getIssuedTo());
            return false;
        }
        
        if (!member.hasIssuedBook(bookId)) {
            System.out.println("❌ Member doesn't have this book issued!");
            return false;
        }
        
        // Return the book
        if (book.returnBook()) {
            member.removeIssuedBook(bookId);
            saveAllData();
            
            System.out.println("✅ Book returned successfully!");
            System.out.println("📖 Book: " + book.getTitle());
            System.out.println("👤 Member: " + member.getName());
            System.out.println("📅 Return Date: " + LocalDate.now().format(DATE_FORMAT));
            return true;
        }
        
        return false;
    }
    
    // Display Methods
    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("📚 No books available in the library.");
            return;
        }
        
        System.out.println("\n═══════════════════════════════════════");
        System.out.println("📚 ALL BOOKS IN LIBRARY");
        System.out.println("═══════════════════════════════════════");
        System.out.println("Total Books: " + books.size());
        System.out.println("Available: " + getAvailableBooks().size());
        System.out.println("Issued: " + getIssuedBooks().size());
        System.out.println("═══════════════════════════════════════");
        
        for (Book book : books) {
            System.out.println(book);
        }
        System.out.println("═══════════════════════════════════════");
    }
    
    public void displayAllMembers() {
        if (members.isEmpty()) {
            System.out.println("👥 No members registered in the library.");
            return;
        }
        
        System.out.println("\n═══════════════════════════════════════");
        System.out.println("👥 ALL LIBRARY MEMBERS");
        System.out.println("═══════════════════════════════════════");
        System.out.println("Total Members: " + members.size());
        System.out.println("═══════════════════════════════════════");
        
        for (Member member : members) {
            System.out.println(member + " | Books: " + member.getCurrentBooksCount() + "/" + member.getMaxBooksAllowed());
        }
        System.out.println("═══════════════════════════════════════");
    }
    
    public void displayLibraryStatistics() {
        System.out.println("\n═══════════════════════════════════════");
        System.out.println("📊 LIBRARY STATISTICS");
        System.out.println("═══════════════════════════════════════");
        System.out.println("Total Books: " + books.size());
        System.out.println("Available Books: " + getAvailableBooks().size());
        System.out.println("Issued Books: " + getIssuedBooks().size());
        System.out.println("Total Members: " + members.size());
        System.out.println("Total Librarians: " + librarians.size());
        
        // Books by category
        System.out.println("\n📚 Books by Category:");
        books.stream()
                .collect(Collectors.groupingBy(Book::getCategory, Collectors.counting()))
                .forEach((category, count) -> System.out.println("  " + category + ": " + count));
        
        // Members by type
        System.out.println("\n👥 Members by Type:");
        members.stream()
                .collect(Collectors.groupingBy(Member::getMembershipType, Collectors.counting()))
                .forEach((type, count) -> System.out.println("  " + type + ": " + count));
        
        System.out.println("═══════════════════════════════════════");
    }
    
    // Getters
    public List<Book> getAllBooks() { return new ArrayList<>(books); }
    public List<Member> getAllMembers() { return new ArrayList<>(members); }
    public List<Librarian> getAllLibrarians() { return new ArrayList<>(librarians); }
    
    // Backup data
    public boolean backupData() {
        return FileHandler.backupData(books, members, librarians);
    }
}
