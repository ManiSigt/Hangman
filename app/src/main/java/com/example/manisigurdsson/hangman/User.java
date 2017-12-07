package com.example.manisigurdsson.hangman;

/**
 * Created by Audur Reynisdottir on 07/12/2017.
 */

public class User {
    private String name;
    private int score;
    private int wins;
    private int losses;
    private int played;
    private int rubies;

    public User(String name) {
        this.name = name;
        this.score = 0;
        this.wins = 0;
        this.losses = 0;
        this.rubies = 0;
    }
    public User() {
        this.name = "";
        this.score = 0;
        this.wins = 0;
        this.losses = 0;
        this.rubies = 0;
    }

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

    public int getRubies() {
        return rubies;
    }

    public int getPlayed() { return (wins + losses); }

    public void setName(String name) {
        this.name = name;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void addWin() {
        this.wins += 1;
    }

    public void addLoss() {
        this.losses += 1;
    }

    public void addRubies(int rubies) {
        this.rubies += rubies;
    }
}
