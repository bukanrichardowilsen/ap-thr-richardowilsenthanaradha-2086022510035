import java.util.Scanner;
import java.util.Stack;

// Superclass
class Book {
    String title, author;
    int year;

    public Book(String title, String author, int year) {
        // Constraint: Title < 255
        if (title.length() >= 255) {
            this.title = title.substring(0, 254);
        } else {
            this.title = title;
        }

        // Constraint: Author < 50
        if (author.length() >= 50) {
            this.author = author.substring(0, 49);
        } else {
            this.author = author;
        }

        // Constraint: Year MUST be (1800 < year < 2026)
        if (year > 1800 && year < 2026) {
            this.year = year;
        } else {
            this.year = 2025; // Default jika di luar jangkauan
        }
    }

    public void getInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Year of Publication: " + year);
    }
}

// Subclass 1
class GeneralBook extends Book {
    String genre;

    public GeneralBook(String t, String a, int y, String g) {
        super(t, a, y); // Memanggil constructor parent
        // Constraint: Genre < 30 chars
        this.genre = (g.length() < 30) ? g : g.substring(0, 29);
    }

    @Override
    public void getInfo() {
        super.getInfo();
        System.out.println("Genre: " + genre);
    }
}

// Subclass 2
class ChildrenBook extends Book {
    int minAge;
    boolean hasVisualisation;

    public ChildrenBook(String t, String a, int y, int age, boolean vis) {
        super(t, a, y);
        // Constraint: 3 < age < 12
        this.minAge = (age > 3 && age < 12) ? age : 5;
        this.hasVisualisation = vis;
    }

    @Override
    public void getInfo() {
        super.getInfo();
        System.out.println("Minimum Age: " + minAge);
        // Output harus "Yes" atau "No" sesuai ekspektasi soal
        System.out.println("Has Visualisation: " + (hasVisualisation ? "Yes" : "No"));
    }
}

public class thrquest5 {
    public static void main(String[] args) {
        // Menggunakan Stack sesuai aturan soal
        Stack<Book> library = new Stack<>();
        Scanner sc = new Scanner(System.in);

        // Menambahkan data awal (Instantiate books)
        // Gunakan 'true' (kecil), bukan 'True' (besar) agar tidak error di Java
        library.push(new Book("Why Black Moves First", "Wesley So", 2025));
        library.push(new GeneralBook("Inside Black Mesa", "Dr. Isaac Kleiner", 1997, "Documentary"));
        library.push(new ChildrenBook("Got Science?", "Rachel Dawes", 2015, 5, true));

        // Loop Menu Utama
        boolean running = true;
        while (running) {
            System.out.println("\n--- Library of Alexandria Menu ---");
            System.out.println("1. View Stored Books");
            System.out.println("2. Add New Book");
            System.out.println("3. Delete Last Book (Stack Pop)");
            System.out.println("4. Exit");
            System.out.print("Choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine(); // Membersihkan buffer

            switch (choice) {
                case 1:
                    System.out.println("\n--- Current Library Content ---");
                    for (Book b : library) {
                        b.getInfo();
                        System.out.println(); // Spasi antar buku
                    }
                    break;
                case 2:
                    System.out.print("Enter Title: ");
                    String t = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String a = sc.nextLine();
                    System.out.print("Enter Year: ");
                    int y = sc.nextInt();
                    library.push(new Book(t, a, y));
                    System.out.println("Book added to the top of the stack!");
                    break;
                case 3:
                    if (!library.isEmpty()) {
                        Book removed = library.pop();
                        System.out.println("Deleted: " + removed.title);
                    } else {
                        System.out.println("Stack is empty!");
                    }
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        sc.close();
    }
}