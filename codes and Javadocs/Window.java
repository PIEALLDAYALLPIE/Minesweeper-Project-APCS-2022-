import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.*;
import java.awt.event.*;
 
 
/**
 * This class will display the game, manage inputs using GUIs, 
 * and record the best records.
 * 
 * @author  Andrew Chang
*  @version 5/17/22
*  @author  Period 5
*
*  @author  Michael Lee
 */
public class Window extends JFrame implements MouseListener
{
 private JFrame frame = new JFrame("Minesweeper");
 private JButton[][] buttons;
 private Container grid = new Container();
 private Board guiboard;

 private MyTimer tmr;
 private String[] bestTimes = {"99:99:99", "99:99:99", "99:99:99"};
 private int gameDifficulty;//beginner - 0, intermediate - 1, advanced - 2
 
 private final ImageIcon cellIMG = new ImageIcon("cell0.png");
 private final ImageIcon flagIMG = new ImageIcon("cell1.png");
 private final ImageIcon crossedFlagIMG = new ImageIcon("crossedflag.png");
 private final ImageIcon blankIMG = new ImageIcon("cell2.png");
 private final ImageIcon mineIMG = new ImageIcon("mine.jpg");
 private final ImageIcon redMineIMG = new ImageIcon("redmine.jpg");
 private final ImageIcon oneIMG = new ImageIcon("numcell1 (1).png");
 private final ImageIcon twoIMG = new ImageIcon("numcell2 (1).png");
 private final ImageIcon threeIMG = new ImageIcon("numcell3 (1).png");
 private final ImageIcon fourIMG = new ImageIcon("numcell4 (1).png");
 private final ImageIcon fiveIMG = new ImageIcon("numcell5 (1).png");
 private final ImageIcon sixIMG = new ImageIcon("numcell6 (1).png");
 private final ImageIcon sevenIMG = new ImageIcon("numcell7 (1).png");
 private final ImageIcon eightIMG = new ImageIcon("numcell8 (1).png");

