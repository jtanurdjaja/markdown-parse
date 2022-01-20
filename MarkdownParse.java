// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/* Test Case 1
Test Case is failing due to incorrect order of brackets and parenthesis.
Opening and closing brackets should always come before open/closing parenthesis
Maybe we can check the index of the first opening parenthesis with the index of the first closing bracket
if the index of our first opening parenthesis comes before the index
of our first closinng bracket then we break and return an empty array list.
 */

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        
        int currentIndex = 0;
        int nextOpenBracket = 0;
        int nextCloseBracket = markdown.indexOf("]");
        int openParen = markdown.indexOf("(");
        int closeParen = 0;

        while(currentIndex < markdown.length()) {
            if (nextCloseBracket > openParen) break;
            nextOpenBracket = markdown.indexOf("[", currentIndex);
            nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            openParen = markdown.indexOf("(", nextCloseBracket);
            closeParen = markdown.indexOf(")", openParen);
            toReturn.add(markdown.substring(openParen + 1, closeParen));
            System.out.println("current index is :" + currentIndex);
            currentIndex = closeParen + 1;
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}