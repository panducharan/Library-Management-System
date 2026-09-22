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
        
        System.out.println("Welcome to Library Management System");
        System.out.println("====================================");
        
        boolean running = true;
        while (running) {
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
                    displayLibraryStatistics();
                    break;
                case 7:
                    library.backupData();
                    break;
                case 8:
                    library.saveAllData();
                    System.out.println("Thank you for using Library Management System!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
            
            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    private static void displayMainMenu() {
        String[] menuOptions = {
            "1. Book Management",
            "2. Member Management", 
            "3. Librarian Management",
            "4. Issue/Return Books",
            "5. Search & Display",
            "6. Library Statistics",
            "7. Backup Data",
            "8. Exit"
        };
        printMenuTable("LIBRARY MANAGEMENT SYSTEM", menuOptions);
    }
    
    private static void bookManagementMenu() {
        String[] menuOptions = {
            "1. Add New Book",
            "2. Search Books",
            "3. Display All Books",
            "4. Display Available Books",
            "5. Display Issued Books",
            "6. Back to Main Menu"
        };
        printMenuTable("BOOK MANAGEMENT", menuOptions);
        System.out.println("=======================");
        
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
                System.out.println("Invalid choice!");
        }
    }
    
    private static void memberManagementMenu() {
        String[] menuOptions = {
            "1. Add New Member",
            "2. Search Member",
            "3. Display All Members",
            "4. Back to Main Menu"
        };
        printMenuTable("MEMBER MANAGEMENT", menuOptions);
        System.out.println("=========================");
        
        int choice = getIntInput("Enter your choice: ");
        
        switch (choice) {
            case 1:
                addNewMember();
                break;
            case 2:
                searchMember();
                break;
            case 3:
                library.displayAllMembers();
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid choice!");
        }
    }
    
    private static void librarianManagementMenu() {
        String[] menuOptions = {
            "1. Add New Librarian",
            "2. Search Librarian",
            "3. Display All Librarians",
            "4. Back to Main Menu"
        };
        printMenuTable("LIBRARIAN MANAGEMENT", menuOptions);
        System.out.println("============================");
        
        int choice = getIntInput("Enter your choice: ");
        
        switch (choice) {
            case 1:
                addNewLibrarian();
                break;
            case 2:
                searchLibrarian();
                break;
            case 3:
                displayAllLibrarians();
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid choice!");
        }
    }
    
    private static void issueReturnMenu() {
        String[] menuOptions = {
            "1. Issue Book to Member",
            "2. Return Book from Member",
            "3. View Member's Issued Books",
            "4. Back to Main Menu"
        };
        printMenuTable("ISSUE/RETURN BOOKS", menuOptions);
        System.out.println("==========================");
        
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
                System.out.println("Invalid choice!");
        }
    }
    
    private static void searchAndDisplayMenu() {
        System.out.println("\n=== SEARCH & DISPLAY ===");
        System.out.println("1. Search Books");
        System.out.println("2. View Book Details");
        System.out.println("3. View Member Details");
        System.out.println("4. View Librarian Details");
        System.out.println("5. Back to Main Menu");
        System.out.println("========================");
        
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
                System.out.println("Invalid choice!");
        }
    }
    
    // Book Management Methods
    private static void addNewBook() {
        System.out.println("\n=== ADD NEW BOOK ===");
        
        String bookId = getStringInput("Enter Book ID: ");
        String title = getStringInput("Enter Book Title: ");
        String author = getStringInput("Enter Author Name: ");
        String category = getStringInput("Enter Category: ");
        
        Book book = new Book(bookId, title, author, category);
        library.addBook(book);
    }
    
    private static void searchBooks() {
        System.out.println("\n=== SEARCH BOOKS ===");
        
        String query = getStringInput("Enter search query (title/author/category/ID): ");
        List<Book> results = library.searchBooks(query);
        
        if (results.isEmpty()) {
            System.out.println("No books found matching: " + query);
        } else {
            displayBookList(results, "SEARCH RESULTS");
        }
    }
    
    // Member Management Methods
    private static void addNewMember() {
        System.out.println("\n=== ADD NEW MEMBER ===");
        
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
            System.out.println("No librarians registered.");
            return;
        }
        
        String[] headers = {"ID", "Name", "Email", "Phone", "Department", "Salary"};
        String[][] data = new String[librarians.size()][6];
        
        for (int i = 0; i < librarians.size(); i++) {
            Librarian librarian = librarians.get(i);
            data[i][0] = librarian.getId();
            data[i][1] = librarian.getName();
            data[i][2] = librarian.getEmail();
            data[i][3] = librarian.getPhone();
            data[i][4] = librarian.getDepartment();
            data[i][5] = String.format("%.2f", librarian.getSalary());
        }
        
        printDataTable("ALL LIBRARIANS (Total: " + librarians.size() + ")", headers, data);
    }
    
    private static void displayLibraryStatistics() {
        List<Book> allBooks = library.getAllBooks();
        int issuedCount = 0;
        for (Book book : allBooks) {
            if (book.isIssued()) {
                issuedCount++;
            }
        }
        int availableCount = allBooks.size() - issuedCount;
        
        String[] headers = {"Category", "Count"};
        String[][] data = {
            {"Total Books", String.valueOf(library.getAllBooks().size())},
            {"Total Members", String.valueOf(library.getAllMembers().size())},
            {"Total Librarians", String.valueOf(library.getAllLibrarians().size())},
            {"Books Issued", String.valueOf(issuedCount)},
            {"Books Available", String.valueOf(availableCount)}
        };
        
        printDataTable("LIBRARY STATISTICS", headers, data);
    }
    
    private static void searchLibrarian() {
        String librarianId = getStringInput("Enter Librarian ID: ");
        Librarian librarian = library.findLibrarianById(librarianId);
        
        if (librarian != null) {
            librarian.displayInfo();
        } else {
            System.out.println("Librarian not found with ID: " + librarianId);
        }
    }
    
    // Issue/Return Methods
    private static void issueBook() {
        System.out.println("\n=== ISSUE BOOK ===");
        
        String bookId = getStringInput("Enter Book ID: ");
        String memberId = getStringInput("Enter Member ID: ");
        
        library.issueBook(bookId, memberId);
    }
    
    private static void returnBook() {
        System.out.println("\n=== RETURN BOOK ===");
        
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
            System.out.println("Librarian not found with ID: " + librarianId);
        }
    }
    
    // Utility Methods
    private static void displayBookList(List<Book> books, String title) {
        if (books.isEmpty()) {
            System.out.println("No books to display.");
            return;
        }
        
        String[] headers = {"ID", "Title", "Author", "Category", "Status"};
        String[][] data = new String[books.size()][5];
        
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            data[i][0] = book.getBookId();
            data[i][1] = book.getTitle();
            data[i][2] = book.getAuthor();
            data[i][3] = book.getCategory();
            data[i][4] = book.isIssued() ? "Issued" : "Available";
        }
        
        printDataTable(title + " (Total: " + books.size() + ")", headers, data);
    }
    
    private static void initializeSampleData() {
        // Add sample books if none exist
        if (library.getAllBooks().isEmpty()) {
            System.out.println("Initializing sample data...");
            
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
    private static void printTableHeader(String title, int width) {
        System.out.println("┌" + "─".repeat(width - 2) + "┐");
        int padding = (width - title.length() - 2) / 2;
        System.out.print("│");
        System.out.print(" ".repeat(padding));
        System.out.print(title);
        System.out.print(" ".repeat(width - title.length() - padding - 2));
        System.out.println("│");
        System.out.println("├" + "─".repeat(width - 2) + "┤");
    }
    
    private static void printTableRow(String content, int width) {
        System.out.print("│ ");
        System.out.print(content);
        System.out.print(" ".repeat(width - content.length() - 3));
        System.out.println("│");
    }
    
    private static void printTableFooter(int width) {
        System.out.println("└" + "─".repeat(width - 2) + "┘");
    }
    
    private static void printMenuTable(String title, String[] options) {
        int width = 50;
        printTableHeader(title, width);
        
        for (String option : options) {
            printTableRow(option, width);
        }
        
        printTableFooter(width);
    }
    
    private static void printDataTable(String title, String[] headers, String[][] data) {
        // Calculate column widths
        int[] colWidths = new int[headers.length];
        for (int i = 0; i < headers.length; i++) {
            colWidths[i] = headers[i].length();
            for (String[] row : data) {
                if (i < row.length && row[i] != null) {
                    colWidths[i] = Math.max(colWidths[i], row[i].length());
                }
            }
            colWidths[i] += 2; // Add padding
        }
        
        int totalWidth = 1; // Start border
        for (int width : colWidths) {
            totalWidth += width + 1; // Column width + separator
        }
        
        // Print title
        System.out.println("┌" + "─".repeat(totalWidth - 2) + "┐");
        int titlePadding = (totalWidth - title.length() - 2) / 2;
        System.out.print("│");
        System.out.print(" ".repeat(titlePadding));
        System.out.print(title);
        System.out.print(" ".repeat(totalWidth - title.length() - titlePadding - 2));
        System.out.println("│");
        
        // Print header separator
        System.out.print("├");
        for (int i = 0; i < colWidths.length; i++) {
            System.out.print("─".repeat(colWidths[i]));
            if (i < colWidths.length - 1) {
                System.out.print("┼");
            }
        }
        System.out.println("┤");
        
        // Print headers
        System.out.print("│");
        for (int i = 0; i < headers.length; i++) {
            System.out.print(" " + headers[i]);
            System.out.print(" ".repeat(colWidths[i] - headers[i].length() - 1));
            System.out.print("│");
        }
        System.out.println();
        
        // Print data separator
        System.out.print("├");
        for (int i = 0; i < colWidths.length; i++) {
            System.out.print("─".repeat(colWidths[i]));
            if (i < colWidths.length - 1) {
                System.out.print("┼");
            }
        }
        System.out.println("┤");
        
        // Print data rows
        for (String[] row : data) {
            System.out.print("│");
            for (int i = 0; i < headers.length; i++) {
                String cell = (i < row.length && row[i] != null) ? row[i] : "";
                System.out.print(" " + cell);
                System.out.print(" ".repeat(colWidths[i] - cell.length() - 1));
                System.out.print("│");
            }
            System.out.println();
        }
        
        // Print footer
        System.out.println("└" + "─".repeat(totalWidth - 2) + "┘");
    }

    // Input Helper Methods
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }
    
    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }
}
