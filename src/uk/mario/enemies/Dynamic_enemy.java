package uk.mario.enemies;

import city.cs.engine.PolygonShape;
import city.cs.engine.Walker;
import uk.mario.levels.Level;
import org.jbox2d.common.Vec2;

/**
 * An abstract class for every dynamic enemy in the game
 * 
 */
public abstract class Dynamic_enemy extends Walker{

   
    private float speed;
    private Vec2 start_position;
    private Vec2 size;   
    
    /** 
     * Creates an enemy
     * @param _level the level where the enemy will be created
     */
    public Dynamic_enemy(Level _level){
        super(_level);
    }
    /** 
     * Creates an enemy
     * @param _level the level where the enemy will be created
     * @param  shape the shape that we use to create the enemy
     */
    public Dynamic_enemy(Level _level, PolygonShape shape) {
        super(_level,shape);
    }
    
    
    
    
     //------------------------ABSTRACT METHODS---------------------------------
    /** 
     * Turns the enemy to the opposite direction
     * 
     */
    public abstract void turn();
    /** 
     * Kills the enemy
     * 
     */
    public abstract void die();
    /** 
     * Updates the enemy's image, used for the animation
     * 
     */
    public abstract void update_sprite();
    /** 
     * moves the enemy to it's starting position
     * 
     */
    public abstract void move_to_startposition ();
    /** 
     * Makes the enemy to jump
     * @param frame the number of the frame, we use this to make things happen at a certain time
     */
    public abstract void jump(int frame);
    
    // -------------------- GETTER and SETTER METHODS --------------------------
    
    /** 
     * Gets the enemy's starting position
     * @return return the start_position
     */
    public Vec2 get_start_position (){return start_position;}
    /** 
     * Gets the size of the enemy
     * @return return the value of size
     */
    public Vec2 get_size(){return size;}
    /** 
     * Gets the speed of the enemy
     * @return returns the value of speed
     */
    public float get_speed(){return speed;}
}



