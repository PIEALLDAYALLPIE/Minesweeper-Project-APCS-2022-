 
/**
 * a single cell of the board that contains information of is 
 * the cell revealed, if there is a mine or a flag in the cell, 
 * and what number should be displayed on the cell.
*
*  @author  Michael Lee
*  @version 5/2/22
*  @author  Period 5
*
*  @author  Andrew Chang
*/
 
public class Cell
{
   private boolean isMine;
   private boolean isRevealed;
   private int count;
   private boolean isFlag;
 
   /**
    * First constructor for cell class
    * initializes isMine and isRevealed to false
    * creates a new blank cell that isn't revealed
    */
   public Cell()
   {
       isMine = false;
       isRevealed = false;
   }
 
   /**
    * Second constructor for cell class
    * initializes isMine to user preference
    * creates either a blank cell or a mine cell
    * isn't revealed
    * @param mine boolean if user wants it to be a mine or not
    */
   public Cell(boolean mine)
   {
       isMine = mine;
       isRevealed = false;
   }
 
   /**
    * accessor method for count
    * @return count's value
    */
   public int getCount()
   {
       return count;
   }
 
   /**
    * sets the field count to num
    * @param num int to set count to
    */
   public void setCount(int num)
   {
       count = num;
   }
 
   /**
    * returns if cell is a mine or not
    * @return isMine field
    */
   public boolean getIsMine()
   {
       return isMine;
   }
 
   /**
    * sets if current cell is a mine
    * @param mine boolean is it a mine or not
    */
   public void setIsMine(boolean mine)
   {
       isMine = mine;
   }
 
   /**
    * accessor for isRevealed field
    * @return boolean is it revealed or not
    */
   public boolean getIsRevealed()
   {
       return isRevealed;
   }
 
   /**
    * sets current cell to revealed or not
    * @param revealed boolean is it revealed or not
    */
   public void setIsRevealed(boolean revealed)
   {
       isRevealed = revealed;
   }
 
   /**
    * is the cell flagged?
    * can only be flagged if it is not revealed
    * both mines and numbers can be flagged
    * @return boolean of if flagged or not
    */
   public boolean getIsFlag()
   {
       return isFlag;
   }
 
   /**
    * sets if the cell is flagged or not
    * @param flag user sets if cell is flagged or not
    */
   public void setIsFlag(boolean flag)
   {
       isFlag = flag;
   }
 
}
 
 
