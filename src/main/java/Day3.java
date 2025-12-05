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

    public static int findMaximumJoltage(List<Integer> digits) {
        int lastValue = digits.getLast();
        int highestV = Collections.max(digits);
        if(lastValue == highestV) {
            digits.remove(digits.indexOf(lastValue));
            int highestValue = Collections.max(digits);
            return Integer.parseInt(highestValue + "" + lastValue);
        } else {
            int highestValue = Collections.max(digits);
            int highestValueIndex = digits.indexOf(highestValue) + 1;
            digits.subList(0, highestValueIndex).clear();
            int newHighestValue = Collections.max(digits);
            return Integer.parseInt(highestValue + "" + newHighestValue);
        }
    }

    void main() {
        List<String> joltageRatings = new ArrayList<>();

        try (Stream<String> lines = Files.lines(inputPath)) {
            lines
                    .forEach(joltageRatings::add);
        } catch (IOException e) {
            logger.error("Error reading file", e);
        } catch (NumberFormatException e) {
            logger.error("Invalid number in file", e);
        }
        List<Integer> totalResult = new ArrayList<>();
        joltageRatings.forEach(joltageRating -> {
            List<Integer> digits = new ArrayList<>();
            for (char c : joltageRating.toCharArray()) {
                digits.add(Character.getNumericValue(c));
            }
            totalResult.add(findMaximumJoltage(digits));

        });
        int sum = totalResult.stream()
                .mapToInt(Integer::intValue)
                .sum();
        logger.info(String.valueOf(sum));
    }
    // Result was 17034
}