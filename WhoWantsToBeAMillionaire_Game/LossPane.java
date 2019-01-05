//imports
import java.util.*;
import java.awt.*; 
import java.awt.event.*;
import javax.swing.*; 
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.*;
//this creates a pane, so JFrame is needed
public class LossPane extends JFrame
{
    //GridBagLayout shouldFill and RIGHT_TO_LEFT booleans
    final static boolean RIGHT_TO_LEFT = false;
    public static int money;
    //constructor that allows LossPane to accept a money value
    public int money()
    {
        return this.money;
    }

    public LossPane(int money)
    {
        //set orientation
        if (RIGHT_TO_LEFT)
        {
            this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        //initialize button for closing the pane
        JButton close = new JButton ("OK"); 
        close.addActionListener (new goBackListener());
        //setting up GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        //load the picture of the cheque
        ImageIcon card = new ImageIcon("checkpic.jpg");
        //create JLabel with the image on it
        JLabel pic = new JLabel(card);
        //set minimum size for the picture
        pic.setMinimumSize(new Dimension(500,500));
        //set up GridBagConstraints for the picture
        c.gridx = 5;
        c.gridy = 0;
        //add the picture along with the constraints
        add(pic, c);
        //set up GridBagConstraints for the JLabel
        c.gridx = 5;
        c.gridy = 1;
        //new JLabel basically shows how much money they have won in total
        JLabel label = new JLabel("     YOU HAVE WON $" + money);
        //set text colour and font, etc.
        label.setForeground(Color.white);
        label.setFont(new Font("Serif", Font.BOLD, 36));
        //add the JLabel
        add (label, c);
        //set up GridBagConstraints for the button
        c.gridx = 5;
        c.gridy = 2;
        c.ipadx = 20;
        c.ipady = 20;
        //add the button
        add (close, c);
        pack();
        setTitle ("You Won");
        //set the default close operation and relative location
        setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo (null);
        //set the frame size, background and visibilty
        setSize(1200,700);
        getContentPane().setBackground( new Color(10, 10, 100 ));
        setVisible(true);
    }

    class goBackListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            //closes the pane
            dispose();
        }
    }

    public static void main (String[] args)
    {
        //initialize the pane and make it visible
        LossPane window = new LossPane (money);
        window.setVisible(true);
    }
}
