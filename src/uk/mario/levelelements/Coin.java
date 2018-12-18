package uk.mario.levelelements;

import city.cs.engine.BodyImage;
import city.cs.engine.GhostlyFixture;
import city.cs.engine.PolygonShape;
import city.cs.engine.Sensor;
import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;
import city.cs.engine.StaticBody;
import uk.mario.Hero;
import uk.mario.levels.Level;
import org.jbox2d.common.Vec2;
import uk.mario.SoundPlayer;

/**
 Coin pick ups for the game, each is worth 1 point.
 */
public class Coin extends StaticBody implements SensorListener {

    Level level;
    BodyImage image_one = new BodyImage("sprites/items/coin_one.png", 2f);
    BodyImage image_two = new BodyImage("sprites/items/coin_two.png", 2f);
    BodyImage image_three = new BodyImage("sprites/items/coin_three.png", 2f);
    BodyImage image_four = new BodyImage("sprites/items/coin_four.png", 2f);
    BodyImage last_image;

    /** Creates a coin to the specified level and location 
     * @param _level the level where the coin appears
     * @param position the position of the coin
     */
    public Coin(Level _level, Vec2 position) {
        super(_level);
        level = _level;
        PolygonShape middle_shape = new PolygonShape(-0.187f, 0.75f, 0.186f, 0.752f, 0.561f, 0.373f, 0.561f, -0.373f, 0.186f, -0.75f, -0.186f, -0.748f, -0.564f, -0.371f, -0.562f, 0.375f);
        GhostlyFixture coin_fixture = new GhostlyFixture(this, middle_shape);

        PolygonShape sensor_shape = new PolygonShape(-0.187f, 0.75f, 0.186f, 0.752f, 0.561f, 0.373f, 0.561f, -0.373f, 0.186f, -0.75f, -0.186f, -0.748f, -0.564f, -0.371f, -0.562f, 0.375f);
        Sensor coin_sensor = new Sensor(this, sensor_shape);

        addImage(image_one);
        last_image = image_one;
        coin_sensor.addSensorListener(this);
        setPosition(position);
        level.get_coins().add(this);
    }

    /** Adds the animation for the coins by changing their image at a certain time 
     * @param frame number of actual frame to help the animation
     */
    public void update_sprite(int frame) {

        if (frame % 53 == 0) {
            removeImage(last_image);
            addImage(image_two);
            last_image = image_two;
        }
        if (frame % 101 == 0) {
            removeImage(last_image);
            addImage(image_one);
            last_image = image_one;
        }
        if (frame % 151 == 0) {
            removeImage(last_image);
            addImage(image_three);
            last_image = image_three;
        }
        if (frame % 199 == 0) {
            removeImage(last_image);
            addImage(image_four);
            last_image = image_four;
        }
    }

    /** Destroys the coin when Mario begins contact with it, also adds a point to score and plays the sound for the pickup */
    @Override
    public void beginContact(SensorEvent e) {
        if (e.getContactBody().getClass().equals(Hero.class)) {
            this.destroy();
            SoundPlayer.playCoin();
            level.get_hero().add_score(1);

        }
    }
    
    /**  Override method from sensorListener interface, does nothing at this class*/
    @Override
    public void endContact(SensorEvent e) {

    }

}
