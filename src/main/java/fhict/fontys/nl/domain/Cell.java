package fhict.fontys.nl.domain;

import java.util.BitSet;

public class Cell {
    private BitSet symbol;
    private BitSet isEmpty;

    public Cell() {
        this.symbol = new BitSet(1);
        this.isEmpty = new BitSet(1);
        this.isEmpty.set(0);
    }

    public boolean reset() {
        if (!this.isEmpty.get(0)) {
            this.isEmpty.set(0);
            return true;
        }
        // trying to reset a clean unset cell
        return false;
    }

    public static GameResult symbolToPlayer(boolean playerSymbol) {
        if(playerSymbol)
            return GameResult.O;
        else
            return GameResult.X;
    }

    public boolean getSymbol() {
        return symbol.get(0);
    }

    public void setSymbol(boolean symbol) {
        this.symbol.set(0, symbol);
        // this cell is not empty anymore
        this.isEmpty.set(0, false);
    }

    public boolean isEmpty() {
        return isEmpty.get(0);
    }
}
