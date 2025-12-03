import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Day3 {

    // https://adventofcode.com/2025/day/3
    static Logger logger = LoggerFactory.getLogger(Day3.class);
    static Path inputPath = Paths.get("src/main/resources/Day3/PuzzleInput.txt");
    int largest = Integer.MIN_VALUE;
    int secondLargest = Integer.MIN_VALUE;

    public static String getOrderedTopTwo(String str) {
        if (str == null || str.length() < 2) {
            return "Invalid Input";
        }

        int highest = -1;
        int secondHighest = -1;

        // We track where we found these numbers
        int highestIndex = -1;
        int secondHighestIndex = -1;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (Character.isDigit(c)) {
                int currentDigit = Character.getNumericValue(c);

                if (currentDigit > highest) {
                    // 1. Demote current highest to second place
                    secondHighest = highest;
                    secondHighestIndex = highestIndex; // It keeps its original index!

                    // 2. Set new highest
                    highest = currentDigit;
                    highestIndex = i; // Set new index

                } else if (currentDigit > secondHighest && currentDigit != highest) {
                    // Update second place only
                    secondHighest = currentDigit;
                    secondHighestIndex = i;
                }
            }
        }

        if (secondHighest == -1) {
            return "No second highest number found";
        }

        // Compare indices to decide print order
        if (secondHighestIndex < highestIndex) {
            // The second highest number appeared first (Left -> Right)
            return "" + secondHighest + highest;
        } else {
            // The highest number appeared first
            return "" + highest + secondHighest;
        }
    }


    void main() {

        // Find highest number
        // Find highest number that is < highest number after the highest number
        // I.e highestNumber = 9
        // nextHighestNumber = 7
        // So 76534753932365483
        // Highest number would be 9
        // Now the string looks like this 932365483
        // Now we find the next highest number which is 8.
        // The result is 98.

        // How do we find the highest number?

        List<String> joltageRatings = new ArrayList<>();

        try (Stream<String> lines = Files.lines(inputPath)) {
            lines
                    .forEach(joltageRatings::add);
        } catch (IOException e) {
            logger.error("Error reading file", e);
        } catch (NumberFormatException e) {
            logger.error("Invalid number in file", e);
        }
        joltageRatings.forEach(joltageRating -> {
            List<Integer> digits = new ArrayList<>();
            for (char c : joltageRating.toCharArray()) {
                digits.add(Character.getNumericValue(c));
            }
            logger.info("digits: {}", digits);
            digits.remove(digits.size() - 1);
            int maxValue = Collections.max(digits);
            logger.info("Max value in digits is {} and the index is {}", maxValue, digits.indexOf(maxValue));
            if (digits.indexOf(maxValue) == 0) {
                digits.remove(0);
            } else {
                digits.subList(0, maxValue).clear();
            }
            int newMaxValue = Collections.max(digits);
            logger.info("New max value in digits is {}", newMaxValue);
            logger.info("The total joltage is {}{}", maxValue, newMaxValue);
        });

    }
}