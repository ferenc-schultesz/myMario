package uk.mario;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.Walker;
import uk.mario.levels.Level;
import org.jbox2d.common.Vec2;

/**
 An extended walker with animation while moving. Also keeps data about its score, life, and the profile who is actually playing the game.
 */
public class Hero extends Walker {

    private boolean hero_looks_right = true;
    private static final Vec2 HERO_SIZE = new Vec2(0.6f, 1.05f);
    private static final BodyImage HERO_STAND_RIGHT = new BodyImage("sprites/hero/hero_stand_right.png", 2.75f);
    private static final BodyImage HERO_MOVE_RIGHT_1 = new BodyImage("sprites/hero/hero_move_right_1.png", 2.75f);
    private static final BodyImage HERO_MOVE_RIGHT_2 = new BodyImage("sprites/hero/hero_move_right_2.png", 2.75f);
    private static final BodyImage HERO_STAND_LEFT = new BodyImage("sprites/hero/hero_stand_left.png", 2.75f);
    private static final BodyImage HERO_MOVE_LEFT_1 = new BodyImage("sprites/hero/hero_move_left_1.png", 2.75f);
    private static final BodyImage HERO_MOVE_LEFT_2 = new BodyImage("sprites/hero/hero_move_left_2.png", 2.75f);
    private BodyImage last_hero_image = (HERO_STAND_RIGHT);
    private static final Shape HERO_SHAPE = new PolygonShape(-0.645f, 1.32f, 0.645f, 1.327f, 0.697f, -1.008f, 0.593f, -1.119f, -0.6f, -1.109f, -0.704f, -1.008f);
    private static int score = 0;
    private static int life = 5;
    private static String profileName;

    /** Creates a Hero for the specified level */
    public Hero(Level _level) {
        super(_level, HERO_SHAPE);
        this.addImage(last_hero_image);
        this.setGravityScale(3);

    }
/** Adds animation to Hero, by changing its bodyImage depending on its x position and on which way he is looking at */
    public void update_sprite() {
        float hero_xPos = this.getPosition().x;
        //if he looks right
        if (this.hero_looks_right) {
            //and he moves, then change image so it look like he walks
            if (Math.abs(this.getLinearVelocity().x) > 0) {
                if ((int) hero_xPos % 2 == 0) {
                    this.swap_sprite(Hero.HERO_MOVE_RIGHT_1);
                }
                if ((int) hero_xPos % 2 == 1) {
                    this.swap_sprite(Hero.HERO_MOVE_RIGHT_2);
                }
            }
            //if he stands
            if (this.getLinearVelocity().x == 0) {
                this.swap_sprite(Hero.HERO_STAND_RIGHT);
            }

        }

        //if he look left
        if (!this.hero_looks_right) {
            //and he moves, then change image so it look like he walks
            if (Math.abs(this.getLinearVelocity().x) > 0) {
                if ((int) hero_xPos % 2 == 0) {
                    this.swap_sprite(Hero.HERO_MOVE_LEFT_1);
                }
                if ((int) hero_xPos % 2 == 1) {
                    this.swap_sprite(Hero.HERO_MOVE_LEFT_2);
                }
            }
            //if he stands
            if (this.getLinearVelocity().x == 0) {
                this.swap_sprite(Hero.HERO_STAND_LEFT);
            }
        }
    }

    /**  A helper method to swap Hero's BodyImage for the animation 
     * @param new_image the next image for the animation
     */
    public void swap_sprite(BodyImage new_image) {
        this.removeImage(this.last_hero_image);
        this.addImage(new_image);
        this.last_hero_image = new_image;
    }

    // -------------------- GETTER and SETTER METHODS --------------------------


    /** Sets Hero's hero_look_right variable 
     * @param b true if hero is looking right*/
    public void set_hero_look_right(boolean b) {
        this.hero_looks_right = b;
    }

    /** Get the score
     * @return returns the value of score
     *
     */
    public static int get_score() {
        return score;
    }
    /** 
     * Increases the score 
     * @param point the number of scores that we want to add 
     */
    public void add_score(int point) {
        score = score + point;
        System.out.println("Score: " + get_score());
        if (score == 10) {
            score = 0;
            addLife(1);
            SoundPlayer.playExtraLife();
            System.out.println("Extra life BANG!!! Actual life:  " + get_life());
        }
    }

    /**  Get the actual lives of the hero 
     * @return  returns the value of life
     */
    public static int get_life() {
        return life;
    }

    /** Returns the actual profile's name
     * @return  value of profileName
     *
     *
     */
    
    public static String getProfileName() {
        return Hero.profileName;
    }
/** Increases hero's life
     * @param extra_life ads this to Hero's actual life
     *
     */
    public static void addLife(int extra_life) {
        life = life + extra_life;
    }

    /** Changes Hero's life
     *@param life the new value of life
     *
     */
    public static void setLife(int life) {
        Hero.life = life;
    }
/** Changes the value of score
     * @param score the new value of score 
     *
     */
    public static void setScore(int score) {
        Hero.score = score;
    }
/** Changes the value of profileName
     * @param name the new value of profileName
     *
     */
    public static void setProfileName(String name) {
        Hero.profileName = name;
    }
    
    //public void jump (float speed){
      //  applyImpulse(new Vec2(300,400));
    //}
    
}