  /**
   * No Args Constructor for Window class
   * @param tmr a timer to count records
   */
 public Window(MyTimer tmr)
 {
   super("Minesweeper");
   guiboard = new Board();
   JFrame.setDefaultLookAndFeelDecorated(true);
   frame.setSize(400, 400);
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   
  
   grid.setLayout(new GridLayout(8, 8));
   buttons = new JButton[8][8];
   for (int i = 0; i < buttons.length; i++)
   {
     for (int j = 0; j < buttons.length; j++)
     {
       buttons[i][j] = new JButton();
       buttons[i][j].addMouseListener(this);
       buttons[i][j].setIcon(cellIMG);
       grid.add(buttons[i][j]);
     }
   }
   frame.add(grid, BorderLayout.CENTER);
  
   JMenuBar menubar = new JMenuBar();
   frame.setJMenuBar(menubar);
   JMenu file = new JMenu("File");
   menubar.add(file);
   JMenuItem exit = new JMenuItem("Exit");
   JMenu change = new JMenu("Change Board Size");
   JMenuItem changeB = new JMenuItem("Beginner: 8 x 8 \t(10 Mines)");
   JMenuItem changeI = new JMenuItem("Intermediate: 12 x 12 \t(22 Mines)");
   JMenuItem changeA = new JMenuItem("Advanced: 20 x 20 \t(62 Mines)");
   JMenuItem BestRecords = new JMenuItem("Best Records");
   change.add(changeB);
   change.add(changeI);
   change.add(changeA);
   JMenuItem refresh = new JMenuItem("Refresh");
   file.add(change);

   file.add(BestRecords);

   file.add(exit);
   menubar.add(refresh);
 
   class exitAction implements ActionListener {
     public void actionPerformed (ActionEvent e) {
       System.exit(0);
     }
   }
   class changeSizeB implements ActionListener {
     public void actionPerformed (ActionEvent e) {
       gameDifficulty = 0;
       sizeChange(8);
     }
   }
   class changeSizeI implements ActionListener {
     public void actionPerformed (ActionEvent e) {
      gameDifficulty = 1;
       sizeChange(12);
     }
   }
   class changeSizeA implements ActionListener {
     public void actionPerformed (ActionEvent e) {
      gameDifficulty = 2;
       sizeChange(20);
     }
   }
   class SeeBestRecords implements ActionListener {
    public void actionPerformed (ActionEvent e) {
      //new Pop-up showing the records
      PopUpBestRecords popUp = new PopUpBestRecords(new JFrame(), bestTimes);
      popUp.setVisible(true);
      int timeOut = 0;

      while(!popUp.isButtonPressed() && timeOut <= 70)
      {
          try {
              Thread.sleep(100);
              timeOut++;
          } catch(InterruptedException er) {
              System.out.println("got interrupted!");
          }
      }
    }
  }
   class refreshAction implements ActionListener {
     public void actionPerformed (ActionEvent e) {
       refreshGrid();
     }
   }
   exit.addActionListener(new exitAction());
   changeB.addActionListener(new changeSizeB());
   changeI.addActionListener(new changeSizeI());
   changeA.addActionListener(new changeSizeA());
   BestRecords.addActionListener(new SeeBestRecords());
   refresh.addActionListener(new refreshAction());
   frame.setLocationRelativeTo(null);
   frame.setVisible(true);  

   this.tmr = tmr;
   //bestTimes = 
   gameDifficulty = 0;
 }
 
 
 /**
  * begins the game with the given value set.
  * @param size the length of one side of the square game board
  */
 public Window(int size)
 {
   super("Minesweeper");
   guiboard = new Board(size);
   JFrame.setDefaultLookAndFeelDecorated(true);
   frame.setSize(size * 50, size * 50);
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  

   grid.setLayout(new GridLayout(size, size));
   buttons = new JButton[size][size];
   for (int i = 0; i < buttons.length; i++)
   {
     for (int j = 0; j < buttons[0].length; j++)
     {
       buttons[i][j] = new JButton();
       buttons[i][j].addMouseListener(this);
       buttons[i][j].setIcon(cellIMG);
       grid.add(buttons[i][j]);
     }
   }
   frame.add(grid, BorderLayout.CENTER);
  
   // frame.add(grid, BorderLayout.CENTER);
  
   JMenuBar menubar = new JMenuBar();
   frame.setJMenuBar(menubar);
   JMenu file = new JMenu("File");
   menubar.add(file);
   JMenuItem exit = new JMenuItem("Exit");
   JMenu change = new JMenu("Change Board Size");
   JMenuItem changeB = new JMenuItem("Beginner: 8 x 8 \t(10 Mines)");
   JMenuItem changeI = new JMenuItem("Intermediate: 12 x 12 \t(22 Mines)");
   JMenuItem changeA = new JMenuItem("Advanced: 20 x 20 \t(62 Mines)");
   JMenuItem BestRecords = new JMenuItem("Best Records");
   change.add(changeB);
   change.add(changeI);
   change.add(changeA);
   JMenuItem refresh = new JMenuItem("Refresh");
   file.add(change);
   file.add(BestRecords);
   file.add(exit);
   menubar.add(refresh);
 
   class exitAction implements ActionListener {
     public void actionPerformed (ActionEvent e) {
       System.exit(0);
     }
   }
   class changeSizeB implements ActionListener {
     public void actionPerformed (ActionEvent e) {
       sizeChange(8);
     }
   }
   class changeSizeI implements ActionListener {
     public void actionPerformed (ActionEvent e) {
       sizeChange(12);
     }
   }
   class changeSizeA implements ActionListener {
     public void actionPerformed (ActionEvent e) {
       sizeChange(20);
     }
   }
   class SeeBestRecords implements ActionListener {
    public void actionPerformed (ActionEvent e) {
    //new Pop-up showing the records
    }
  }
   class refreshAction implements ActionListener {
     public void actionPerformed (ActionEvent e) {
       refreshGrid();
     }
   }
   exit.addActionListener(new exitAction());
   changeB.addActionListener(new changeSizeB());
   changeI.addActionListener(new changeSizeI());
   changeA.addActionListener(new changeSizeA());
   BestRecords.addActionListener(new SeeBestRecords());
   refresh.addActionListener(new refreshAction());
   frame.setLocationRelativeTo(null);
   frame.setVisible(true);
 }
 
 
 
