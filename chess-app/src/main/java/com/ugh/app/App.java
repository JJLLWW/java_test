package com.ugh.app;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Piece piece = new Piece(new Square(1, 1), Piece.Color.WHITE, Piece.Type.KING);
        String line = piece.toString();
        System.out.println(line);
    }
}
