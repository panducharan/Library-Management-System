import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * Main Application class with console-based menu system
 * Demonstrates complete Library Management System functionality
 */
public class LibraryManagementApp {
    private static Library library;
    private static Scanner scanner;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    public static void main(String[] args) {
        library = new Library();
        scanner = new Scanner(System.in);
        
        // Initialize with sample data if no data exists
        initializeSampleData();
        
        displayWelcomeScreen();
        
        boolean running = true;
        while (running) {
            clearScreen();
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    bookManagementMenu();
                    break;
                case 2:
                    memberManagementMenu();
                    break;
                case 3:
                    librarianManagementMenu();
                    break;
                case 4:
                    issueReturnMenu();
                    break;
                case 5:
                    searchAndDisplayMenu();
                    break;
                case 6:
                    library.displayLibraryStatistics();
                    break;
                case 7:
                    library.backupData();
                    break;
                case 8:
                    library.saveAllData();
                    displayExitMessage();
                    running = false;
                    break;
                default:
                    displayErrorBox("Invalid choice! Please try again.");
            }
            
            if (running) {
                pauseForUser();
            }
        }
        
        scanner.close();
    }
    
    private static void displayMainMenu() {
        printCenteredBox("LIBRARY MANAGEMENT SYSTEM", "🏛️");
        System.out.println();
        printMenuBox(new String[]{
            "1. 📚 Book Management",
            "2. 👥 Member Management", 
            "3. 👨‍💼 Librarian Management",
            "4. 🔄 Issue/Return Books",
            "5. 🔍 Search & Display",
            "6. 📊 Library Statistics",
            "7. 💾 Backup Data",
            "8. 🚪 Exit"
        }, "MAIN MENU");
    }
    
    private static void bookManagementMenu() {
        clearScreen();
        printCenteredBox("BOOK MANAGEMENT", "📚");
        System.out.println();
        printMenuBox(new String[]{
            "1. Add New Book",
            "2. Search Books",
            "3. Display All Books",
            "4. Display Available Books", 
            "5. Display Issued Books",
            "6. Back to Main Menu"
        }, "BOOK OPERATIONS");
        
        int choice = getIntInput("Enter your choice: ");
        
        switch (choice) {
            case 1:
                addNewBook();
                break;
            case 2:
                searchBooks();
                break;
            case 3:
                library.displayAllBooks();
                break;
            case 4:
                displayBookList(library.getAvailableBooks(), "AVAILABLE BOOKS");
                break;
            case 5:
                displayBookList(library.getIssuedBooks(), "ISSUED BOOKS");
                break;
            case 6:
                return;
            default:
                displayErrorBox("Invalid choice!");
        }
    }
    
    private static void memberManagementMenu() {
        clearScreen();
        printCenteredBox("MEMBER MANAGEMENT", "👥");
        System.out.println();
        printMenuBox(new String[]{
            "1. Add New Member",
            "2. Display All Members",
            "3. Search Member by ID",
            "4. Back to Main Menu"
        }, "MEMBER OPERATIONS");
        
        int choice = getIntInput("Enter your choice: ");
        
        switch (choice) {
            case 1:
                addNewMember();
                break;
            case 2:
                library.displayAllMembers();
                break;
            case 3:
                searchMember();
                break;
            case 4:
                return;
            default:
                displayErrorBox("Invalid choice!");
        }
    }
    
    private static void librarianManagementMenu() {
        clearScreen();
        printCenteredBox("LIBRARIAN MANAGEMENT", "👨‍💼");
        System.out.println();
        printMenuBox(new String[]{
            "1. Add New Librarian",
            "2. Display All Librarians",
            "3. Search Librarian by ID",
            "4. Back to Main Menu"
        }, "LIBRARIAN OPERATIONS");
        
        int choice = getIntInput("Enter your choice: ");
        
        switch (choice) {
            case 1:
                addNewLibrarian();
                break;
            case 2:
                displayAllLibrarians();
                break;
            case 3:
                searchLibrarian();
                break;
            case 4:
                return;
            default:
                displayErrorBox("Invalid choice!");
        }
    }
    
    private static void issueReturnMenu() {
        clearScreen();
        printCenteredBox("ISSUE/RETURN BOOKS", "🔄");
        System.out.println();
        printMenuBox(new String[]{
            "1. Issue Book to Member",
            "2. Return Book from Member",
            "3. View Member's Issued Books",
            "4. Back to Main Menu"
        }, "BOOK TRANSACTIONS");
        
        int choice = getIntInput("Enter your choice: ");
        
        switch (choice) {
            case 1:
                issueBook();
                break;
            case 2:
                returnBook();
                break;
            case 3:
                viewMemberBooks();
                break;
            case 4:
                return;
            default:
                displayErrorBox("Invalid choice!");
        }
    }
    
    private static void searchAndDisplayMenu() {
        clearScreen();
        printCenteredBox("SEARCH & DISPLAY", "🔍");
        System.out.println();
        printMenuBox(new String[]{
            "1. Search Books",
            "2. View Book Details",
            "3. View Member Details",
            "4. View Librarian Details",
            "5. Back to Main Menu"
        }, "SEARCH OPERATIONS");
        
        int choice = getIntInput("Enter your choice: ");
        
        switch (choice) {
            case 1:
                searchBooks();
                break;
            case 2:
                viewBookDetails();
                break;
            case 3:
                viewMemberDetails();
                break;
            case 4:
                viewLibrarianDetails();
                break;
            case 5:
                return;
            default:
                displayErrorBox("Invalid choice!");
        }
    }
    
    // Book Management Methods
    private static void addNewBook() {
        clearScreen();
        printCenteredBox("ADD NEW BOOK", "📚");
        System.out.println();
        
        String bookId = getStringInput("Enter Book ID: ");
        String title = getStringInput("Enter Book Title: ");
        String author = getStringInput("Enter Author Name: ");
        String category = getStringInput("Enter Category: ");
        
        Book book = new Book(bookId, title, author, category);
        if (library.addBook(book)) {
            printSuccessBox("Book added successfully!");
        }
    }
    
    private static void searchBooks() {
        clearScreen();
        printCenteredBox("SEARCH BOOKS", "🔍");
        System.out.println();
        
        String query = getStringInput("Enter search query (title/author/category/ID): ");
        List<Book> results = library.searchBooks(query);
        
        if (results.isEmpty()) {
            displayErrorBox("No books found matching: " + query);
        } else {
            displayBookList(results, "SEARCH RESULTS");
        }
    }
    
    // Member Management Methods
    private static void addNewMember() {
        System.out.println("\n👥 ADD NEW MEMBER");
        System.out.println("═════════════════");
        
        String memberId = getStringInput("Enter Member ID: ");
        String name = getStringInput("Enter Member Name: ");
        String email = getStringInput("Enter Email: ");
        String phone = getStringInput("Enter Phone: ");
        String address = getStringInput("Enter Address: ");
        
        System.out.println("Select Membership Type:");
        System.out.println("1. Regular (3 books max)");
        System.out.println("2. Premium (10 books max)");
        System.out.println("3. Student (5 books max)");
        
        int typeChoice = getIntInput("Enter choice: ");
        String membershipType;
        switch (typeChoice) {
            case 2: membershipType = "Premium"; break;
            case 3: membershipType = "Student"; break;
            default: membershipType = "Regular"; break;
        }
        
        String membershipDate = LocalDate.now().format(DATE_FORMAT);
        Member member = new Member(memberId, name, email, phone, address, membershipDate, membershipType);
        library.addMember(member);
    }
    
    private static void searchMember() {
        String memberId = getStringInput("Enter Member ID: ");
        Member member = library.findMemberById(memberId);
        
        if (member != null) {
            member.displayInfo();
        } else {
            System.out.println("❌ Member not found with ID: " + memberId);
        }
    }
    
    // Librarian Management Methods
    private static void addNewLibrarian() {
        System.out.println("\n👨‍💼 ADD NEW LIBRARIAN");
        System.out.println("═══════════════════");
        
        String librarianId = getStringInput("Enter Librarian ID: ");
        String name = getStringInput("Enter Name: ");
        String email = getStringInput("Enter Email: ");
        String phone = getStringInput("Enter Phone: ");
        String address = getStringInput("Enter Address: ");
        String employeeId = getStringInput("Enter Employee ID: ");
        String department = getStringInput("Enter Department: ");
        String joinDate = LocalDate.now().format(DATE_FORMAT);
        double salary = getDoubleInput("Enter Salary: ");
        
        Librarian librarian = new Librarian(librarianId, name, email, phone, address, 
                                          employeeId, department, joinDate, salary);
        library.addLibrarian(librarian);
    }
    
    private static void displayAllLibrarians() {
        List<Librarian> librarians = library.getAllLibrarians();
        if (librarians.isEmpty()) {
            System.out.println("👨‍💼 No librarians registered.");
            return;
        }
        
        System.out.println("\n═══════════════════════════════════════");
        System.out.println("👨‍💼 ALL LIBRARIANS");
        System.out.println("═══════════════════════════════════════");
        for (Librarian librarian : librarians) {
            System.out.println(librarian);
        }
        System.out.println("═══════════════════════════════════════");
    }
    
    private static void searchLibrarian() {
        String librarianId = getStringInput("Enter Librarian ID: ");
        Librarian librarian = library.findLibrarianById(librarianId);
        
        if (librarian != null) {
            librarian.displayInfo();
        } else {
            System.out.println("❌ Librarian not found with ID: " + librarianId);
        }
    }
    
    // Issue/Return Methods
    private static void issueBook() {
        System.out.println("\n📖 ISSUE BOOK");
        System.out.println("══════════════");
        
        String bookId = getStringInput("Enter Book ID: ");
        String memberId = getStringInput("Enter Member ID: ");
        
        library.issueBook(bookId, memberId);
    }
    
    private static void returnBook() {
        System.out.println("\n📚 RETURN BOOK");
        System.out.println("═══════════════");
        
        String bookId = getStringInput("Enter Book ID: ");
        String memberId = getStringInput("Enter Member ID: ");
        
        library.returnBook(bookId, memberId);
    }
    
    private static void viewMemberBooks() {
        String memberId = getStringInput("Enter Member ID: ");
        Member member = library.findMemberById(memberId);
        
        if (member == null) {
            System.out.println("❌ Member not found with ID: " + memberId);
            return;
        }
        
        List<String> issuedBookIds = member.getIssuedBooks();
        if (issuedBookIds.isEmpty()) {
            System.out.println("📚 Member has no books issued.");
            return;
        }
        
        System.out.println("\n═══════════════════════════════════════");
        System.out.println("📚 BOOKS ISSUED TO: " + member.getName());
        System.out.println("═══════════════════════════════════════");
        
        for (String bookId : issuedBookIds) {
            Book book = library.findBookById(bookId);
            if (book != null) {
                System.out.println(book);
            }
        }
        System.out.println("═══════════════════════════════════════");
    }
    
    // Detail View Methods
    private static void viewBookDetails() {
        String bookId = getStringInput("Enter Book ID: ");
        Book book = library.findBookById(bookId);
        
        if (book != null) {
            book.displayDetails();
        } else {
            System.out.println("❌ Book not found with ID: " + bookId);
        }
    }
    
    private static void viewMemberDetails() {
        String memberId = getStringInput("Enter Member ID: ");
        Member member = library.findMemberById(memberId);
        
        if (member != null) {
            member.displayInfo();
        } else {
            System.out.println("❌ Member not found with ID: " + memberId);
        }
    }
    
    private static void viewLibrarianDetails() {
        String librarianId = getStringInput("Enter Librarian ID: ");
        Librarian librarian = library.findLibrarianById(librarianId);
        
        if (librarian != null) {
            librarian.displayInfo();
        } else {
            System.out.println("❌ Librarian not found with ID: " + librarianId);
        }
    }
    
    // Utility Methods
    private static void displayBookList(List<Book> books, String title) {
        if (books.isEmpty()) {
            displayErrorBox("No books to display.");
            return;
        }
        
        System.out.println();
        printCenteredBox(title, "📚");
        System.out.println();
        
        String[] bookData = new String[books.size() + 1];
        bookData[0] = "Total Books: " + books.size();
        
        for (int i = 0; i < books.size(); i++) {
            bookData[i + 1] = books.get(i).toString();
        }
        
        printDataBox(title, bookData);
    }
    
    private static void initializeSampleData() {
        // Add sample books if none exist
        if (library.getAllBooks().isEmpty()) {
            System.out.println("🔧 Initializing sample data...");
            
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
    
    // UI Helper Methods
    private static void clearScreen() {
        // Clear screen for better UI experience
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    
    private static void displayWelcomeScreen() {
        clearScreen();
        printCenteredBox("WELCOME TO LIBRARY MANAGEMENT SYSTEM", "🏛️");
        System.out.println();
        printInfoBox(new String[]{
            "📚 Object-Oriented Programming Demo",
            "🔧 Features: Classes, Inheritance, File Handling",
            "💾 Data Persistence with File I/O",
            "🎯 Complete CRUD Operations"
        });
        pauseForUser();
    }
    
    private static void displayExitMessage() {
        System.out.println();
        printCenteredBox("THANK YOU FOR USING LIBRARY MANAGEMENT SYSTEM", "👋");
        System.out.println();
        printInfoBox(new String[]{
            "✅ All data has been saved successfully",
            "📚 Books, Members, and Librarians are preserved",
            "🔄 Your session data is available for next run",
            "💡 Run the application again anytime!"
        });
    }
    
    private static void displayErrorBox(String message) {
        System.out.println();
        printErrorBox(message);
    }
    
    private static void pauseForUser() {
        System.out.println();
        printSeparator();
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
    }
    
    private static void printCenteredBox(String title, String icon) {
        int width = 60;
        int titleLength = title.length() + (icon.length() * 2) + 2;
        int padding = (width - titleLength) / 2;
        
        System.out.println("╔" + "═".repeat(width - 2) + "╗");
        System.out.print("║");
        System.out.print(" ".repeat(padding));
        System.out.print(icon + " " + title + " " + icon);
        System.out.print(" ".repeat(width - 2 - padding - titleLength));
        System.out.println("║");
        System.out.println("╚" + "═".repeat(width - 2) + "╝");
    }
    
    private static void printMenuBox(String[] options, String title) {
        int width = 50;
        
        System.out.println("┌" + "─".repeat(width - 2) + "┐");
        
        // Title
        int titlePadding = (width - title.length() - 2) / 2;
        System.out.print("│");
        System.out.print(" ".repeat(titlePadding));
        System.out.print(title);
        System.out.print(" ".repeat(width - 2 - titlePadding - title.length()));
        System.out.println("│");
        
        System.out.println("├" + "─".repeat(width - 2) + "┤");
        
        // Options
        for (String option : options) {
            System.out.print("│ ");
            System.out.print(option);
            System.out.print(" ".repeat(width - option.length() - 3));
            System.out.println("│");
        }
        
        System.out.println("└" + "─".repeat(width - 2) + "┘");
    }
    
    private static void printInfoBox(String[] messages) {
        int width = 60;
        
        System.out.println("┌" + "─".repeat(width - 2) + "┐");
        
        for (String message : messages) {
            System.out.print("│ ");
            System.out.print(message);
            System.out.print(" ".repeat(width - message.length() - 3));
            System.out.println("│");
        }
        
        System.out.println("└" + "─".repeat(width - 2) + "┘");
    }
    
    private static void printErrorBox(String message) {
        int width = 50;
        String fullMessage = "❌ " + message;
        
        System.out.println("┌" + "─".repeat(width - 2) + "┐");
        System.out.print("│ ");
        System.out.print(fullMessage);
        System.out.print(" ".repeat(width - fullMessage.length() - 3));
        System.out.println("│");
        System.out.println("└" + "─".repeat(width - 2) + "┘");
    }
    
    private static void printSuccessBox(String message) {
        int width = 50;
        String fullMessage = "✅ " + message;
        
        System.out.println("┌" + "─".repeat(width - 2) + "┐");
        System.out.print("│ ");
        System.out.print(fullMessage);
        System.out.print(" ".repeat(width - fullMessage.length() - 3));
        System.out.println("│");
        System.out.println("└" + "─".repeat(width - 2) + "┘");
    }
    
    private static void printSeparator() {
        System.out.println("═".repeat(60));
    }
    
    private static void printDataBox(String title, String[] data) {
        int width = 70;
        
        System.out.println("╔" + "═".repeat(width - 2) + "╗");
        
        // Title
        int titlePadding = (width - title.length() - 2) / 2;
        System.out.print("║");
        System.out.print(" ".repeat(titlePadding));
        System.out.print(title);
        System.out.print(" ".repeat(width - 2 - titlePadding - title.length()));
        System.out.println("║");
        
        System.out.println("╠" + "═".repeat(width - 2) + "╣");
        
        // Data
        for (String item : data) {
            System.out.print("║ ");
            System.out.print(item);
            System.out.print(" ".repeat(width - item.length() - 3));
            System.out.println("║");
        }
        
        System.out.println("╚" + "═".repeat(width - 2) + "╝");
    }

    // Input Helper Methods
    private static String getStringInput(String prompt) {
        System.out.print("➤ " + prompt);
        return scanner.nextLine().trim();
    }
    
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print("➤ " + prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                displayErrorBox("Please enter a valid number!");
            }
        }
    }
    
    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print("➤ " + prompt);
                double value = Double.parseDouble(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                displayErrorBox("Please enter a valid number!");
            }
        }
    }
}
