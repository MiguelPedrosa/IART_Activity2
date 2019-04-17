package algorithm;

import gameElements.Board;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Algorithm {

    private Board initialBoard;
    private Board targetBoard;
    private PriorityQueue<Board> queue;
    private Comparator<Board> compareFunction;


    public Algorithm(Board initialBoard, Board targetBoard, Comparator<Board> costFunction, Comparator<Board> heuristicFunction) {
        if(initialBoard == null || targetBoard == null)
            throw new IllegalArgumentException("One of the boards was null");

        final Boolean heightCheck = initialBoard.getHeight() == targetBoard.getHeight();
        final Boolean widthCheck = initialBoard.getWidth() == targetBoard.getWidth();
        if(! (heightCheck && widthCheck))
            throw new IllegalArgumentException("Boards have different dimensions");

        this.initialBoard = initialBoard;
        this.targetBoard = targetBoard;
        this.compareFunction = (a, b) -> -1* (costFunction.compare(a,b) + heuristicFunction.compare(a, b));
        this.queue = new PriorityQueue<>(this.compareFunction);

    }

    public boolean run() {
        return solve(this.initialBoard);
    }

    private Boolean solve(Board analyseBoard) {
        if(analyseBoard.isEqual(targetBoard))
            return true;
        return false;
    }

    private class Node {
        public Node(Board board, int depth);

    }

}
