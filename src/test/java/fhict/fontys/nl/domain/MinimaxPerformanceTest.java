package fhict.fontys.nl.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MinimaxPerformanceTest {
    @Test
    void minimaxSingleIterationPerformanceTest() {
        int boardSize = 4;

        TicTacToeBoard gameBoard = new TicTacToeBoard(boardSize);
        Minimax computer = new Minimax(boardSize, GameResult.X);

        long start = System.currentTimeMillis();

        BoardPosition boardPosition = computer.minimax(gameBoard, boardSize, 12);

        long duration = System.currentTimeMillis() - start;
        System.out.println("Time execution: " + duration + "ms with board Size: " + boardSize);

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
