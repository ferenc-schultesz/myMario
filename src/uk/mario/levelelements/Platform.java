package uk.mario.levelelements;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.Sensor;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import uk.mario.listeners.Dynamic_enemy_turner_sensor;
import java.util.ArrayList;
import org.jbox2d.common.Vec2;
import uk.mario.levels.Level;

/**
 *
 * A platform element for the game, which can be 5 different kind, and any length
 */
public class Platform {

    private ArrayList<StaticBody> platform = new ArrayList<StaticBody>();

    private final BodyImage BRICK = new BodyImage("Sprites/platforms/platform_brick_element.png", 2.5f);
    private final BodyImage GOLDEN = new BodyImage("Sprites/platforms/platform_golden_element.png", 2.5f);
    private final BodyImage CLOUD = new BodyImage("Sprites/platforms/platform_cloud_element.png", 2.5f);
    private final BodyImage GROUND = new BodyImage("Sprites/platforms/platform_ground_element.png", 2.5f);
    private final BodyImage TRUNK = new BodyImage("Sprites/platforms/platform_trunk_element.png", 2.5f);
    private BodyImage selected_type;
/** 
     * Creates a specified long and typed platform on a level
     * @param level the level where we create the platform
     * @param position the position where we create the platform
     * @param pieces the number of pieces that decides how long is the platform
     * @param position the position of the first piece of the platform
     * @param type the type of the platform
     */
    public Platform(Level level, int pieces, Vec2 position, String type) {

        Vec2 block_position = position;
        //check and save which type of platform we want to build
        switch (type) {
            case "BRICK":
                selected_type = BRICK;
                break;
            case "GOLDEN":
                selected_type = GOLDEN;
                break;
            case "CLOUD":
                selected_type = CLOUD;
                break;
            case "GROUND":
                selected_type = GROUND;
                break;
            case "TRUNK":
                selected_type = TRUNK;
                break;
        }
        //build the whole platform using an inner class as building blocks
        for (int i = 0; i < pieces; i++) {
            if (pieces == 1){
                platform.add(new block_single(level));}
            else{
                if (i == 0) {
                    platform.add(new block_first(level));
                }
                if (i == pieces - 1) {
                    platform.add(new block_last(level));
                }

                if (i < pieces - 1 && i > 0) {
                    platform.add(new block(level));
                }
            }        
            platform.get(i).setPosition(block_position);
            platform.get(i).addImage(selected_type);
            block_position.x = block_position.x + 2.4f;
        }
    }

    /**
     * An inner class, one piece of the whole platform not the first or the last
     *
     */
    private class block extends StaticBody {

        private StaticBody block;       
        private final Shape middle_shape;       
        private final SolidFixture middle_fixture;
        // inner class for one block of the platform
        public block(World w) {
            super(w);
            
            middle_shape = new BoxShape(1.2f, 1.25f, new Vec2(0f, 0f));
            middle_fixture = new SolidFixture(this, middle_shape);
            //left_side_fixture.setFriction(0);
            //right_side_fixture.setFriction(0);
        }

    }
/**
     * Inner class for the first block of a platform
     *
     */
    private class block_first extends StaticBody {

        private StaticBody block;
        //private final Shape top_shape;
        private final Shape left_side_shape;
        //private final Shape right_side_shape;
        private final Shape middle_shape;
        private final Shape sensor_shape_left;
        //private final SolidFixture top_fixture;
        private final SolidFixture left_side_fixture;
        //private final SolidFixture right_side_fixture;
        private final SolidFixture middle_fixture;        
        private final Sensor side_sensor;

        public block_first(World w) {
            super(w);
            
            sensor_shape_left = new BoxShape(0.05f, 4f, new Vec2(-1.15f, 2.7f));
            side_sensor = new Sensor(this, sensor_shape_left);
            side_sensor.addSensorListener(new Dynamic_enemy_turner_sensor());
            
            left_side_shape = new BoxShape(0.05f, 1.25f, new Vec2(-1.15f, 0f));
            left_side_fixture = new SolidFixture(this, left_side_shape);
            
            middle_shape = new BoxShape(1.15f, 1.25f, new Vec2(0.05f, 0f));
            middle_fixture = new SolidFixture(this, middle_shape);
            left_side_fixture.setFriction(0);
            
        }

    }
/**
     *Inner class for the last block of the platform
     *
     */
    private class block_last extends StaticBody {

        private StaticBody block;
        
        private final Shape right_side_shape;
        private final Shape middle_shape;
        private final Shape sensor_shape_right;
      
        private final SolidFixture right_side_fixture;
        private final SolidFixture middle_fixture;
        private final Sensor side_sensor;

        // inner class for one block of the platform
        public block_last(World w) {
            super(w);
            sensor_shape_right = new BoxShape(0.05f, 4f, new Vec2(1.15f, 2.7f));
            side_sensor = new Sensor(this, sensor_shape_right);
            side_sensor.addSensorListener(new Dynamic_enemy_turner_sensor());           
            right_side_shape = new BoxShape(0.05f, 1.25f, new Vec2(1.15f, 0f));
            right_side_fixture = new SolidFixture(this, right_side_shape);
            middle_shape = new BoxShape(1.15f, 1.25f, new Vec2(-0.05f, 0f));
            middle_fixture = new SolidFixture(this, middle_shape);           
            right_side_fixture.setFriction(0);
        }

    }
    /**
     * Inner class for one block of the platform if the platform is only one block
     *
     */
    public class block_single extends StaticBody {

        private StaticBody block;
        //private final Shape top_shape;
        private final Shape left_side_shape;
        private final Shape right_side_shape;
        private final Shape middle_shape;
        private final Shape sensor_shape_right;
        private final Shape sensor_shape_left;
        //private final SolidFixture top_fixture;
        private final SolidFixture left_side_fixture;
        private final SolidFixture right_side_fixture;
        private final SolidFixture middle_fixture;
        private final Sensor left_side_sensor;
        private final Sensor right_side_sensor;
        

        // inner class for one block of the platform
        public block_single(World w) {
            super(w);
            
            sensor_shape_right = new BoxShape(0.05f, 4f, new Vec2(1.2f, 2.7f));
            sensor_shape_left = new BoxShape(0.05f, 4f, new Vec2(-1.2f, 2.7f));
            left_side_sensor = new Sensor(this, sensor_shape_left);
            right_side_sensor = new Sensor(this, sensor_shape_right);
            left_side_sensor.addSensorListener(new Dynamic_enemy_turner_sensor());
            right_side_sensor.addSensorListener(new Dynamic_enemy_turner_sensor());            
            left_side_shape = new BoxShape(0.05f, 1.25f, new Vec2(-1.15f, 0f));
            left_side_fixture = new SolidFixture(this, left_side_shape);
            right_side_shape = new BoxShape(0.05f, 1.25f, new Vec2(1.15f, 0f));
            right_side_fixture = new SolidFixture(this, right_side_shape);
            middle_shape = new BoxShape(1.1f, 1.25f, new Vec2(0f, 0f));
            middle_fixture = new SolidFixture(this, middle_shape);
            left_side_fixture.setFriction(0);
            right_side_fixture.setFriction(0);
        }

    }
}
