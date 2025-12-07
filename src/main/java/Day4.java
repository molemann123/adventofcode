import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day4 {

    // https://adventofcode.com/2025/day/3
    static Logger logger = LoggerFactory.getLogger(Day4.class);
    static Path inputPath = Paths.get("src/main/resources/Day4/PuzzleInput.txt");
    static char[][] grid;
    static int rows;
    static int cols;

    static void printGrid() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                System.out.print(grid[r][c] + "");
            }
            System.out.println();
        }
    }

    static void printGrid(int highlightRow, int highlightCol) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                if (r == highlightRow && c == highlightCol) {
                    System.out.print("[" + grid[r][c] + "]");
                } else {
                    System.out.print(" " + grid[r][c] + " ");
                }

            }
            System.out.println();
        }
    }

    static void printGrid(int hr1, int hc1, int hr2, int hc2) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                if (r == hr1 && c == hc1) {
                    System.out.print("[" + grid[r][c] + "]"); // primary highlight
                } else if (r == hr2 && c == hc2) {
                    System.out.print("(" + grid[r][c] + ")"); // secondary highlight
                } else {
                    System.out.print(" " + grid[r][c] + " ");
                }

            }
            System.out.println();
        }
    }


    static char[][] readGridFromFile() throws IOException {
        List<char[]> gridList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(inputPath.toFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                gridList.add(line.toCharArray());
            }
        }
        return gridList.toArray(new char[0][]);
    }


    // This was before my refactor :D Was fun to play around with the 2D grid.
//    static int checkDirections(int row, int col) {
//        int gridLength = grid.length;
//
//        int count = 0;
//        // Down
//        if (row != gridLength - 1 && grid[row + 1][col] == '@') {
//            count++;
//        }
//        // Up
//        if (row != 0 && grid[row - 1][col] == '@') {
//            count++;
//        }
//
//        //Up left
//        if (row != 0 && col != 0 && grid[row - 1][col - 1] == '@') {
//            count++;
//        }
//
//        // Left
//        if (col != 0 && grid[row][col - 1] == '@') {
//            count++;
//        }
//        //Left down
//        if (row != gridLength - 1 && col != 0 && grid[row + 1][col - 1] == '@') {
//            count++;
//        }
//
//        // Right
//        if (col != grid[row].length - 1 && grid[row][col + 1] == '@') {
//            count++;
//        }
//        //Up right
//        if (col != gridLength - 1 && row != 0 && grid[row - 1][col + 1] == '@') {
//            count++;
//        }
//
//        //Down right
//        if (row != gridLength - 1 && col != gridLength - 1 && grid[row + 1][col + 1] == '@') {
//            count++;
//        }
//        return count;
//    }

    static int checkDirections(int row, int col) {
        int count = 0;

        int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},  // Up-left, Up, Up-right
                {0, -1},          {0, 1},    // Left,      Right
                {1, -1}, {1, 0}, {1, 1}      // Down-left, Down, Down-right
        };

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                if (grid[newRow][newCol] == '@') {
                    count++;
                }
            }
        }

        return count;
    }

    void main() throws IOException {
        grid = readGridFromFile();
        rows = grid.length;
        cols = grid[0].length;
        int result = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == '@') {
                    if(checkDirections(row, col) < 4) {
                        result++;
                    }
                }
            }
        }

        logger.info("Result: " + result);
    }
    // Result is 1489
}