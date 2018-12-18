package uk.mario.levels;

import city.cs.engine.World;

import uk.mario.enemies.Bowser;
import uk.mario.enemies.Cannon;
import uk.mario.enemies.Dynamic_enemy;
import uk.mario.enemies.Fire_rod;
import uk.mario.enemies.Static_enemy;
import uk.mario.Hero;
import uk.mario.levelelements.Coin;
import uk.mario.levelelements.Pipe;
import uk.mario.levelelements.Platform;

import java.util.ArrayList;

import org.jbox2d.common.Vec2;
import uk.mario.control.GameController;

/**
 * An abstract class for every level in the game with the common methods and
 * variables.
 *
 */
public abstract class Level extends World {

    //-------------------------DECLARING VARIABLES -----------------------------
    private GameController gameController;
    private Hero hero;

    //Arraylists for the different enemies
    private ArrayList<Dynamic_enemy> dynamic_enemies = new ArrayList<Dynamic_enemy>();
    private ArrayList<Static_enemy> static_enemies = new ArrayList<Static_enemy>();
    private ArrayList<Fire_rod> fire_rods = new ArrayList<Fire_rod>();
    private ArrayList<Cannon> cannons = new ArrayList<Cannon>();
    protected Bowser bowser;
    protected boolean has_bowser = false;

    //Arraylists for the different static level elements
    private ArrayList<Platform> platforms = new ArrayList<Platform>();
    private ArrayList<Pipe> pipes = new ArrayList<Pipe>();
    private ArrayList<Coin> coins = new ArrayList<Coin>();

    // ------------------------- LEVEL SETUP METHOD ----------------------------
    /**
     * Setup method to create and put the hero to the level
     *
     * @param gameController is the Control class for the whole game to control
     * the transition between levels
     */
    public void setup_level(GameController gameController) {
        this.gameController = gameController;

        hero = new Hero(this);
        hero.setPosition(start_position());

    }

    //------------------------ABSTRACT METHODS----------------------------------
    /**
     * Kills the hero and calls the method to reset his position and change the
     * data in Hero class
     *
     */
    public abstract void hero_dies();

    /**
     * sets the hero's starting position
     */

    public abstract Vec2 start_position();

    /**
     * sets the position of the item that causes the hero to move to next level
     * @return the level transit item's position
     */
    public abstract Vec2 set_finish_item_position();

    /**
     * returns the level code of the actual level
     *
     */
    public abstract int get_level_code();

    // -------------------- GETTER and SETTER METHODS --------------------------
    /**
     * get every pipe on the level
     *
     * @return returns the values of pipes
     */
    public ArrayList<Pipe> get_pipes() {
        return pipes;
    }

    /**
     * get every static enemy of the level
     *
     * @return returns the values of static_enemies
     */
    public ArrayList<Static_enemy> get_static_enemies() {
        return static_enemies;
    }

    /**
     * get all dynamic_enemies of the level
     *
     * @return returns the value of dynamic_enemy
     */
    public ArrayList<Dynamic_enemy> get_dynamic_enemies() {
        return dynamic_enemies;
    }

    /**
     * get all coins on the level
     *
     * @return returns the value of coins
     */
    public ArrayList<Coin> get_coins() {
        return coins;
    }

    /**
     * get all fire rods on the level
     *
     * @return returns the value of fire_rods
     */
    public ArrayList<Fire_rod> get_fire_rods() {
        return fire_rods;
    }

    /**
     * get the hero
     *
     * @return returns the value of hero
     */
    public Hero get_hero() {
        return this.hero;
    }

    /**
     * get all platforms of the level
     *
     * @return returns the value of platforms
     */
    public ArrayList<Platform> get_platforms() {
        return platforms;
    }

    /**
     * get all cannons of the level
     *
     * @return returns the value of cannons
     */
    public ArrayList<Cannon> get_cannons() {
        return cannons;
    }

    /**
     * get the bowser from the level
     * @return returns the value of bowser
     */
    public Bowser get_bowser() {
        return bowser;
    }
/** 
     * tells us if bowser is alive on a level
     * @return returns the value of has_bowser
     */
    public boolean bowser_alive() {
        return has_bowser;
    }
/** 
     * get the gamecontroller
     * @return returns the value of gameController
     */
    public GameController getGameController() {
        return gameController;
    }
}
