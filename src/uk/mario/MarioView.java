package uk.mario;

import city.cs.engine.UserView;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import uk.mario.levels.Level;

/**
 *
 * @author Ferenc
 * A specialised UserView with own background and methods to draw data on this screen about the game 
 */
public class MarioView extends UserView {

    private Image background_level_1,background_level_2, background_level_3,background_level_4, score_text, lives_text, level_text, profile_text;
   
    private Level level;
    private int level_code;

    private Image zero, one, two, three, four, five, six, seven, eight, nine;
    private Image q,w,r,t,y,u,i,o,p,a,s,d,f,g,h,j,k,l,z,x,c,v,b,n,m,e, unknown;
    private Image[] stringImage;
    
    /** 
     * Creates a MarioView with a specific size that shows a level 
     * @param level the level that we want to see
     * @param width the width of the MarioView
     * @param height the height of the MarioView
     */
    public MarioView(Level level, int width, int height) {
        super(level, width, height);

        background_level_1 = new ImageIcon("sprites/background/level_1/background.png").getImage();
        background_level_2 = new ImageIcon("sprites/background/level_2/background.png").getImage();
        background_level_3 = new ImageIcon("sprites/background/level_3/background.png").getImage();
        background_level_4 = new ImageIcon("sprites/background/level_4/background.png").getImage();
        score_text = new ImageIcon("sprites/foreground/score_text.png").getImage();
        lives_text = new ImageIcon("sprites/foreground/lives_text.png").getImage();
        level_text = new ImageIcon("sprites/foreground/level_text.png").getImage();
        profile_text = new ImageIcon("sprites/foreground/profile_text.png").getImage();
        this.level = level;
        level_code = level.get_level_code();
        
        //this.setGridResolution(1);
        
    }

