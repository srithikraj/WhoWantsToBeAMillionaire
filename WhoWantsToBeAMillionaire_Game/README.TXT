import javax.swing.JButton; // importing Jbutton, for actions that can be preformed by the components 
import javax.swing.JFrame;

// Test File imports
import java.io.BufferedReader; 
import java.io.File; 
import java.io.FileReader; 
import java.io.IOException; 
import java.nio.charset.StandardCharsets; 
import java.nio.file.Files; 
import java.nio.file.Paths; 
import java.util.Collections; 
import java.util.*; 
import java.util.Scanner;

// swing and awt package 
import java.awt.* ;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.* ;

import java.awt.Container; // importing java container, the components are placed and tracked in the java container 
import java.awt.GridLayout; //layout class that lays out the component of a container in a grid-like manner (same sized)

import javax.swing.JButton;// importing Jbutton, for actions that can be preformed by the components 
import javax.swing.JFrame;// importing Jframe, its a window that sets the border and capsules the components, also includes the title  

// to create random questions

import java.util.Random;

//////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////
public class Millionaire extends JFrame implements ActionListener
{
    // array for the buttons and the options 
    JButton button[] = new JButton [4];  
    Question[] choice;
    boolean result = true;
    int questionCount = 0;
    String [] option = new String[4];  

    
    // Used for the gridBag layout 
    static GridBagConstraints c = new GridBagConstraints();

    // allow these variables to be globally accessed 
    int i;

    //creates a new JLabel
    JLabel label;

    // to generate random questions
    private Random rand = new Random();

    public static Millionaire frame = new Millionaire ();
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    //Random rand = new Random();
    //public static JPanel content = new JPanel ();        // Create a content pane

    public String answer = "a";
    public String optionA = "a) right answer";
    public String optionB = "b) wrong answer";
    public String optionC = "c) wrong answer";
    public String optionD = "d) wrong answer";

    //Shift Code Combo box
    JButton halfBtn = new JButton("50 : 50");
    JButton voteBtn = new JButton("Vote");
    JButton callBtn = new JButton("Call");

