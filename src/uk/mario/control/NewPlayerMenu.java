package uk.mario.control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * A special JPanel for the new profile menu
 */
public class NewPlayerMenu extends JPanel implements ActionListener {
    
    GameController gameController;
    JPanel holdingPanel;
    BoxLayout boxlayout;
    JButton okButton;
    JTextField nameField;
    Font font;
    Image loadGameBackgroundImage = new ImageIcon("sprites/background/createprofile.png").getImage();
    ImageIcon create = new ImageIcon("sprites/foreground/menu/create.png");
    /** 
     * creates a new player menu
     * @param gameController the controller class for the whole game
     */
    public NewPlayerMenu(GameController gameController) {

        this.gameController = gameController;
        setPreferredSize(new Dimension(1300, 700));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        holdingPanel = new JPanel();
        holdingPanel.setPreferredSize(new Dimension(150, 150));
        holdingPanel.setMaximumSize(new Dimension(250, 150));  
        holdingPanel.setMinimumSize(new Dimension(150, 150));
        
        
        holdingPanel.setLayout(new BorderLayout());
        holdingPanel.setOpaque(false);
        
        
        font = new Font("testFont", Font.BOLD, 25);
        nameField = new JTextField(" (max 10 characters)" , 15);
        
        nameField.setEditable(true);
        nameField.setFont(font);
        
        okButton = new JButton();
        okButton.setBackground(Color.cyan);
        okButton.setAlignmentX(CENTER_ALIGNMENT);
        okButton.addActionListener(this);
        okButton.setIcon(create);

        add(Box.createRigidArea(new Dimension(0, 250)));
        add(holdingPanel);
        
        holdingPanel.add(nameField, BorderLayout.NORTH);
        
        holdingPanel.add(okButton, BorderLayout.SOUTH);
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(loadGameBackgroundImage, 0, 0, null);
    }

    /** 
     * Override method to create a new profile, calls the createNewProfile method from the controlles class
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == okButton) {
            System.out.println("OK Button pressed");
            gameController.removeNewPlayerMenu();
            gameController.startLevel(1);
            gameController.createNewProfile(nameField.getText());
            
        }
    }
    
    
}

