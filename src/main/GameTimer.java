package main;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class GameTimer extends TimerTask {

    private final TimerBuffer timerBuffer;
    private Timer timer;

    public GameTimer(TimerBuffer timerBuffer) {

        this.timerBuffer = timerBuffer;
    }

    @Override
    public void run() {

        if (timerBuffer.GetSeconds() == 60) {
            timerBuffer.ChangeMinutes();
        }
        if (timerBuffer.GetMinutes() == 60) {
            timerBuffer.ChangeHours();
        }
        timerBuffer.SetSeconds(((new Date().getTime() - timerBuffer.GetStartTime()) / 1000));
    }

    public void Start() {
        timerBuffer.ChangeStartTime(new Date().getTime() - timerBuffer.GetMilliSeconds());
        TimerTask timerTask = new GameTimer(timerBuffer);
        timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    public void Stop() {

        timer.cancel();
        timerBuffer.ChangeStartTime(timerBuffer.GetMilliSeconds());
    }
}
