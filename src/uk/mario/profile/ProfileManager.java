package uk.mario.profile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import org.jbox2d.common.Vec2;
import uk.mario.Hero;
import uk.mario.control.GameController;

/**
 * 
 * The profile controller class, we save and load the game by the help of this class
 */
public class ProfileManager {
    
    GameController gameController;
    Profile profile;
    private String loadedProfile;
    
    public ProfileManager (GameController gameController){
        this.gameController = gameController;
    }
    
    /**
     * This methods creates a profile and saves it in a file named by the profile name
     * @param playerName The name of the profile and the file at Profiles/..
     * @param lives number of lives
     * @param level actual level
     * @param score actual score
     * @param marioPos hero's positio on the actual level
     */
    
    public void createProfile(String playerName, int lives, int level, int score, Vec2 marioPos){
        profile = new Profile(playerName, lives, level, score, marioPos);
        
        
         try {
            
            OutputStream file = new FileOutputStream("Profiles/" + playerName);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);
            output.writeObject(profile);
            output.flush();
            output.close();
            loadedProfile = playerName;
            Hero.setProfileName(playerName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Profile created, name: " + profile.getName() + " level: " + profile.getLevel() + " Score: "+ profile.getScore() + 
               " Lives: " + profile.getLives() +  " Hero pos: " + profile.getMarioPos());
    }
    
    /**
     * Saves the actual progress of the game to the last loaded/created profile's file
     * @param lives number of lives
     * @param level actual level
     * @param score actual score
     * @param marioPos hero's position on the level
     */
    public void saveGame (int lives, int level, int score, Vec2 marioPos){
    profile = new Profile(loadedProfile, lives, level, score, marioPos);
        
        
         try {
            
            OutputStream file = new FileOutputStream("Profiles/" + loadedProfile);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);
            output.writeObject(profile);
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
         
         System.out.println("Profile saved, name: " + profile.getName() + " level: " + profile.getLevel() + " Score: "+ profile.getScore() + 
               " Lives: " + profile.getLives() +  " Hero pos: " + profile.getMarioPos());
    }
    /**
     * Loads a specified profile and calls the gameController start loaded game method
     * @param profileName the profile that we want to load
     */
    public void loadProfile (String profileName){
    

         InputStream file;
        try {
            file = new FileInputStream("Profiles/" + profileName);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream (buffer);
            profile = (Profile)input.readObject();
            
            input.close();
            loadedProfile = profileName;
            Hero.setProfileName(profileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Can't find profile, called: " + profileName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Profile loaded, name: " + profile.getName() + " level: " + profile.getLevel() + " Score: "+ profile.getScore() + 
               " Lives: " + profile.getLives() +  " Hero pos: " + profile.getMarioPos());
    gameController.startLoadedProfile(profile.getName(), profile.getLevel(), profile.getLives(), profile.getScore(), profile.getMarioPos());

    }
    
}
