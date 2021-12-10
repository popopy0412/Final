import javax.swing.*;
import java.awt.*;

public class DetailPanel extends JPanel{
    private JPanel panel;
    private JPanel btnPanel;
    private InformationPanel ipanel;
    private TextPanel sPanel;
    private TextPanel rPanel;
    private JButton mbtn;
    private JButton dbtn;
    public DetailPanel() {
        setLayout(new BorderLayout());
        panel = new JPanel(new GridLayout(3, 1));
        btnPanel = new JPanel(new FlowLayout());

        panel.setBorder(BorderFactory.createTitledBorder("상세 보기"));
        ipanel = new InformationPanel();
        sPanel = new TextPanel("줄거리");
        rPanel = new TextPanel("감상평");
        mbtn = new JButton("수정");
        dbtn = new JButton("삭제");

        panel.add(ipanel);
        panel.add(sPanel);
        panel.add(rPanel);

        btnPanel.add(mbtn); btnPanel.add(dbtn);

        add(panel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }
    public InformationPanel getIpanel() {
        return ipanel;
    }
    public TextPanel getsPanel() {
        return sPanel;
    }
    public TextPanel getrPanel() {
        return rPanel;
    }


}
