import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day3 {

    // https://adventofcode.com/2025/day/3
    static Logger logger = LoggerFactory.getLogger(Day3.class);
    static Path inputPath = Paths.get("src/main/resources/Day3/PuzzleInput.txt");

    static int findMaximumJoltage(List<Integer> digits) {
        int lastValue = digits.getLast();
        int highestV = Collections.max(digits);
        if (lastValue == highestV) {
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
        int sum = 0;

        try (Stream<String> lines = Files.lines(inputPath)) {
            sum = lines
                    .map(line -> line.chars()
                            .map(Character::getNumericValue)
                            .boxed()
                            .collect(Collectors.toList()))
                    .mapToInt(Day3::findMaximumJoltage)
                    .sum();

        } catch (IOException | NumberFormatException e) {
            logger.error("Error reading file", e);
        }
        logger.info(String.valueOf(sum));
    }

    // Result was 17034
}