import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Sample White-Box tests for the Checkout system.
 * This class demonstrates how to write white-box tests using:
 * - Control Flow Graph (CFG) analysis
 * - Statement coverage
 * - Branch coverage
 * - Path coverage
 *
 * White-box testing focuses on testing the IMPLEMENTATION by
 * examining the code structure and ensuring all paths are tested.
 */
public class whiteBoxTest {

    private Checkout checkout;

    @BeforeEach
    public void setUp() {
        checkout = new Checkout();
    }

    @Test
    @DisplayName("WB Test: countBooksByType - null type branch")
    public void testCountBooksByType_NullType() {
        // Branch: type == null → TRUE
        int result = checkout.countBooksByType(null, false);
        assertEquals(0, result, "Should return 0 for null type");
    }
    @Test
    @DisplayName("WB Test: countBooksByType - empty inventory")
    public void testCountBooksByType_EmptyInventory(){
        int result = checkout.countBooksByType(Book.BookType.FICTION, false);
        assertEquals(0, result, "Should return 0 for empty inventory");
    }
    @Test
    @DisplayName("WB Test: Matches type - all books counted")
    public void testCountBooksByType_AllBooks(){
        Book book = new Book("1", "Book", "Author", Book.BookType.FICTION,1);
        checkout.addBook(book);
        int result = checkout.countBooksByType(Book.BookType.FICTION, false);
        assertEquals(1, result, "Should return 1 for one book matching");
    }
    @Test
    @DisplayName("WB Test: countBooksByTypee - available books counted")
    public void testCountBooksByType_Available(){
        Book book = new Book("1", "Book", "Author", Book.BookType.FICTION, 1);
        checkout.addBook(book);
        int result = checkout.countBooksByType(Book.BookType.FICTION, true);
        assertEquals(1, result, "Should return 1 for available books counted");
    }
    @Test
    @DisplayName("WB Test: Unavailable books not counted")
    public void testCountByType_unavailable(){
        Book book = new Book("1", "Book", "Author", Book.BookType.FICTION, 1);
        book.setAvailableCopies(0);
        checkout.addBook(book);
        int result = checkout.countBooksByType(Book.BookType.FICTION, true);
        assertEquals(0, result, "Should return 0 for unavailable books not counted");
    }
    @Test
    @DisplayName("WB Test: Types don't match")
    public void testCountBooksByType_TypeMismatch(){
        Book book = new Book("1", "Book", "Author", Book.BookType.TEXTBOOK, 1);
        checkout.addBook(book);
        int result = checkout.countBooksByType(Book.BookType.FICTION, false);
        assertEquals(0, result, "Should return 0 for book types not matching counted");
    }

}
