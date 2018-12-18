/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.mario.profile;

import java.io.Serializable;
import org.jbox2d.common.Vec2;

/**
 *
 * A class for a profile implements the Serializable interface so we can save our game state in a file what can not be read 
 * by anybody outside of the application. Use this to save a profile with a game state for the game
 */
public class Profile implements Serializable {

    private String name;
    private int lives;
    private int level;
    private int score;
    private Vec2 marioPos;

    /**
     * Creates a profile by the specified parameters
     * @param playerName name of the profile
     * @param lives number of lives
     * @param level actual level 
     * @param score profile's score
     * @param marioPos the hers position on the actual level
     */
    public Profile(String playerName, int lives, int level, int score, Vec2 marioPos) {
        this.name = playerName;
        this.lives = lives;
        this.level = level;
        this.score = score;
        this.marioPos = marioPos;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setMarioPos(Vec2 marioPos) {
        this.marioPos = marioPos;
    }

    public String getName() {
        return name;
    }

    public int getLives() {
        return lives;
    }

    public int getLevel() {
        return level;
    }

    public int getScore() {
        return score;
    }

    public Vec2 getMarioPos() {
        return marioPos;
    }
}
