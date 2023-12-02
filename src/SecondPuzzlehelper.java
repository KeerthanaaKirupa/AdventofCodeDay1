import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SecondPuzzlehelper {

    public static void main(String[] args) {
        String inputFilePath = "src/input.txt"; // Replace with your input file path
        String outputFilePath = "src/outputFromSecondPuzzleHelper.txt"; // Replace with your output file path

        // Order the search list from most specific to least specific
        String[] searchList = new String[]{"oneight", "twone", "eighthree", "eightwo", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String[] replacementList = new String[]{"18", "21", "83", "82", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        try {
            // Read the content of the file into a string
            String content = new String(Files.readAllBytes(Paths.get(inputFilePath)));

            // Replace words with numbers
            String replacedContent = replaceWordsWithNumbers(content, searchList, replacementList);

            // Write the modified content back to the file or to a new file
            Files.write(Paths.get(outputFilePath), replacedContent.getBytes());

            System.out.println("The file has been processed and the output is saved to: " + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String replaceWordsWithNumbers(String input, String[] searchList, String[] replacementList) {
        // Perform the replacements in the order provided
        for (int i = 0; i < searchList.length; i++) {
            input = input.replaceAll(searchList[i], replacementList[i]);
        }
        return input;
    }
}