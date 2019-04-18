package gameElements;

public class Board {
    final static int keyPieceValue = 0;
    private int width;
    private int height;
    private int[][] board;
    private int xKeyPiece;
    private int yKeyPiece;

    public Board(int[][] board) {
        this.board = board;
        this.width = this.board.length;
        this.height = this.board[0].length;
        initKeyPiece();
    }
    public Board(Board board) {
        this.width = board.width;
        this.height = board.height;
        this.xKeyPiece = board.xKeyPiece;
        this.yKeyPiece = board.yKeyPiece;

        this.board = new int[this.height][this.width];
        for(int yIterator = 0; yIterator < this.board.length; yIterator++)
            for(int xIterator = 0; xIterator < this.board[yIterator].length; xIterator++)
                this.board[yIterator][xIterator] = board.getBoard()[yIterator][xIterator];
    }

    public int getWidth() {
        return this.width;
    }
    public int getHeight() {
        return this.height;
    }
    public int[][] getBoard() {
        return this.board;
    }
    public void printBoard() {
        final int maxNumber = this.width * this.height;
        int maxDigitsPerNumber = 1;
        int maxNumberCopy = maxNumber;
        while(maxNumberCopy/10 > 0) {
            maxDigitsPerNumber++;
            maxNumberCopy /= 10;
        }
        for(int[] i : this.board) {
            for(int j : i) {
                System.out.format("|%0" + maxDigitsPerNumber + "d", j);
            }
            System.out.println("|");
        }
    }

    public boolean canMoveUp() {
        return this.yKeyPiece > 0;
    }
    public boolean canMoveDown() {
        return this.yKeyPiece < this.height -1;
    }
    public boolean canMoveLeft() {
        return this.xKeyPiece > 0;
    }
    public boolean canMoveRight() {
        return this.xKeyPiece < this.width -1;
    }

    public boolean moveUp() {
        final int newXposition = this.xKeyPiece;
        final int newYposition = this.yKeyPiece -1;

        try {
            swapPieces(xKeyPiece, yKeyPiece, newXposition, newYposition);
        }
        catch(Exception e) {
            System.err.println("Move up can't be done");
            return false;
        }

        this.xKeyPiece = newXposition;
        this.yKeyPiece = newYposition;

        return true;
    }
    public boolean moveDown() {
        final int newXposition = this.xKeyPiece;
        final int newYposition = this.yKeyPiece +1;

        try {
            swapPieces(xKeyPiece, yKeyPiece, newXposition, newYposition);
        }
        catch(Exception e) {
            System.err.println("Move down can't be done");
            return false;
        }

        this.xKeyPiece = newXposition;
        this.yKeyPiece = newYposition;

        return true;
    }
    public boolean moveLeft() {
        final int newXposition = this.xKeyPiece -1;
        final int newYposition = this.yKeyPiece;

        try {
            swapPieces(xKeyPiece, yKeyPiece, newXposition, newYposition);
        }
        catch(Exception e) {
            System.err.println("Move Left can't be done");
            return false;
        }

        this.xKeyPiece = newXposition;
        this.yKeyPiece = newYposition;

        return true;
    }
    public boolean moveRight() {
        final int newXposition = this.xKeyPiece +1;
        final int newYposition = this.yKeyPiece;

        try {
            swapPieces(xKeyPiece, yKeyPiece, newXposition, newYposition);
        }
        catch(Exception e) {
            System.err.println("Move right can't be done");
            return false;
        }

        this.xKeyPiece = newXposition;
        this.yKeyPiece = newYposition;

        return true;
    }

    public Boolean isEqual(Board board) {
        final int[][] board1 = this.getBoard();
        final int[][] board2 = board.getBoard();

        for(int yIterator = 0; yIterator < board1.length; yIterator++)
            for(int xIterator = 0; xIterator < board1[yIterator].length; xIterator++)
                if(board1[yIterator][xIterator] != board2[yIterator][xIterator])
                    return false;

        return true;
    }


    private void initKeyPiece() {
        Boolean keyPieceFound = false;
        for(int yIterator = 0; yIterator < this.height; yIterator++)
            for(int xIterator = 0; xIterator < this.width; xIterator++)
                if(this.board[yIterator][xIterator] == keyPieceValue) {
                    this.xKeyPiece = xIterator;
                    this.yKeyPiece = yIterator;
                    keyPieceFound = true;
                }
        if(! keyPieceFound)
            throw new IllegalArgumentException("Key piece " + keyPieceValue + " was not found in board");
    }

    private void swapPieces(int x1, int y1, int x2, int y2) {
        int temp = this.board[y1][x1];
        this.board[y1][x1] = this.board[y2][x2];
        this.board[y2][x2] = temp;
    }
}
