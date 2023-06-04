package com.ugh.app;

public class Move {
    // data
    public Square from, to;
    public Castle castleflag;
    public boolean enpassant;
    public enum Castle {
        NO_CASTLE,
        WHITE_KSC,
        WHITE_QSC,
        BLACK_KSC,
        BLACK_QSC
    }
    // constructors
    public Move(Castle flag) {
        from = null;
        to = null;
        castleflag = flag;
    }
    public Move(Square from, Square to) {
        this.from = from;
        this.to = to;
        castleflag = Castle.NO_CASTLE;
        enpassant = false;
    }
    public Move(Square from, Square to, boolean enpassant) {
        this(from, to);
        this.enpassant = enpassant;
    }
}
