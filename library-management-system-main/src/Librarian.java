/**
 * Librarian class extending Person - demonstrates inheritance
 * Represents library staff with administrative privileges
 */
public class Librarian extends Person {
    private String employeeId;
    private String department;
    private String joinDate;
    private double salary;
    
    // Constructor
    public Librarian(String id, String name, String email, String phone, String address,
                     String employeeId, String department, String joinDate, double salary) {
        super(id, name, email, phone, address);
        this.employeeId = employeeId;
        this.department = department;
        this.joinDate = joinDate;
        this.salary = salary;
    }
    
    // Getters
    public String getEmployeeId() { return employeeId; }
    public String getDepartment() { return department; }
    public String getJoinDate() { return joinDate; }
    public double getSalary() { return salary; }
    
    // Setters
    public void setDepartment(String department) { this.department = department; }
    public void setSalary(double salary) { this.salary = salary; }
    
    @Override
    public String getRole() {
        return "Librarian";
    }
    
    @Override
    public void displayInfo() {
        System.out.println("═══════════════════════════════════════");
        System.out.println("👨‍💼 LIBRARIAN INFORMATION");
        System.out.println("═══════════════════════════════════════");
        System.out.println("Librarian ID: " + id);
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Address: " + address);
        System.out.println("Department: " + department);
        System.out.println("Join Date: " + joinDate);
        System.out.println("Salary: $" + salary);
        System.out.println("═══════════════════════════════════════");
    }
    
    @Override
    public String toFileString() {
        return "LIBRARIAN," + id + "," + name + "," + email + "," + phone + "," + 
               address + "," + employeeId + "," + department + "," + joinDate + "," + salary;
    }
    
    // Create Librarian from file string
    public static Librarian fromFileString(String fileString) {
        String[] parts = fileString.split(",");
        if (parts.length >= 10 && parts[0].equals("LIBRARIAN")) {
            String id = parts[1];
            String name = parts[2];
            String email = parts[3];
            String phone = parts[4];
            String address = parts[5];
            String employeeId = parts[6];
            String department = parts[7];
            String joinDate = parts[8];
            double salary = Double.parseDouble(parts[9]);
            
            return new Librarian(id, name, email, phone, address, employeeId, department, joinDate, salary);
        }
        return null;
    }
    
    // Administrative methods
    public void issueBookToMember(Library library, String bookId, String memberId) {
        library.issueBook(bookId, memberId);
    }
    
    public void acceptReturnedBook(Library library, String bookId, String memberId) {
        library.returnBook(bookId, memberId);
    }
    
    public void addNewBook(Library library, Book book) {
        library.addBook(book);
    }
    
    public void addNewMember(Library library, Member member) {
        library.addMember(member);
    }
}
