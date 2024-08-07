package contactmanagement;

public class ContactManagementTest {
    public static void main(String[] args) {
        ContactManagement management = new ContactManagement();

        // Test adding contacts
        management.addContact(new Contact(1, "Afsha", "9897654432"));
        management.addContact(new Contact(2, "Samba", "7665322467"));
        management.addContact(new Contact(3, "Swathi", "9853578654"));

        // Display contacts
        management.displayContacts();

        // Test removing contacts
        System.out.println("Removing Contact ID 2: " + management.removeContact(2));
        System.out.println("Removing Contact ID 4 (non-existent): " + management.removeContact(4));

        // Display contacts after removal
        management.displayContacts();
    }
}
