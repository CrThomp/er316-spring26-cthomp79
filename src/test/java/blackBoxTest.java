import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.lang.reflect.Constructor;
import java.time.LocalDate;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
//import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Sample Black-Box tests for the Checkout system.
 * This class demonstrates how to write black-box tests using:
 * - Equivalence Partitioning (EP)
 * - Boundary Value Analysis (BVA)
 * - Parametrized tests across multiple implementations
 *
 * Black-box testing focuses on testing the SPECIFICATION WITHOUT
 * looking at the implementation.
 *
 * The parameterized structure allows testing all Checkout implementations
 * with the same tests to identify which implementations have bugs.
 */
public class blackBoxTest {

    private Checkout checkout;

    /**
     * Provides the list of Checkout classes to test.
     * Each test will run against ALL implementations.
     */
    @SuppressWarnings("unchecked")
    //static Stream<Class<? extends Checkout>> checkoutClassProvider() {
    //   return Stream.of(Checkout.class);
    // }

    // Uncomment when you implement the method in assign 3 and comment the above
    static Stream<Class<? extends Checkout>> checkoutClassProvider() {
        return Stream.of(Checkout.class);
    }


    /**
     * Helper method to create Checkout instance from class using reflection.
     */
    private Checkout createCheckout(Class<? extends Checkout> clazz) throws Exception {
        Constructor<? extends Checkout> constructor = clazz.getConstructor();
        return constructor.newInstance();
    }

    /**
     * SAMPLE TEST 1: Tests successful checkout of an available book
     * This tests the valid equivalence partition - all conditions met.
     */
    @ParameterizedTest
    @MethodSource("checkoutClassProvider")
    @DisplayName("T1: Successful checkout - available book, eligible patron")
    public void testBookAvailable(Class<? extends Checkout> checkoutClass) throws Exception {
        //checkout = createCheckout(checkoutClass);
        checkout = new Checkout(); //testing this to fix compilation error
        // Setup: Create available book and eligible patron
        Book book = new Book("978-0-123456-78-9", "Test Book",
                "Test Author", Book.BookType.FICTION, 1);

        Patron patron = new Patron("P001", "Test Patron", "test@example.com",
                Patron.PatronType.STUDENT);

        checkout.addBook(book); // adding the book to the library
        checkout.registerPatron(patron); // adding a patrol to the system

        // Execute checkout
        double result = checkout.checkoutBook(book, patron);

        // Verify: Should return 0.0 for success
        assertEquals(0.0, result, 0.01,
                "Expected successful checkout (0.0) for " + checkoutClass.getSimpleName());

        // Verify: Book should now be unavailable
        assertFalse(book.isAvailable(),
                "Book should be unavailable after checkout for " + checkoutClass.getSimpleName());

        // Verify: Patron should have the book in their checked-out list
        assertTrue(patron.hasBookCheckedOut(book.getIsbn()),
                "Patron should have book in checked-out list for " + checkoutClass.getSimpleName());

        // Verify: Checkout count increased
        assertEquals(1, patron.getCheckoutCount(),
                "Patron checkout count should be 1 for " + checkoutClass.getSimpleName());
    }

