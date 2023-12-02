import java.io.*;

public class Main {
    public static void main(String[] args) {
        File filePath =  new File("src/input.txt");
        int sum = calculateCalibrationSumFromFile(filePath);
        System.out.println("Sum of calibration values: " + sum);

        String outputFilePath = "src/output.txt"; // Replace with the desired output file path
        saveSumToFile(outputFilePath, sum);
    }


    public static int calculateCalibrationSumFromFile(File filePath) {
        int sum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String firstDigit = line.replaceAll("[^0-9]", "").substring(0, 1);
                String lastDigit = line.replaceAll("[^0-9]", "").substring(line.replaceAll("[^0-9]", "").length() - 1);
                int calibrationValue = Integer.parseInt(firstDigit + lastDigit);
                //System.out.println("value" +calibrationValue);
                sum += calibrationValue;
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }

        return sum;
    }

    public static void saveSumToFile(String filePath, int sum) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Sum of calibration values: " + sum);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}