 /**
  * runs when a mouse click is detected
  * ends the game if the condition is satisfied
  */
 @Override
 public void mouseClicked(java.awt.event.MouseEvent e)
 {
   // TODO Auto-generated method stub
   int click = e.getButton();
   for (int i = 0; i < buttons.length; i++)
   {
     Boolean found = false;
     for (int j = 0; j < buttons[0].length; j++)
     {
       if (e.getSource() == buttons[i][j])
       {
         found = true;
         // Cell we want to investigate
         Cell g = guiboard.gameBoard[i][j];
         if (click == 1)
         {
           if (!g.getIsRevealed() && !g.getIsFlag())
           {
             if (g.getIsMine())
             {
               buttons[i][j].setIcon(redMineIMG);
               g.setIsRevealed(true);
               System.out.println("YOU LOSE");
               loseCondition(i, j);
               // YOU LOSE ALGORITHM
             }
             if (!g.getIsMine() && g.getCount() == 0)
             {
               guiboard.clearBlanks(i, j);
               replicate();
             }
             if (!g.getIsMine() && g.getCount() > 0)
             {
               if (winCondition())
               {
                 win();
               }
               g.setIsRevealed(true);
               int num = g.getCount();
               switch (num)
               {
                 case 1: buttons[i][j].setIcon(oneIMG);
                   break;
                 case 2: buttons[i][j].setIcon(twoIMG);
                   break;
                 case 3: buttons[i][j].setIcon(threeIMG);
                   break;
                 case 4: buttons[i][j].setIcon(fourIMG);
                   break;
                 case 5: buttons[i][j].setIcon(fiveIMG);
                   break;
                 case 6: buttons[i][j].setIcon(sixIMG);
                   break;
                 case 7: buttons[i][j].setIcon(sevenIMG);
                   break;
                 case 8: buttons[i][j].setIcon(eightIMG);
                   break;
                 default: buttons[i][j].setIcon(blankIMG);
                   break;
               }
             }
           }
         }
         if (click == 3)
         {
           if (!g.getIsRevealed())
           {
             if (g.getIsFlag())
             {
               g.setIsFlag(false);
               buttons[i][j].setIcon(cellIMG);
             }
             else
             {
               g.setIsFlag(true);
               buttons[i][j].setIcon(flagIMG);
               if (winCondition())
               {
                 win();
               }
             }
           }
         }
       }
     }
     if (found)
     {
       break;
     }
   }
 }
 
 /**
  * update the board by getting information from the board and turn it into GUI
  */
 public void replicate()
 {
   System.out.println("REPLICATING IMAGE");
   for (int i = 0; i < buttons.length; i++)
   {
     for (int j = 0; j < buttons[0].length; j++)
     {
       Cell g = guiboard.gameBoard[i][j];
       if (g.getIsRevealed() && (g.getCount() == 0) && !(g.getIsMine()))
       {
         buttons[i][j].setIcon(blankIMG);
       }
       if (!g.getIsRevealed() && g.getIsFlag())
       {
         buttons[i][j].setIcon(flagIMG);
       }
       if (!g.getIsRevealed() && !g.getIsFlag())
       {
         buttons[i][j].setIcon(cellIMG);
       }
       if (g.getIsRevealed() && (g.getCount() > 0))
       {
         int num = g.getCount();
         switch (num)
         {
           case 1: buttons[i][j].setIcon(oneIMG);
             break;
           case 2: buttons[i][j].setIcon(twoIMG);
             break;
           case 3: buttons[i][j].setIcon(threeIMG);
             break;
           case 4: buttons[i][j].setIcon(fourIMG);
             break;
           case 5: buttons[i][j].setIcon(fiveIMG);
             break;
           case 6: buttons[i][j].setIcon(sixIMG);
             break;
           case 7: buttons[i][j].setIcon(sevenIMG);
             break;
           case 8: buttons[i][j].setIcon(eightIMG);
             break;
           default: buttons[i][j].setIcon(blankIMG);
             break;
         }
       }
     }
   }
 }
 
