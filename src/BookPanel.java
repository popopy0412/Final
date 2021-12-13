import javax.swing.*;
import java.awt.*;

public class BookPanel extends JPanel { // 입력, 수정 다이얼로그에 들어갈 책 정보 입력 패널
    private JPanel panel, imagePanel, westPanel, centerPanel; // 라벨과 입력창들이 들어갈 패널, 사진입력창만 따로 들어갈 패널, 라벨이 들어갈 패널, 입력창이 들어갈 패널
    private JLabel[] labels; // 제목, 출판사 등등 라벨
    private JTextArea[] ta; // 제목, 출판사 등등 정보를 입력받음
    private JComboBox<Integer> cb; // 출판년도
    private JTextField tf; // 사진 경로
    private JButton btn; // 사진 불러오기 버튼
    private JSlider slider; // 별점
    private String[] str = {"제목", "저자", "출판사", "출판년도", "책이미지", "별점", "책소개", "감상평"}; // 라벨 문자열들
    public BookPanel(){ // 각 컴포넌트들을 생성하어 패널에 붙임
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

        btn.addActionListener(new LoadbtnListener(tf));

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

    public Book getInformation(){ // 입력된 책 정보를 받음
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
    
    public void setInformation(Book book){ // 입력창에 수정할 책 정보를 입력함
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
