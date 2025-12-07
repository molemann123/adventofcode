import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Day6 {

    // https://adventofcode.com/2025/day/6
    static Logger logger = LoggerFactory.getLogger(Day6.class);
    static Path inputPath = Paths.get("src/main/resources/Day6/PuzzleInput.txt");

    static List<List<String>> readColumnsFromFile() throws IOException {
        List<List<String>> columns = new ArrayList<>();
        List<String[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputPath.toFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.trim().split("\\s+");
                rows.add(parts);
            }
        }

        if (rows.isEmpty()) return columns;

        int numCols = rows.getFirst().length;
        for (int i = 0; i < numCols; i++) {
            columns.add(new ArrayList<>());
        }

        for (String[] row : rows) {
            for (int i = 0; i < row.length; i++) {
                columns.get(i).add(row[i]);
            }
        }

        return columns;
    }

    String buildEquation(List<String> columns) {
        StringBuilder builder = new StringBuilder();

        columns.forEach(equation -> {
            if (columns.getLast().equals("*")) {
                if (!Objects.equals(equation, "*")) {
                    builder.append(equation).append("*");
                }
            } else {
                if (!Objects.equals(equation, "+")) {
                    builder.append(equation).append("+");
                }
            }

        });

        return builder.toString();
    }

    long getEquationResult(String equation) {
        boolean multiply = equation.endsWith("*");
        if (equation.endsWith("*") || equation.endsWith("+")) {
            equation = equation.substring(0, equation.length() - 1);
        }

        logger.info("Equation: " + equation);

        String[] numbers = equation.split("[*+]");
        long result = multiply ? 1 : 0;
        for (String num : numbers) {
            if (multiply) {
                result *= Integer.parseInt(num);
            } else {
                result += Integer.parseInt(num);
            }
        }

        logger.info("Result: " + result);
        return result;
    }

    void main() throws IOException {
        List<List<String>> columns = readColumnsFromFile();
        List<Long> results = new ArrayList<>();

        columns.forEach(column -> {
            column.forEach(equation -> {
               logger.info("Equation: " + equation);
            });

        });

    }
    // Result is 4412382293768
}