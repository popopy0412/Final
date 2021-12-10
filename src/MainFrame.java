import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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

        tppanel.getTotal().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                dpanel = new DetailPanel();
                if(tppanel.getTotal().getSelectedValue() instanceof Movie){
                    Movie movie = (Movie)tppanel.getTotal().getSelectedValue();
                    dpanel.getIpanel().getImagePanel().setPoster(movie.getPoster());

                    JLabel[] llabels = new JLabel[7];
                    JLabel[] dlabels = new JLabel[7];
                    String[] lstr = {"제목", "감독", "배우", "장르", "등급", "개봉년도", "별점"};
                    String[] dstr = {movie.getTitle(), movie.getProducer(), movie.getActors(), movie.getGenre(), movie.getRated(), movie.getYear()+"년도", movie.getPoint()+"점"};

                    dpanel.getIpanel().getDscrpPanel().setLayout(new GridLayout(7, 1));
                    dpanel.getIpanel().getLabelPanel().setLayout(new GridLayout(7, 1));

                    for(String s : lstr) dpanel.getIpanel().getLabelPanel().add(new JLabel(s));
                    for(String s : dstr) dpanel.getIpanel().getDscrpPanel().add(new JLabel(s));

/*                    dpanel.getIpanel().getCenterPanel().add(dpanel.getIpanel().getLabelPanel(), BorderLayout.WEST);
                    dpanel.getIpanel().getCenterPanel().add(dpanel.getIpanel().getDscrpPanel(), BorderLayout.CENTER);*/
                }
                else {
                    Book book = (Book)tppanel.getTotal().getSelectedValue();
                    dpanel.getIpanel().getImagePanel().setPoster(book.getPoster());

                    JLabel[] llabels = new JLabel[5];
                    JLabel[] dlabels = new JLabel[5];
                    String[] lstr = {"제목", "저자", "출판사", "출판년도", "별점"};
                    String[] dstr = {book.getTitle(), book.getProducer(), book.getPublisher(), book.getYear()+"년도", book.getPoint()+"점"};

                    dpanel.getIpanel().getDscrpPanel().setLayout(new GridLayout(5, 1));
                    dpanel.getIpanel().getLabelPanel().setLayout(new GridLayout(5, 1));

                    for(String s : lstr) dpanel.getIpanel().getLabelPanel().add(new JLabel(s));
                    for(String s : dstr) dpanel.getIpanel().getDscrpPanel().add(new JLabel(s));

/*                    dpanel.getIpanel().getCenterPanel().add(dpanel.getIpanel().getLabelPanel(), BorderLayout.WEST);
                    dpanel.getIpanel().getCenterPanel().add(dpanel.getIpanel().getDscrpPanel(), BorderLayout.CENTER);*/
                }
                dpanel.getsPanel().setTa(((Item) tppanel.getTotal().getSelectedValue()).getSummary());
                dpanel.getrPanel().setTa(((Item) tppanel.getTotal().getSelectedValue()).getReview());
                dpanel.revalidate();
                add(dpanel, BorderLayout.CENTER);
            }
        });
        tppanel.getMovies().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                dpanel = new DetailPanel();
                Movie movie = (Movie)tppanel.getTotal().getSelectedValue();
                dpanel.getIpanel().getImagePanel().setPoster(movie.getPoster());

                JLabel[] llabels = new JLabel[7];
                JLabel[] dlabels = new JLabel[7];
                String[] lstr = {"제목", "감독", "배우", "장르", "등급", "개봉년도", "별점"};
                String[] dstr = {movie.getTitle(), movie.getProducer(), movie.getActors(), movie.getGenre(), movie.getRated(), movie.getYear()+"년도", movie.getPoint()+"점"};

                dpanel.getIpanel().getDscrpPanel().setLayout(new GridLayout(7, 1));
                dpanel.getIpanel().getLabelPanel().setLayout(new GridLayout(7, 1));

                for(String s : lstr) dpanel.getIpanel().getLabelPanel().add(new JLabel(s));
                for(String s : dstr) dpanel.getIpanel().getDscrpPanel().add(new JLabel(s));

                dpanel.getsPanel().setTa(((Item) tppanel.getTotal().getSelectedValue()).getSummary());
                dpanel.getrPanel().setTa(((Item) tppanel.getTotal().getSelectedValue()).getReview());
                dpanel.revalidate();
                add(dpanel, BorderLayout.CENTER);
            }
        });
        tppanel.getBooks().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                dpanel = new DetailPanel();
                Book book = (Book)tppanel.getTotal().getSelectedValue();
                dpanel.getIpanel().getImagePanel().setPoster(book.getPoster());

                JLabel[] llabels = new JLabel[5];
                JLabel[] dlabels = new JLabel[5];
                String[] lstr = {"제목", "저자", "출판사", "출판년도", "별점"};
                String[] dstr = {book.getTitle(), book.getProducer(), book.getPublisher(), book.getYear()+"년도", book.getPoint()+"점"};

                dpanel.getIpanel().getDscrpPanel().setLayout(new GridLayout(5, 1));
                dpanel.getIpanel().getLabelPanel().setLayout(new GridLayout(5, 1));

                for(String s : lstr) dpanel.getIpanel().getLabelPanel().add(new JLabel(s));
                for(String s : dstr) dpanel.getIpanel().getDscrpPanel().add(new JLabel(s));

                dpanel.getsPanel().setTa(((Item) tppanel.getTotal().getSelectedValue()).getSummary());
                dpanel.getrPanel().setTa(((Item) tppanel.getTotal().getSelectedValue()).getReview());
                dpanel.revalidate();
                add(dpanel, BorderLayout.CENTER);
            }
        });
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
