import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

// https://adventofcode.com/2019/day/1
public class Day2 {
    static Logger logger = LoggerFactory.getLogger(Day2.class);
    static Path inputPath = Paths.get("src/main/resources/Day2/PuzzleInput.txt");

    static void main(String[] args) {
        Map<String, String> idRanges = new HashMap<>();


        // Split the input and insert them into map. Order doesn't matter in this case.
        try (Stream<String> lines = Files.lines(inputPath)) {
            lines.forEach(line -> {
                String[] parts = line.split(",");
                for (String part : parts) {
                    part = part.trim();
                    if (!part.isEmpty()) {
                        String[] range = part.split("-");
                        if (range.length == 2) {
                            idRanges.put(range[0], range[1]);
                        }
                    }
                }
            });
        } catch (IOException e) {
            logger.error("Error reading file", e);
        }

        List<Long> results = new ArrayList<>();
        idRanges.forEach((start, end) -> {
            logger.info("Going from {} to {}", start, end);
            for (long num = Long.parseLong(start); num <= Long.parseLong(end); num++) {
                String strNum = String.valueOf(num);
                if (strNum.length() % 2 == 0) {
                    int mid = strNum.length() / 2;
                    String firstHalf = strNum.substring(0, mid);
                    String secondHalf = strNum.substring(mid);

                    if (firstHalf.equals(secondHalf)) {
                        results.add(num);
                    }
                }
            }
        });
        Long sum = results.stream()
                .mapToLong(Long::longValue)
                .sum();
        logger.info(String.valueOf(sum));

    }

    // Result is 22062284697
}
