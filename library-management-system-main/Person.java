/**
 * Abstract base class Person demonstrating inheritance
 * Common properties and methods for all library users
 */
public abstract class Person {
    protected String id;
    protected String name;
    protected String email;
    protected String phone;
    protected String address;
    
    // Constructor
    public Person(String id, String name, String email, String phone, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
    
    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    
    // Setters
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setAddress(String address) { this.address = address; }
    
    // Abstract methods to be implemented by subclasses
    public abstract String getRole();
    public abstract void displayInfo();
    public abstract String toFileString();
    
    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Role: %s | Email: %s", 
                           id, name, getRole(), email);
    }
}
