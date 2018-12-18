package uk.mario;

import city.cs.engine.SoundClip;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Ferenc
 * This class is used to play every effect and music in the game
 */
public class SoundPlayer {

    private static SoundClip coin, brownEnemyDies, turtleEnemyDies, heroDies, heroJumps, extraLife, fireball, cannon;
    private static SoundClip levelOneMusic, levelTwoMusic, levelThreeMusic, levelFourMusic, levelFiveMusic, actualLevelMusic;

    private static boolean levelMusic = false;
    
   
/** 
     * A setup method to read every sound file and set their volume   
     *
     */
    public static void init (){
        try {
            heroJumps = new SoundClip("Sounds/effects/hero_jumps.wav");
            heroJumps.setVolume(0.4);

            coin = new SoundClip("Sounds/effects/coin_effect.wav");
            coin.setVolume(1.5f);

            brownEnemyDies = new SoundClip("Sounds/effects/brown_enemy_dies.wav");

            turtleEnemyDies = new SoundClip("Sounds/effects/turtle_enemy_dies.wav");

            levelOneMusic = new SoundClip("Sounds/effects/level_one_music.mid");
            levelOneMusic.setVolume(0.1);

            levelTwoMusic = new SoundClip("Sounds/effects/level_two_music.mid");
            levelTwoMusic.setVolume(0.3);

            levelThreeMusic = new SoundClip("Sounds/effects/level_three_music.mid");
            levelThreeMusic.setVolume(0.2f);
            
            levelFourMusic = new SoundClip("Sounds/effects/level_boss_music.mid");
            levelFourMusic.setVolume(0.3f);
            
            levelFiveMusic = new SoundClip ("Sounds/effects/level_five_music.wav");

            heroDies = new SoundClip("Sounds/effects/lost_a_life.wav");
            heroDies.setVolume(2.0);

            extraLife = new SoundClip("Sounds/effects/extra_life.wav");
            extraLife.setVolume(1.5f);

            fireball = new SoundClip("Sounds/effects/fireball.wav");
            fireball.setVolume(1.5f);

            cannon = new SoundClip("Sounds/effects/cannon.wav");
            
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            Logger.getLogger(SoundPlayer.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error while opening some sound effect files..");
        }
        actualLevelMusic = levelOneMusic;
    
    }
    
    /** 
     * plays jump sound 
     */
    public static void playHeroJumps() {
        heroJumps.play();
    }
/** 
     * plays the hero dies sound
     */
    public static void playHeroDies() {
        heroDies.play();
    }
/** 
     * plays the coin pickup sound
     */
    public static void playCoin() {
        coin.play();
    }
/** 
     * plays the extra life sound
     */
    public static void playExtraLife() {
        extraLife.play();
    }
/** 
     * plays the brown enemy dies sound
     */
    public static void playBrownEnemyDies() {
        brownEnemyDies.play();
    }
/** 
     * plays the turtle enemy dies sound
     */
    public static void playTurtleEnemyDies() {
        turtleEnemyDies.play();
    }
/** 
     * plays the fireball sound
     */
    public static void playFireball() {
        fireball.play();
    }
/** 
     * plays the cannon sounds
     */
    public static void playCannon() {
        cannon.play();
    }


    /** 
     * Starts to play the specified level's music. If a music is already playing, it stops that first.
     * @param levelCode the levelCode of the level whose music we want to play
     */
    public static void playLevelMusic (int levelCode){
        
        if (levelMusic){
            actualLevelMusic.stop();
        }
        
        switch (levelCode){
                case 1: 
                    actualLevelMusic = levelOneMusic;
                    
                    break;
                case 2:
                    actualLevelMusic = levelTwoMusic;
                    break;
                case 3: 
                    actualLevelMusic = levelThreeMusic;
                    break;
                case 4: 
                    actualLevelMusic = levelFourMusic;
                    break;
                case 5:
                    actualLevelMusic = levelFiveMusic;
                    break;
        }
        
        actualLevelMusic.loop();
        levelMusic = true;
    }
    
    /** 
     * Pauses the actual level's music
     */
    public static void pauseMusic(){
         actualLevelMusic.pause();
    }
    /** 
     * Continues to play the level@s music
     */
    public static void resumeMusic(){
        actualLevelMusic.resume();
    }

}
