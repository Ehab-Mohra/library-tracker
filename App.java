
import java.util.Scanner;

public class App {

    static void print_banner() {
        System.out.println("==============================");
        System.out.println("  Welcome to Library Tracker v1.0 ");
        System.out.println("==============================");
    }

    public static void main(String[] args) {
        Library library = new Library();
        Scanner in = new Scanner(System.in);

        print_banner();
        System.out.println("Books in library: " + library.countBooks());

        while (true) {
            System.out.println();
            System.out.println("1) Add book");
            System.out.println("2) List books");
            System.out.println("3) Search books");
            System.out.println("4) Remove book");
            System.out.println("0) Exit");
            System.out.print("Choose: ");

            String choice = in.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    System.out.print("Title: ");
                    String title = in.nextLine().trim();

                    System.out.print("Author: ");
                    String author = in.nextLine().trim();

                    library.addBook(title, author);
                }
                case "2" -> library.listBooks();

                case "3" -> {
                    System.out.print("Search: ");
                    library.searchBooks(in.nextLine().trim());
                }
                case "4" -> {
                    System.out.print("Title to remove: ");
                    library.removeBook(in.nextLine().trim());
                }
                case "0" -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }
}
