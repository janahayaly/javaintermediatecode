package org.cis1200.twentyfortyeight;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.LinkedList;

public class TwentyFortyEight {

    public Tile[][] board;

    private LinkedList<Tile[][]> states;

    private LinkedList<Tile> contents;

    private int scoreIncrease;

    private int totalScore;

    private File stateStore;


    public TwentyFortyEight() {
        reset();
    }

    public void undo() {
        //decrement total score by scoreIncrease
        this.totalScore -= this.scoreIncrease;
        //return to previous state
        Tile[][] lastState = this.states.removeLast();
        this.board = this.states.getLast();
        this.contents = new LinkedList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    this.contents.add(board[i][j]);
                }
            }
        }
    }

    public void clearBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = null;
            }
        }
    }

    //used for debugging
    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(this.board[i][j]);
            }
            System.out.println(" ");
        }
    }

    //used for debugging
    public void printStates() {
        for (Tile[][] t : this.states) {
            for (int i = 0; i < t.length; i++) {
                for (int j = 0; j < t[i].length; j++) {
                    System.out.print(t[i][j]);
                }
                System.out.println(" ");
            }
            System.out.println(" ");
            System.out.println(" ");
        }
    }

    public void reset() {

        //reinitialize board
        this.board = new Tile[4][4];
        this.contents = new LinkedList<>();
        this.states = new LinkedList<>();
        this.totalScore = 0;

        //generate two tiles at random places
        Tile first = new Tile(2);
        Tile second = new Tile(2);
        if (first.getRow() == second.getRow() && first.getCol() == second.getCol()) {
            if (first.getCol() < 3) {
                first.moveRight();
            } else {
                first.moveLeft();
            }
        }

        this.board[first.getRow()][first.getCol()] = first;
        this.board[second.getRow()][second.getCol()] = second;

        this.contents.add(first);
        this.contents.add(second);

        Tile[][] copy = TwentyFortyEight.makeCopy(board);
        states.add(copy);

    }

    public static Tile[][] makeCopy(Tile[][] og) {
        Tile[][] res = new Tile[og.length][og[0].length];
        for (int i = 0; i < og.length; i++) {
            for (int j = 0; j < og[i].length; j++) {
                if (og[i][j] != null) {
                    Tile copy = new Tile(og[i][j].getValue());
                    copy.setRow(og[i][j].getRow());
                    copy.setCol(og[i][j].getCol());
                    res[i][j] = copy;
                }
            }
        }
        return res;
    }

    public void saveState(String filename) {
        try {
            stateStore = new File(filename);
            BufferedWriter state = new BufferedWriter(new FileWriter(filename, false));
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    try {
                        state.write(board[i][j].getValue());
                    } catch (NullPointerException e) {
                        state.write(0);
                    }
                }
            }
            state.close();
        } catch (IOException e) {
            System.out.println("No file to save state to :(");
        }
    }

    public boolean didPlayerWin() {
        for (Tile t : this.contents) {
            if (t.getValue() == 2048) {
                return true;
            }
        }
        return false;
    }

    public boolean didPlayerLose() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public void setScoreIncrease(int v) {
        this.scoreIncrease = v;
    }

    public void addToScoreIncrease(int v) {
        this.scoreIncrease += v;
    }

    public void addToContents(Tile t) {
        this.contents.add(t);
    }

    public void addToStates(Tile[][] t) {
        this.states.add(t);
    }

    public void addToTotalScore(int v) {
        this.totalScore += v;
    }

    public int getTotalScore() {
        return this.totalScore;
    }

    public File getStateStore() {
        return stateStore;
    }

}
