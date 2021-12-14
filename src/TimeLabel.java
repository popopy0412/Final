import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

class TimeLabel extends JLabel implements Runnable { // 프로그램 우상단에 나타나는 시간을 표현하는 라벨
    private Thread timerThread = null; // 실시간으로 현재 시각을 나타내기 위한 스레드
    public TimeLabel() {
        setText(makeClockText());
        setFont(new Font("TimesRoman", Font.ITALIC, 30));
        setHorizontalAlignment(JLabel.CENTER); // 폰트와 위치 설정
        timerThread = new Thread(TimeLabel.this);
        timerThread.start(); // 현재 시각을 나타내는 스레드 시작
    }

    public String makeClockText() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND); // 년, 월, 일, 시, 분, 초를 나타냄

        String clockText = Integer.toString(year);
        clockText = clockText.concat("년 ");
        clockText = clockText.concat(Integer.toString(month+1));
        clockText = clockText.concat("월 ");
        clockText = clockText.concat(Integer.toString(date));
        clockText = clockText.concat("일 ");
        clockText = clockText.concat(Integer.toString(hour));
        clockText = clockText.concat(":");
        clockText = clockText.concat((min >= 10 ? "" : "0") + Integer.toString(min));
        clockText = clockText.concat(":");
        clockText = clockText.concat((second >= 10 ? "" : "0") + Integer.toString(second)); // 양식에 맞추어 문자열 생성

        return clockText;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000); // 스레드를 1초마다 갱신
            }
            catch(InterruptedException e){return;}
            setText(makeClockText()); // 1초마다 시각 갱신
        }
    }
}