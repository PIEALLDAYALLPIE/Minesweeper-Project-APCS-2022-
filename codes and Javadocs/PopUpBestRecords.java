import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import java.awt.Window;
import java.awt.Container;


import javax.swing.*;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import org.w3c.dom.Text;

/**
 * a pop-up window that appears when a player clicks 
 * sBestRecords in the menu bar. Displays the best 
 * records for each difficulty. If nothing is recorded, 
 * show --:--:-- instead of the best time.
*  @author  Michael Lee
*  @version 5/17/22
*  @author  Period 5
*
*  @author  Andrew Chang
 */
public class PopUpBestRecords extends JDialog
{
    private boolean buttonPressed;

    /**
     * constructs a best record pop-up window with the main 
     * window parent and the list of the best records bestTimes.
     * 
     * @param parent the main game window
     * @param bestTimes a list of the best records
     */
    public PopUpBestRecords(JFrame parent, String[] bestTimes)//need to show the best record
    {
        super(parent, "Best Records", ModalityType.APPLICATION_MODAL);
        setSize(360, 300);
        setLayout(null);
        setLocationRelativeTo(null);

        buttonPressed = false;

        JLabel lbB, lbI, lbA;
        String text = "";
        text += "Beginner: ";
        if (bestTimes[0].equals("99:99:99")) text += "--:--:--";
        else text += bestTimes[0];
        lbB = new JLabel(text);
        lbB.setFont(new Font("mine-sweeper.otf", Font.PLAIN, 17));
        lbB.setBounds(70, 30, 450, 50);

        text = "";
        text = text + System.lineSeparator() + "\nIntermediate: ";
        if (bestTimes[1].equals("99:99:99")) text += "--:--:--";
        else text += bestTimes[1];
        lbI = new JLabel(text);
        lbI.setFont(new Font("mine-sweeper.otf", Font.PLAIN, 17));
        lbI.setBounds(70, 60, 450, 50);

        text = "";
        text = text + System.lineSeparator() + "Advanced: ";
        if (bestTimes[2].equals("99:99:99")) text += "--:--:--";
        else text += bestTimes[2];
        lbA = new JLabel(text);
        lbA.setFont(new Font("mine-sweeper.otf", Font.PLAIN, 17));
        lbA.setBounds(70, 90, 450, 50);

        JButton btn = new JButton("Close Window");
        btn.setBounds(90, 215, 150, 40);
        btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                buttonPressed = true;
                dispose(); // close the pop-up
            }
        });

        add(btn);
        add(lbB);
        add(lbI);
        add(lbA);
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