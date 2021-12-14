import javax.swing.*;
import java.awt.*;

public class MoviePanel extends JPanel { // 입력, 수정 다이얼로그에 들어갈 영화 정보 입력 패널
    private JPanel panel, imagePanel, westPanel, centerPanel; // 라벨과 입력창들이 들어갈 패널, 사진입력창만 따로 들어갈 패널, 라벨이 들어갈 패널, 입력창이 들어갈 패널
    private JLabel[] labels; // 제목, 배우 등등 라벨
    private JTextArea[] ta; // 제목, 배우 등등 정보를 입력받음
    private JComboBox genrecb, ratedcb, yearcb; // 장르, 등급, 제작년도
    private JTextField tf; // 사진 경로
    private JButton btn; // 사진 불러오기 버튼
    private JSlider slider; // 별점
    private String[] str = {"제목", "감독", "배우", "장르", "등급", "개봉년도", "포스터", "별점", "줄거리", "감상평"}; // 라벨 문자열들
    private String[] genre = {"액션/스릴러", "공포", "코미디", "드라마", "SF", "판타지", "애니메이션", "음악/뮤지컬", "멜로", "스포츠", "범죄/느와르"}; // 장르 콤보박스에 들어갈 것들
    private String[] rated = {"전체", "12세 이상", "15세 이상", "청소년 관람 불가"}; // 등급 콤보박스에 들어갈 것들
    public MoviePanel(){ // 각 컴포넌트들을 생성하여 패널에 붙임
        super(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("영화 정보"));

        panel = new JPanel(new BorderLayout());
        imagePanel = new JPanel(new BorderLayout());
        westPanel = new JPanel(new GridLayout(10, 1, 20, 20));
        centerPanel = new JPanel(new GridLayout(10, 1, 20, 20));

        tf = new JTextField();
        tf.setEditable(false);
        btn = new JButton("불러오기");
        imagePanel.add(tf, BorderLayout.CENTER); imagePanel.add(btn, BorderLayout.EAST);

        btn.addActionListener(new LoadbtnListener(tf));

        labels = new JLabel[10];
        ta = new JTextArea[5];
        for(int i=0;i<10;i++){
            labels[i] = new JLabel(str[i]);
            westPanel.add(labels[i]);
        }
        for(int i=0;i<5;i++){
            ta[i] = new JTextArea();
            new JScrollPane(ta[i]);
            ta[i].setLineWrap(true);
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

        for(int i=0;i<3;i++) centerPanel.add(new JScrollPane(ta[i]));
        centerPanel.add(genrecb); centerPanel.add(ratedcb); centerPanel.add(yearcb);
        centerPanel.add(imagePanel);
        centerPanel.add(slider);
        for(int i=3;i<5;i++) centerPanel.add(new JScrollPane(ta[i]));

        panel.add(westPanel, BorderLayout.WEST); panel.add(centerPanel, BorderLayout.CENTER);
        add(panel, BorderLayout.CENTER); // 각 컴포넌트들 위치 설정 후 위치 설정
    }

    public Movie getInformation(){ // 입력된 영화 정보를 받음
        String title = ta[0].getText(); // 제목
        String producer = ta[1].getText(); // 제작자(감독, 저자)
        String summary = ta[3].getText(); // 줄거리, 내용
        String review = ta[4].getText(); // 감상평
        String path = tf.getText(); // 이미지 경로
        int point = slider.getValue(); // 별점
        int year = (int)yearcb.getSelectedItem(); // 제작 년도
        String actors = ta[2].getText(); // 배우
        String genre = (String)genrecb.getSelectedItem(); // 장르
        String rated = (String)ratedcb.getSelectedItem(); // 등급
        return new Movie(title, producer, summary, review, path, point, year, actors, genre, rated);
    }

    public void setInformation(Movie movie){ // 입력창에 수정할 영화 정보를 입력함
        ta[0].setText(movie.getTitle());
        ta[1].setText(movie.getProducer());
        ta[3].setText(movie.getSummary());
        ta[4].setText(movie.getReview());
        tf.setText(movie.getPath());
        slider.setValue(movie.getPoint());
        yearcb.setSelectedItem(movie.getYear());
        ta[2].setText(movie.getActors());
        genrecb.setSelectedItem(movie.getGenre());
        ratedcb.setSelectedItem(movie.getRated());
    }
}