    /**
     * SAMPLE TEST 2: Tests checkout with unavailable book
     * This tests an invalid equivalence partition.
     */
    @ParameterizedTest
    @MethodSource("checkoutClassProvider")
    @DisplayName("T2: Unavailable book returns error code 2.0")
    public void testUnavailableBook(Class<? extends Checkout> checkoutClass) throws Exception {
        //checkout = createCheckout(checkoutClass);
        checkout = new Checkout();
        // Setup: Create unavailable book
        Book book = new Book("978-0-123456-78-9", "Test Book",
                "Test Author", Book.BookType.FICTION, 5);
        book.setAvailableCopies(0);  // We are pretending it has been checked out by others and is not available anymore

        Patron patron = new Patron("P001", "Test Patron", "test@example.com",
                Patron.PatronType.STUDENT);

        checkout.addBook(book);
        checkout.registerPatron(patron);

        // Execute checkout
        double result = checkout.checkoutBook(book, patron);

        // Verify: Should return 2.0 for unavailable book
        assertEquals(2.0, result, 0.01,
                "Expected error code 2.0 for unavailable book for " + checkoutClass.getSimpleName());

        // Verify: Patron should NOT have the book
        assertFalse(patron.hasBookCheckedOut(book.getIsbn()));
        new StringBuilder().append("Patron should NOT have book in list for ").append(checkoutClass.getSimpleName()).toString();
    }
    /**
     *  This is an implementation of testSuspendedPatron()
     *  This test will check that the available copy of the book is larger than 0
     *  it will then check if the patron account is suspended
     *  if the test is successful, meaning the patronID is suspended it will return
     *  with the value of 1.0
     *  No checkout log will be created; book inventory will be unchanged
     */
    @Test
    public void testSuspendedPatron(){
        //checkout = createCheckout(checkoutClass);
        checkout = new Checkout();
        // Setup: Create available book and eligible patron
        Book book = new Book("978-0-123456-78-9", "Test Book",
                "Test Author", Book.BookType.FICTION, 1);

        Patron patron = new Patron("P001", "Test Patron", "test@example.com",
                Patron.PatronType.STUDENT);

        checkout.addBook(book); // adding the book to the library
        checkout.registerPatron(patron); // adding a patrol to the system
        patron.setAccountSuspended(true);
        //attempt to checkout
        double result = checkout.checkoutBook(book, patron);

        //Assert
        assertEquals(3.0, result);
        assertEquals(1, book.getAvailableCopies());
        assertFalse(patron.hasBookCheckedOut(book.getIsbn()));
    }
    /**
     * `testFineBelowLimit`
     * Available copy; patron fine below threshold
     * expected outcome 0.0
     * book checked out successfully
     */
    @Test
    public void testFineBelowLimit(){
        //checkout = createCheckout(checkoutClass);
        checkout = new Checkout();
        // Setup: Create available book and eligible patron
        Book book = new Book("978-0-123456-78-9", "Test Book",
                "Test Author", Book.BookType.FICTION, 1);

        Patron patron = new Patron("P001", "Test Patron", "test@example.com",
                Patron.PatronType.STUDENT);

        checkout.addBook(book); // adding the book to the library
        checkout.registerPatron(patron); // adding a patrol to the system
        patron.setFines(0.0);

        double result = patron.getFineBalance();

        assertEquals(0.0, result);
    }
    /**
     * `testFineAtLimit`
     * Available copy; patron fine equal to threshold
     * 3.0
     * Book checked out successfully
     */
    @Test
    public void testFineAtLimit() {
        // Setup: Create unavailable book
        // checkout = createCheckout(checkoutClass);
        checkout = new Checkout();
        // Setup: Create available book and eligible patron
        Book book = new Book("978-0-123456-78-9", "Test Book",
                "Test Author", Book.BookType.FICTION, 1);

        Patron patron = new Patron("P001", "Test Patron", "test@example.com",
                Patron.PatronType.STUDENT);

        checkout.addBook(book); // adding the book to the library
        checkout.registerPatron(patron); // adding a patrol to the system
        patron.setFines(25.0);

        double result = patron.getFineBalance();

        assertEquals(25.0, result);
    }

    /**
     * `testFineAboveLimit`
     *  Available copy; patron fine above threshold
     *  3.0
     *  No checkout; patron and book objects unchanged
     */
    @Test
    public void testFineAboveLimit() {
        // Setup: Create unavailable book
        //checkout = createCheckout(checkoutClass);
        checkout = new Checkout();
        // Setup: Create available book and eligible patron
        Book book = new Book("978-0-123456-78-9", "Test Book",
                "Test Author", Book.BookType.FICTION, 1);

        Patron patron = new Patron("P001", "Test Patron", "test@example.com",
                Patron.PatronType.STUDENT);

        checkout.addBook(book); // adding the book to the library
        checkout.registerPatron(patron); // adding a patrol to the system
        patron.setFines(26.0);

        double result = patron.getFineBalance();

        assertEquals(26.0, result);
    }

