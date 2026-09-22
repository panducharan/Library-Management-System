import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * FileHandler class for data persistence
 * Demonstrates file handling operations for saving and loading data
 */
public class FileHandler {
    private static final String BOOKS_FILE = "data/books.txt";
    private static final String MEMBERS_FILE = "data/members.txt";
    private static final String LIBRARIANS_FILE = "data/librarians.txt";
    
    // Save books to file
    public static boolean saveBooks(List<Book> books) {
        try {
            createDataDirectory();
            FileWriter writer = new FileWriter(BOOKS_FILE);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            
            for (Book book : books) {
                bufferedWriter.write(book.toFileString());
                bufferedWriter.newLine();
            }
            
            bufferedWriter.close();
            return true;
        } catch (IOException e) {
            System.err.println("Error saving books: " + e.getMessage());
            return false;
        }
    }
    
    // Load books from file
    public static List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        try {
            File file = new File(BOOKS_FILE);
            if (!file.exists()) {
                return books; // Return empty list if file doesn't exist
            }
            
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            
            while ((line = bufferedReader.readLine()) != null) {
                Book book = Book.fromFileString(line);
                if (book != null) {
                    books.add(book);
                }
            }
            
            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("Error loading books: " + e.getMessage());
        }
        return books;
    }
    
    // Save members to file
    public static boolean saveMembers(List<Member> members) {
        try {
            createDataDirectory();
            FileWriter writer = new FileWriter(MEMBERS_FILE);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            
            for (Member member : members) {
                bufferedWriter.write(member.toFileString());
                bufferedWriter.newLine();
            }
            
            bufferedWriter.close();
            return true;
        } catch (IOException e) {
            System.err.println("Error saving members: " + e.getMessage());
            return false;
        }
    }
    
    // Load members from file
    public static List<Member> loadMembers() {
        List<Member> members = new ArrayList<>();
        try {
            File file = new File(MEMBERS_FILE);
            if (!file.exists()) {
                return members; // Return empty list if file doesn't exist
            }
            
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            
            while ((line = bufferedReader.readLine()) != null) {
                Member member = Member.fromFileString(line);
                if (member != null) {
                    members.add(member);
                }
            }
            
            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("Error loading members: " + e.getMessage());
        }
        return members;
    }
    
    // Save librarians to file
    public static boolean saveLibrarians(List<Librarian> librarians) {
        try {
            createDataDirectory();
            FileWriter writer = new FileWriter(LIBRARIANS_FILE);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            
            for (Librarian librarian : librarians) {
                bufferedWriter.write(librarian.toFileString());
                bufferedWriter.newLine();
            }
            
            bufferedWriter.close();
            return true;
        } catch (IOException e) {
            System.err.println("Error saving librarians: " + e.getMessage());
            return false;
        }
    }
    
    // Load librarians from file
    public static List<Librarian> loadLibrarians() {
        List<Librarian> librarians = new ArrayList<>();
        try {
            File file = new File(LIBRARIANS_FILE);
            if (!file.exists()) {
                return librarians; // Return empty list if file doesn't exist
            }
            
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            
            while ((line = bufferedReader.readLine()) != null) {
                Librarian librarian = Librarian.fromFileString(line);
                if (librarian != null) {
                    librarians.add(librarian);
                }
            }
            
            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("Error loading librarians: " + e.getMessage());
        }
        return librarians;
    }
    
    // Create data directory if it doesn't exist
    private static void createDataDirectory() {
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
    }
    
    // Backup all data
    public static boolean backupData(List<Book> books, List<Member> members, List<Librarian> librarians) {
        try {
            String timestamp = String.valueOf(System.currentTimeMillis());
            String backupDir = "data/backup_" + timestamp;
            File dir = new File(backupDir);
            dir.mkdirs();
            
            // Backup books
            FileWriter booksWriter = new FileWriter(backupDir + "/books_backup.txt");
            BufferedWriter booksBuffer = new BufferedWriter(booksWriter);
            for (Book book : books) {
                booksBuffer.write(book.toFileString());
                booksBuffer.newLine();
            }
            booksBuffer.close();
            
            // Backup members
            FileWriter membersWriter = new FileWriter(backupDir + "/members_backup.txt");
            BufferedWriter membersBuffer = new BufferedWriter(membersWriter);
            for (Member member : members) {
                membersBuffer.write(member.toFileString());
                membersBuffer.newLine();
            }
            membersBuffer.close();
            
            // Backup librarians
            FileWriter librariansWriter = new FileWriter(backupDir + "/librarians_backup.txt");
            BufferedWriter librariansBuffer = new BufferedWriter(librariansWriter);
            for (Librarian librarian : librarians) {
                librariansBuffer.write(librarian.toFileString());
                librariansBuffer.newLine();
            }
            librariansBuffer.close();
            
            System.out.println("✅ Data backup created successfully in: " + backupDir);
            return true;
        } catch (IOException e) {
            System.err.println("❌ Error creating backup: " + e.getMessage());
            return false;
        }
    }
}
