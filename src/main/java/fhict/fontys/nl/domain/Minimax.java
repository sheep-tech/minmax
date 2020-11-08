package fhict.fontys.nl.domain;

public class Minimax {
    private int boardSize;
    private GameResult symbol;
    private boolean symbolValue;

    public Minimax(int boardSize, GameResult symbol) {
        this.boardSize = boardSize;
        setSymbol(symbol);
    }

    public BoardPosition minimax(TicTacToeBoard board, int boardSize, int depth) {
        this.boardSize = boardSize;

        int bestScore = Integer.MIN_VALUE;
        BoardPosition bestPosition = new BoardPosition(1, 1);
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                // check if spot is empty
                if (board.getBoard()[i][j].isEmpty()) {
                    // Machine turn
                    board.playAtCell(i+1, j+1, this.symbolValue, true);
                    int score = recursiveMinimax(board, depth-1, Integer.MIN_VALUE, Integer.MAX_VALUE,false);
                    board.resetCell(i+1, j+1);

                    if (score > bestScore) {
                        bestScore = score;
                        bestPosition = new BoardPosition(i+1, j+1);
                    }
                }
            }
        }

        return bestPosition;
    }

    public int recursiveMinimax(TicTacToeBoard board, int depth, int alpha,int beta, boolean isMaximizing) {
        // evaluate this state's score
        int result = evaluateBoardScore(board);

        if (result != Integer.MIN_VALUE || depth == 0) {
            return result;
        }

        int bestScore;
        // machine
        if (isMaximizing) {
            bestScore = max(board, depth, alpha, beta);
        }
        // opponent
        else {
            bestScore = min(board, depth, alpha, beta);
        }

        return bestScore;
    }

    private int max(TicTacToeBoard board, int depth, int alpha,int beta) {
        int bestScore = Integer.MIN_VALUE;
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                // check if spot is empty
                if (board.getBoard()[i][j].isEmpty()) {
                    // Machine turn
                    board.playAtCell(i+1, j+1, this.symbolValue, true);
                    int score = recursiveMinimax(board, depth-1, alpha, beta,false);
                    board.resetCell(i+1, j+1);

                    bestScore = Integer.max(score, bestScore);
                    alpha = Integer.max(alpha, score);

                    if (beta <= alpha)
                        break;
                }
            }
        }

        return bestScore;
    }

    private int min(TicTacToeBoard board, int depth, int alpha,int beta) {
        int bestScore = Integer.MAX_VALUE;
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                // check if spot is empty
                if (board.getBoard()[i][j].isEmpty()) {
                    // opponent turn, so flip symbol value
                    board.playAtCell(i+1, j+1, !this.symbolValue, true);
                    int score = recursiveMinimax(board, depth-1, alpha, beta,true);
                    board.resetCell(i+1, j+1);

                    bestScore = Integer.min(score, bestScore);
                    beta = Integer.min(beta, score);

                    if (beta <= alpha)
                        break;
                }
            }
        }

        return bestScore;
    }

    public int evaluateBoardScore(TicTacToeBoard board) {
        int boardScore = 0;

        GameResult boardResult =  TicTacToeBoard.checkWinner(board);

        switch (boardResult) {
            case Tie:
                boardScore = 0;
                break;

            case Playing:
                boardScore = Integer.MIN_VALUE;
                break;

            default:
                if (boardResult == this.symbol)
                    boardScore = 100;
                else
                    boardScore = -100;
                break;
        }

        return boardScore;
    }

    private int evaluateLineScore(Cell cell1, Cell cell2, Cell cell3) {
        int lineScore = 0;
        // a line can be row, column and diagonal. In tot 8 possibilities

        // cells should not be empty
        if (!cell1.isEmpty() && !cell2.isEmpty() && !cell3.isEmpty()) {
            if (cell1.getSymbol() == cell2.getSymbol() && cell2.getSymbol() == cell3.getSymbol())
                lineScore += 100;
        }
        return 0;
    }

    private static boolean equals(Cell cell1, Cell cell2,Cell cell3) {
        // cells should not be empty
        if (!cell1.isEmpty() && !cell2.isEmpty() && !cell3.isEmpty()) {
            if (cell1.getSymbol() == cell2.getSymbol() && cell2.getSymbol() == cell3.getSymbol())
                return true;
        }
        return false;
    }

    private void setSymbol(GameResult symbol) {
        // symbol must be a X or O
        if (symbol != GameResult.Tie && symbol != GameResult.Playing) {
            this.symbol = symbol;
            if (this.symbol == GameResult.O)
                this.symbolValue = true;
            else
                this.symbolValue = false;
        }
    }
}
