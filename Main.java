import java.util.ArrayList;
import java.util.List;

/**
 *Single Responsibility Principle (SRP):
 * CatalogItem class encapsulates the properties and behavior of an individual item in the library catalog.
 * LibraryCatalog class manages the collection of catalog items and provides methods for adding, removing, and searching items.
 * CLI class provides a command-line interface for librarians to interact with the system.
 *
 *Open/Closed Principle (OCP):
 * The system is open for extension by allowing new types of catalog items (e.g., Book, Magazine, CD)
 * to be added without modifying existing code.
 *
 *Liskov Substitution Principle (LSP):
 * Subclasses such as Book, Magazine, and CD can be substituted for their base class CatalogItem without altering
 * the correctness of the program.
 *
 *Interface Segregation Principle (ISP):
 * Each class provides specific interfaces tailored to the needs of clients.
 * For example, LibraryCatalog provides methods for managing the catalog, while CLI provides methods for interacting
 * with the system via the command line.
 *
 *Dependency Inversion Principle (DIP):
 * High-level modules (e.g., LibraryCatalog, CLI) depend on abstractions (CatalogItem) rather than
 * concrete implementations, promoting decoupling and flexibility.
 * */



// CatalogItem class representing an individual item in the library catalog
class CatalogItem {
    private String title;
    private String author;
    private String itemType;

    public CatalogItem(String title, String author, String itemType) {
        this.title = title;
        this.author = author;
        this.itemType = itemType;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getItemType() {
        return itemType;
    }
}

// LibraryCatalog class managing the collection of catalog items
class LibraryCatalog {
    private List<CatalogItem> catalogItems;

    public LibraryCatalog() {
        this.catalogItems = new ArrayList<>();
    }

    public void addItem(CatalogItem item) {
        catalogItems.add(item);
    }

    public void removeItem(CatalogItem item) {
        catalogItems.remove(item);
    }

    public List<CatalogItem> searchItem(String title) {
        List<CatalogItem> searchResults = new ArrayList<>();
        for (CatalogItem item : catalogItems) {
            if (item.getTitle().equalsIgnoreCase(title)) {
                searchResults.add(item);
            }
        }
        return searchResults;
    }
}

// CLI class providing a simple command-line interface for librarians
class CLI {
    private LibraryCatalog catalog;

    public CLI(LibraryCatalog catalog) {
        this.catalog = catalog;
    }

    public void addItem(String title, String author, String itemType) {
        CatalogItem item = new CatalogItem(title, author, itemType);
        catalog.addItem(item);
    }

    public void removeItem(String title) {
        List<CatalogItem> itemsToRemove = catalog.searchItem(title);
        for (CatalogItem item : itemsToRemove) {
            catalog.removeItem(item);
        }
    }

    public List<CatalogItem> searchItem(String title) {
        return catalog.searchItem(title);
    }
}

// Subclasses of CatalogItem
class Book extends CatalogItem {
    public Book(String title, String author) {
        super(title, author, "Book");
    }
}

class Magazine extends CatalogItem {
    public Magazine(String title, String author) {
        super(title, author, "Magazine");
    }
}

class CD extends CatalogItem {
    public CD(String title, String author) {
        super(title, author, "CD");
    }
}

public class Main {
    public static void main(String[] args) {
        LibraryCatalog catalog = new LibraryCatalog();
        CLI cli = new CLI(catalog);

        cli.addItem("The Great Gatsby", "F. Scott Fitzgerald", "Book");
        cli.addItem("National Geographic", "Various", "Magazine");
        cli.addItem("Abbey Road", "The Beatles", "CD");

        List<CatalogItem> searchResults = cli.searchItem("The Great Gatsby");
        for (CatalogItem item : searchResults) {
            System.out.println("Title: " + item.getTitle() + ", Author: " + item.getAuthor() + ", Type: " + item.getItemType());
        }
    }
}
/**
 * In summary, the above design adheres to the SOLID principles by ensuring each class
 * has a single responsibility, allowing for extension without modification, supporting
 * substitution of subclasses, providing specific interfaces, and implementing dependency
 * inversion. Additionally, the CLI class provides a simple command-line interface for librarians
 * to interact with the system.
 * */
