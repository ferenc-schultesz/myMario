/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.mario.control;

import javax.swing.JFrame;
import org.jbox2d.common.Vec2;
import uk.mario.Hero;
import uk.mario.MarioView;
import uk.mario.SoundPlayer;
import uk.mario.levels.GameEndAnimation;
import uk.mario.levels.Level;
import uk.mario.levels.Level_1;
import uk.mario.levels.Level_2;
import uk.mario.levels.Level_3;
import uk.mario.levels.Level_4;
import uk.mario.listeners.HeroController;
import uk.mario.listeners.Step_handler;
import uk.mario.profile.ProfileManager;

/**
 * A controller class for the transition between the levels, and for controlling the menu, and the profiles
 * 
 */
public final class GameController {

    private final JFrame GAMEWINDOW;
    private MarioView marioView;
    private Level level;
    private HeroController marioController;
    
    private JFrame debugView;
    
    private boolean gamePaused = false;
    private MainMenu mainMenu;
    private PauseMenu pauseMenu;
    private LoadGameMenu loadGameMenu;
    private NewPlayerMenu newPlayerMenu;
    private boolean levelStarted = false;

    private ProfileManager profileManager;

    /** 
     * Creates a GameController, with a JFrame (GAMEWINDOW), and a View in it. Also adds the main menu.
     * 
     */
    public GameController() {

        GAMEWINDOW = new JFrame("MyMario ------ author: Ferenc Schultesz");
        GAMEWINDOW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GAMEWINDOW.setLocationByPlatform(true);
        GAMEWINDOW.setFocusable(true);

        SoundPlayer.init();
        
        mainMenu = new MainMenu(this);
        pauseMenu = new PauseMenu(this);
        loadGameMenu = new LoadGameMenu(this);
        newPlayerMenu = new NewPlayerMenu(this);
        
        mainMenu.setBackgroundNewGame();
        addMainMenu();

        marioController = new HeroController(this);
        GAMEWINDOW.addKeyListener(marioController);
        //startLevel(1);
        GAMEWINDOW.setResizable(false);
        GAMEWINDOW.pack();
        GAMEWINDOW.setVisible(true);

        profileManager = new ProfileManager(this);

    }
/** 
     * 
     * Adds a main menu to GAMEWINDOW
     */
    public void addMainMenu() {
        GAMEWINDOW.add(mainMenu);
        GAMEWINDOW.revalidate();
        GAMEWINDOW.repaint();
    }
/** 
     * 
     * adds a NewPlayerMenu to the GAMEWINDOW
     */
    public void addNewPlayerMenu(){
        GAMEWINDOW.add(newPlayerMenu);
        GAMEWINDOW.revalidate();
        GAMEWINDOW.repaint();
        
    }
    /** 
     * 
     * adds a PauseMenu to the GAMEWINDOW if the game is not paused at the call. 
     * If it is, it removes the pause menu
     */
    public void addPauseMenu() {
        
        if (gamePaused) {
            SoundPlayer.resumeMusic();
            marioView.remove(pauseMenu);
            level.start();
            GAMEWINDOW.revalidate();
            GAMEWINDOW.repaint();
            gamePaused = false;
        } else {
            SoundPlayer.pauseMusic();
            this.level.stop();
            marioView.add(pauseMenu);
            gamePaused = true;
            GAMEWINDOW.revalidate();
            GAMEWINDOW.repaint();
        }
    }
    /** 
     * 
     * adds a load game menu to the GAMEWINDOW
     */
    public void addLoadGameMenu(){
        GAMEWINDOW.add(loadGameMenu);
        GAMEWINDOW.revalidate();
        GAMEWINDOW.repaint();
        
    }
/** 
     * 
     * removes the NewPlayerMenu from the GAMEWINDOW
     */
    public void removeNewPlayerMenu(){
        GAMEWINDOW.getContentPane().remove(newPlayerMenu);
        GAMEWINDOW.getContentPane().revalidate();
        GAMEWINDOW.getContentPane().repaint();
    }
    
    /** 
     * 
     * removes the LoadGameMenu from the GAMEWINDOW
     */
    public void removeLoadGameMenu(){
        GAMEWINDOW.getContentPane().remove(loadGameMenu);
        GAMEWINDOW.getContentPane().revalidate();
        GAMEWINDOW.getContentPane().repaint();
    }
    /** 
     * 
     * removes the MArioView from the GAMEWINDOW
     */
    public void removeMarioView (){
        GAMEWINDOW.remove(marioView);
        GAMEWINDOW.revalidate();
        GAMEWINDOW.repaint();
    }
    
