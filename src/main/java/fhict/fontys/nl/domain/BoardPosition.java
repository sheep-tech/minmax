package fhict.fontys.nl.domain;

public class BoardPosition {
    public int row;
    public int column;

    public BoardPosition() {
    }

    public BoardPosition(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
