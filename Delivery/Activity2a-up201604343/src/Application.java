import gameElements.*;
import algorithm.*;


public class Application {
    public static void main(String[] args) {

        if(! areArgumentsValid(args)) {
            return;
        }

        Board initialBoard = getBoards()[Integer.parseInt(args[0]) -1];
        System.out.println("Inital board:");
        initialBoard.printBoard();
        Board targetBoard = generateDefaultTarget(initialBoard);
        System.out.println("\nTarget board:");
        targetBoard.printBoard();

        Algorithm algorithm;
        switch(Integer.parseInt(args[1])) {
            // Uniform cost / BFS
            case(1):
                algorithm = new Algorithm(initialBoard, targetBoard, Costs::costToMove, Heuristics::value0);
                break;
            // Greedy with Heuristic 1
            case(2):
                algorithm = new Algorithm(initialBoard, targetBoard, Costs::cost0, Heuristics::outOfPlacePieces);
                break;
            // Greedy with Heuristic 2
            case(3):
                algorithm = new Algorithm(initialBoard, targetBoard, Costs::cost0, Heuristics::manhattanDistance);
                break;
            // A star with Heuristic 1
            case(4):
                algorithm = new Algorithm(initialBoard, targetBoard, Costs::costToMove, Heuristics::outOfPlacePieces);
                break;
            // A star with Heuristic 2
            case(5):
                algorithm = new Algorithm(initialBoard, targetBoard, Costs::costToMove, Heuristics::manhattanDistance);
                break;
            default:
                System.err.println("Invalid Number selection");
                return;
        }

        long startTime = System.nanoTime();
        algorithm.run();
        long endTime = System.nanoTime();

        System.out.println("Time taken = " + ((double) (endTime - startTime)/1000000000));
        algorithm.printSolution();
    }

    private static boolean areArgumentsValid(String[] args) {
        if(args.length != 2) {
            System.err.println("Expected format: java Application <board: 1-4> <Algorithm number>");
            System.err.println("Algorithm numbers:");
            System.err.println("\t1 - Uniform Cost");
            System.err.println("\t2 - Greedy with 'out of place pieces'");
            System.err.println("\t3 - Greedy with 'Manhattan Distance'");
            System.err.println("\t4 - A* with out of place pieces");
            System.err.println("\t5 - A* with 'Manhattan Distance'");
            return false;
        }
        else
            return true;
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

    private static Board[] getBoards() {
        int[][] board9_1_arr = {
            {1, 2, 3},
            {5, 0, 6},
            {4, 7, 8}};
        Board board9_1 = new Board(board9_1_arr);

        int[][] board9_2_Arr = {
            {1, 3, 6},
            {5, 2, 0},
            {4, 7, 8}};
        Board board9_2 = new Board(board9_2_Arr);

        int[][] board9_3_arr = {
            {1, 6, 2},
            {5, 7, 3},
            {0, 4, 8}};
        Board board9_3 = new Board(board9_3_arr);

        int[][] board16_1_arr = {
            {  5,  1,  3,  4},
            {  2,  0,  7,  8},
            { 10,  6, 11, 12},
            {  9, 13, 14, 15}};
        Board board16_1 = new Board(board16_1_arr);

        Board boards[] = {board9_1, board9_2, board9_3, board16_1}; 
        return boards;
    }

}