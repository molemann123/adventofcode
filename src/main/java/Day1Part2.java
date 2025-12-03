import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day1Part2 {
    static Logger logger = LoggerFactory.getLogger(Day1Part2.class);
    static Path inputPath = Paths.get("src/main/resources/Day1/PuzzleInput.txt");

    static int dial = 50;
    static long password = 0;
    static final int MAX_POSITION = 100;

    static void turnDial(char direction, int clicks) {
        int start = dial;

        if (direction == 'R') {
            int clicksToFirstZero = MAX_POSITION - start;

            if (clicks >= clicksToFirstZero) {
                password++;
                int clicksRemaining = clicks - clicksToFirstZero;
                password += clicksRemaining / MAX_POSITION;
            }

            dial = (start + clicks) % MAX_POSITION;

        } else {
            int clicksToFirstZero = (start == 0) ? MAX_POSITION : start;

            if (clicks >= clicksToFirstZero) {
                password++;
                int clicksRemaining = clicks - clicksToFirstZero;
                password += clicksRemaining / MAX_POSITION;
            }

            dial = ((start - clicks) % MAX_POSITION + MAX_POSITION) % MAX_POSITION;
        }
    }

     static void main(String[] args) {
        List<String> dialRotations = new ArrayList<>();

        try (Stream<String> lines = Files.lines(inputPath)) {
            lines
                    .filter(line -> !line.trim().isEmpty())
                    .forEach(dialRotations::add);
        } catch (IOException e) {
            logger.error("Error reading file: " + e.getMessage());
            return;
        }

        dialRotations.forEach(line -> {
            try {
                char direction = line.charAt(0);
                int clicks = Integer.parseInt(line.substring(1));
                turnDial(direction, clicks);
            } catch (Exception e) {
                logger.error("Error processing line: " + line);
            }
        });

        logger.info("Value of dial is %d and password is {} {}", dial, password);
    }
}