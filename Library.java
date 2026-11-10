import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Library {
    private static final Path FILE = Path.of("books.json");
    private final List<Book> books = new ArrayList<>();

    public Library() {
        load();
    }

    private void load() {
        books.clear();
        try {
            if (!Files.exists(FILE)) return;
            String json = Files.readString(FILE);
            Matcher m = Pattern.compile(
                "\\{\\s*\"title\"\\s*:\\s*\"(.*?)\"\\s*,\\s*\"author\"\\s*:\\s*\"(.*?)\"\\s*\\}"
            ).matcher(json);
            while (m.find()) {
                books.add(new Book(m.group(1), m.group(2)));
            }
        } catch (IOException e) {
            System.out.println("Could not read books.json: " + e.getMessage());
        }
    }

    private void save() {
        StringBuilder sb = new StringBuilder("[\n");
        for (int i = 0; i < books.size(); i++) {
            sb.append("  ").append(books.get(i).toJson());
            if (i < books.size() - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("]\n");
        try {
            Files.writeString(FILE, sb.toString());
        } catch (IOException e) {
            System.out.println("Could not save books.json: " + e.getMessage());
        }
    }

    // ===== Student A: feature/add-list-books =====
    public void addBook(String title, String author) {
        if (title.isEmpty() || author.isEmpty()) {
            System.out.println("Title and author cannot be empty.");
            return;
        }
        books.add(new Book(title, author));
        save();
        System.out.println("Added: " + title + " by " + author);    }

    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books yet.");
            return;
        }
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i));
        }
    }
// ===== Student B: feature/search-remove =====
public void searchBooks(String keyword) {
    if (keyword.isEmpty()) {
        System.out.println("Please enter a book title to search.");
        return;
    }

    boolean found = false;

    for (Book book : books) {
        if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
            System.out.println(book);
            found = true;
        }
    }

    if (!found) {
        System.out.println("Book not found.");
    }
}
public void removeBook(String title) {
    if (title.isEmpty()) {
        System.out.println("Please enter a book title to remove.");
        return;
    }

    boolean removed = books.removeIf(
        book -> book.getTitle().equalsIgnoreCase(title)
    );

    if (removed) {
        save();
        System.out.println("Removed: " + title + " (" + books.size() + " books left)");
    } else {
        System.out.println("Book not found.");
    }
}
public int countBooks() {
    return books.size();
}

}