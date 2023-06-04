package com.ugh.app;

import static org.junit.Assert.*;

import org.junit.Test;

public class SquareTest {
    @Test
    public void ConstructorsTest() {
        int xpos = 2, ypos = 4;
        Square sq = new Square(xpos, ypos);
        assertEquals("xpos wrong", 2, sq.GetXpos());
        String algnot = "d4";
        Square sq2 = new Square(algnot);
        assertEquals("xpos wrong", 3, sq2.GetXpos());
        assertEquals("ypos wrong", 4, sq2.GetYpos());
        int badx = 123, bady = -453;
        Square sq3 = new Square(badx, bady);
    }
    @Test
    public void ConversionTest() {
        for(int xpos = 0; xpos<8; ++xpos) {
            for(int ypos = 0; ypos<8; ++ypos) {
                Square sq = new Square(xpos, ypos);
                String AlgNot = sq.ToAlgNotation();
                sq = new Square(AlgNot);
                assertTrue(sq.GetXpos() == xpos && sq.GetYpos() == ypos);
            }
        }
    }
}
