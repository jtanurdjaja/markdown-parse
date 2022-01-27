import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.*;

public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void readFiles() throws IOException{
        Path fileName = Path.of("test-file.md");
	    String contents = Files.readString(fileName);
        List links = List.of("https://something.com", "some-page.html");
        assertEquals("checks result", links, MarkdownParse.getLinks(contents));

        Path fileName2 = Path.of("test-file2.md");
	    String contents2 = Files.readString(fileName2);
        List links2 = List.of();
        assertEquals("checks result of test file 2", links2, MarkdownParse.getLinks(contents2));

        Path fileName3 = Path.of("test-file3.md");
	    String contents3 = Files.readString(fileName3);
        List links3 = List.of("www.edwin.com");
        assertEquals("checks result of test file 3", links3, MarkdownParse.getLinks(contents3));

        Path fileName4 = Path.of("test-file4.md");
	    String contents4 = Files.readString(fileName4);
        List links4 = List.of("https://CSE15L.com");
        assertEquals("checks result of test file 4", links4, MarkdownParse.getLinks(contents4));
    }
}