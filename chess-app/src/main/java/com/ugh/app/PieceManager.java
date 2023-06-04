package com.ugh.app;

import java.util.ArrayList;

import com.ugh.app.Move.Castle;
import com.ugh.app.Piece.Color;
import com.ugh.app.Piece.Type;
import com.ugh.app.Square.Direction;

public final class PieceManager {
    // data
    private ArrayList<Piece> _pieces = new ArrayList<Piece>();
    private Square _enpSquare = null;
    // constructors
    public PieceManager(ArrayList<Piece> pieces) {
        _pieces = pieces;
    }
    // public methods
    public void SetToFEN(FEN fen) {
        _pieces = fen.pieces;
        _enpSquare = fen.EPSquare;
    }
    /**
     * @param sq square to check whether a piece is on.
     * @return the piece at the square, or null if square empty.
     */
    public boolean IsPieceAt(Square sq) {
        return PieceAt(sq) != null;
    }
    public Piece PieceAt(Square sq) {
        for(Piece piece : _pieces) {
            if(piece.GetPos().equals(sq)) {
                return piece;
            }
        }
        return null;
    }
    public Piece FindKing(Piece.Color color) {
        for(Piece piece : _pieces) {
            if(piece.GetType() == Piece.Type.KING) {
                if(piece.GetColor() == color) {
                    return piece;
                }
            }
        }
        return null;
    }
    public String OutputPieceState() {
        char[][] grid = new char[8][8];
        for(int i=0; i<8; ++i) {
            for(int j=0; j<8; ++j) {
                grid[i][j] = '+';
            }
        }
        for(Piece piece : _pieces) {
            Square pos = piece.GetPos();
            char repr = piece.ToChar();
            grid[pos.GetYpos()][pos.GetXpos()] = repr;
        }
        String res = "";
        for(int i=7; i>=0; --i) {
            res += new String(grid[i]);
            res += "\n";
        }
        return res;
    }
    /**
     * @param move a move that has already been validated.
     */
    public void ApplyMove(Move move) {
        if(move.castleflag != Move.Castle.NO_CASTLE) {
            _ApplyCastle(move);
            return;
        }
        Piece atSrc = PieceAt(move.from);
        assert atSrc != null : "expecting validated move";
        Piece atDest = PieceAt(move.to);
        if(atDest != null) { // en passant would have no piece here
            _DeletePiece(atDest);
        }
        atSrc.SetPos(move.to);
        if(move.enpassant) {
            _DeletePiece(PieceAt(_enpSquare));
        }
        // update the enpassant square for a double pawn push.
        if(atSrc.GetType() == Type.PAWN) {
            if(Math.abs(move.from.GetYpos() - move.to.GetYpos()) == 2) {
                _enpSquare = Square.GetMidpoint(move.from, move.to);
            }
        }
    }
    // private methods
    private void _ApplyCastle(Move move) {
        Piece King, Rook;
        switch(move.castleflag) {
            case WHITE_KSC:
                King = PieceAt(new Square("e1"));
                Rook = PieceAt(new Square("h1"));
                _MoveNSquaresInDir(King, Direction.EAST, 2);
                _MoveNSquaresInDir(Rook, Direction.WEST, 2);
                break;
            case WHITE_QSC:
                King = PieceAt(new Square("e1"));
                Rook = PieceAt(new Square("a1"));
                _MoveNSquaresInDir(King, Direction.WEST, 2);
                _MoveNSquaresInDir(Rook, Direction.EAST, 3);
                break;
            case BLACK_KSC:
                King = PieceAt(new Square("e8"));
                Rook = PieceAt(new Square("h8"));
                _MoveNSquaresInDir(King, Direction.EAST, 2);
                _MoveNSquaresInDir(Rook, Direction.WEST, 2);
                break;
            case BLACK_QSC:
                King = PieceAt(new Square("e8"));
                Rook = PieceAt(new Square("a8"));
                _MoveNSquaresInDir(King, Direction.WEST, 2);
                _MoveNSquaresInDir(Rook, Direction.EAST, 3);
                break;
            default:
                assert false;
        }
    }
    private void _DeletePiece(Piece p) {
        _pieces.remove(p);
    }
    private void _MoveNSquaresInDir(Piece p, Direction dir, int N) {
        Square cur = p.GetPos();
        Square dest = Square.NextInDir(cur, dir, N);
        p.SetPos(dest);
    }
}
