package uk.mario.enemies;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.StaticBody;
import uk.mario.listeners.Enemy_collision_detector;
import java.util.ArrayList;
import uk.mario.levels.Level;
import org.jbox2d.common.Vec2;

/**
 * Static enemies for the game, there are different types; spikes, lava or little black flowers
 *
 */
public class Static_enemy {

    private int pieces;
    private String type;
    private BoxShape piece_shape = new BoxShape(1f,1f);
    private ArrayList <StaticBody> static_enemy = new ArrayList <StaticBody>();
    
    private final BodyImage BLACK_FLOWER_OPEN = new BodyImage("Sprites/enemies/enemy_static/enemy_black_open.png", 2f);
    private final BodyImage BLACK_FLOWER_CLOSE = new BodyImage("Sprites/enemies/enemy_static/enemy_black_close.png", 2f);
    private final BodyImage SPIKE = new BodyImage("Sprites/enemies/enemy_static/spike.png", 2f);
    private final BodyImage LAVA_ONE = new BodyImage("Sprites/enemies/enemy_static/laval_one.png", 2f);
    private final BodyImage LAVA_TWO = new BodyImage("Sprites/enemies/enemy_static/laval_two.png", 2f);
    private BodyImage actual_image;
    
    
   /** 
     *  Creates static enemies on the specified level of a certain type and size
     * @param _pieces how many pieces of static enemies we want next to eachother
     * @param position the position of the first static enemy
     * @param _type the type of the static enemies
     * @param level the level were we creat the enemies
     */
    
    public Static_enemy (int _pieces, Vec2 position, String _type, Level level) {
        
        Vec2 block_position = position;
        type = _type;
        pieces = _pieces;
        
        // check what type of enemies we need and add the suitable image also save it to actual_image
        // later we will add more enemy types
        switch (type) {
            case "BLACK_FLOWER":  actual_image = BLACK_FLOWER_OPEN;
                break;
            case "SPIKE":  actual_image = SPIKE;
                break;
            case "LAVA":  actual_image = LAVA_ONE;
                break;
        }
        
        //fill upt the array with the number of enemies
        for (int i = 0 ; i < pieces ; i++ ){
               static_enemy.add(new StaticBody(level, piece_shape));
               block_position.x = block_position.x + 2f;
               static_enemy.get(i).setPosition(block_position);
               static_enemy.get(i).addImage(actual_image);
               static_enemy.get(i).addCollisionListener(new Enemy_collision_detector(level));
               
        }
        
        
    }

    
/** 
     * Creates animation for the enemies
     * @param frame the actual number of frame of the game helps us to do the animation
     */
    public void update_sprite (int frame){
        switch (type) {
            case "BLACK_FLOWER": 
                if (frame % 53 == 0){
                    for (int i = 0 ; i < pieces ; i++ ){
                        static_enemy.get(i).removeImage(actual_image);
                        static_enemy.get(i).addImage(BLACK_FLOWER_CLOSE);
                       
                    }
                     actual_image = BLACK_FLOWER_CLOSE;
                }
                if (frame % 101 == 0){
                    for (int i = 0 ; i < pieces ; i++ ){
                        static_enemy.get(i).removeImage(actual_image);
                        static_enemy.get(i).addImage(BLACK_FLOWER_OPEN);
                        
                    }
                    actual_image = BLACK_FLOWER_OPEN;
                }
                break;
                
            case "LAVA": 
                if (frame % 53 == 0){
                    for (int i = 0 ; i < pieces ; i++ ){
                        static_enemy.get(i).removeImage(actual_image);
                        static_enemy.get(i).addImage(LAVA_TWO);
                       
                    }
                     actual_image = LAVA_TWO;
                }
                if (frame % 101 == 0){
                    for (int i = 0 ; i < pieces ; i++ ){
                        static_enemy.get(i).removeImage(actual_image);
                        static_enemy.get(i).addImage(LAVA_ONE);
                        
                    }
                    actual_image = LAVA_ONE;
                }
                break;
        }
    }
    
    
}
