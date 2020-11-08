package fhict.fontys.nl.domain;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class MinimaxTest {
    @Test
    void when_computerMakeMove_BoardPositionReturned() {
        TicTacToeBoard gameBoard = new TicTacToeBoard();
        Minimax computer = new Minimax(3, GameResult.X);
        BoardPosition boardPosition = computer.minimax(gameBoard, 3, 12);

        boolean isGreaterThanZero = false;

        // make sure the position is not 0
        if (boardPosition.row > 0 && boardPosition.column > 0) {
            isGreaterThanZero = true;
            System.out.println(boardPosition.row + " " + boardPosition.column);
        }

        assertNotNull(boardPosition);
        assertTrue(isGreaterThanZero);
    }

    @Test
    void playAGameWith2Minimax() {
        TicTacToeBoard gameBoard = new TicTacToeBoard();
        Minimax computer = new Minimax(3, GameResult.X);
        Minimax opponent = new Minimax(3, GameResult.O);
        BoardPosition boardPosition;
        boolean isOpponentTurn = true;
        boolean gameState = true;

        do {
            if (isOpponentTurn) {
                boardPosition = opponent.minimax(gameBoard, 3, 12);
            } else {
                // here minimax chooses its move
                boardPosition = computer.minimax(gameBoard, 3, 12);
            }

            // game will interupt if a player wins
            gameState = gameBoard.gameTurn(boardPosition.row, boardPosition.column, isOpponentTurn);
            isOpponentTurn = !isOpponentTurn;
        } while(gameState);
    }
}