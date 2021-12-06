import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame{

    private JLabel label; // My Notes

    public MainFrame(String title){
        super(title); // 제목 설정
        setSize(1000, 1000); // 크기 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 종료 조건 설정

        Container c = getContentPane(); // ContentPane 가져옴
        c.setLayout(new BorderLayout()); // 배치 관리자 설정

        label = new JLabel("My Notes");
        label.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 30));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(Color.BLUE);

        JPanel northPanel = new JPanel(new GridLayout(1, 2));
        TimeLabel timeLabel = new TimeLabel();
        timeLabel.setHorizontalAlignment(JLabel.RIGHT);

        northPanel.add(label);
        northPanel.add(timeLabel);

        c.add(northPanel, BorderLayout.NORTH);
        c.add(new TabbedPanePanel(), BorderLayout.WEST);
        c.add(new DetailPanel(), BorderLayout.CENTER);

        setVisible(true); // 보이게 설정
    }

    public static void main(String[] args){
        new MainFrame("JAVA 3분반 1714112 천소현"); // 프레임 생성
    }
}