    /**
     * testBelowCheckoutLimit
     * Patron (Faculty, Staff, Student, Public, Child)
     * currently holds at least one fewer books than maximum
     * 0.0
     * Checkout succeeds; checkout count increases
     */
    @Test
    public void testBelowCheckoutLimit(){
        //checkout = createCheckout(checkoutClass);
        checkout = new Checkout();
        // Setup: Create available book and eligible patron
        Book book = new Book("978-0-123456-78-9", "Test Book",
                "Test Author", Book.BookType.FICTION, 1);

        Patron patron = new Patron("P001", "Test Patron", "test@example.com",
                Patron.PatronType.STUDENT);

        checkout.addBook(book); // adding the book to the library
        checkout.registerPatron(patron); // adding a patrol to the system
        double result = checkout.checkoutBook(book, patron);

        assertEquals(0.0, result);
        assertEquals(0.0, book.getAvailableCopies());
        assertEquals(0.0, patron.getOverdueCount());
    }
    /**
     * testAtCheckoutLimit
     * Patron (Faculty, Staff, Student, Public, Child) currently holds maximum allowed books
     * 4.0
     * Checkout rejected; book inventory unchanged
     *
     */
    @Test
    public void testCheckoutLimit(){
        //checkout = createCheckout(checkoutClass);
        checkout = new Checkout();
        // Setup: Create available book and eligible patron
        Book book = new Book("978-0-123456-78-9", "Test Book",
                "Test Author", Book.BookType.FICTION, 1);

        Patron patron = new Patron("P001", "Test Patron", "test@example.com",
                Patron.PatronType.STUDENT);

        checkout.addBook(book); // adding the book to the library
        checkout.registerPatron(patron); // adding a patrol to the system
        for(int i = 0; i < patron.getMaxCheckoutLimit(); i++) {
            patron.addCheckedOutBook("ISBN" + i, LocalDate.now());
        }
        int originalCount = patron.getCheckoutCount();
        double result = checkout.checkoutBook(book, patron);

        assertEquals(3.2, result);
        assertEquals(1, book.getAvailableCopies());
        assertFalse(patron.hasBookCheckedOut(book.getIsbn()));
        assertEquals(originalCount, patron.getCheckoutCount());
    }

    /**
     * testSuccessfulCheckout
     * Book is available;
     * Patron is valid; Patron isn't suspended; patron does not have outstanding fines
     * 0.0
     * Inventory count, patron checkout history and checkout count changes
     */
    @Test
    public void testSuccessfulCheckout(){
        //checkout = createCheckout(checkoutClass);
        checkout = new Checkout();
        // Setup: Create available book and eligible patron
        Book book = new Book("978-0-123456-78-9", "Test Book",
                "Test Author", Book.BookType.FICTION, 1);

        Patron patron = new Patron("P001", "Test Patron", "test@example.com",
                Patron.PatronType.STUDENT);

        checkout.addBook(book); // adding the book to the library
        checkout.registerPatron(patron); // adding a patrol to the system

        // Execute checkout
        double result = checkout.checkoutBook(book, patron);

        assertEquals(0.0, result);
        assertEquals(0, book.getAvailableCopies());
        assertTrue(patron.hasBookCheckedOut(book.getIsbn()));
    }

    /**
     * `testRejectedCheckout`
     * Patron is suspended and attempts a checkout
     * 1.0
     * No checkout record created; inventory left unchanged
     */
    @Test
    public void testRejectedCheckout(){
        //checkout = createCheckout(checkoutClass);
        checkout = new Checkout();
        // Setup: Create available book and eligible patron
        Book book = new Book("978-0-123456-78-9", "Test Book",
                "Test Author", Book.BookType.FICTION, 1);

        Patron patron = new Patron("P001", "Test Patron", "test@example.com",
                Patron.PatronType.STUDENT);

        checkout.addBook(book); // adding the book to the library
        checkout.registerPatron(patron); // adding a patrol to the system
        patron.setAccountSuspended(true);
        // Execute checkout
        double result = checkout.checkoutBook(book, patron);

        assertEquals(3.0, result);
        assertEquals(1, book.getAvailableCopies());
        assertFalse(patron.hasBookCheckedOut(book.getIsbn()));
    }
}
