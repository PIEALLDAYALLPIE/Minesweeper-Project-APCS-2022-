import java.util.Random;
 
/**
* This class manages the change in each cell. 
* It will initialize the game board when the game starts. 
* It also processes the inputs and applies them to the game.
* It has a 2D array of Cells where the game will actually run.
*
*  @author  Andrew Chang
*  @version 5/2/22
*  @author  Period 5
*
*  @author  Michael Lee
*
*/
public class Board
{
    /**
     * the game board consist of cells that the game actually runs
     */
   public Cell[][] gameBoard;
   private int boardsize;
 
   /**
    * First constructor which initializes gameBoard
    * to an 8 x 8 size array
    * sets boardsize to 8
    * Fills the entire array with initialized blank cells
    */
   public Board()
   {
       boardsize = 8;
       gameBoard = new Cell[8][8];
       for (int row = 0; row < boardsize; row++)
       {
           for (int col = 0; col < boardsize; col++)
           {
               gameBoard[row][col] = new Cell();
           }
       }
   }
 
   /**
    * Second constructor where user can specify
    * the size of the board
    * Sets boardsize to size
    * Fills the entire array with initialized blank cells
    * @param size row length of a square board
    */
   public Board(int size)
   {
       boardsize = size;
       gameBoard = new Cell[size][size];
       for (int row = 0; row < boardsize; row++)
       {
           for (int col = 0; col < boardsize; col++)
           {
               gameBoard[row][col] = new Cell();
           }
       }
   }
 
   /**
    * assigns a certain number of mines to the board
    * randomly until all mines are assigned
    */
   public void assignMines()
   {
       System.out.println("Assigned Mines");
       Random rand = new Random();
       int numMines = (int)((boardsize * boardsize) / 6.4);
       while (numMines > 0)
       {
           int row = rand.nextInt(boardsize);
           int col = rand.nextInt(boardsize);
 
           if (!gameBoard[row][col].getIsMine())
           {
               gameBoard[row][col].setIsMine(true);
               numMines--;
           }
       }
   }
 
   /**
    * Assigns numbers that corresponds to how many
    * mines the 8 cells around that cell has
    * for every cell in the board
    */
   public void assignNum()
   {
       System.out.println("Assigned Numbers");
       for (int row = 0; row < boardsize; row++)
       {
           for (int col = 0; col < boardsize; col++)
           {
               if (!gameBoard[row][col].getIsMine())
               {
                   gameBoard[row][col].setCount(countAround(row, col));
               }
           }
       }
   }
 
   /**
    * recursion algorithm to clear squares
    * @param row what row on gameBoard
    * @param col what column on gameBoard
    */
   public void clearBlanks(int row, int col)
   {
       if (!inBounds(row, col))
       {
           return;
       }
 
       if (gameBoard[row][col].getIsRevealed())
       {
           return;
       }
 
       if (gameBoard[row][col].getIsFlag())
       {
           return;
       }
 
       if (gameBoard[row][col].getIsMine())
       {
           return;
       }
 
       gameBoard[row][col].setIsRevealed(true);
 
       if ((gameBoard[row][col].getCount() == 0))
       {      
           clearBlanks(row + 1, col + 1);
           clearBlanks(row + 1, col - 1);
           clearBlanks(row - 1, col + 1);
           clearBlanks(row - 1, col - 1);
           clearBlanks(row + 1, col);
           clearBlanks(row - 1, col);
           clearBlanks(row, col + 1);
           clearBlanks(row, col - 1);
       }
   }
 
   //------- *HELPER METHODS* -------\\
 
   /**
    * pre-condition: cell is not revealed and is not a mine
    * helper method for assignNum
    * @param row the row coordinate of the cell that will count the number of mines nearby.
    * @param col the col coordinate of the cell that will count the number of mines nearby.
    * @return number of mines around it
    */
   public int countAround(int row, int col)
   {
       if (!inBounds(row, col))
       {
           return 0;
       }
       int numMines = 0;
       for (int i = row - 1; i <= row + 1; i++)
       {
           for (int j = col - 1; j <= col + 1; j++)
           {
               if (inBounds(i, j))
               {
                   if (!gameBoard[i][j].equals(gameBoard[row][col]))
                   {
                       if (gameBoard[i][j].getIsMine())
                       {
                           numMines++;
                       }
                   }
                  
               }
           }
       }
       return numMines;
   }
 
   /**
    * tells if the coordinate(row, col) is in boundary of the game board
    * @param row row coordinate
    * @param col col coordinate
    * @return if (row, col) is on the game board
    */
   public boolean inBounds(int row, int col)
   {
       if (((row >= 0) && (row < boardsize)) && ((col >= 0) && (col < boardsize)))
       {
           return true;
       }
       return false;
   }
 
   /**
    * printout the board in terminal
    */
   public void printBoard()
   {
       for (Cell[] e : gameBoard)
       {
           for (Cell g : e)
           {
               System.out.print(g.getIsRevealed() + "\t");
           }
           System.out.println("");
       }
   }
 
   /**
    * set each cell into a new cell object
    */
   public void refresh()
   {
       gameBoard = new Cell[boardsize][boardsize];
       for (int row = 0; row < boardsize; row++)
       {
           for (int col = 0; col < boardsize; col++)
           {
               gameBoard[row][col] = new Cell();
           }
       }
   }
 
   /**
    * change the size of the game board to the given size
    * @param size the length of one side of the square shaped game board
    */
   public void arrayChange(int size)
   {
       gameBoard = new Cell[size][size];
       boardsize = size;
       refresh();
   }
 
//    public static void main(String[] args)
//    {
//        Board b = new Board();
//        b.assignMines();
//        b.assignNum();
//        b.printBoard();
//        b.clearBlanks(0, 0);
//        System.out.println("-------------------------------------------------------------------");
//        b.printBoard();
//    }
}