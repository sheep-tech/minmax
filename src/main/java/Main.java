import fhict.fontys.nl.domain.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner( System.in );
        TicTacToeBoard gameBoard = new TicTacToeBoard();
        Minimax computer = new Minimax(3, GameResult.X);
        BoardPosition boardPosition;
        int row, col;
        boolean isHumanTurn = true;
        boolean gameState;

        do {
            if (isHumanTurn) {
                System.out.print( "Type row number: " );
                row = scanner.nextInt();
                System.out.print( "Type col number: " );
                col = scanner.nextInt();

                boardPosition = new BoardPosition(row, col);
            }
            else {
                // here minimax chooses its move
                boardPosition = computer.minimax(gameBoard, 3, 12);
            }

            // game will interupt if a player wins
            gameState = gameBoard.gameTurn(boardPosition.row, boardPosition.column, isHumanTurn);
            isHumanTurn = !isHumanTurn;
        } while(gameState);
    }
}
