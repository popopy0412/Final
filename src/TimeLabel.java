import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

class TimeLabel extends JLabel implements Runnable {
    private Thread timerThread = null;
    public TimeLabel() {
        setText(makeClockText());
        setFont(new Font("TimesRoman", Font.ITALIC, 30));
        setHorizontalAlignment(JLabel.CENTER);
        timerThread = new Thread(TimeLabel.this);
        timerThread.start();
    }

    public String makeClockText() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);

        String clockText = Integer.toString(year);
        clockText = clockText.concat("년 ");
        clockText = clockText.concat(Integer.toString(month));
        clockText = clockText.concat("월 ");
        clockText = clockText.concat(Integer.toString(date));
        clockText = clockText.concat("일 ");
        clockText = clockText.concat(Integer.toString(hour));
        clockText = clockText.concat(":");
        clockText = clockText.concat((min >= 10 ? "" : "0") + Integer.toString(min));
        clockText = clockText.concat(":");
        clockText = clockText.concat((second >= 10 ? "" : "0") + Integer.toString(second));

        return clockText;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
            }
            catch(InterruptedException e){return;}
            setText(makeClockText());
        }
    }
}