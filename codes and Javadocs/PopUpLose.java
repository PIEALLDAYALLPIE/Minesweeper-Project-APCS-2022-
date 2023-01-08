import java.awt.*;
import java.awt.event.*;
import java.beans.beancontext.BeanContextSupport;
import java.awt.Font;
import java.awt.Window;
import java.awt.Container;


import javax.swing.*;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * a pop-up window that appears when the player opened a cell
 *  containing a mine and loses a game. Displays the time taken
 *  for the game and the best records for current difficulty.
 * @author  Michael Lee
*  @version 5/17/22
*  @author  Period 5
*
*  @author  Andrew Chang
 */
public class PopUpLose extends JDialog
{
    private boolean buttonPressed;

    /**
     * constructs a lost pop-up window with the main window parent.
     * 
     * @param parent the main game window
     * @param tmr the timer that counts the game record
     * @param bestTimes a list of best records 
     * @param gameDifficulty current game difficulty
     */
    public PopUpLose(Window parent, MyTimer tmr, String[] bestTimes, int gameDifficulty)
    {
        super(parent, "Game Over", ModalityType.APPLICATION_MODAL);
        setSize(360, 300);
        setLayout(null);
        setLocationRelativeTo(null);

        buttonPressed = false;

        JLabel lb, lbTime, lbNewBest;
        lb = new JLabel("You lost this game...");
        lb.setFont(new Font("mine-sweeper.otf", Font.PLAIN, 25));
        lb.setBounds(70, 30, 450, 50);

        lbTime = new JLabel("time: " + tmr.getCurTime());
        lbTime.setFont(new Font("mine-sweeper.otf", Font.PLAIN, 15));
        lbTime.setBounds(70, 90, 450, 50);

        
        if (bestTimes[gameDifficulty].equals("99:99:99")) lbNewBest = new JLabel("Your best record: --:--:--" );
        else lbNewBest = new JLabel("Your best record: " + bestTimes[gameDifficulty]);
        lbNewBest.setFont(new Font("mine-sweeper.otf", Font.PLAIN, 15));
        lbNewBest.setBounds(70, 140, 450, 50);
        
        JButton btn = new JButton("Play Again");
        btn.setBounds(115, 190, 100, 40);
        btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                buttonPressed = true;
                dispose(); // close the pop-up
            }
        });

        add(btn);
        add(lb);
        add(lbTime);
        add(lbNewBest);
    }

    /**
     * tells if the "Play Again" button is pressed
     * @return true if the "Play Again" button is pressed
     */
    public boolean isButtonPressed()
    {
        return buttonPressed;
    }
}