import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecondPuzzle {

    private static final Map<String, Integer> numberMap = new HashMap<>();
    static {
        numberMap.put("one", 1);
        numberMap.put("two", 2);
        numberMap.put("three", 3);
        numberMap.put("four", 4);
        numberMap.put("five", 5);
        numberMap.put("six", 6);
        numberMap.put("seven", 7);
        numberMap.put("eight", 8);
        numberMap.put("nine", 9);
    }

    public static void main(String[] args) {
        String inputFilePath = "src/outputFromSecondPuzzleHelper.txt"; // Replace with the actual input file path
        String outputFilePath = "src/final.txt"; // Replace with the actual output file path

        try {
            List<String> allLines = Files.readAllLines(Paths.get(inputFilePath));
            int sum = 0;

            for (String line : allLines) {
                int calibrationValue = findCalibrationValue(line);
                sum += calibrationValue;
                System.out.println("Line: " + line + ", Calibration Value: " + calibrationValue);
            }

            System.out.println("Sum of all calibration values: " + sum);

            // Write the sum to the output file
            saveSumToFile(outputFilePath, sum);
            System.out.println("Sum of all calibration values has been written to: " + outputFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int findCalibrationValue(String input) {
        // Replace spelled-out numbers with digits
        for (Map.Entry<String, Integer> entry : numberMap.entrySet()) {
            input = input.replaceAll("\\b" + entry.getKey() + "\\b", entry.getValue().toString());
        }

        // Find all the numeric digits in the string
        Pattern p = Pattern.compile("\\d");
        Matcher m = p.matcher(input);
        Integer firstDigit = null;
        Integer lastDigit = null;

        while (m.find()) {
            if (firstDigit == null) {
                firstDigit = Integer.parseInt(m.group());
            }
            lastDigit = Integer.parseInt(m.group());
        }

        // Calculate calibration value
        if (firstDigit != null && lastDigit != null) {
           /* if (firstDigit.equals(lastDigit)) {
                // Only one number was found in the string
                return firstDigit;
            } else {
                // Concatenate the first and last digits if they are different */
                return firstDigit * 10 + lastDigit;
            }

        return 0; // Return 0 if no numbers found
    }

    public static void saveSumToFile(String filePath, int sum) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Sum of calibration values: " + sum);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}