    /** 
     * Override method to draw the background for the different levels
     *
     */
    @Override
    protected void paintBackground(Graphics2D g) {
        level_code = level.get_level_code();
        switch (level_code){
            case 1:
                g.drawImage(background_level_1, 0, 0, this);
                break;
            case 2:
                g.drawImage(background_level_2, 0, 0, this);
                break;
            case 3:
                g.drawImage(background_level_3, 0, 0, this);
                break;
            case 4:
                g.drawImage(background_level_4, 0, 0, this);
                break;
            case 5:
                g.drawImage(background_level_4, 0, 0, this);
                break;    
        }
    }
/** 
     * Override method to draw the different elements to the foreground of the game. (Score, life, level, profile.)
     *
     */
    @Override
    protected void paintForeground(Graphics2D g) {
        
        // Drawing actual score
        int digit_one = level.get_hero().get_score() / 10;
        int digit_two = level.get_hero().get_score() % 10;
        
        g.drawImage(score_text, 520, 0, 90, 51, this);
        g.drawImage(get_number_image(digit_one), 610, 11, 27, 40, this);
        g.drawImage(get_number_image(digit_two), 635, 11, 27, 40, this);
        
        //Drawing lives       
        digit_one = Hero.get_life() / 10;
        digit_two = Hero.get_life() % 10;
        
        g.drawImage(lives_text, 800, 0, 90, 51, this);
        g.drawImage(get_number_image(digit_one), 890, 11, 27, 40, this);
        g.drawImage(get_number_image(digit_two), 915, 11, 27, 40, this);
        
        //Drawing Level info
        digit_one = level.get_level_code() / 10;
        digit_two = level.get_level_code() % 10;
        
        g.drawImage(level_text, 1020, 0, 90, 51, this);
        g.drawImage(get_number_image(digit_one), 1110, 11, 27, 40, this);
        g.drawImage(get_number_image(digit_two), 1135, 11, 27, 40, this);
        
        //Drawing profile info
        g.drawImage(profile_text,10,0, 90,51, this);
        String profileName = Hero.getProfileName();
        stringImage = getStringImage(profileName);
        int j = 100;
        for (int i = 0; i < stringImage.length ; i++){
            g.drawImage(stringImage[i] ,i * 25  +j, 5, 33,42 , this);
            if (i == 10){i = stringImage.length;}
        }
       

    }
/** 
     * Return an image for a number
     * @param number the number that we want to convert to image
     * @return Returns an ImageIcon for the number
     */
    public Image get_number_image(int number) {
        zero = new ImageIcon("sprites/foreground/0.png").getImage();
        one = new ImageIcon("sprites/foreground/1.png").getImage();
        two = new ImageIcon("sprites/foreground/2.png").getImage();
        three = new ImageIcon("sprites/foreground/3.png").getImage();
        four = new ImageIcon("sprites/foreground/4.png").getImage();
        five = new ImageIcon("sprites/foreground/5.png").getImage();
        six = new ImageIcon("sprites/foreground/6.png").getImage();
        seven = new ImageIcon("sprites/foreground/7.png").getImage();
        eight = new ImageIcon("sprites/foreground/8.png").getImage();
        nine = new ImageIcon("sprites/foreground/9.png").getImage();

        switch (number) {
            case 0:
                return zero;
            case 1:
                return one;
            case 2:
                return two;
            case 3:
                return three;
            case 4:
                return four;
            case 5:
                return five;
            case 6:
                return six;
            case 7:
                return seven;
            case 8:
                return eight;
            case 9:
                return nine;

        }

        return null;
    }
    /** 
     * Returns an image for a String. The image includes all letter of the String
     * @param string the text what we want to convert to image
     * @return returns an Image which show the input text
     *
     */
    public Image[] getStringImage (String string){
        string.toLowerCase();
        stringImage = new Image[string.length()];
        
        for (int i = 0 ; i < string.length() ; i++){
            stringImage[i] = getLetterImage(Character.toString(string.toLowerCase().charAt(i))); 
        }
        
        return stringImage;
    }
    /** 
     * Converts one letter to an Image
     * @param letter the letter that we would like to convert
     * @return returns one ImageIcon for the specified letter
     */
    public Image getLetterImage (String letter){
        
        a = new ImageIcon("sprites/foreground/abc/a.png").getImage();
        b = new ImageIcon("sprites/foreground/abc/b.png").getImage();
        c = new ImageIcon("sprites/foreground/abc/c.png").getImage();
        d = new ImageIcon("sprites/foreground/abc/d.png").getImage();
        e = new ImageIcon("sprites/foreground/abc/e.png").getImage();
        f = new ImageIcon("sprites/foreground/abc/f.png").getImage();
        g = new ImageIcon("sprites/foreground/abc/g.png").getImage();
        h = new ImageIcon("sprites/foreground/abc/h.png").getImage();
        i = new ImageIcon("sprites/foreground/abc/i.png").getImage();
        j = new ImageIcon("sprites/foreground/abc/j.png").getImage();
        k = new ImageIcon("sprites/foreground/abc/k.png").getImage();
        l = new ImageIcon("sprites/foreground/abc/l.png").getImage();
        m = new ImageIcon("sprites/foreground/abc/m.png").getImage();
        n = new ImageIcon("sprites/foreground/abc/n.png").getImage();
        o = new ImageIcon("sprites/foreground/abc/o.png").getImage();
        p = new ImageIcon("sprites/foreground/abc/p.png").getImage();
        q = new ImageIcon("sprites/foreground/abc/q.png").getImage();
        r = new ImageIcon("sprites/foreground/abc/r.png").getImage();
        s = new ImageIcon("sprites/foreground/abc/s.png").getImage();
        t = new ImageIcon("sprites/foreground/abc/t.png").getImage();
        w = new ImageIcon("sprites/foreground/abc/w.png").getImage();
        y = new ImageIcon("sprites/foreground/abc/y.png").getImage();
        z = new ImageIcon("sprites/foreground/abc/z.png").getImage();
        x = new ImageIcon("sprites/foreground/abc/x.png").getImage();
        v = new ImageIcon("sprites/foreground/abc/v.png").getImage();
        unknown = new ImageIcon("sprites/foreground/abc/unknown.png").getImage();
        switch (letter) {
            case "a":
                return a;
            case "q":
                return q;
            case "w":
                return w;
            case "e":
                return e;
            case "r":
                return r;
            case "t":
                return t;
            case "y":
                return y;
            case "u":
                return u;
            case "i":
                return i;
            case "o":
                return o;
            case "p":
                return p;
            case "s":
                return s;
            case "d":
                return d;
            case "f":
                return f;
            case "g":
                return g;
            case "h":
                return h;
            case "j":
                return j;
            case "k":
                return k;
            case "l":
                return l;
            case "z":
                return z;
            case "x":
                return x;
            case "c":
                return c;
            case "v":
                return v;
            case "b":
                return b;
            case "n":
                return n;
            case "m":
                return m;
            default:
                return unknown;
        }
    }
    
    /** 
     * Changes the level that the MarioView shows
     * @param _level the new level that we would like to follow
     */
    public void set_level (Level _level){
    this.level = _level;}

}
