import javax.swing.*;
import java.awt.*;

public class DetailPanel extends JPanel{
    private JPanel panel;
    private JPanel btnPanel;
    private TextPanel sPanel;
    private TextPanel rPanel;
    private JButton mbtn;
    private JButton dbtn;
    public DetailPanel() {
        setLayout(new BorderLayout());
        panel = new JPanel(new GridLayout(3, 1));
        btnPanel = new JPanel(new FlowLayout());

        panel.setBorder(BorderFactory.createTitledBorder("상세 보기"));
        sPanel = new TextPanel("줄거리");
        rPanel = new TextPanel("감상평");
        mbtn = new JButton("수정");
        dbtn = new JButton("삭제");

        panel.add(new JPanel());
        panel.add(sPanel);
        panel.add(rPanel);

        btnPanel.add(mbtn); btnPanel.add(dbtn);

        add(panel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }
}
