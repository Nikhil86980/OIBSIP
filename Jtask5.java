import java.util.*;

class Book {
    private int bookId;
    private String title;
    private String author;
    private boolean available;

    public Book(int bookId, String title, String author, boolean available) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.available = available;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class Library {
    private Map<Integer, Book> books;
    private Set<String> users;

    public Library() {
        books = new HashMap<>();
        users = new HashSet<>();
    }

    public void addBook(int bookId, String title, String author) {
        books.put(bookId, new Book(bookId, title, author, true));
    }

    public void addUser(String username) {
        users.add(username);
    }

    public boolean authenticateUser(String username) {
        return users.contains(username);
    }

    public void displayBooks() {
        System.out.println("\nLibrary Catalog:");
        for (Book book : books.values()) {
            System.out.println("Book ID: " + book.getBookId() +
                    ", Title: " + book.getTitle() +
                    ", Author: " + book.getAuthor() +
                    ", Available: " + (book.isAvailable() ? "Yes" : "No"));
        }
    }

    public void issueBook(int bookId) {
        Book book = books.get(bookId);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            System.out.println("Book with ID " + bookId + " has been issued.");
        } else {
            System.out.println("Book with ID " + bookId + " is not available for issuing.");
        }
    }

    public void returnBook(int bookId) {
        Book book = books.get(bookId);
        if (book != null && !book.isAvailable()) {
            book.setAvailable(true);
            System.out.println("Book with ID " + bookId + " has been returned.");
        } else {
            System.out.println("Book with ID " + bookId + " cannot be returned.");
        }
    }
}

class LibrarySystem {
    public static void main(String[] args) {
        Library library = new Library();
        library.addBook(1, "Book1", "Author1");
        library.addBook(2, "Book2", "Author2");
        library.addUser("admin");
        library.addUser("user");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        if (library.authenticateUser(username)) {
            System.out.println("Welcome, " + username + "!");
            boolean isAdmin = username.equals("admin");

            while (true) {
                System.out.println("\n1. Display Books");
                System.out.println("2. Issue Book");
                System.out.println("3. Return Book");
                System.out.println("4. Exit");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (choice == 4) {
                    System.out.println("Goodbye!");
                    break;
                }

                if (isAdmin) {
                    // Admin functionalities
                    if (choice == 1) {
                        library.displayBooks();
                    } else if (choice == 2) {
                        System.out.print("Enter book ID to issue: ");
                        int bookId = scanner.nextInt();
                        library.issueBook(bookId);
                    } else if (choice == 3) {
                        System.out.print("Enter book ID to return: ");
                        int bookId = scanner.nextInt();
                        library.returnBook(bookId);
                    } else {
                        System.out.println("Invalid choice.");
                    }
                } else {
                    // User functionalities
                    if (choice == 1) {
                        library.displayBooks();
                    } else if (choice == 2 || choice == 3) {
                        System.out.println("Sorry, you don't have permission to issue or return books.");
                    } else {
                        System.out.println("Invalid choice.");
                    }
                }
            }
        } else {
            System.out.println("Invalid username. Access denied!");
        }
    }
}