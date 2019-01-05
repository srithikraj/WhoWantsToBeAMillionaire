//importing Jbutton, for actions that can be preformed by the components 
import javax.swing.JButton; 
import javax.swing.JFrame;
//Test File imports
import java.io.BufferedReader; 
import java.io.File; 
import java.io.FileReader; 
import java.io.*; 
import java.nio.charset.StandardCharsets; 
import java.nio.file.Files; 
import java.nio.file.Paths; 
import java.util.Collections; 
import java.util.*; 
import java.util.Scanner;
//swing and awt package 
import java.awt.* ;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.* ;
import java.awt.Container; // importing java container, the components are placed and tracked in the java container 
import java.awt.GridLayout; //layout class that lays out the component of a container in a grid-like manner (same sized)
import javax.swing.JButton;// importing Jbutton, for actions that can be preformed by the components 
import javax.swing.JFrame;// importing Jframe, its a window that sets the border and capsules the components, also includes the title  
//to create random questions
import java.util.Random;
public class Millionaire extends JFrame implements ActionListener
{
    //array for the buttons and the options 
    JButton button[] = new JButton [4];  
    Question[] choice;
    boolean result = true;
    int questionCount = 0;
    String [] option = new String[4];  
    //used for the gridBag layout 
    static GridBagConstraints c = new GridBagConstraints();
    //allow these variables to be globally accessed 
    int i;
    //creates a new JLabel
    JLabel label;
    //to generate random questions
    private Random rand = new Random();
    public static Millionaire frame = new Millionaire ();
    public String answer = "a";
    public String optionA;
    public String optionB;
    public String optionC; 
    public String optionD;
    JButton halfBtn = new JButton("50 : 50");
    JButton voteBtn = new JButton("Vote");
    JButton callBtn = new JButton("Call");
    //variables
    public boolean status;
    public int shift;
    public int money = 0;
    public int safetynet = 0;
    JLabel moneyDisplay = new JLabel("MONEY LEVEL : " + money);
    //method to get the questions
    //this just gets the questions from the note pad and is used for clearing the text on the buttons and a panel for the question
    public Question[] loadQuestions()
    {
        Scanner sc;
        try
        {
            sc = new Scanner ( new File ("Selections.txt"));
            Question choice[]= new Question[50];
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

    public Millionaire()
    {
        //loads the questions from notepad
        choice = loadQuestions();  
        //shuffles the questions
        Collections.shuffle(Arrays.asList(choice));
        //arranging the buttons 
        setLayout(new GridBagLayout());
        setTitle("WHO WANTS TO BE A MILLIONAIRE");
        GridBagConstraints c = new GridBagConstraints();
        GridBagConstraints c1 = new GridBagConstraints();
        GridBagConstraints c2 = new GridBagConstraints();
        GridBagConstraints c3 = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c1.gridx = 0;
        c1.gridy = 0;
        c2.gridx = 0;
        c2.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START; //bottom of space
        c1.anchor = GridBagConstraints.LINE_START; //bottom of space
        c2.anchor = GridBagConstraints.PAGE_START; //bottom of space
        //millionaire picture
        ImageIcon card=new ImageIcon("Millionaire.png");
        JLabel pic = new JLabel(card);
        pic.setMinimumSize(new Dimension(500,500));
        c2.fill = GridBagConstraints.VERTICAL;
        c2.weighty = 1.0;
        c2.gridx = 1;
        c2.gridy = 0;
        add(pic, c2);
        //money ladder picture
        ImageIcon card1=new ImageIcon("moneyladder.png");
        JLabel pic1 = new JLabel(card1);
        c3.fill = GridBagConstraints.VERTICAL;
        c3.gridx = 2;
        c3.gridy = 0;
        add(pic1, c3);
        //money Label
        moneyDisplay.setForeground(Color.white);
        moneyDisplay.setFont(new Font("Serif", Font.BOLD, 16));
        c3.gridx = 2;
        c3.gridy = 1;
        add(moneyDisplay, c3);
        ImageIcon coinPic=new ImageIcon("guy.png");
        c3.gridx = 0;
        c3.gridy = 0;
        JLabel picCoin = new JLabel(coinPic);
        add(picCoin,c3);
        i=0; 
        //initializing 0
        //50:50 Btn
        c1.ipady = 10; 
        c1.gridx = 0;
        c1.gridy = 1;
        halfBtn.setPreferredSize(new Dimension(250,30));
        halfBtn.setMaximumSize(new Dimension(250,30));
        add(halfBtn,c1);
        halfBtn.addActionListener(new halfBtnListener());
        //vote Btn
        c1.ipady = 10; 
        c1.gridx = 0;
        c1.gridy = 2;
        voteBtn.setPreferredSize(new Dimension(250,30));
        voteBtn.setMaximumSize(new Dimension(250,30));
        add(voteBtn,c1);
        voteBtn.addActionListener(new voteBtnListener());
        //call Btn
        c1.ipady = 10; 
        c1.gridx = 0;
        c1.gridy = 3;
        callBtn.setPreferredSize(new Dimension(250,30));
        callBtn.setMaximumSize(new Dimension(250,30));
        add(callBtn,c1);
        callBtn.addActionListener(new callBtnListener());
        //JLabel showing the question
        label = new JLabel((i+1) + ") "+choice[i].question);
        label.setForeground(Color.white);
        label.setFont(new Font("Serif", Font.BOLD, 18));
        c.gridx = 1;
        c.gridy = 1;
        add(label,c);
        //the answer option buttons
        button[0] = new JButton(""+ choice[i].option[0]);
        c.ipady = 10; 
        c.ipadx = 150; 
        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        button[0].setPreferredSize(new Dimension(250,30));
        button[0].setMaximumSize(new Dimension(250,30));
        add(button[0],c);
        button[0].addActionListener(this);

        button[1] = new JButton("" + choice[i].option[1]);
        c.gridx = 2;
        c.gridy = 2;
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        button[1].setPreferredSize(new Dimension(250,30));
        button[1].setMaximumSize(new Dimension(250,30));
        add(button[1],c);
        button[1].addActionListener(this);

        button[2] = new JButton("" + choice[i].option[2]);
        c.gridx = 1;
        c.gridy = 3;
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        button[2].setPreferredSize(new Dimension(250,30));
        button[2].setMaximumSize(new Dimension(250,30));
        add(button[2],c);
        button[2].addActionListener(this);

        button[3] = new JButton(""+ choice[i].option[3]);
        c.gridx = 2;
        c.gridy = 3;
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        button[3].setPreferredSize(new Dimension(250,30));
        button[3].setMaximumSize(new Dimension(250,30));
        add(button[3],c);
        button[3].addActionListener(this);
        
        optionA = choice[i].option[0];
        optionB = choice[i].option[1];
        optionC = choice[i].option[2];
        optionD = choice[i].option[3];
        //checks the answer
        answer = "" + choice[i].answer.charAt(0);
        if (answer.charAt(0) == 'a' || answer.charAt(0) == 'A')
            answer = "a";
        else if (answer.charAt(0) == 'b' || answer.charAt(0) == 'B')
            answer = "b";
        else if (answer.charAt(0) == 'c' || answer.charAt(0) == 'C')
            answer = "c";
        else if (answer.charAt(0) == 'd' || answer.charAt(0) == 'D')
            answer = "d";
    }

    public void actionPerformed(ActionEvent e)
    {
        if (questionCount < 14)//this is for the round number ==> there will only be 15 rounds maximum
        {
            if(e.getActionCommand().charAt(0) == choice [i].answer.charAt(0))//if the answer was correct
            {
                //basically sets up all the text for the next question
                result = true;
                i++;
                label.setText((i+1) + ") "+choice[i].question);

                button[0].setText(choice[i].option[0]);
                button[1].setText(choice[i].option[1]);
                button[2].setText(choice[i].option[2]);
                button[3].setText(choice[i].option[3]);

                optionA = choice[i].option[0];
                optionB = choice[i].option[1];
                optionC = choice[i].option[2];
                optionD = choice[i].option[3];

                answer = "" + choice[i].answer.charAt(0);
                if (answer.charAt(0) == 'a' || answer.charAt(0) == 'A')
                    answer = "a";
                else if (answer.charAt(0) == 'b' || answer.charAt(0) == 'B')
                    answer = "b";
                else if (answer.charAt(0) == 'c' || answer.charAt(0) == 'C')
                    answer = "c";
                else if (answer.charAt(0) == 'd' || answer.charAt(0) == 'D')
                    answer = "d";

                button[0].setEnabled(true);
                button[1].setEnabled(true);
                button[2].setEnabled(true);
                button[3].setEnabled(true);
                money = addMoney(i+1, true);
                moneyDisplay.setText("MONEY LEVEL : " + money);
                money = addMoney(i, true);
                //also makes a pane pop up showing the money they have as of that round (closes with a button)
                JOptionPane.showMessageDialog(frame,"You now have $" + money,"CORRECT ANSWER!",JOptionPane.PLAIN_MESSAGE);
                
                setVisible(true);
            }
            else //if the answer was incorrect
            {
                //they lose
                result = false;
                //set accumulated money to the last safety net
                //money = addMoney(i, false);
                button[0].setEnabled(false);
                button[1].setEnabled(false);
                button[2].setEnabled(false);
                button[3].setEnabled(false);
                halfBtn.setEnabled(false);
                voteBtn.setEnabled(false);
                callBtn.setEnabled(false);
                
                if(answer.equals("a"))
                {
                    button[0].setBackground(Color.GREEN);
                    button[0].setEnabled(true);
                }
                else if(answer.equals("b"))
                {
                    button[1].setBackground(Color.GREEN);
                    button[1].setEnabled(true);
                }
                else if(answer.equals("c"))
                {
                    button[2].setBackground(Color.GREEN);
                    button[2].setEnabled(true);
                }
                else if(answer.equals("d"))
                {
                    button[3].setBackground(Color.GREEN);
                    button[3].setEnabled(true);
                }
                //display LossPane ==> pop up for when they lose
                money = addMoney(i+1, true);
                moneyDisplay.setText("MONEY LEVEL : " + money);
                money = addMoney(i, true);
                JOptionPane.showMessageDialog(frame,"The right answer is "+ answer +"\nYou now have $" + money,"WRONG ANSWER!",JOptionPane.PLAIN_MESSAGE);
                setVisible(false);
                LossPane window = new LossPane (money);
                window.setVisible(true);
            }
            //next round
            questionCount++;
        }
        else if (questionCount == 14)//if they complete the 15th round
        {
            //display winning screen
            setVisible(false);
            new WinningPane();
        }
    }
    //main method 
    public static void main ( String[] args ) throws Exception
    {
        Millionaire frame = new Millionaire ();
        frame.setSize(1350,700); //sets the size of frame
        frame.getContentPane().setBackground( new Color(10, 10, 100 ));//set background colour
        frame.setVisible( true ); //sets the frame visibility
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    //method for adding money according to round number
    public int addMoney (int r, boolean bust)//accepts the round number and whether or not the user has lost
    {
        //as long as the round is within 15
        if (r <= 15)
        {
            //sets the money value based on round number
            //safety net system is also included; basically it changes every 5 rounds
            if (r == 1)
            {
                money = 100;
                safetynet = 0;
            }
            else if (r == 2)
            {
                money = 200; 
                safetynet = 0;
            }
            else if (r == 3)
            {
                money = 300; 
                safetynet = 0;
            }
            else if (r == 4)
            {
                money = 500;
                safetynet = 0;
            }
            else if (r == 5)
            {
                money = 1000;
                safetynet = 1000;
            }
            else if (r == 6)
            {
                money = 2000;
                safetynet = 1000;
            }
            else if (r == 7)
            {
                money = 5000;
                safetynet = 1000;
            }
            else if (r == 8)
            {
                money = 12500;
                safetynet = 1000;
            }
            else if (r == 9)
            {
                money = 25000;
                safetynet = 1000;
            }
            else if (r == 10)
            {
                money = 50000;
                safetynet = 50000;
            }
            else if (r == 11)
            {
                money = 75000;
                safetynet = 50000;
            }
            else if (r == 12)
            {
                money = 150000;
                safetynet = 50000;
            }
            else if (r == 13)
            {
                money = 325000;
                safetynet = 50000;
            }
            else if (r == 14)
            {
                money = 500000;
                safetynet = 50000;
            }
            else if (r == 15)
            {
                money = 1000000;
                safetynet = 1000000;
            }
        }
        //if they lost this round
        if (bust == false)
        {
            //drop them down to the last safety net
            return safetynet;
        }
        // if they didn't lose, return the new monetary value
        else
        {
            return money;
        }
    }
    //
    class halfBtnListener implements ActionListener //inner class for handling events
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
            int x1 = 0;
            int x2 = 0;
            while(x1 == a || x2 == a || x2 == x1)
            {
                x1 = rand.nextInt(4)+ 1;
                x2 = rand.nextInt(4)+ 1;
            }
            if (x1 == 1 || x2 == 1)
                button[0].setEnabled(false);
            if (x1 == 2 || x2 == 2)
                button[1].setEnabled(false);
            if (x1 == 3 || x2 == 3)
                button[2].setEnabled(false);
            if (x1 == 4 || x2 == 4)
                button[3].setEnabled(false);
            setVisible(true);
            halfBtn.setEnabled(false);
        }
    }

    class voteBtnListener implements ActionListener // Inner class for handling events
    {
        public void actionPerformed (ActionEvent e)
        {
            //setLayout (new GridLayout (5,5))
            String ans = "";
            if (answer == "a")
                ans = optionA;
            else if (answer == "b")
                ans = optionB;
            else if (answer == "c")
                ans = optionC;
            else
                ans = optionD;
            JOptionPane.showMessageDialog(frame, "The majority voted for "+ ans,"VOTE",JOptionPane.PLAIN_MESSAGE);

            setVisible(true);
            voteBtn.setEnabled(false);
        }
    }

    class callBtnListener implements ActionListener // Inner class for handling events
    {
        public void actionPerformed (ActionEvent e)
        {
            String ans = "";
            if (answer == "a")
                ans = optionA;
            else if (answer == "b")
                ans = optionB;
            else if (answer == "c")
                ans = optionC;
            else if (answer == "d")
                ans = optionD;
            JOptionPane.showMessageDialog(frame,"Caller : I am pretty sure the answer is "+ ans,"Call",JOptionPane.PLAIN_MESSAGE);

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