    //variables
    public boolean status;
    public int shift;

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    // Method to get the questions don't touch this! 
    // This just gets the questions from the note pad and is used for cearing the text on the buttons and a panle for the question. 
    public Question[] loadQuestions()
    {
        Scanner sc;
        try
        {
            sc = new Scanner ( new File ("Selections.txt"));
            Question choice[]= new Question[20];
            //Question option[]= new Question[4];

            int x=0;
            while (sc.hasNext()) 
            { 
                choice[x] = new Question();
                choice[x].question=sc.nextLine();
                choice[x].option[0]=sc.nextLine();
                choice[x].option[1]=sc.nextLine(); 
                choice[x].option[2]=sc.nextLine();
                choice[x].option[3]=sc.nextLine();
                choice[x].answer=sc.nextLine();            
                x++;    
            }

            sc.close();
            return choice;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    //constructor add the GUI stuff here, remember we all will have our own GUI componenets, so when whe put this together make sure it is dont funtionally 
    public Millionaire()
    {

        choice = loadQuestions(); // loads the questions from notepad 
        Collections.shuffle(Arrays.asList(choice));// shuffles the questions
        // arranging the buttons 
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LAST_LINE_START; //bottom of space

        i=0; // initializing 0

        label = new JLabel(" "+choice[i].question);
        add(label);
        button[0] = new JButton(""+ choice[i].option[0]);
        c.ipady = 10; 
        c.ipadx = 150; 
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        add(button[0],c);
        button[0].addActionListener(this);

        
        button[1] = new JButton("" + choice[i].option[1]);
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        add(button[1],c);
        button[1].addActionListener(this);

        button[2] = new JButton("" + choice[i].option[2]);
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        add(button[2],c);
        button[2].addActionListener(this);

        button[3] = new JButton(""+ choice[i].option[3]);
        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        add(button[3],c);
        button[3].addActionListener(this);
        ////////////////////////////////////////////////////////////////////////////////////////

        
        //halfBtn
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        add(halfBtn,c);
        halfBtn.addActionListener(new halfBtnListener());

        //button[3] = new JButton(""+ choice[i].option[3]);
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        add(voteBtn,c);
        voteBtn.addActionListener(new voteBtnListener());

        //button[3] = new JButton(""+ choice[i].option[3]);
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        add(callBtn,c);
        callBtn.addActionListener(new callBtnListener());
        ///////////////////////////////////////////////////////////////////////////////////////////
        //getContentPane(Options.content);

        //Options options = new Options();
        //button[4] = new JButton(""+ choice[i].option[3]);
        //c.gridx = 1;
        //c.gridy = 2;
        //c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        //add(button[3],c);
        //button[3].addActionListener(this);

    }

    public void actionPerformed(ActionEvent e)
    {
        if (questionCount < 14)
        {
            if(e.getActionCommand().charAt(0) == choice [i].answer.charAt(0))
            {
                System.out.println("Correct!");
                result = true;
                i++;
                label.setText(" "+choice[i].question);

                button[0].setText(choice[i].option[0]);
                button[1].setText(choice[i].option[1]);
                button[2].setText(choice[i].option[2]);
                button[3].setText(choice[i].option[3]);
                setVisible(true);
            }
            else
            {
                System.out.println("Wrong");
                result = false;
                questionCount = 100;
            }
            //if(result == true)
            //{
            // to get random questions

            //while(random >= 12)
            //{

            //}
            questionCount++;
        }
    }

    // main method 
    public static void main ( String[] args ) throws Exception
    {
        Millionaire frame = new Millionaire ();
        frame.setSize(800,700); // sets the size of frame
        frame.setVisible( true ); // sets the frame visible
        //Options opt = new Options();

        //frame.add(o);

        //frame.pack();
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); // closes when crossed out
    }

    class halfBtnListener implements ActionListener // Inner class for handling events
    {
        public void actionPerformed (ActionEvent e)
        {
            int a = 0;
            String ans = "";
            if (answer == "a")
                a = 1;
            else if (answer == "b")
                a = 2;
            else if (answer == "c")
                a = 3;
            else if (answer == "d")
                a = 4;

            int q = a;

            while(q == a)
            {
                q = rand.nextInt(4)+ 1;
                if (q != a)
                {
                    if(q == 1)
                    {
                        ans = optionA;
                        if (answer == "a")
                            ans += " " + optionA;
                        else if (answer == "b")
                            ans += " " + optionB;
                        else if (answer == "c")
                            ans += " " + optionC;
                        else if (answer == "d")
                            ans += " " + optionD;
                    }
                    else if(q == 2)
                    {
                        if (a < q)
                        {
                            if (answer == "a")
                                ans = optionA;
                            else if (answer == "b")
                                ans = optionB;
                            else if (answer == "c")
                                ans = optionC;
                            else if (answer == "d")
                                ans = optionD;
                            ans += " " + optionB;
                        }
                        else
                        {
                            ans = optionB;
                            if (answer == "a")
                                ans += " " + optionA;
                            else if (answer == "b")
                                ans += " " + optionB;
                            else if (answer == "c")
                                ans += " " + optionC;
                            else if (answer == "d")
                                ans += " " + optionD;
                        }
                    }
                    else if(q == 3)
                    {
                        if (a < q)
                        {
                            if (answer == "a")
                                ans = optionA;
                            else if (answer == "b")
                                ans = optionB;
                            else if (answer == "c")
                                ans = optionC;
                            else if (answer == "d")
                                ans = optionD;
                            ans += " " + optionC;
                        }
                        else
                        {
                            ans = optionC;
                            if (answer == "a")
                                ans += " " + optionA;
                            else if (answer == "b")
                                ans += " " + optionB;
                            else if (answer == "c")
                                ans += " " + optionC;
                            else if (answer == "d")
                                ans += " " + optionD;
                        }
                    }
                    else if(q == 4)
                    {
                        if (answer == "a")
                            ans = optionA;
                        else if (answer == "b")
                            ans = optionB;
                        else if (answer == "c")
                            ans = optionC;
                        else if (answer == "d")
                            ans = optionD;
                        ans += optionD;
                    }
                }
            }
            add(new JLabel(ans));
            setVisible(true);
            halfBtn.setEnabled(false);
        }
    }

    class voteBtnListener implements ActionListener // Inner class for handling events
    {
        public void actionPerformed (ActionEvent e)
        {
            setLayout (new GridLayout (5,5));
            String ans = "";
            if (answer == "a")
                ans = optionA;
            else if (answer == "b")
                ans = optionB;
            else if (answer == "c")
                ans = optionC;
            else
                ans = optionD;
            add(new JLabel("The majority voted for "+ ans));
            setVisible(true);
            voteBtn.setEnabled(false);
        }
    }

    class callBtnListener implements ActionListener // Inner class for handling events
    {
        public void actionPerformed (ActionEvent e)
        {
            String ans = "";
            setLayout (new GridLayout (5,5));
            if (answer == "a")
                ans = optionA;
            else if (answer == "b")
                ans = optionB;
            else if (answer == "c")
                ans = optionC;
            else
                ans = optionD;

            add(new JLabel("I am pretty sure the answer is "+ ans));
            setVisible(true);
            callBtn.setEnabled(false);
        }
    }

}



// //setLayout( new FlowLayout() );
//         //choice = loadQuestions();
//         //add(new JLabel(""+choice[0].question)); 
// 
//         //JFrame aWindow = new JFrame("Grid Tester"); // set the title for the window 
//         GridLayout grid = new GridLayout(2, 2, 10, 10); // this grid layout sets the rows:3, columns:4, hgap: 30, vgap;20
//         //Container content = aWindow.getContentPane();  // creating the container 
//         setLayout(grid); // set the layout to grid 
//         JButton button = null; 
//         for (int i = 1; i <= 4; i++) 
//         {
//             add(button = new JButton(" Button " + i)); // add the button for each component 
//         }
//         //pack(); 
//         setVisible(true); // set the components to visible 
