
/**
 * a timer that starts counting when the game is opened. 
 * It will record the player’s current record and will be 
 * shown by GUI at the end of the game. The timer gets 
 * reset when the game restarts. 
 */
public class MyTimer {
 
    private String curTime;
    private int timeCnt = 0;
    private boolean stop;

    /**
     * create and reset the timer
     */
    public MyTimer()
    {
        curTime = "";
        timeCnt = 0;
        stop = false;
    }

    // public void main(String args[])
    // {
    //     startTimer();
    // }

    /**
     * start couting the time in 10ms
     */
    public void startTimer()
    {
        while(!stop)
        {
            try {
                Thread.sleep(10);
            } catch(InterruptedException e) {
                System.out.println("got interrupted!");
            }
            timeCnt++;
            //System.out.println(getCurTime());
        }
    }

    /**
     * stop the timer
     */
    public void stopTimer()
    {
        stop = true;
    }


    /**
     * set time back to 00:00:00 (min:sec:ms)
     */
    public void resetTimer()
    {
        timeCnt = 0;
    }

    /**
     * get the minute count on the timer in integer type
     * @return minute count
     */
    public int getMin()
    {
        return timeCnt / 100 / 60;
    }

    /**
     * get the second count on the timer in integer type
     * @return second count
     */
    public int getSec()
    {
        return timeCnt / 100 % 60;
    }

    /**
     * get the two digits of millisecond count on the timer in integer type
     * @return millisecond count
     */
    public int getTenMilSec()
    {
        return timeCnt % 100;
    }

    /**
     * get the whole current recorded time in the timer in "min:sec:ms" formatted String
     * @return current time in "min:sec:ms" format
     */
    public String getCurTime()
    {
        String min = "";
        String sec = "";
        String milsec = "";

        if (getMin() < 10)
            min = "0" + getMin();
        else
            min = "" + getMin();
        
        if (getSec() < 10)
            sec = "0" + getSec();
        else
            sec = "" + getSec();

        if (getTenMilSec() < 10)
        {
            milsec = "0" + getTenMilSec();
        }
        else
        {
            milsec = "" + getTenMilSec();
        }

        curTime = min + ":" + sec + ":" + milsec;
        return curTime;
    }


}
 

