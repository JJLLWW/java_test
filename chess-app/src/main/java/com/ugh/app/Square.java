package com.ugh.app;

/** 
 * Square is a single square on an 8x8 board, contains helper methods
 * for determining squares above, below, to the left and right respecting
 * the boundaries of the board.
 */
public final class Square {
    // data
    private int _xpos, _ypos;
    private static String _FileChars = "abcdefgh", _RankChars = "012345678";
    // constructors
    public Square(int xpos, int ypos) {
        if(!_IsInBounds(xpos, ypos)) {
            _xpos = -1;
            _ypos = -1;
        }
        _xpos = xpos;
        _ypos = ypos;
    }
    /**
     * @param AlgNot An algebraic notation string eg. "e5", "b2" describing the square.
     */
    public Square(String AlgNot) {
        if(!_IsValidAlgNot(AlgNot)) {
            _xpos = -1;
            _ypos = -1;
        }
        _xpos = _FileChars.indexOf(AlgNot.charAt(0));
        _ypos = _RankChars.indexOf(AlgNot.charAt(1));
    }
    public Square(Square other) {
        _xpos = other._xpos;
        _ypos = other._ypos;
    }
    // public methods
    @Override
    public boolean equals(Object other) {
        if(other == null) {
            return false;
        }
        if(getClass() != other.getClass()) {
            return false;
        }
        Square otherSquare = (Square)other;
        return _xpos == otherSquare._xpos && _ypos == otherSquare._ypos;
    }
    @Override
    public String toString() {
        return String.format("(%d, %d)", _xpos, _ypos);
    }
    public static Square GetMidpoint(Square sq1, Square sq2) {
        int x1 = sq1._xpos, x2 = sq2._xpos;
        int y1 = sq1._ypos, y2 = sq2._ypos;
        Square res = new Square((x1 + x2)/2, (y1 + y2)/2);
        assert res._xpos != -1 && res._ypos != -1;
        return res;
    }
    public enum Direction {
        NORTH, EAST, SOUTH, WEST,
        NEAST, NWEST, SEAST, SWEST
    }
    public static Square NextInDir(Square base, Direction dir, int count) {
        Square cur = new Square(base);
        for(int i=0; i<count; ++i) {
            cur = NextInDir(base, dir);
        }
        return cur;
    }
    public static Square NextInDir(Square base, Direction dir) {
        switch(dir) {
            case NORTH:
                return new Square(base._xpos, base._ypos + 1);
            case EAST:
                return new Square(base._xpos + 1, base._ypos);
            case SOUTH:
                return new Square(base._xpos, base._ypos - 1);
            case WEST:
                return new Square(base._xpos - 1, base._ypos);
            case NEAST:
                return new Square(base._xpos + 1, base._ypos + 1);
            case NWEST:
                return new Square(base._xpos - 1, base._ypos + 1);
            case SEAST:
                return new Square(base._xpos + 1, base._ypos - 1);
            case SWEST:
                return new Square(base._xpos - 1, base._ypos - 1);
            default:
                return null;
        }
    }
    public String ToAlgNotation() {
        char[] chars = new char[2];
        chars[0] = _FileChars.charAt(_xpos);
        chars[1] = _RankChars.charAt(_ypos);
        return new String(chars);
    }
    // private methods
    private static boolean _IsInBounds(int xypos) {
        return 0 <= xypos && xypos < 8;
    }
    private static boolean _IsInBounds(int xpos, int ypos) {
        return _IsInBounds(xpos) && _IsInBounds(ypos);
    }
    private static boolean _IsValidAlgNot(String AlgNot) {
        if(AlgNot.length() != 2) {
            return false;
        }
        char file = AlgNot.charAt(0);
        char rank = AlgNot.charAt(1);
        int xpos = _FileChars.indexOf(file);
        int ypos = _RankChars.indexOf(rank);
        if(xpos == -1 || ypos == -1) {
            return false;
        }
        return true;
    }
    // Getters / Setters
    int GetXpos() {
        return _xpos;
    }
    boolean SetXpos(int xpos) {
        if(!_IsInBounds(xpos)) {
            return false;
        }
        _xpos = xpos;
        return true;
    }
    int GetYpos() {
        return _ypos;
    }
    boolean SetYpos(int ypos) {
        if(!_IsInBounds(ypos)) {
            return false;
        }
        _ypos = ypos;
        return true;
    }
    boolean SetPos(int xpos, int ypos) {
        if(!_IsInBounds(xpos, ypos)) {
            return false;
        }
        _xpos = xpos;
        _ypos = ypos;
        return true;
    }
}
