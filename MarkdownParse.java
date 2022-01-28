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

/*Test Case 2
Test Case 2 is failing because it is returning an incorrect output
Although it has the correct syntax for a link, it is referencing an image with a .png
extension which shouldn't be included in our ouptu
Try to create an array that contains all possible image extensions
Iterate through thatt array to check if the links contains one of those extensions
If it does, don't include that in our output, else add it to our array list
 */

/*Test Case 3
Test Case 3 returns an incorrect password
 */
public class MarkdownParse {
    static String[] imageExtensions = {".png", ".jpeg", ".gif", ".csv", ".jpg", ".svg", ".pdf"};

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        // if(!(markdown.indexOf("[") < markdown.indexOf("]") && markdown.indexOf("]")< markdown.indexOf("(") && markdown.indexOf("(") < markdown.indexOf(")"))){ very clean code 100% (LOL)
        int currentIndex = 0;
        int nextOpenBracket = 0;
        int nextCloseBracket = markdown.indexOf("]");
        int openParen = markdown.indexOf("(");
        int closeParen = 0;
        while(currentIndex < markdown.length()) {
            if (nextCloseBracket > openParen) break;
            nextOpenBracket = markdown.indexOf("[", currentIndex);
            
            //checks if its an image instead of a link
            if(markdown.contains("!")){
                int exclamation = markdown.indexOf("!");
                if(exclamation == nextOpenBracket-1){
                    break;
                }
            }

            nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            openParen = markdown.indexOf("(", nextCloseBracket);
            closeParen = markdown.indexOf(")", openParen);

            if (!checkExtension(markdown.substring(openParen +1, closeParen)) && openParen-nextCloseBracket == 1){
                toReturn.add(markdown.substring(openParen + 1, closeParen));
            }
            currentIndex = closeParen + 1;
        }
        return toReturn;
    }

    public static boolean checkExtension(String substring) {
        for (int i = 0; i < imageExtensions.length; ++i) {
            if (substring.contains(imageExtensions[i])) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}