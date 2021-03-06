package com.example.manisigurdsson.hangman;

public class User {
    private String name = "";
    private int score = 0;
    private int wins = 0;
    private int losses = 0;
    private int played = 0;
    private int rubies = 0;

    public User(String s) {
        name = s;
    }
    public User() { }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getRubies() { return rubies; }

    public int getPlayed() { return (played); }

    public void setName(String s) {
        name = s;
    }

    public void addScore(int n) {
        score = score  + n;
    }

    public void addWin() { wins = wins + 1; played = played +1; }

    public void addLoss() { losses = losses + 1; played = played + 1; }

    public void addRubies(int n) {
        rubies = rubies + n;
    }

    public void removeRubies(int n) {rubies = rubies - n;}
}
