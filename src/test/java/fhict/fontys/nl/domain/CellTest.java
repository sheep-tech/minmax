package fhict.fontys.nl.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    Cell cell = new Cell();
    @Test
    void SetSymbolTest() {
        cell.setSymbol(false);

        assertEquals(cell.getSymbol().get(0), false);
        assertEquals(cell.isEmpty().get(0), false);
    }

    @Test
    void getSymbol() {
        assertEquals(cell.getSymbol().get(0), false);
        cell.setSymbol(true);
        assertEquals(cell.getSymbol().get(0), true);
    }

}