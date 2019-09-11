import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testStringConversion() {
        assertEquals("[T][-] watch movie", new Todo("watch movie").toString());
    }

    @Test
    public void testTextFileConversion() {
        assertEquals("T | - | watch movie", new Todo("watch movie").toTextFile());
    }
}
