package org.cis1200.twentyfortyeight;
import javax.swing.*;
import java.awt.*;

public class Tile {

    private int value;

    private int row;

    private int column;

    public Tile(int value) {
        this.value = value;
        this.row = (int) (Math.random() * 4);
        this.column = (int) (Math.random() * 4);
    }

    public void drawTile(Graphics g) {
        int v = this.value;
        g.setColor(pickColor());
        g.fillRect(column * 100, row * 100,
                GameBoard.BOARD_WIDTH / 4,
                GameBoard.BOARD_HEIGHT / 4);
        g.setColor(Color.black);
        g.drawRect(column * 100, row * 100,
                GameBoard.BOARD_WIDTH / 4,
                GameBoard.BOARD_HEIGHT / 4);
        g.drawString(v + "", column * 100 + 50, row * 100 + 50);
    }

    public Color pickColor() {
        int v = this.value;
        if (v == 2) {
            return new Color(255, 204, 229);
        } else if (v == 4) {
            return new Color(255, 204, 255);
        } else if (v == 8) {
            return new Color(229, 204, 255);
        } else if (v == 16) {
            return new Color(204, 204, 255);
        } else if (v == 32) {
            return new Color(204, 229, 255);
        } else if (v == 64) {
            return new Color(204, 255, 255);
        } else if (v == 128) {
            return new Color(204, 255, 229);
        } else if (v == 256) {
            return new Color(229, 255, 204);
        } else if (v == 512) {
            return new Color(255, 255, 204);
        } else if (v == 1024) {
            return new Color(255, 229, 204);
        } else {
            return new Color(255, 204, 204);
        }
    }

    public void moveRight() {
        this.column++;
    }

    public void moveLeft() {
        this.column--;
    }

    public void moveUp() {
        this.row--;
    }

    public void moveDown() {
        this.row++;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int v) {
        this.value = v;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.column;
    }

    public void setRow(int r) {
        this.row = r;
    }

    public void setCol(int c) {
        this.column = c;
    }

}
