import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Day5 {

    // https://adventofcode.com/2025/day/5
    static Logger logger = LoggerFactory.getLogger(Day5.class);
    static Path inputPath = Paths.get("src/main/resources/Day5/PuzzleInput.txt");

    void main() {
        List<long[]> idRanges = new ArrayList<>();
        List<Long> availableIngredients = new ArrayList<>();

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
                        } else if (range.length == 1) {
                            try {
                                long value = Long.parseLong(range[0].trim());
                                availableIngredients.add(value);
                            } catch (NumberFormatException e) {
                                logger.warn("Skipping invalid value: " + part, e);
                            }
                        }
                    }
                }
            });
        } catch (IOException e) {
            logger.error("Error reading file", e);
        }


        Set<Long> freshIngredients = new LinkedHashSet<>();
        for (long id : availableIngredients) {
            for (long[] range : idRanges) {
                if (id >= range[0] && id <= range[1]) {
                    freshIngredients.add(id);
                    break;
                }
            }
        }

        logger.info("Fresh ingredients total {}", freshIngredients.size());

        // Part 2
        idRanges.sort(Comparator.comparingLong(a -> a[0]));

        long count = 0;
        long currentEnd = Long.MIN_VALUE;

        for (long[] range : idRanges) {
            long start = range[0];
            long end = range[1];

            if (start > currentEnd) {
                count += (end - start + 1);
                currentEnd = end;
            } else if (end > currentEnd) {
                count += (end - currentEnd);
                currentEnd = end;
            }
        }
        logger.info(String.valueOf(count));
    }
}
// Result 733
// Result 345821388687084
