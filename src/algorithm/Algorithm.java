package algorithm;

import gameElements.Board;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;


public class Algorithm {

    static final int maxExplorationDepth = 20;
    private Board initialBoard;
    private Board targetBoard;
    private Comparator<Board> costFunction;
    private PriorityQueue<Node> unexploredNodes;
    private List<Node> alreadyExploredNodes;
    private Stack<String> solutionStack;

    public Algorithm(Board initialBoard, Board targetBoard, Comparator<Board> costFunction, Comparator<Board> heuristicFunction) {
        if(initialBoard == null || targetBoard == null)
            throw new IllegalArgumentException("One of the boards was null");

        final Boolean heightCheck = initialBoard.getHeight() == targetBoard.getHeight();
        final Boolean widthCheck = initialBoard.getWidth() == targetBoard.getWidth();
        if(! (heightCheck && widthCheck))
            throw new IllegalArgumentException("Boards have different dimensions");

        this.initialBoard = initialBoard;
        this.targetBoard = targetBoard;
        this.costFunction = costFunction;
        this.alreadyExploredNodes = new ArrayList<>();
        this.unexploredNodes = new PriorityQueue<>((Node a, Node b) -> {
            int aValue = a.getCost() + heuristicFunction.compare(a.getBoard(), this.targetBoard);
            int bValue = b.getCost() + heuristicFunction.compare(b.getBoard(), this.targetBoard);
            return aValue - bValue;});
        this.solutionStack = new Stack<>();
    }

    public void printSolution() {
        System.out.println("Number of seen nodes: " + alreadyExploredNodes.size());
        System.out.println("Number of created nodes: " + (alreadyExploredNodes.size() + unexploredNodes.size()));
        System.out.println("Number of moves: " + solutionStack.size());
        int i = 1;
        while(solutionStack.size() > 0) {
            System.out.println(i + ". " + solutionStack.pop());
            i++;
        }
    }

    public boolean run() {
        Node parentNode = new Node(new Board(this.initialBoard), null, "", 0, 0);
        return solve(parentNode);
    }
    private Boolean solve(Node explorationNode) {
        // Test if node is the target state
        if(explorationNode.getBoard().isEqual(targetBoard)) {
            System.out.println("Solution found!\n");
            grabSolution(explorationNode);
            return true;
        }

        // Add children Nodes
        addChildNodes(explorationNode);

        //Pop a node and explore
        Node newNode;
        do {
            newNode = unexploredNodes.poll();
            if(newNode == null)
                return false;
        }    
        while(newNode.getDepth() >= maxExplorationDepth);

        return solve(newNode);
    }

    private void grabSolution(Node explorationNode) {
        Node node = explorationNode;
        while(node != null) {
            solutionStack.push(node.getOperation());
            node = node.getParentNode();
        }
        solutionStack.pop();
    }

    private void addChildNodes(Node parentNode) {
        final int depth = parentNode.getDepth();
        final int cost = parentNode.getCost();

        if(parentNode.getBoard().canMoveUp()) {
            Board childBoard = new Board(parentNode.getBoard());
            childBoard.moveUp();
            final int newNodeCost = cost + this.costFunction.compare(initialBoard, childBoard);
            Node childNode = new Node(childBoard, parentNode, "move Up", depth +1,  newNodeCost);
            if(!alreadyExploredNodes.contains(childNode) && !unexploredNodes.contains(childNode)) {
                unexploredNodes.add(childNode);
                alreadyExploredNodes.add(childNode);
            }
        }
        if(parentNode.getBoard().canMoveDown()) {
            Board childBoard = new Board(parentNode.getBoard());
            childBoard.moveDown();
            final int newNodeCost = cost + this.costFunction.compare(initialBoard, childBoard);
            Node childNode = new Node(childBoard, parentNode, "move Down", depth +1,  newNodeCost);
            if(!alreadyExploredNodes.contains(childNode) && !unexploredNodes.contains(childNode)) {
                unexploredNodes.add(childNode);
                alreadyExploredNodes.add(childNode);
            }
        }
        if(parentNode.getBoard().canMoveRight()) {
            Board childBoard = new Board(parentNode.getBoard());
            childBoard.moveRight();
            final int newNodeCost = cost + this.costFunction.compare(initialBoard, childBoard);
            Node childNode = new Node(childBoard, parentNode, "move Right", depth +1,  newNodeCost);
            if(!alreadyExploredNodes.contains(childNode) && !unexploredNodes.contains(childNode)) {
                unexploredNodes.add(childNode);
                alreadyExploredNodes.add(childNode);
            }
        }
        if(parentNode.getBoard().canMoveLeft()) {
            Board childBoard = new Board(parentNode.getBoard());
            childBoard.moveLeft();
            final int newNodeCost = cost + this.costFunction.compare(initialBoard, childBoard);
            Node childNode = new Node(childBoard, parentNode, "move Left", depth +1,  newNodeCost);
            if(!alreadyExploredNodes.contains(childNode) && !unexploredNodes.contains(childNode)) {
                unexploredNodes.add(childNode);
                alreadyExploredNodes.add(childNode);
            }
        }

    }

    private class Node {
        private Board board;
        private Node parentNode;
        private String operation;
        private int depth;
        private int cost;

        public Node(Board board, Node parentNode, String operation, int depth, int cost) {
            this.board = board;
            this.parentNode = parentNode;
            this.operation = operation;
            this.depth = depth;
            this.cost = cost;
        }

        public Board getBoard() {
            return this.board;
        }
        public Node getParentNode() {
            return this.parentNode;
        }
        public String getOperation() {
            return this.operation;
        }
        public int getDepth() {
            return this.depth;
        }
        public int getCost() {
            return this.cost;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null)
                return false;
            if (getClass() != o.getClass())
                return false;
            Node node = (Node) o;
            return this.getBoard().isEqual(node.getBoard());
        }
    }

}