    /** 
     * 
     * removes the MAinMenu from the GAMEWINDOW
     */
    public void removeMainMenu(){
        GAMEWINDOW.getContentPane().remove(mainMenu);
        GAMEWINDOW.getContentPane().revalidate();
        GAMEWINDOW.getContentPane().repaint();
    }
    
    /** 
     * Starts a specified level with the music. Also sets MArioView to show the level, and changes the listeners to listen to the level
     * @param levelCode the code of the level where we want to transit
     */
    public void startLevel(int levelCode) {
        int code = levelCode;
        levelStarted = true;
        SoundPlayer.playLevelMusic(code);
        switch (code) {
            case 1:
                level = new Level_1();
                break;
            case 2:
                level = new Level_2();

                break;
            case 3:
                level = new Level_3();
                break;
            case 4:
                level = new Level_4();
                break;
            case 5:
                level = new GameEndAnimation();
                break;
        }

        level.setup_level(this);
        if (marioView != null) {
            GAMEWINDOW.remove(marioView);
        }
        marioView = new MarioView(level, 1300, 700);
        marioView.setZoom(16.5f);

        level.addStepListener(new Step_handler(level, marioView));
        GAMEWINDOW.add(marioView);

        GAMEWINDOW.revalidate();
        GAMEWINDOW.repaint();

        //debugView = new DebugViewer(level, 1000, 500);
    }
/** 
     * creates the game over screen which is a gameover background with a main menu on it
     */
    public void gameOver() {
        level.stop();
        mainMenu.setBackgroundGameOver();
        GAMEWINDOW.remove(marioView);
        GAMEWINDOW.revalidate();
        GAMEWINDOW.repaint();
        GAMEWINDOW.add(mainMenu);
    }
/** 
     *  Creates a new profile
     * @param playerName the name of the new profile
     */
    public void createNewProfile(String playerName) {
        profileManager.createProfile(playerName, Hero.get_life(), level.get_level_code(), Hero.get_score(), level.get_hero().getPosition());
    }
/** 
     * Saves the actual game state for the loaded profile
     */
    public void saveGame() {
        profileManager.saveGame(Hero.get_life(), level.get_level_code(), Hero.get_score(), level.get_hero().getPosition());
        addPauseMenu();
    }
/** 
     * Loads a profile with the game state
     * @param profileName the name of the profile that we want to load
     */
    public void loadProfile(String profileName) {
        profileManager.loadProfile(profileName);
    }
/** 
     * Starts the loaded profile's saved game
     * @param name the name of the profile
     * @param level the level where the game was saved
     * @param lives the number of lives that the player had when he saved the game
     * @param score the number of scores at the last saved game
     * @param position the position of the hero of the saved game
     */
    public void startLoadedProfile(String name, int level, int lives, int score, Vec2 position) {
        startLevel(level);
        System.out.println("lives before set: " + Hero.get_life());
        Hero.setLife(lives);
        System.out.println("lives after set: " + Hero.get_life());
        Hero.setScore(score);
        this.level.get_hero().setPosition(position);
        Hero.setProfileName(name);
    }

    //--------------------- SETTER & GETTER METHODS ----------------------------
    
    /** 
     * get the mainmenu
     * @return returns the value of mainMenu
     */
    public MainMenu getMainMenu() {
        return mainMenu;
    }
/** 
     * get the LoadGameMenu
     * @return returns the value of loadGameMenu
     */
    public LoadGameMenu getLoadGameMenu (){
        return loadGameMenu;
    }
   /** 
     * get the game window
     * @return returns the value of GAMEWINDOW
     */
    public JFrame getGameWindow() {
        return GAMEWINDOW;
    }
/** 
     * get the actual level
     * @return returns the value of level
     */
    public Level getLevel() {
        return level;
    }
    /** 
     * get the MArioView
     * @return returns the value of marioView
     */
    public MarioView getMarioView() {
        return marioView;
    }
    
    /** 
     *  tells you if the gameplay on a level has already started
     * @return returns the value of levelStarted
     */
    public boolean getLevelStarted() {
        return levelStarted;
    }
    
    
}
