package main;


import java.awt.*;

public class TimerBuffer {

    private long startTime;
    private long seconds;
    private long minutes = 0;
    private long hours = 0;

    private final Font font = new Font("Courier", Font.BOLD, 30); // объявление шрифта

    public long GetStartTime() {

        return startTime;
    }
    public void ChangeStartTime(long time) {

        this.startTime = time;
    }
    public void SetSeconds(long seconds) {

        this.seconds = seconds;
    }
    public long GetSeconds() {

        return seconds;
    }
    public void ChangeMinutes() {

        seconds = 0;
        minutes += 1;
    }

    public void Render(Graphics graphics) {

        graphics.setFont(font);
        graphics.setColor(Color.WHITE);
        graphics.drawString(GetFullTime(), (int) ((int) (GameWindow.size.getWidth() * 0.8) - ("HighScore: ".length() * font.getSize() * 0.6) / 2), font.getSize() * 2);
    }
    public long GetMinutes() {

        return minutes;
    }
    public void ChangeHours() {

        minutes = 0;
        hours += 1;
    }
    public long GetHours() {

        return hours;
    }
    public long GetMilliSeconds() {

        return (((hours * 3600) + (minutes * 60) + seconds) * 1000);
    }

    public String GetFullTime() {

        String hours = String.valueOf(this.hours);
        String minutes = String.valueOf(this.minutes);
        String seconds = String.valueOf(this.seconds);
        if (hours.length() < 2) {
            hours = "0" + hours;
        }
        if (minutes.length() < 2) {
            minutes = "0" + minutes;
        }
        if (seconds.length() < 2) {
            seconds = "0" + seconds;
        }
        return (hours + ":" + minutes + ":" + seconds);
    }
}
