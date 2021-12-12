import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookPanel extends JPanel {
    private JPanel panel, imagePanel, westPanel, centerPanel;
    private JLabel[] labels;
    private JTextArea[] ta;
    private JComboBox<Integer> cb;
    private JTextField tf;
    private JButton btn;
    private JSlider slider;
    private String[] str = {"제목", "저자", "출판사", "출판년도", "책이미지", "별점", "책소개", "감상평"};
    public BookPanel(){
        super(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("도서 정보"));

        panel = new JPanel(new BorderLayout());
        westPanel = new JPanel(new GridLayout(8, 1, 20, 20));
        centerPanel = new JPanel(new GridLayout(8, 1, 20, 20));
        imagePanel = new JPanel(new BorderLayout());
        labels = new JLabel[8];
        ta = new JTextArea[5];
        cb = new JComboBox<>();
        tf = new JTextField();
        tf.setEditable(false);
        btn = new JButton("불러오기");
        imagePanel.add(tf, BorderLayout.CENTER); imagePanel.add(btn, BorderLayout.EAST);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser ch = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, JPEG, PNG", "jpg", "png", "jpeg");
                ch.setFileFilter(filter);

                int ret = ch.showOpenDialog(null);
                if(ret == JFileChooser.APPROVE_OPTION){
                    tf.setText(ch.getSelectedFile().getPath());
                }
            }
        });

        for(int i=1900;i<=2021;i++) cb.addItem(i);
        for(int i=0;i<5;i++){
            ta[i] = new JTextArea();
            ta[i].setLineWrap(true);
        }
        for(int i=0;i<8;i++){
            labels[i] = new JLabel(str[i]);
            westPanel.add(labels[i]);
        }

        slider = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
        slider.setPaintLabels(true); slider.setPaintTicks(true); slider.setPaintTrack(true);
        slider.setMajorTickSpacing(1);

        for(int i=0;i<3;i++) centerPanel.add(new JScrollPane(ta[i]));
        centerPanel.add(cb);
        centerPanel.add(imagePanel);
        centerPanel.add(slider);
        for(int i=3;i<5;i++) centerPanel.add(new JScrollPane(ta[i]));

        panel.add(westPanel, BorderLayout.WEST); panel.add(centerPanel, BorderLayout.CENTER);
        add(panel, BorderLayout.CENTER);
    }

    public Book getInformation(){
        String title = ta[0].getText(); // 제목
        String producer = ta[1].getText(); // 제작자(감독, 저자)
        String summary = ta[3].getText(); // 줄거리, 내용
        String review = ta[4].getText(); // 감상평
        String path = tf.getText(); // 이미지 경로
        int point = slider.getValue(); // 별점
        int year = (int)cb.getSelectedItem(); // 제작 년도
        String publisher = ta[2].getText(); // 출판사
        return new Book(title, producer, summary, review, path, point, year, publisher);
    }
    
    public void setInformation(Book book){
        ta[0].setText(book.getTitle());
        ta[1].setText(book.getProducer());
        ta[3].setText(book.getSummary());
        ta[4].setText(book.getReview());
        tf.setText(book.getPath());
        slider.setValue(book.getPoint());
        cb.setSelectedItem(book.getYear());
        ta[2].setText(book.getPublisher());
    }
}
