package com.ugh.app;

public class Board {
    protected PieceManager pm;
    public Board() {
        SetToFEN(FEN.startpos);
    }
    // don't bother validating it for now.
    public void SetToFEN(String s) {
        FEN fen = new FEN(s);
        pm.SetToFEN(fen);
    }
    public void PrintSelf() {
        String str = pm.OutputPieceState();
        System.out.println(str);
    }
}
