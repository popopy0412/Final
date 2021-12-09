import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame{

    private JLabel label; // My Notes
    private InputDialog dialog; // 입력 다이얼로그
    private TabbedPanePanel tppanel; // TabbedPane 패널
    private DetailPanel dpanel; // 세부정보 패널
    public MainFrame(String title){
        super(title); // 제목 설정
        setSize(1000, 700); // 크기 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 종료 조건 설정

        Container c = getContentPane(); // ContentPane 가져옴
        c.setLayout(new BorderLayout()); // 배치 관리자 설정

        tppanel = new TabbedPanePanel();
        dpanel = new DetailPanel();

        dialog = new InputDialog(this, "입력");
        dialog.setVisible(false);

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
        c.add(tppanel, BorderLayout.WEST);
        c.add(dpanel, BorderLayout.CENTER);

        setVisible(true); // 보이게 설정
    }

    public InputDialog getDialog() {
        return dialog;
    }
    public TabbedPanePanel getTppanel() { return tppanel; }
    public DetailPanel getDpanel() { return dpanel; }

    public void renewDialog() { dialog = new InputDialog(this, "입력"); }
}
