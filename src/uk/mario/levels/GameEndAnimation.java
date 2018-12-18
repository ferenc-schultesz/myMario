package uk.mario.levels;

import city.cs.engine.*;
import uk.mario.enemies.Fire_rod;
import uk.mario.levelelements.Platform;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;
import org.jbox2d.common.Vec2;
import uk.mario.control.GameController;
import uk.mario.enemies.Bowser;
/**
 *
 * @author Ferenc
 */
public final class GameEndAnimation extends Level implements ActionListener {
    private Vec2 PLAYER_START_POSITION = new Vec2(80f, 2f); //x: 20 for default
    private final Vec2 FINISH_BARREL_POSITION = new Vec2(-4444f, 2f);
    private BodyImage groundimage;
    private final int LEVEL_CODE = 5;
    private StaticBody left_wall;
    private StaticBody right_wall;
    private StaticBody top_wall;
    private StaticBody ground;
    private StaticBody small_wall;
    
    
    private Timer timer;
    
    // ------------------------- LEVEL METHODS -------------------------------
    //we override the setup_level from the abstract class, so we can put here 
    //everything what is different between the levels
    @Override
    public void setup_level(GameController gameController)  {

        super.setup_level(gameController);
        get_hero().destroy();
        
        
        
        
        get_fire_rods().add(new Fire_rod(this, new Vec2(135, -3f)));
        get_fire_rods().add(new Fire_rod(this, new Vec2(120, -3f)));
        get_fire_rods().add(new Fire_rod(this, new Vec2(105, -3f)));

        get_platforms().add(new Platform(this, 4, new Vec2(82f, 6f), "GOLDEN"));
        get_platforms().add(new Platform(this, 3, new Vec2(98f, 11f), "GOLDEN"));
        get_platforms().add(new Platform(this, 3, new Vec2(110f, 18f), "GOLDEN"));
        get_platforms().add(new Platform(this, 1, new Vec2(113f, 6f), "GOLDEN"));
        get_platforms().add(new Platform(this, 3, new Vec2(128f, 8f), "GOLDEN"));

        {
            // make the ground  
            groundimage = new BodyImage("sprites/background/level_3/ground_sprite.png", 4f);

            Shape shape = new BoxShape(250, 0.1f);
            ground = new StaticBody(this, shape);
            ground.setPosition(new Vec2(170, 0f));
            //ground.addImage(groundimage, new Vec2(-248.5f, 0f));
            ground.setClipped(false);
            for (float i = -248.5f; i < 250f; i = i + 4f) {
                ground.addImage(groundimage, new Vec2(i + 3.5f, -2f));
            }
            Shape wall_shape = new BoxShape(1, 17);
            StaticBody wall = new StaticBody(this, wall_shape);
            wall.setPosition(new Vec2(-3f, 20f));
            wall.addImage(new BodyImage("sprites/platforms/invisible_wall.png"));
            StaticBody wall2 = new StaticBody(this, wall_shape);
            wall2.setPosition(new Vec2(346f, 20f));
            wall2.addImage(new BodyImage("sprites/platforms/invisible_wall.png"));
        }

        Shape side_wall_shape = new BoxShape(1, 16.5f);
        left_wall = new StaticBody(this, side_wall_shape);
        left_wall.setPosition(new Vec2(75f, 22));
        BodyImage wall_piece_image = new BodyImage("sprites/platforms/platform_ground_element.png", 2f);
        for (float i = -17.5f; i < 15.5f; i = i + 2f) {
            left_wall.addImage(wall_piece_image, new Vec2(0, i + 2));
        }

        right_wall = new StaticBody(this, side_wall_shape);
        right_wall.setPosition(new Vec2(150f, 22));
        for (float i = -17.5f; i < 15.5f; i = i + 2f) {
            right_wall.addImage(wall_piece_image, new Vec2(0, i + 2));
        }
        Shape top_wall_shape = new BoxShape(36.5f, 1);
        top_wall = new StaticBody(this, top_wall_shape);
        top_wall.setPosition(new Vec2(112.5f, 37.5f));
        for (float i = -37.5f; i < 35.5f; i = i + 2f) {
            top_wall.addImage(wall_piece_image, new Vec2(i + 2, 0));
        }

        {
            BoxShape small_wall_shape = new BoxShape(1f, 2.7f);
            small_wall = new StaticBody(this, small_wall_shape);
            small_wall.setPosition(new Vec2(75f, 2.8f));

            for (float i = -3.6f; i < 1f; i = i + 2f) {
                small_wall.addImage(wall_piece_image, new Vec2(0, i + 2));
            }
            this.start();
        }
        timer = new Timer (500, this);
        timer.start();
    }
    
    
    public void new_bang (){ //middle is 112, 25    
        Random rand = new Random();
        
        BodyImage one = new BodyImage ("sprites/items/bang.png", 8f );
        float  random_y = rand.nextInt(30) + 3 ;
        float  random_x = rand.nextInt(70) + 1 +80;
        BoxShape bang_shape = new BoxShape (1f ,1f);
        StaticBody bang = new StaticBody(this, bang_shape);
        bang.setPosition(new Vec2(random_x,random_y));
        bang.addImage(one);
        
    }
    
    
    
    //----------------- Overriding ABSTRACT METHODS-----------------------------
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        new_bang();
        
    }
    
    
    
    @Override
    public void hero_dies() {
    }
    @Override
    public Vec2 start_position() {
        return PLAYER_START_POSITION;
    }
    @Override
    public int get_level_code() {
        return LEVEL_CODE;
    }
    @Override
    public Vec2 set_finish_item_position() {
        return FINISH_BARREL_POSITION;
    }
    //--------------- GETTER and SETTER methods --------------------------------
    

    

}