 /**
  * the win condition
  * @return if the player won the game
  */
 public boolean winCondition()
 {
   for (int i = 0; i < buttons.length; i++)
   {
     for (int j = 0; j < buttons.length; j++)
     {
       Cell g = guiboard.gameBoard[i][j];
       // if there is a flag over a non-mine, you don't win
       if (g.getIsFlag() && !g.getIsMine())
       {
         return false;
       }
       // if there isn't a flag over a mine, you don't win
       if (!g.getIsFlag() && g.getIsMine() && !g.getIsRevealed())
       {
         return false;
       }
     }
   }
   return true;
 }
 
 /**
  * will run when the player satisfies the win conditions.
  */
 public void win()
 {
   for (int i = 0; i < buttons.length; i++)
   {
     for (int j = 0; j < buttons.length; j++)
     {
       Cell g = guiboard.gameBoard[i][j];
       if (!g.getIsRevealed() && !g.getIsMine())
       {
         g.setIsRevealed(true);
       }
     }
   }
   replicate();

   //records the current time on myTimer tmr
   //bestTimes[gameDifficulty] = tmr.getCurTime();

   //updates the best record
   String curTime = tmr.getCurTime();
   boolean newBest = compareAndUpdateBestRecords(curTime);
   if (newBest)
   {
     updateRecord(curTime, gameDifficulty);
   }

   //pop up comes up at this point
   PopUpWin popUp = new PopUpWin(this, curTime, newBest, gameDifficulty, bestTimes);
   popUp.setVisible(true);
   
   int timeOut = 0;
   while(!popUp.isButtonPressed() && timeOut <= 70)
   {
       try {
           Thread.sleep(100);
           timeOut++;
       } catch(InterruptedException e) {
           System.out.println("got interrupted!");
       }
   }
   
   System.out.println(popUp.isButtonPressed());
   // pop up code until here
   
   // frame.add();
   System.out.println("YOU WIN");

   refreshGrid();
 }
 
 /**
  * reveal the mines and incorrect flags
  * @param row the row coordinate of the cell
  * @param col the col coordinate of the cell
  */
 public void loseCondition(int row, int col)
 {
   for (int i = 0; i < buttons.length; i++)
   {
     for (int j = 0; j < buttons.length; j++)
     {
       Cell g = guiboard.gameBoard[i][j];
       if (!((row == i) && (col == j)))
       {
         if (g.getIsMine() && !g.getIsFlag())
         {
           g.setIsRevealed(true);
           buttons[i][j].setIcon(mineIMG);
         }
         if (g.getIsFlag() && !g.getIsMine())
         {
           buttons[i][j].setIcon(crossedFlagIMG);
         }
       }
       buttons[i][j].setDisabledIcon(buttons[i][j].getIcon());
       buttons[i][j].setEnabled(false);
     }
   }

   //pop-up code comes from here
   PopUpLose popUp = new PopUpLose(this, tmr, bestTimes, gameDifficulty);
   popUp.setVisible(true);

   int timeOut = 0;
   while(!popUp.isButtonPressed() && timeOut <= 100)
   {
       try {
           Thread.sleep(100);
           timeOut++;
       } catch(InterruptedException e) {
           System.out.println("got interrupted!");
       }
   }
   
   //System.out.println(popUp.isButtonPressed());
   System.out.println("YOU LOSE");
   refreshGrid();
   
   //until here
 }
 
