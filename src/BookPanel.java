import javax.swing.*;
import java.awt.*;

public class BookPanel extends JPanel {
    private JPanel panel, imagePanel, westPanel, centerPanel;
    private JLabel[] labels;
    private JTextField[] tf;
    private JComboBox<Integer> cb;
    private JTextArea ta;
    private JButton btn;
    private JSlider slider;
    private String[] str = {"제목", "저자", "출판사", "출판년도", "책이미지", "별점", "책소개", "감상평"};
    public BookPanel(){
        super(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("도서 정보"));

        panel = new JPanel(new BorderLayout());
        westPanel = new JPanel(new GridLayout(8, 1));
        centerPanel = new JPanel(new GridLayout(8, 1));
        imagePanel = new JPanel(new BorderLayout());
        labels = new JLabel[8];
        tf = new JTextField[5];
        cb = new JComboBox<>();
        ta = new JTextArea();
        btn = new JButton("불러오기");
        imagePanel.add(ta, BorderLayout.CENTER); imagePanel.add(btn, BorderLayout.EAST);

        for(int i=1900;i<=2021;i++) cb.addItem(i);
        for(int i=0;i<5;i++){
            tf[i] = new JTextField(20);
            new JScrollPane(tf[i]);
        }
        for(int i=0;i<8;i++){
            labels[i] = new JLabel(str[i]);
            westPanel.add(labels[i]);
        }

        slider = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
        slider.setPaintLabels(true); slider.setPaintTicks(true); slider.setPaintTrack(true);
        slider.setMajorTickSpacing(1);

        for(int i=0;i<3;i++) centerPanel.add(tf[i]);
        centerPanel.add(cb);
        centerPanel.add(imagePanel);
        centerPanel.add(slider);
        for(int i=3;i<5;i++) centerPanel.add(tf[i]);

        panel.add(westPanel, BorderLayout.WEST); panel.add(centerPanel, BorderLayout.CENTER);
        add(panel, BorderLayout.CENTER);
    }
}
