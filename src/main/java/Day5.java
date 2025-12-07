import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Day5 {

    // https://adventofcode.com/2025/day/3
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

                        // A range like "3-5"
                        if (range.length == 2) {
                            try {
                                long start = Long.parseLong(range[0].trim());
                                long end = Long.parseLong(range[1].trim());
                                idRanges.add(new long[]{start, end});
                            } catch (NumberFormatException e) {
                                logger.warn("Skipping invalid range: " + part, e);
                            }
                        }
                        // A single number like "8"
                        else if (range.length == 1) {
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
    }
}
// Result 733
