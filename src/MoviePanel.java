import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoviePanel extends JPanel {
    private JPanel panel, imagePanel, westPanel, centerPanel;
    private JLabel[] labels;
    private JTextArea[] ta;
    private JComboBox genrecb, ratedcb, yearcb;
    private JTextField tf;
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
        westPanel = new JPanel(new GridLayout(10, 1, 20, 20));
        centerPanel = new JPanel(new GridLayout(10, 1, 20, 20));

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

                do {
                    String name; // 파일 이름
                    String pngjpg, jpeg; // 사진인지 아닌지 확인하는 문자열
                    int len; // 파일 이름 길이
                    int ret = ch.showOpenDialog(null);
                    if (ret == JFileChooser.APPROVE_OPTION) {
                        name = ch.getSelectedFile().getName();
                        len = name.length();
                        pngjpg = name.substring(len-3, len);
                        jpeg = name.substring(len-4, len);
                        if(pngjpg.equals("png") || pngjpg.equals("jpg") || jpeg.equals("jpeg")) {
                            tf.setText(ch.getSelectedFile().getPath());
                            break;
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "PNG, JPG, JPEG 파일이 아닙니다. 다시 선택해주세요", "잘못된 파일 선택", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    else break;
                }while(true);
            }
        });

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
        add(panel, BorderLayout.CENTER);
    }

    public Movie getInformation(){
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

    public void setInformation(Movie movie){
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
