import gameElements.*;
import algorithm.*;


public class Application {
    public static void main(String[] args) {

        int[][] arr9 = {
                {1, 2, 3},
                {8, 0, 4},
                {7, 6, 5}};

        int[][] arr16 = {
            { 1,  2,  3,  4 },
            { 5,  6,  7,  8 },
            { 9, 10, 11, 12 },
            {13, 14, 15,  0 }};

        Board initial = new Board(arr9);

        Board target = generateDefaultTarget(initial);
        target.printBoard();

        Algorithm algo = new Algorithm(initial, target, Costs::cost0, Heuristics::outOfPlacePieces);

        System.out.println("Num of pieces = " + algo.run());

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