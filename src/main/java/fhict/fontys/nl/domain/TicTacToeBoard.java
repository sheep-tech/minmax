package fhict.fontys.nl.domain;

public class TicTacToeBoard implements IPlayerController {
    private Cell[][] board;
    private static int boardSize = 3;
    private static int availableCells;

    public TicTacToeBoard() {
        initialize();
    }

    public TicTacToeBoard(int newBoardSize) {
        if (newBoardSize > 0) {
            boardSize = newBoardSize;
        }
        initialize();
    }

    private void initialize() {
        this.board = new Cell[boardSize][boardSize];
        availableCells = boardSize*boardSize;

        // initialize each cell on board
        this.initializeCleanBoard();
    }

    public boolean gameTurn(int row, int col, boolean isHumanPlaying) {
        if (isHumanPlaying)
            playAtCell(row, col, true);
        else
            playAtCell(row, col, false);

        displayBoard();

        if(printWinnerName(checkWinner(this)) || availableCells == 0)
            return false;

        return true;
    }

    private boolean printWinnerName(GameResult winner) {
        if (winner != GameResult.Playing) {
            if (winner == GameResult.O)
                System.out.println("The winner is " + winner.name());
            else if (winner == GameResult.X)
                System.out.println("The winner is " + winner.name());
            else
                System.out.println(winner.name() + "! Bad Luck!\nTry next time");
            return true;
        }
        else {
            System.out.println("No winner at the moment, continue to play!");
            return false;
        }
    }

    public void initializeCleanBoard() {
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                board[i][j] = new Cell();
            }
        };
    }

    //1:O -1:X 0: tie
    public static GameResult checkWinner(TicTacToeBoard GameBoard) {
        Cell[][] board = GameBoard.getBoard();

        // check if a player lined 3 symbols
        for (int i = 0; i < boardSize; i++) {
            // rows
            if (equals(board[i][0], board[i][1], board[i][2])) {
//                System.out.println(i+" row");
//                System.out.flush();
                return Cell.symbolToPlayer(board[i][0].getSymbol());
            }

            // columns
            if (equals(board[0][i], board[1][i], board[2][i])) {
//                System.out.println(i +" column");
//                System.out.flush();
                return Cell.symbolToPlayer(board[0][i].getSymbol());
            }
        }

        // diagonals
        if (equals(board[0][0], board[1][1], board[2][2])) {
//            System.out.println("1 diagonal");
//            System.out.flush();
            return Cell.symbolToPlayer(board[0][0].getSymbol());
        }

        if (equals(board[0][2], board[1][1], board[2][0])) {
//            System.out.println("2 diagonal");
//            System.out.flush();
            return Cell.symbolToPlayer(board[1][1].getSymbol());
        }

        // If some cells are empty, it's not a tie
        if (availableCells > 0)
            return GameResult.Playing;
        else
            return GameResult.Tie;
    }

    private static boolean equals(Cell cell1, Cell cell2,Cell cell3) {
        // cells should not be empty
        if (!cell1.isEmpty() && !cell2.isEmpty() && !cell3.isEmpty()) {
            if (cell1.getSymbol() == cell2.getSymbol() && cell2.getSymbol() == cell3.getSymbol())
                return true;
        }
        return false;
    }

    @Override
    public boolean playAtCell(int row, int col, boolean symbol) {
        if (row > boardSize+1 || row < 1 || col > boardSize+1 || col < 1 ) {
            return false;
        }

        if (this.board[row-1][col-1].isEmpty()) {
            this.board[row-1][col-1].setSymbol(symbol);
            availableCells--;
            return true;
        }

        return false;
    }

    // this method won't minus available cells to play
    @Override
    public boolean playAtCell(int row, int col, boolean symbol, boolean bot) {

        if (this.board[row-1][col-1].isEmpty()) {
            this.board[row-1][col-1].setSymbol(symbol);
            return true;
        }

        return false;
    }

    public boolean resetCell(int row, int col) {
        return this.board[row-1][col-1].reset();
    }

    @Override
    public void displayBoard() {
        int padding = 10;

        System.out.println();

        // title
        printPadding(padding-1);
        System.out.println("Tic Tac Toe Game Board");
        System.out.println();

        // column number
        printPadding(padding);
        System.out.print(" \t");
        for (int j = 0; j < this.boardSize; j++) {
            System.out.print((j+1) + "\t");
        }
        System.out.println();

        // game board
        for (int i = 0; i < this.boardSize; i++) {
            printPadding(padding);

            for (int j = 0; j < this.boardSize; j++) {
                // row number
                if(j==0)
                    System.out.print((i+1) + "\t");

                if (!this.board[i][j].isEmpty()) {
                    if (this.board[i][j].getSymbol())
                        System.out.print("O\t");
                    else
                        System.out.print("X\t");
                }
                else
                    System.out.print("_\t");
            }
            System.out.println();
        }
    }

    private void printPadding(int paddingValue) {
        for (int j = 0; j < paddingValue; j++) {
            System.out.print("\t");
        }
    }

    public Cell[][] getBoard() {
        return board;
    }

    public static int getBoardSize() {
        return boardSize;
    }
}
