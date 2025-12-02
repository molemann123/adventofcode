import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// https://adventofcode.com/2025/day/1
public class Day1 {
    static Logger logger = LoggerFactory.getLogger(Day1.class);
    static Path inputPath = Paths.get("src/main/resources/Day1/PuzzleInput.txt");
    static int dial = 50;
    static int password = 0;

    static void turnDial(char direction, int clicks) {
        if(direction == 'R') {
            dial += clicks;
        } else {
            dial -= clicks;
        }
        logger.info(String.valueOf(dial));
        dial = ((dial % 100) + 100) % 100;
        if(dial == 0) {
            password++;
        }
    }

    static void main(String[] args) {
        List<String> dialDirections = new ArrayList<>();

        try (Stream<String> lines = Files.lines(inputPath)) {
            lines
                    .forEach(dialDirections::add);
        } catch (IOException e) {
            logger.error("Error reading file", e);
        } catch (NumberFormatException e) {
            logger.error("Invalid number in file", e);
        }

        dialDirections.forEach(line -> {
            char direction = line.charAt(0);
            int clicks = Integer.parseInt(line.substring(1));
            turnDial(direction, clicks);

        });
        logger.info("Value of dial is {} and value of password is {}", dial, password);
    }
}
