package org.cis1200.twentyfortyeight;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {

    @Test
    public void testDidPlayerWin() {

        TwentyFortyEight game = new TwentyFortyEight();
        game.board[0][0] = new Tile(2048);
        game.addToContents(game.board[0][0]);

        assertTrue(game.didPlayerWin());
    }

    @Test
    public void testDidPlayerLose() {

        TwentyFortyEight game = new TwentyFortyEight();
        for (int i = 0; i < game.board.length; i++) {
            for (int j = 0; j < game.board[i].length; j++) {
                game.board[i][j] = new Tile(2);
            }
        }

        assertTrue(game.didPlayerLose());
    }

    @Test
    public void testRightShift() {

        JLabel score = new JLabel("Total Score: 0");
        GameBoard tester = new GameBoard(score);

        tester.game.clearBoard();

        Tile t1 = new Tile(2);
        t1.setRow(0);
        t1.setCol(0);
        tester.game.board[0][0] = t1;

        Tile t2 = new Tile(4);
        t2.setRow(1);
        t2.setCol(0);
        tester.game.board[1][0] = t2;

        Tile t3 = new Tile(8);
        t3.setRow(2);
        t3.setCol(0);
        tester.game.board[2][0] = t3;

        Tile t4 = new Tile(16);
        t4.setRow(3);
        t4.setCol(0);
        tester.game.board[3][0] = t4;

        tester.update('d');

        assertEquals(t1, tester.game.board[0][3]);
        assertEquals(t2, tester.game.board[1][3]);
        assertEquals(t3, tester.game.board[2][3]);
        assertEquals(t4, tester.game.board[3][3]);
    }

    @Test
    public void testRightCollision() {

        JLabel score = new JLabel("Total Score: 0");
        GameBoard tester = new GameBoard(score);

        tester.game.clearBoard();

        Tile t1 = new Tile(2);
        t1.setRow(0);
        t1.setCol(0);
        tester.game.board[0][0] = t1;

        Tile t2 = new Tile(2);
        t2.setRow(0);
        t2.setCol(2);
        tester.game.board[0][2] = t2;

        Tile t3 = new Tile(4);
        t3.setRow(2);
        t3.setCol(0);
        tester.game.board[2][0] = t3;

        Tile t4 = new Tile(4);
        t4.setRow(2);
        t4.setCol(2);
        tester.game.board[2][2] = t4;

        tester.update('d');

        assertEquals(4, tester.game.board[0][3].getValue());
        assertEquals(8, tester.game.board[2][3].getValue());
        assertEquals(12, tester.game.getTotalScore());
    }

    @Test
    public void testLeftShift() {
        JLabel score = new JLabel("Total Score: 0");
        GameBoard tester = new GameBoard(score);

        tester.game.clearBoard();

        Tile t1 = new Tile(2);
        t1.setRow(0);
        t1.setCol(3);
        tester.game.board[0][0] = t1;

        Tile t2 = new Tile(4);
        t1.setRow(1);
        t1.setCol(3);
        tester.game.board[1][0] = t2;

        Tile t3 = new Tile(8);
        t1.setRow(2);
        t1.setCol(3);
        tester.game.board[2][0] = t3;

        Tile t4 = new Tile(16);
        t1.setRow(3);
        t1.setCol(3);
        tester.game.board[3][0] = t4;

        tester.update('a');

        assertEquals(t1, tester.game.board[0][0]);
        assertEquals(t2, tester.game.board[1][0]);
        assertEquals(t3, tester.game.board[2][0]);
        assertEquals(t4, tester.game.board[3][0]);
    }

    @Test
    public void testLeftCollision() {

        JLabel score = new JLabel("Total Score: 0");
        GameBoard tester = new GameBoard(score);

        tester.game.clearBoard();

        Tile t1 = new Tile(2);
        t1.setRow(0);
        t1.setCol(1);
        tester.game.board[0][1] = t1;

        Tile t2 = new Tile(2);
        t2.setRow(0);
        t2.setCol(3);
        tester.game.board[0][3] = t2;

        Tile t3 = new Tile(4);
        t3.setRow(2);
        t3.setCol(1);
        tester.game.board[2][1] = t3;

        Tile t4 = new Tile(4);
        t4.setRow(2);
        t4.setCol(3);
        tester.game.board[2][3] = t4;

        tester.update('a');

        assertEquals(4, tester.game.board[0][0].getValue());
        assertEquals(8, tester.game.board[2][0].getValue());
        assertEquals(12, tester.game.getTotalScore());
    }

    @Test
    public void testUpShift() {

        JLabel score = new JLabel("Total Score: 0");
        GameBoard tester = new GameBoard(score);

        tester.game.clearBoard();

        Tile t1 = new Tile(2);
        t1.setRow(3);
        t1.setCol(0);
        tester.game.board[3][0] = t1;

        Tile t2 = new Tile(4);
        t2.setRow(3);
        t2.setCol(1);
        tester.game.board[3][1] = t2;

        Tile t3 = new Tile(8);
        t3.setRow(3);
        t3.setCol(2);
        tester.game.board[3][2] = t3;

        Tile t4 = new Tile(16);
        t4.setRow(3);
        t4.setCol(3);
        tester.game.board[3][3] = t4;

        tester.update('w');

        assertEquals(t1, tester.game.board[0][0]);
        assertEquals(t2, tester.game.board[0][1]);
        assertEquals(t3, tester.game.board[0][2]);
        assertEquals(t4, tester.game.board[0][3]);
    }

    @Test
    public void testUpCollision() {

        JLabel score = new JLabel("Total Score: 0");
        GameBoard tester = new GameBoard(score);

        tester.game.clearBoard();

        Tile t1 = new Tile(2);
        t1.setRow(3);
        t1.setCol(1);
        tester.game.board[3][1] = t1;

        Tile t2 = new Tile(2);
        t2.setRow(1);
        t2.setCol(1);
        tester.game.board[1][1] = t2;

        Tile t3 = new Tile(4);
        t3.setRow(2);
        t3.setCol(3);
        tester.game.board[2][3] = t3;

        Tile t4 = new Tile(4);
        t4.setRow(0);
        t4.setCol(3);
        tester.game.board[0][3] = t4;

        tester.update('w');

        assertEquals(4, tester.game.board[0][1].getValue());
        assertEquals(8, tester.game.board[0][3].getValue());
        assertEquals(12, tester.game.getTotalScore());
    }

    @Test
    public void testDownShift() {

        JLabel score = new JLabel("Total Score: 0");
        GameBoard tester = new GameBoard(score);

        tester.game.clearBoard();

        Tile t1 = new Tile(2);
        t1.setRow(0);
        t1.setCol(0);
        tester.game.board[0][0] = t1;

        Tile t2 = new Tile(4);
        t2.setRow(0);
        t2.setCol(1);
        tester.game.board[0][1] = t2;

        Tile t3 = new Tile(8);
        t3.setRow(0);
        t3.setCol(2);
        tester.game.board[0][2] = t3;

        Tile t4 = new Tile(16);
        t4.setRow(0);
        t4.setCol(3);
        tester.game.board[0][3] = t4;

        tester.update('s');

        assertEquals(t1, tester.game.board[3][0]);
        assertEquals(t2, tester.game.board[3][1]);
        assertEquals(t3, tester.game.board[3][2]);
        assertEquals(t4, tester.game.board[3][3]);

    }

    @Test
    public void testDownCollision() {

        JLabel score = new JLabel("Total Score: 0");
        GameBoard tester = new GameBoard(score);

        tester.game.clearBoard();

        Tile t1 = new Tile(2);
        t1.setRow(0);
        t1.setCol(1);
        tester.game.board[0][1] = t1;

        Tile t2 = new Tile(2);
        t2.setRow(2);
        t2.setCol(1);
        tester.game.board[2][1] = t2;

        Tile t3 = new Tile(4);
        t3.setRow(0);
        t3.setCol(3);
        tester.game.board[0][3] = t3;

        Tile t4 = new Tile(4);
        t4.setRow(2);
        t4.setCol(3);
        tester.game.board[2][3] = t4;

        tester.update('s');

        assertEquals(4, tester.game.board[3][1].getValue());
        assertEquals(8, tester.game.board[3][3].getValue());
        assertEquals(12, tester.game.getTotalScore());
    }


    @Test
    public void testUndo() {

        JLabel score = new JLabel("Total Score: 0");
        GameBoard tester = new GameBoard(score);

        tester.update('d');

        Tile[][] ogBoard = tester.game.makeCopy(tester.game.board);

        tester.update('w');

        tester.undo();

        for (int i = 0; i < tester.game.board.length; i++) {
            for (int j = 0; j < tester.game.board[i].length; j++) {
                if (ogBoard[i][j] == null) {
                    assertEquals(ogBoard[i][j], tester.game.board[i][j]);
                } else {
                    assertEquals(ogBoard[i][j].getValue(),
                            tester.game.board[i][j].getValue());
                }
            }
        }
    }

    @Test
    public void testReset() {
        JLabel score = new JLabel("Total Score: 0");
        GameBoard tester = new GameBoard(score);

        Tile[][] ogBoard = tester.game.makeCopy(tester.game.board);

        tester.update('d');
        tester.update('s');
        tester.update('w');
        tester.update('a');

        Tile[][] movedBoard = tester.game.makeCopy(tester.game.board);

        tester.reset();

        int tileNumbers = 0;

        for (int i = 0; i < tester.game.board.length; i++) {
            for (int j = 0; j < tester.game.board[i].length; j++) {
                if (tester.game.board[i][j] != null) {
                    assertNotEquals(ogBoard[i][j], tester.game.board[i][j]);
                    assertNotEquals(movedBoard[i][j], tester.game.board[i][j]);
                    tileNumbers++;
                }
            }
        }
        assertEquals(0, tester.game.getTotalScore());
        assertEquals(2, tileNumbers);
    }

}
