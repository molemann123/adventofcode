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

        // Split the input and insert them into the list.
        List<long[]> idRanges = new ArrayList<>();

        try (Stream<String> lines = Files.lines(inputPath)) {
            lines.forEach(line -> {
                String[] parts = line.split(",");
                for (String part : parts) {
                    part = part.trim();
                    if (!part.isEmpty()) {
                        String[] range = part.split("-");
                        if (range.length == 2) {
                            try {
                                long start = Long.parseLong(range[0].trim());
                                long end = Long.parseLong(range[1].trim());
                                idRanges.add(new long[]{start, end});
                            } catch (NumberFormatException e) {
                                logger.warn("Skipping invalid range: " + part, e);
                            }
                        }
                    }
                }
            });
        } catch (IOException e) {
            logger.error("Error reading file", e);
        }


        List<Long> results = new ArrayList<>();
        for (long[] range : idRanges) {
            long start = range[0];
            long end = range[1];
            for (long num = Long.parseLong(String.valueOf(start)); num <= Long.parseLong(String.valueOf(end)); num++) {
                String strNum = String.valueOf(num);
                if (strNum.length() % 2 == 0) {
                    int mid = strNum.length() / 2;
                    String firstHalf = strNum.substring(0, mid);
                    String secondHalf = strNum.substring(mid);

                    if (firstHalf.equals(secondHalf)) {
                        results.add(num);
                        logger.info(num + " is made by repeating " + firstHalf + " twice.");
                    }
                }
            }
        }


        Long sum = results.stream()
                .mapToLong(Long::longValue)
                .sum();

        logger.info(String.valueOf(sum));
    }

    // Result is 22062284697
}
