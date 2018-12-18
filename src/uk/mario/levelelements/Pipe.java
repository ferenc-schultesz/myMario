package uk.mario.levelelements;

import uk.mario.enemies.Pipe_flower;
import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.GhostlyFixture;
import city.cs.engine.Sensor;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.StaticBody;
import uk.mario.listeners.Dynamic_enemy_turner_sensor;
import uk.mario.levels.Level;
import org.jbox2d.common.Vec2;

/**
 *
 * A static level element for the game a green pipe, which can have a flower inside
 */
public class Pipe extends StaticBody {

    private final Level level;
    //private Level_1 level;
    private final Vec2 pipe_position;
    private Pipe_flower flower;
    private final boolean has_flower;
    private boolean top_destroyed = false;
    private Shape top_shape = new BoxShape(2.05f, 0.1f, new Vec2(0, 3.9f));
    private SolidFixture top_fixture = new SolidFixture(this, top_shape);

    /** 
     * Creates a flower to the specified level and position, with or without a flower
     * @param _level the level where we create the pipe
     * @param position the position where we want the pipe
     * @param with_flower true if we want a flower inside, false if we don't
     */
    public Pipe(Level _level, Vec2 position, boolean with_flower) {
        super(_level);
        //level = _level;
        pipe_position = position;
        has_flower = with_flower;
        level = _level;

        BoxShape left_side_shape = new BoxShape(0.1f, 4f, new Vec2(-2.15f, 0f));
        SolidFixture left_side_fixture = new SolidFixture(this, left_side_shape);

        Shape middle_shape = new BoxShape(2f, 4f, new Vec2(0, 0));
        GhostlyFixture middle_fixture = new GhostlyFixture(this, middle_shape);

        Shape right_side_shape = new BoxShape(0.1f, 4f, new Vec2(2.15f, 0));
        SolidFixture right_side_fixture = new SolidFixture(this, right_side_shape);

        BoxShape left_sensor_shape = new BoxShape(0.1f, 4f, new Vec2(-2.25f, 0));
        BoxShape right_sensor_shape = new BoxShape(0.1f, 4f, new Vec2(2.25f, 0));

        Sensor left_sensor = new Sensor(this, left_sensor_shape);
        Sensor right_sensor = new Sensor(this, right_sensor_shape);

        left_sensor.addSensorListener(new Dynamic_enemy_turner_sensor());
        right_sensor.addSensorListener(new Dynamic_enemy_turner_sensor());
        right_side_fixture.setFriction(0f);
        left_side_fixture.setFriction(0f);
        addImage(new BodyImage("sprites/platforms/pipe_bottom_medium.png", 8f));
        this.setPosition(position);

        if (with_flower == true) {
            _level.getDynamicBodies().add(flower = new Pipe_flower(position, level));
        }
    }

    /** 
     * This method make the flowers jump at every 300. frame. When this happens it destroys the top
     * solid fixture of the pipe and when the flower falls back it recreates it
     * @param frame_counter the actual frame of the game, to decide when the flower jumps
     */
    public void jump_flower(int frame_counter) {

        //this variable take the difference between the middle of the pipe and the hero's position
        float hero_pipe_x_diff = this.pipe_position.x - level.get_hero().getPosition().x;

        //System.out.println("Pipe pos: " + this.getPosition().x + "Player x pos: " + Level_1.hero.getPosition().x);
        //at every 300 frames the flowers come out of the pipes
        if (frame_counter % 300 == 0) {
            this.flower.jump(5.5f);

        }
        // if the hero is not on the middle of the pipe and the flower is coming out we destroy the top of the pipe and let the flower out
        if (this.flower.getPosition().y > this.getPosition().y + 0.9f && !top_destroyed && (Math.abs(hero_pipe_x_diff) > 1.6f)) {
            this.top_fixture.destroy();
            top_destroyed = true;

        }
        // if the lid is destroyed and the flower moved back in to the pipe we put back the top of the pipe
        if (this.flower.getPosition().y < this.getPosition().y + 0.9f && top_destroyed) {

            top_fixture = new SolidFixture(this, top_shape);
            top_destroyed = false;
        }
    }

    public Pipe_flower get_flower() {
        return this.flower;
    }

    public boolean with_flower() {
        return this.has_flower;
    }

}
