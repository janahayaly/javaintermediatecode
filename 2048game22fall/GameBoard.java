package org.cis1200.twentyfortyeight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GameBoard extends JPanel {

    private boolean full;

    public TwentyFortyEight game;

    private boolean motion;

    private JLabel status;

    public static final int BOARD_WIDTH = 400;
    public static final int BOARD_HEIGHT = 400;

    public GameBoard(JLabel init) {

        this.full = false;
        this.motion = false;

        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        game = new TwentyFortyEight(); // initializes model for the game
        status = init;

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {

                char key = e.getKeyChar();

                update(key); // updates the status JLabel

                repaint(); // repaints the game board
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draws board grid
        g.drawLine(100, 0, 100, 400);
        g.drawLine(200, 0, 200, 400);
        g.drawLine(300, 0, 300, 400);
        g.drawLine(0, 100, 400, 100);
        g.drawLine(0, 200, 400, 200);
        g.drawLine(0, 300, 400, 300);

        for (int i = 0; i < game.board.length; i++) {
            for (int j = 0; j < game.board[i].length; j++) {
                try {
                    game.board[i][j].drawTile(g);
                } catch (NullPointerException e) {

                }
            }
        }
    }

    public void update(char key) {
        //do shifts
        game.setScoreIncrease(0);

        if (key == 'd') {
            for (int i = 0; i < game.board.length ; i++) {
                for (int j = game.board[i].length - 1; j >= 0; j--) {
                    try {
                        rightShift(game.board[i][j]);
                    } catch (NullPointerException e) {
                    }
                }
            }
        }

        if (key == 'a') {
            for (int i = 0; i < game.board.length; i++) {
                for (int j = 0; j < game.board[i].length; j++) {
                    try {
                        leftShift(game.board[i][j]);
                    } catch (NullPointerException e) {
                    }
                }
            }
        }

        if (key == 'w') {
            for (int i = 0; i < game.board.length; i++) {
                for (int j = 0; j < game.board[i].length; j++) {
                    try {
                        upShift(game.board[i][j]);
                    } catch (NullPointerException e) {
                    }
                }
            }
        }

        if (key == 's') {
            for (int i = game.board.length - 1; i >= 0; i--) {
                for (int j = 0; j < game.board[i].length; j++) {
                    try {
                        downShift(game.board[i][j]);
                    } catch (NullPointerException e) {
                    }
                }
            }
        }
        if ((key == 'w' || key == 'a' || key == 's' || key == 'd') &&
            motion) {
            Tile random = new Tile(2);
            boolean unfound = true;
            while (unfound) {
                if (game.board[random.getRow()][random.getCol()] == null) {
                    game.board[random.getRow()][random.getCol()] = random;
                    game.addToContents(random);
                    unfound = false;
                } else {
                    random.setRow((int) (Math.random() * 4));
                    random.setCol((int) (Math.random() * 4));
                }
            }

            this.motion = false;
            Tile[][] copy =  TwentyFortyEight.makeCopy(game.board);
            game.addToStates(copy);
            game.saveState("gamestate.txt");
        }
        //CHECK IF THE PLAYER WON
        if (game.didPlayerWin()) {
            JOptionPane victory = new JOptionPane("You Win!");
            victory.showMessageDialog(this, "You Win!");
            game.getStateStore().delete();
        } else if (game.didPlayerLose()) {
            //pop up window
            JOptionPane loss = new JOptionPane("You Lose!");
            loss.showMessageDialog(this, "You Lose!");
            game.getStateStore().delete();
        }
    }

    public int getTotalScore() {
        return game.getTotalScore();
    }

    public void handleCollision(Tile first, Tile second) {
        int newValue = first.getValue() + second.getValue();
        game.board[first.getRow()][first.getCol()] = null;
        game.board[second.getRow()][second.getCol()].setValue(newValue);
        game.addToScoreIncrease(newValue);
        game.addToTotalScore(newValue);
        status.setText("Total Score: " + game.getTotalScore());
        this.motion = true;
    }

    public void rightShift(Tile t) {
        for (int col = t.getCol() + 1; col < 4; col++) {
            Tile next = game.board[t.getRow()][col];
            if (next != null) {
                if (next.getValue() == t.getValue()) {
                    handleCollision(t, next);
                } else {
                    return;
                }
            } else {
                game.board[t.getRow()][t.getCol()] = null;
                t.moveRight();
                game.board[t.getRow()][t.getCol()] = t;
                this.motion = true;
            }
        }
    }

    public void leftShift(Tile t) {
        for (int col = t.getCol() - 1; col >= 0; col--) {
            Tile behind = game.board[t.getRow()][col];
            if (behind != null) {
                if (behind.getValue() == t.getValue()) {
                    handleCollision(t, behind);
                } else {
                    return;
                }
            } else {
                game.board[t.getRow()][t.getCol()] = null;
                t.moveLeft();
                game.board[t.getRow()][t.getCol()] = t;
                this.motion = true;
            }
        }
    }

    public void upShift(Tile t) {
        for (int row = t.getRow() - 1; row >= 0; row--) {
            Tile above = game.board[row][t.getCol()];
            if (above != null) {
                if (above.getValue() == t.getValue()) {
                    handleCollision(t, above);
                } else {
                    return;
                }
            } else {
                game.board[t.getRow()][t.getCol()] = null;
                t.moveUp();
                game.board[t.getRow()][t.getCol()] = t;
                this.motion = true;
            }
        }
    }

    public void downShift(Tile t) {
        for (int row = t.getRow() + 1; row < 4; row++) {
            Tile under = game.board[row][t.getCol()];
            if (under != null) {
                if (under.getValue() == t.getValue()) {
                    handleCollision(t, under);
                } else {
                    return;
                }
            } else {
                game.board[t.getRow()][t.getCol()] = null;
                t.moveDown();
                game.board[t.getRow()][t.getCol()] = t;
                this.motion = true;
            }
        }
    }

    public void start() {
        JOptionPane instructions = new JOptionPane("Instructions");
        instructions.showMessageDialog(this,
                "INSTRUCTIONS: Use WASD keys to shift tiles.\n" +
                        "When two tiles have the same value and collide, \n" +
                        "they will combine to form a tile that contains their sum. \n" +
                        "The goal is to combine enough tiles to form the 2048 tile. \n" +
                        "If you fill up the board, you lose.\n" +
                        "If you were playing and want to access your progress,\n" +
                        "just click ok!\n" +
                        "Make sure your caps lock is off... Good luck!");

        game.reset();
        try {
            BufferedReader in = new BufferedReader(new FileReader("gamestate.txt"));
            this.game.board = new Tile[4][4];
            for (int i = 0; i < game.board.length; i++) {
                for (int j = 0; j < game.board[i].length; j++) {
                    int value = in.read();
                    if (value == 0) {
                        this.game.board[i][j] = null;
                    } else {
                        Tile t = new Tile(value);
                        t.setRow(i);
                        t.setCol(j);
                        this.game.board[i][j] = t;
                        game.addToContents(t);
                    }
                }
            }
            in.close();
        } catch (IOException e) {
        }
        repaint();

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }

    public void reset() {
        game.reset();

        repaint();

        requestFocusInWindow();
    }

    public void undo() {
        game.undo();
        repaint();

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }

    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }


}
