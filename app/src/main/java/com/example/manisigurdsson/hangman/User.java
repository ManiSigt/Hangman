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

    public User(String name, int score, int wins, int losses, int played, int rubies) {
        this.name = name;
        this.score = score;
        this.wins = wins;
        this.losses = losses;
        this.played = played;
        this.rubies = rubies;
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

    public int getPlayed() {
        return played;
    }

    public int getRubies() {
        return rubies;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public void setPlayed(int played) {
        this.played = played;
    }

    public void setRubies(int rubies) {
        this.rubies = rubies;
    }
}
