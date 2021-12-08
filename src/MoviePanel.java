import javax.swing.*;
import java.awt.*;

public class MoviePanel extends JPanel {
    private JPanel panel, imagePanel, westPanel, centerPanel;
    private JLabel[] labels;
    private JTextField[] tf;
    private JComboBox genrecb, ratedcb, yearcb;
    private JTextArea ta;
    private JButton btn;
    private JSlider slider;
    private String[] str = {"제목", "감독", "배우", "장르", "등급", "개봉년도", "포스터", "별점", "줄거리", "감상평"};
    private String[] genre = {"액션/스릴러", "공포", "코미디", "드라마", "SF", "판타지", "애니메이션", "음악/뮤지컬", "멜로", "스포츠", "범죄/느와르"};
    private String[] rated = {"전체", "12세 이상", "15세 이상", "청소년 관람 불가"};
    public MoviePanel(){
        super(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("영화 정보"));

        panel = new JPanel(new BorderLayout());
        imagePanel = new JPanel(new BorderLayout());
        westPanel = new JPanel(new GridLayout(10, 1));
        centerPanel = new JPanel(new GridLayout(10, 1));

        ta = new JTextArea();
        btn = new JButton("불러오기");
        imagePanel.add(ta, BorderLayout.CENTER); imagePanel.add(btn, BorderLayout.EAST);

        labels = new JLabel[10];
        tf = new JTextField[5];
        for(int i=0;i<10;i++){
            labels[i] = new JLabel(str[i]);
            westPanel.add(labels[i]);
        }
        for(int i=0;i<5;i++){
            tf[i] = new JTextField(20);
            new JScrollPane(tf[i]);
        }

        slider = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
        slider.setPaintLabels(true); slider.setPaintTicks(true); slider.setPaintTrack(true);
        slider.setMajorTickSpacing(1);

        genrecb = new JComboBox<String>();
        ratedcb = new JComboBox<String>();
        yearcb = new JComboBox<Integer>();

        for(String s : genre) genrecb.addItem(s);
        for(String s : rated) ratedcb.addItem(s);
        for(int i=1900;i<=2021;i++) yearcb.addItem(i);

        for(int i=0;i<3;i++) centerPanel.add(tf[i]);
        centerPanel.add(genrecb); centerPanel.add(ratedcb); centerPanel.add(yearcb);
        centerPanel.add(imagePanel);
        centerPanel.add(slider);
        for(int i=3;i<5;i++) centerPanel.add(tf[i]);

        panel.add(westPanel, BorderLayout.WEST); panel.add(centerPanel, BorderLayout.CENTER);
        add(panel, BorderLayout.CENTER);
    }
}
