package com.ugh.app;

import java.util.ArrayList;

import com.ugh.app.Piece.Color;

public class FEN {
    public ArrayList<Piece> pieces = new ArrayList<Piece>();
    public String ActiveColor;
    public String Castling;
    public Square EPSquare;
    public int halfmove;
    public int fullmove;
    public static String startpos = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    public FEN(String FENString) {
        // don't bother with extensive validation.
        String[] fields = FENString.split(" ");
        String[] rows = fields[0].split("/");
        _GetPieces(rows);
        ActiveColor = fields[1];
        Castling = fields[2];
        if(fields[3].equals("-")) {
            EPSquare = null;
        } else {
            EPSquare = new Square(fields[3]);
        }
        halfmove = Integer.parseInt(fields[4]);
        fullmove = Integer.parseInt(fields[5]);
    }
    private void _GetPieces(String[] Rows) {
        for(int ypos = 7; ypos>=0; ypos--) {
            int xpos = 0;
            for(char c : Rows[ypos].toCharArray()) {
                if(Character.isDigit(c)) {
                    xpos += c - '0';
                } else {
                    Piece.Color col = Character.isUpperCase(c) ? Color.WHITE : Color.BLACK;
                    Piece.Type type = Piece.TypeFromChar(c);
                    Piece next = new Piece(new Square(xpos, ypos), col, type);
                    pieces.add(next);
                    ++xpos;
                }
            }
        }
    }
}
