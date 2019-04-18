package algorithm;

import gameElements.Board;

public class Heuristics {

    public static int value0(Board a, Board b) {
        return 0;
    }

    public static int outOfPlacePieces(Board a, Board b) {
        int differences = 0;
        for(int y = 0; y < a.getHeight(); y++)
            for(int x = 0; x < a.getBoard()[y].length; x++)
                if(a.getBoard()[y][x] != b.getBoard()[y][x])
                    differences++;
        return differences;
    }

    public static int euclideanDistance(Board a, Board b) {
        int[][] aBoard = a.getBoard();
        int[][] bBoard = b.getBoard();
        double distance = 0;
        for(int aY = 0; aY < aBoard.length; aY++)
            for(int aX = 0; aX < aBoard[aY].length; aX++) {
                final int searchNumber = aBoard[aY][aX];
                int bX = 0;
                int bY = 0;
                // Find piece in board b;
                for(int yIterator = 0; yIterator < bBoard.length; yIterator++)
                    for(int xIterator = 0; xIterator < aBoard[yIterator].length; xIterator++)
                        if(bBoard[yIterator][xIterator] == searchNumber) {
                            bX = xIterator;
                            bY = yIterator;
                        }
                distance += pythagoras(aX, bX, aY, bY);
            }
        return (int) distance;
    }

    public static int manhattanDistance(Board a, Board b) {
        int[][] aBoard = a.getBoard();
        int[][] bBoard = b.getBoard();
        double distance = 0;
        for(int aY = 0; aY < aBoard.length; aY++)
            for(int aX = 0; aX < aBoard[aY].length; aX++) {
                final int searchNumber = aBoard[aY][aX];
                int bX = 0;
                int bY = 0;
                // Find piece in board b;
                for(int yIterator = 0; yIterator < bBoard.length; yIterator++)
                    for(int xIterator = 0; xIterator < aBoard[yIterator].length; xIterator++)
                        if(bBoard[yIterator][xIterator] == searchNumber) {
                            bX = xIterator;
                            bY = yIterator;
                        }
                distance += Math.abs(aX - bX) + Math.abs(aY - bY);
            }
        return distance;
    }

    private static double pythagoras(int x1, int x2, int y1, int y2) {
        final double leg1 = Math.pow(x1 - x2, 2);
        final double leg2 = Math.pow(y1 - y2, 2);
        final double hypotenuse = Math.sqrt(leg1 + leg2);
        return hypotenuse;
    }
}