 /**
  * restart the game
  */
 public void refreshGrid()
 {
   guiboard.refresh();
   for (int i = 0; i < buttons.length; i++)
   {
     for (int j = 0; j < buttons[0].length; j++)
     {
       buttons[i][j].setIcon(cellIMG);
       buttons[i][j].setEnabled(true);
     }
   }
   guiboard.assignMines();
   guiboard.assignNum();
   replicate();
   tmr.resetTimer();
 }
 
 /**
  * resize the game board
  * @param size a length of one side of the square game board
  */
 public void sizeChange(int size)
 {
   guiboard.arrayChange(size);
   frame.remove(grid);
   frame.setSize(size * 50, size * 50);
 
   grid = new Container();
   grid.setLayout(new GridLayout(size, size));
   buttons = new JButton[size][size];
   for (int i = 0; i < buttons.length; i++)
   {
     for (int j = 0; j < buttons.length; j++)
     {
       buttons[i][j] = new JButton();
       buttons[i][j].addMouseListener(this);
       buttons[i][j].setIcon(cellIMG);
       grid.add(buttons[i][j]);
     }
   }
   frame.add(grid, BorderLayout.CENTER);
   frame.setLocationRelativeTo(null);
   frame.setVisible(true);
   refreshGrid();
 }
 
 
 //methods to update the record
 /**
  * updates the best record with given values.
  * @param record the new best time
  * @param difficulty the difficulty the player got the new best time.
  */
 public void updateRecord(String record, int difficulty)
 {
    bestTimes[difficulty] = record;
 }

 /**
  * compare the previous best record and the current record the player go.
  * if the player got a new best record, it will be updated.
  * @param curTime the record the player got.
  * @return true if the player has a new best record.
  */
 public boolean compareAndUpdateBestRecords(String curTime)
    {
        boolean newBest = false;

        String bestTime = bestTimes[gameDifficulty];
        //String curTime = tmr.getCurTime();
        String temp = curTime;
        int bestM, bestS, bestMS, curM, curS, curMS;
        bestM = Integer.parseInt(bestTime.substring(0, 2));
        bestTime = bestTime.substring(3);
        bestS = Integer.parseInt(bestTime.substring(0, 2));
        bestTime = bestTime.substring(3);
        bestMS = Integer.parseInt(bestTime.substring(0, 2));
        System.out.println(bestM + ":" + bestS + ":" + bestMS);

        curM = Integer.parseInt(temp.substring(0, 2));
        temp = temp.substring(3);
        curS = Integer.parseInt(temp.substring(0, 2));
        temp = temp.substring(3);
        curMS = Integer.parseInt(temp.substring(0, 2));
        System.out.println(curM + ":" + curS + ":" + curMS);

        if (curM < bestM)
        {
            newBest = true;
        }
        else if (curM == bestM)
        {
            if (curS < bestS)
            {
                newBest = true;
            }
            else if (curS == bestS)
            {
                if (curMS <= bestMS)
                {
                    newBest = true;
                }
            }
        }
    
        if (newBest)
        {
            bestTimes[gameDifficulty] = curTime;
        }

        return newBest;
    }

 @Override
 public void mousePressed(java.awt.event.MouseEvent e)
 {
   // TODO Auto-generated method stub
  
 }
 
 @Override
 public void mouseReleased(java.awt.event.MouseEvent e)
 {
   // TODO Auto-generated method stub
  
 }
 
 @Override
 public void mouseEntered(java.awt.event.MouseEvent e)
 {
   // TODO Auto-generated method stub
  
 }
 
 @Override
 public void mouseExited(java.awt.event.MouseEvent e)
 {
   // TODO Auto-generated method stub
  
 }

 /**
  * get the game board
  * @return the game board
  */
 public Board getGuiboard()
 {
   return guiboard;
 }

 
 
 /*
 public static void main(String [] args)
 {
   Window b = new Window();
   b.guiboard.assignMines();
   b.guiboard.assignNum();
   b.replicate();
 }
 //*/
}
 

