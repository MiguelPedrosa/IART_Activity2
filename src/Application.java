import gameElements.*;
import algorithm.*;


public class Application {
    public static void main(String[] args) {

        int[][] arr9_1 = {
                {1, 2, 3},
                {5, 0, 6},
                {4, 7, 8}};

        int[][] arr9_2 = {
                {1, 3, 6},
                {5, 2, 0},
                {4, 7, 8}};

        int[][] arr9_3 = {
                {1, 6, 2},
                {5, 7, 3},
                {0, 4, 8}};

        int[][] arr16 = {
            {  5,  1,  3,  4},
            {  2,  0,  7,  8},
            { 10,  6, 11, 12},
            {  9, 13, 14, 15}};

        Board initial = new Board(arr16);
        Board target = generateDefaultTarget(initial);

        Algorithm algorithm = new Algorithm(initial, target, Costs::cost0, Heuristics::manhattanDistance);
        algorithm.run();
        algorithm.printSolution();

    }

    private static Board generateDefaultTarget(Board board) {
        final int height = board.getHeight();
        final int width = board.getWidth();

        int[][] array = new int[height][width];
        for(int hIterator = 0; hIterator < height; hIterator++)
            for(int wIterator = 0; wIterator < width; wIterator++)
                array[hIterator][wIterator] = hIterator * height + wIterator + 1;
        array[height-1][width-1] = 0;

        return new Board(array);
    }
}