package fhict.fontys.nl.domain;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeBoardTest {
    final int boardSize = 3;
    TicTacToeBoard gameBoard = new TicTacToeBoard(boardSize);

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @BeforeEach
    void init() {
        // Get some value inside game board
        gameBoard.playAtCell(1,2,true);
        gameBoard.playAtCell(2,2,true);
    }

    @Test
    void boardSizeTest() {
        assertEquals(gameBoard.getBoardSize(), boardSize);
    }

    @Test
    void displayBoardTest() {
        gameBoard.displayBoard();
    }

    @Test
    void when_validPosition_playAtCellReturnTrue() {
        boolean humanSymbol = true;
        boolean computerSymbol = false;

        boolean move1 = gameBoard.playAtCell(3, 3, humanSymbol); // human play his turn
        boolean move2 = gameBoard.playAtCell(3, 2, computerSymbol); // computer play his turn
        gameBoard.displayBoard();
        assertTrue(move1);
        assertTrue(move2);
    }

    @Test
    void when_giveInvalidPosition_returnFalse_andDontDisplayMove() {
        boolean humanSymbol = true;
        boolean computerSymbol = false;

        boolean move1 = gameBoard.playAtCell(6, 3, humanSymbol); // human play his turn
        gameBoard.displayBoard();
        assertTrue(!move1);
    }

    @Test
    void Should_ReturnFalse_When_PlayOnAlreadySetBoardCell() {
        boolean expectedResult = false;

        // play on already set cell, should throw exception
        boolean result = gameBoard.playAtCell(2,2,true);
        gameBoard.displayBoard();
        assertEquals(expectedResult, result);
    }

    @Test
    void checkWinner_With_X_And_O() {
        boolean humanSymbol = true;
        boolean computerSymbol = false;

        printWinner(gameBoard.checkWinner(gameBoard));

        // turn 2. turn 1 is played in BeforeEach function
        gameBoard.playAtCell(3, 3, humanSymbol);
        gameBoard.playAtCell(3, 1, computerSymbol);
        gameBoard.displayBoard();
        printWinner(gameBoard.checkWinner(gameBoard));

        // turn 3
        gameBoard.playAtCell(1, 1, humanSymbol);
        gameBoard.playAtCell(1, 3, computerSymbol);
        gameBoard.displayBoard();
        printWinner(gameBoard.checkWinner(gameBoard));
    }

    @Test
    void checkWinner_With_Tie() {
        boolean humanSymbol = true;
        boolean computerSymbol = false;

        printWinner(gameBoard.checkWinner(gameBoard));

        // turn 2. turn 1 is played in BeforeEach function
        gameBoard.playAtCell(3, 3, humanSymbol);
        gameBoard.playAtCell(3, 1, computerSymbol);
        gameBoard.displayBoard();
        printWinner(gameBoard.checkWinner(gameBoard));

        // turn 3
        gameBoard.playAtCell(1, 1, computerSymbol);
        gameBoard.playAtCell(1, 3, humanSymbol);
        gameBoard.playAtCell(2, 3, computerSymbol);
        gameBoard.playAtCell(2, 1, humanSymbol);
        gameBoard.playAtCell(3, 2, computerSymbol);
        gameBoard.displayBoard();
        printWinner(gameBoard.checkWinner(gameBoard));
    }

    private boolean printWinner(GameResult winner) {
        if (winner != GameResult.Playing) {
            if (winner == GameResult.O)
                System.out.println("Human Won! The winner is " + winner.name());
            else if (winner == GameResult.X)
                System.out.println("Computer Won! The winner is " + winner.name());
            else
                System.out.println(winner.name() + "! Bad Luck!\nTry next time");
            return true;
        }
        else {
            System.out.println("No winner at the moment, continue to play!");
            return false;
        }
    }

    @Test
    void gameTurnTest() {
        gameBoard.initializeCleanBoard();

        BoardPosition[] humanMoves = { new BoardPosition(1,1),
                                       new BoardPosition(3, 3),
                                       new BoardPosition(2, 2)};

        BoardPosition[] machineMoves = { new BoardPosition(1,3),
                new BoardPosition(3, 2),
                new BoardPosition(2, 3)};

        for (int i = 0; i < 4; i++) {
            if (!gameBoard.gameTurn(machineMoves[i].row, machineMoves[i].column, false))
                break;

            if (!gameBoard.gameTurn(humanMoves[i].row, humanMoves[i].column, true))
                break;

        }
    }
}