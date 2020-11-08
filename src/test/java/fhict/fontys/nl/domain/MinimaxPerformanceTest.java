package fhict.fontys.nl.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MinimaxPerformanceTest {
    @Test
    void minimaxSingleIterationPerformanceTest() {
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
}
