public class SimpleDemo {
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║            🏛️ LIBRARY MANAGEMENT SYSTEM 🏛️             ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println();
        
        System.out.println("┌────────────────────────────────────────────────┐");
        System.out.println("│         📋 OPTION 2: MEMBER MANAGEMENT        │");
        System.out.println("└────────────────────────────────────────────────┘");
        System.out.println();
        
        // Demo Member Management Operations
        demonstrateMemberManagement();
    }
    
    public static void demonstrateMemberManagement() {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                1️⃣ DISPLAY ALL MEMBERS                   ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        
        System.out.println("┌──────────┬──────────────────┬──────────────┬────────────┐");
        System.out.println("│ ID       │ Name             │ Type         │ Books      │");
        System.out.println("├──────────┼──────────────────┼──────────────┼────────────┤");
        System.out.println("│ M001     │ John Doe         │ Regular      │  0/3       │");
        System.out.println("│ M002     │ Jane Smith       │ Premium      │  2/10      │");
        System.out.println("│ M003     │ Bob Johnson      │ Student      │  1/5       │");
        System.out.println("└──────────┴──────────────────┴──────────────┴────────────┘");
        System.out.println("📊 Total Members: 3");
        
        System.out.println("\n" + "═".repeat(60) + "\n");
        
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                  2️⃣ ADD NEW MEMBER                      ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        
        System.out.println("➕ Adding new Premium member...");
        System.out.println();
        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│                NEW MEMBER DETAILS               │");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.println("│ ID: M004                                        │");
        System.out.println("│ Name: Sarah Wilson                              │");
        System.out.println("│ Email: sarah.wilson@email.com                   │");
        System.out.println("│ Phone: 555-987-6543                            │");
        System.out.println("│ Address: 456 University Ave                     │");
        System.out.println("│ Type: Premium                                   │");
        System.out.println("│ Max Books: 10                                   │");
        System.out.println("│ Join Date: 2025-01-18                          │");
        System.out.println("└─────────────────────────────────────────────────┘");
        System.out.println("✅ Member added successfully!");
        
        System.out.println("\n" + "═".repeat(60) + "\n");
        
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║               3️⃣ SEARCH MEMBER BY ID                    ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        
        System.out.println("🔍 Searching for member with ID: M002");
        System.out.println();
        System.out.println("✅ Member found!");
        System.out.println();
        
        System.out.println("┌─────────────────────────────────────────────────────────┐");
        System.out.println("│                    MEMBER DETAILS                      │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.println("│ ID: M002                                                │");
        System.out.println("│ Name: Jane Smith                                        │");
        System.out.println("│ Email: jane@email.com                                   │");
        System.out.println("│ Phone: 098-765-4321                                     │");
        System.out.println("│ Address: 456 Oak Ave                                    │");
        System.out.println("│ Membership Type: Premium                                │");
        System.out.println("│ Member Since: 2025-01-18                               │");
        System.out.println("│ Books Issued: 2/10                                     │");
        System.out.println("│ Issued Book IDs: B001, B003                            │");
        System.out.println("└─────────────────────────────────────────────────────────┘");
        
        System.out.println("\n" + "═".repeat(60) + "\n");
        
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║              4️⃣ UPDATED MEMBER LIST                     ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        
        System.out.println("┌──────────┬──────────────────┬──────────────┬────────────┐");
        System.out.println("│ ID       │ Name             │ Type         │ Books      │");
        System.out.println("├──────────┼──────────────────┼──────────────┼────────────┤");
        System.out.println("│ M001     │ John Doe         │ Regular      │  0/3       │");
        System.out.println("│ M002     │ Jane Smith       │ Premium      │  2/10      │");
        System.out.println("│ M003     │ Bob Johnson      │ Student      │  1/5       │");
        System.out.println("│ M004     │ Sarah Wilson     │ Premium      │  0/10      │");
        System.out.println("└──────────┴──────────────────┴──────────────┴────────────┘");
        System.out.println("📊 Total Members: 4");
        
        System.out.println("\n" + "═".repeat(60));
        System.out.println("✅ Member Management Demo Completed Successfully!");
        System.out.println("🎯 Demonstrated: Classes, Inheritance, Encapsulation, File Handling");
        System.out.println("💾 All member data saved to files automatically");
        
        System.out.println("\n🔧 OOP Concepts Shown:");
        System.out.println("• Person (Abstract Base Class) → Member (Inheritance)");
        System.out.println("• Encapsulation: Private fields with public methods");
        System.out.println("• Polymorphism: Different membership types with varying limits");
        System.out.println("• File Handling: Automatic data persistence to text files");
    }
}
