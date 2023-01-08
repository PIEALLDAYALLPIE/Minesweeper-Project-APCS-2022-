/**
 * This is the main class of the project. It will count time with MyTimer, and create a game window.
 */
public class Minesweeper {
    public static void main(String args[])
    {
        MyTimer tmr = new MyTimer();
        Window gameWindow = new Window(tmr);
        
        gameWindow.getGuiboard().assignMines();
        gameWindow.getGuiboard().assignNum();
        gameWindow.replicate();
        tmr.startTimer();

    }
}
