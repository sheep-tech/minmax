package fhict.fontys.nl.domain;

public interface IPlayerController extends IAction{
    public boolean playAtCell(int row, int col, boolean symbol);
    public boolean playAtCell(int row, int col, boolean symbol, boolean bot);

}
