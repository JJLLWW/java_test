package com.ugh.app;

public class Piece {
    // data
    public enum Color {
        WHITE,
        BLACK
    }
    public enum Type {
        PAWN,
        ROOK,
        KNIGHT,
        BISHOP,
        QUEEN,
        KING
    }
    protected Color _color;
    protected Type _type;
    protected Square _pos;
    // constructors
    public Piece(Square pos, Color color, Type type) {
        _color = color;
        _pos = pos;
        _type = type;
    }
    // methods
    @Override
    public String toString() {
        return "Piece> pos : " 
        + _pos.toString() + ", type: "
        + _type.toString() + ", color: " + _color.toString();
    }
    public static Type TypeFromChar(char c) {
        c = Character.toLowerCase(c);
        switch(c) {
            case 'p':
                return Type.PAWN;
            case 'r':
                return Type.ROOK;
            case 'n':
                return Type.KNIGHT;
            case 'b':
                return Type.BISHOP;
            case 'q':
                return Type.KING;
            case 'k':
                return Type.KING;
        }
        assert false;
        return null;
    }
    public char ToChar() {
        char lowercase = '#';
        switch(_type) {
            case PAWN:
                lowercase = 'p';
                break;
            case ROOK:
                lowercase = 'r';
                break;
            case KNIGHT:
                lowercase = 'n';
                break;
            case BISHOP:
                lowercase = 'b';
                break;
            case QUEEN:
                lowercase = 'q';
                break;
            case KING:
                lowercase = 'k';
                break;
        }
        return IsWhite() ? lowercase : Character.toUpperCase(lowercase);
    }
    public boolean IsWhite() {
        return _color == Color.WHITE;
    }
    // getters/setters
    public Color GetColor() {
        return _color; // no setter, should be invariant for the whole game.
    }
    public Type GetType() {
        return _type;
    }
    public void SetType(Type type) {
        if(_type != Type.PAWN) {
            throw new IllegalArgumentException("tried to change non-pawn piece's type");
        }
        if(type == Type.KING || type == Type.PAWN) {
            throw new IllegalArgumentException("cannot promote to a pawn or king");
        }
        _type = type;
    }
    public Square GetPos() {
        return _pos;
    }
    public void SetPos(Square pos) {
        _pos = pos;
    }
}
