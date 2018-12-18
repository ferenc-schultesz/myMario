
package uk.mario.enemies;

import city.cs.engine.*;
import uk.mario.listeners.Dynamic_enemy_killer_sensor;
import uk.mario.listeners.Enemy_collision_detector;
import uk.mario.listeners.Hero_killer_sensor;
import uk.mario.levels.Level;
import org.jbox2d.common.Vec2;
import uk.mario.SoundPlayer;

/**
 *
 * Basic brown enemy for the game
 */
public class Brown_enemy extends Dynamic_enemy {

    private BodyImage last_sprite;
    private float speed;
    private Vec2 start_position;
    private Vec2 size = new Vec2(1f, 1f);   
    private BodyImage sprite_one = new BodyImage("sprites/enemies/Enemy_Dynamic/enemy_brown_1.png", 2.5f);
    private BodyImage sprite_two = new BodyImage("sprites/enemies/Enemy_Dynamic/enemy_brown_2.png", 2.5f);

    /** 
     * Creates a brown enemy at the specified, level, and speed
     * @param _position the position where the enemy will appear
     * @param _speed the starting speed of the enemy
     * @param _level the level where the enemy will appear
     * @param type the type of the brown enemy (at the moment there's only one type, later will be extended
     */
    public Brown_enemy(Vec2 _position, float _speed, String type, Level _level) {
        super(_level);
        speed = _speed;
        start_position = _position;
        
        
        PolygonShape top_shape = new PolygonShape(-0.847f,0.35f, -0.644f,1.156f, 0.647f,1.156f, 0.844f,0.356f);
        PolygonShape bottom_shape = new PolygonShape(-1.25f,0.353f, -1.147f,-0.959f, 1.147f,-0.95f, 1.244f,0.341f);

        Sensor top_sensor = new Sensor(this, top_shape);
        Sensor bottom_sensor = new Sensor(this, bottom_shape);

        SolidFixture top_fixture = new SolidFixture(this, top_shape);
        SolidFixture bottom_fixture = new SolidFixture(this, bottom_shape);

        bottom_sensor.addSensorListener(new Hero_killer_sensor(_level));
        top_sensor.addSensorListener(new Dynamic_enemy_killer_sensor(_level));
        

        
        this.setPosition(start_position);
        this.startWalking(speed);
        

        
        this.addCollisionListener(new Enemy_collision_detector(_level));
        
        this.bodyimage_selector(type);

    }
    
    @Override
    public void move_to_startposition(){
        setPosition(start_position);
    }
    
    @Override
    public void turn() {
        speed = speed * -1;
        startWalking(speed);
    }
    @Override
    public void die() {
        destroy();
        SoundPlayer.playBrownEnemyDies();

    }

    
    @Override
    public void update_sprite (){
        float xPos = this.getPosition().x;

            if ((int) xPos % 2 == 0) {
                this.removeImage(last_sprite);
                this.addImage(sprite_one);
                last_sprite = sprite_one;
            }
            if ((int) xPos % 2 == 1) {
                this.removeImage(last_sprite);
                this.addImage(sprite_two);
                last_sprite = sprite_two;
            }
        
    }
    
/** 
     * to decide which kind of brown enemy image we use (at the monent we only have one kind)
     */
   private void bodyimage_selector(String type) {
        switch (type) {
            case "BROWN":
                this.addImage(sprite_one);
                this.last_sprite = sprite_one;
        }
    }

    public Vec2 get_size() {
        return size;
    }

    @Override
    public void jump(int frame) {
        //this one can't jump
    }
}

