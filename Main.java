import java.util.Scanner;
import java.util.ArrayList;

class Main {
    private Book[] bookList;
    private ArrayList<User> userStudent; // Menggunakan ArrayList untuk menyimpan data mahasiswa

    public static void main(String[] args) {
        Main main = new Main();
        main.initialize();
        main.menu();
    }

    public void initialize() {
        // Inisialisasi data buku
        bookList = new Book[5];
        bookList[0] = new Book("001", "The Great Gatsby", "F. Scott Fitzgerald", 10);
        bookList[1] = new Book("002", "To Kill a Mockingbird", "Harper Lee", 8);
        bookList[2] = new Book("003", "1984", "George Orwell", 12);
        bookList[3] = new Book("004", "Pride and Prejudice", "Jane Austen", 6);
        bookList[4] = new Book("005", "The Catcher in the Rye", "J.D. Salinger", 5);

        // Inisialisasi data mahasiswa dengan ArrayList
        userStudent = new ArrayList<>();
        userStudent.add(new User("Luffy", "202300011122233", "Faculty A", "Program X"));
        userStudent.add(new User("Bob", "202300011122234", "Faculty B", "Program Y"));
        userStudent.add(new User("Charlie", "202300011122235", "Faculty C", "Program Z"));
    }

    // Metode menu dan metode lainnya tidak berubah
    // ...

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Library System");
        System.out.println("1. Login as Admin");
        System.out.println("2. Login as Student");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                Admin admin = new Admin(bookList, userStudent);
                admin.menuAdmin();
                break;
            case 2:
                Student student = new Student(bookList, userStudent);
                student.menuStudent();
                break;
            case 3:
                System.out.println("Exiting...");
                System.exit(0);
            default:
                System.out.println("Invalid choice");
        }
    }
}

class Book {
    private String title;
    private String author;
    private String id;
    private int stock;

    public Book(String id, String title, String author, int stock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.stock = stock;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getId() {
        return id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int i) {
    }
}

class User {
    private String name;
    private String nim;
    private String faculty;
    private String program;

    public User(String name, String nim, String faculty, String program) {
        this.name = name;
        this.nim = nim;
        this.faculty = faculty;
        this.program = program;
    }

    public String getName() {
        return name;
    }

    public String getNim() {
        return nim;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getProgram() {
        return program;
    }
}

class Student {
    private Book[] bookList;
    private ArrayList<User> userStudent;
    private ArrayList<Book> borrowedBooks; // ArrayList untuk menyimpan buku yang dipinjam oleh mahasiswa

    public Student(Book[] bookList, ArrayList<User> userStudent) {
        this.bookList = bookList;
        this.userStudent = userStudent;
        this.borrowedBooks = new ArrayList<>();
    }

    public void menuStudent() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nStudent Dashboard");
            System.out.println("1. Display Books");
            System.out.println("2. Borrow a Book");
            System.out.println("3. Display Borrowed Books"); // Opsi baru untuk menampilkan buku yang dipinjam
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    displayBooks();
                    break;
                case 2:
                    borrowBook();
                    break;
                case 3:
                    displayBorrowedBooks(); // Menampilkan buku yang dipinjam
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public void displayBooks() {
        System.out.println("\nAvailable Books:");
        for (Book book : bookList) {
            int borrowedQuantity = getBorrowedQuantity(book);
            int availableStock = book.getStock() - borrowedQuantity;
            System.out.println(book.getTitle() + " by " + book.getAuthor() + " (ID: " + book.getId() + ", Stock: " + availableStock + ")");
        }
    }

    private int getBorrowedQuantity(Book book) {
        int borrowedQuantity = 0;
        for (Book borrowedBook : borrowedBooks) {
            if (borrowedBook.getId().equals(book.getId())) {
                borrowedQuantity++;
            }
        }
        return borrowedQuantity;
    }


    public void borrowBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the ID of the book you want to borrow: ");
        String bookId = scanner.nextLine();
        Book borrowedBook = null;
        for (Book book : bookList) {
            if (book.getId().equals(bookId)) {
                borrowedBook = book;
                break;
            }
        }
        if (borrowedBook != null) {
            if (borrowedBook.getStock() > 0) {
                // Kurangi stok buku yang dipinjam dari bookList
                borrowedBook.setStock(borrowedBook.getStock() - 1);
                // Tambahkan buku yang dipinjam ke borrowedBooks
                borrowedBooks.add(borrowedBook);
                System.out.println("Book " + borrowedBook.getTitle() + " has been borrowed successfully.");
            } else {
                System.out.println("Sorry, the book " + borrowedBook.getTitle() + " is out of stock.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }


    public void displayBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("You haven't borrowed any books yet.");
        } else {
            System.out.println("\nBorrowed Books:");
            for (Book book : borrowedBooks) {
                System.out.println(book.getTitle() + " by " + book.getAuthor());
            }
        }
    }
}


class Admin {
    private Book[] bookList;
    private ArrayList<User> userStudent;
    private int studentCount;

    public Admin(Book[] bookList, ArrayList<User> userStudent) {
        this.bookList = bookList;
        this.userStudent = userStudent;
        this.studentCount = 0;
    }

    public void menuAdmin() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nAdmin Dashboard");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addStudent();
                    displayStudents(); // Menampilkan semua mahasiswa yang sudah terdaftar
                    break;
                case 2:
                    displayStudents();
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public void addStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Adding Student");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter NIM: ");
        String nim = scanner.nextLine();
        if (nim.length() != 15) {
            System.out.println("NIM must be 15 characters long.");
            return;
        }
        System.out.print("Enter faculty: ");
        String faculty = scanner.nextLine();
        System.out.print("Enter program: ");
        String program = scanner.nextLine();

        User newUser = new User(name, nim, faculty, program);
        userStudent.add(newUser); // Menggunakan metode add() untuk menambahkan elemen baru ke ArrayList

        System.out.println("Student added successfully.");
    }


    public void displayStudents() {
        System.out.println("\nRegistered Students:");
        for (User user : userStudent) {
            if (user != null) {
                System.out.println(user.getName() + " - " + user.getNim() + " - " + user.getFaculty() + " - " + user.getProgram());
            }
        }
    }

}