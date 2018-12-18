package uk.mario.enemies;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.Sensor;
import uk.mario.listeners.Hero_killer_sensor;
import uk.mario.levels.Level;
import org.jbox2d.common.Vec2;

/**
 *
 * An enemy for the pipes in the game. 
 */
public class Pipe_flower extends Dynamic_enemy {

    private Vec2 original_position;
    private BodyImage flower_close = new BodyImage("sprites/enemies/enemy_dynamic/enemy_flower_bottom_one.png", 5.5f);
    private BodyImage flower_open = new BodyImage("sprites/enemies/enemy_dynamic/enemy_flower_bottom_two.png", 5.5f);
    private BodyImage last_sprite;

    /** 
     *  Creates a flower on the specified position and level
     * @param position the position of the flower
     * @param level the level where we create the flower
     */
    public Pipe_flower(Vec2 position, Level level) {
        super(level, new BoxShape(1.2f, 2.5f));
        original_position = position;
        
        BoxShape sensor_shape = new BoxShape (1.2f, 2.5f, new Vec2 (0f,0f));
        Sensor sensor = new Sensor (this, sensor_shape);
        sensor.addSensorListener(new Hero_killer_sensor(level));
        
        setPosition(position);
        setGravityScale(0.2f);
        addImage(flower_close);
        last_sprite = flower_close;
        level.get_dynamic_enemies().add(this);
    }

    /** 
     *  Moves the flower to its original position
     */
    public Vec2 get_original_position() {
        return original_position;
    }

    @Override
    public void turn() {
        
    }

    @Override
    public void die() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update_sprite() {
        float yPos = this.getPosition().y;
        
        if ((int) yPos % 2 == 0) {

            this.removeImage(last_sprite);
            this.addImage(flower_open);
            last_sprite = flower_open;
        }
        if ((int) yPos % 2 == 1) {
            this.removeImage(last_sprite);
            this.addImage(flower_close);
            last_sprite = flower_close;
        }

    }

    @Override
    public void move_to_startposition() {
        setPosition(original_position);
    }

    @Override
    public void jump(int frame) {
        //the jump of the flowers is done in the pipe class because I have to destroy and create fixtures
        //at every jump, it's more simple to do it there
    }

}
