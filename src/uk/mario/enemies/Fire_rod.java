/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.mario.enemies;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.DynamicBody;
import city.cs.engine.GhostlyFixture;
import city.cs.engine.Sensor;
import city.cs.engine.SolidFixture;
import uk.mario.levels.Level;
import uk.mario.listeners.Hero_killer_sensor;
import org.jbox2d.common.Vec2;

/**
 *
 * A special enemy for the game, a spinning fire rod. 
 */
public class Fire_rod extends DynamicBody {
    private Vec2 start_position;
    private Level level;
    
    
    BodyImage image_one = new BodyImage("sprites/enemies/enemy_static/fire_rod_1.png", 1.5f);
    BodyImage image_two = new BodyImage("sprites/enemies/enemy_static/fire_rod_2.png", 1.5f);
    BodyImage image_three = new BodyImage("sprites/enemies/enemy_static/fire_rod_3.png", 1.5f);
    BodyImage image_four = new BodyImage("sprites/enemies/enemy_static/fire_rod_4.png", 1.5f);
    BodyImage last_image;
    
    /** 
     * Creates a fire rod at the specified level and position
     * @param _level the level where we create the rod
     * @param position the position of the rod
     */
    public Fire_rod(Level _level, Vec2 position) {
        super(_level);
        level = _level;
        
        start_position = position;
        
        BoxShape hidden_half_rod_shape =  new BoxShape(4.7f,0.5f, new Vec2(-4.7f,0f));
        BoxShape visible_half_rod_shape =  new BoxShape(4.7f,0.5f, new Vec2(4.7f,0f));
        BoxShape middle_shape = new BoxShape(0.25f,0.25f, new Vec2(0,0));
        
        SolidFixture middle = new SolidFixture(this, middle_shape);
        GhostlyFixture invisible_part = new GhostlyFixture(this, hidden_half_rod_shape);
        GhostlyFixture visible_part = new GhostlyFixture(this, visible_half_rod_shape);
        
        invisible_part.setDensity(1.8f);
        
        Sensor middle_sensor = new Sensor(this, middle_shape);
        middle_sensor.setDensity(0.00000000001f);
        middle_sensor.addSensorListener(new Hero_killer_sensor(level));
  
        Sensor fire_part_sensor = new Sensor(this, visible_half_rod_shape);
        fire_part_sensor.setDensity(0.00000000001f);
        fire_part_sensor.addSensorListener(new Hero_killer_sensor(level));
        setPosition(start_position);
        setGravityScale(0);
        applyTorque(10f);
        setAngularVelocity(1f);
        addImage(image_one);
        last_image = image_one;
        this.setClipped(true);
        
    }
    /** 
     * Resets the position of the rod
     */
    public void reset_position (){
        setPosition(start_position);
        setLinearVelocity(new Vec2(0f,0f));
        applyTorque(10f);
        setAngularVelocity(1f);
    }
    
    /** 
     * Creates the animation of the rod
     * @param frame the actual frame of the game helps us to do the animation for the rod
     */
    public void update_sprite (int frame){
        if (frame % 23 == 0) {
            removeImage(last_image);
            addImage(image_two);
            last_image = image_two;
        }
        if (frame % 47 == 0) {
            removeImage(last_image);
            addImage(image_one);
            last_image = image_one;
        }
        if (frame % 73 == 0) {
            removeImage(last_image);
            addImage(image_three);
            last_image = image_three;
        }
        if (frame % 101 == 0) {
            removeImage(last_image);
            addImage(image_four);
            last_image = image_four;
        }
    }
    
}
