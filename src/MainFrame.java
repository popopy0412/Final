import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainFrame extends JFrame{ // 메인 프레임

    private JPanel panel; // 전체 컴포넌트들이 들어갈 패널
    private JLabel label; // My Notes
    private InputDialog dialog; // 입력 다이얼로그
    private ModifyDialog mdialog; // 수정 다이얼로그
    private TabbedPanePanel tppanel; // TabbedPane 패널
    private JPanel centerPanel; // 세부정보가 들어갈 패널
    private JPanel northPanel; // My Notes, 시간이 표현될 패널
    private DetailPanel[] dpanel; // 전체, 영화, 책, 검색 탭에 따라 뜨는 세부정보 패널
    private Menubar bar; // 툴바
    private CardLayout card; // 전체, 영화, 책, 검색 탭에 따라 뜨는 세부정보 패널을 나타내줄 카드 레이아웃
    public MainFrame(String title){
        super(title); // 제목 설정
        card = new CardLayout();
        setSize(1100, 700); // 크기 설정
        setResizable(false); // 프레임 사이즈 변경 불가
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 종료 조건 설정

        Container c = getContentPane(); // ContentPane 가져옴
        c.setLayout(new BorderLayout()); // 배치 관리자 설정

        panel = new JPanel(new BorderLayout());
        tppanel = new TabbedPanePanel();
        centerPanel = new JPanel(card);
        dpanel = new DetailPanel[4];
        bar = new Menubar();
        setJMenuBar(bar);

        for(int i=0;i<4;i++){
            dpanel[i] = new DetailPanel();
            centerPanel.add(Integer.toString(i), dpanel[i]);
        }

        dialog = new InputDialog(this, "입력");
        dialog.setVisible(false);
        mdialog = new ModifyDialog(this, "수정");
        mdialog.setVisible(false);

        label = new JLabel("My Notes");
        label.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 30));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(Color.BLUE);

        northPanel = new JPanel(new GridLayout(1, 2));
        TimeLabel timeLabel = new TimeLabel();
        timeLabel.setHorizontalAlignment(JLabel.RIGHT);

        northPanel.add(label);
        northPanel.add(timeLabel);

        panel.add(northPanel, BorderLayout.NORTH);
        panel.add(tppanel, BorderLayout.WEST);
        panel.add(centerPanel, BorderLayout.CENTER);
        add(panel); // 각 컴포넌트들 위치 설정

        setVisible(true); // 보이게 설정

        tppanel.getTp().addChangeListener(new ChangeListener() { // 탭을 바꿨을 때
            @Override
            public void stateChanged(ChangeEvent e) {
                card.show(centerPanel, Integer.toString(tppanel.getTp().getSelectedIndex())); // 탭에 따라 세부 정보 패널을 바꿈
            }
        });
        tppanel.getTotal().addListSelectionListener(new ListSelectionListener() { // 전체 탭에 있는 리스트 항목을 눌렀을 때
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int idx = tppanel.getTp().getSelectedIndex(); // 선택한 항목의 인덱스
                if(tppanel.getTotal().getSelectedValue() instanceof Movie){ // 선택한 항목이 영화면
                    Movie movie = (Movie)tppanel.getTotal().getSelectedValue();
                    selectMovie(movie, idx); // 영화를 불러와서 영화 정보를 세부 정보 패널에 띄움
                }
                else { // 책이면
                    Book book = (Book)tppanel.getTotal().getSelectedValue();
                    selectBook(book, idx); // 책을 불러와서 책 정보를 세부 정보 패널에 띄움
                }
            }
        });
        tppanel.getMovies().addListSelectionListener(new ListSelectionListener() { // 영화 탭에 있는 리스트 항목을 눌렀을 때
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int idx = tppanel.getTp().getSelectedIndex();
                Movie movie = (Movie)tppanel.getMovies().getSelectedValue();
                selectMovie(movie, idx); // 영화 정보를 패널에 띄움
            }
        });
        tppanel.getBooks().addListSelectionListener(new ListSelectionListener() { // 책 탭에 있는 리스트 항목을 눌렀을 때
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int idx = tppanel.getTp().getSelectedIndex();
                Book book = (Book)tppanel.getBooks().getSelectedValue();
                selectBook(book, idx); // 책 정보를 패널에 띄움
            }
        });
        tppanel.getStp().getList().addListSelectionListener(new ListSelectionListener() { // 검색 탭에 있는 리스트 항목을 눌렀을 때
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int idx = tppanel.getTp().getSelectedIndex();
                if(tppanel.getStp().getList().getSelectedValue() instanceof Movie){ // 선택한 항목이 영화면
                    Movie movie = (Movie)tppanel.getStp().getList().getSelectedValue();
                    selectMovie(movie, idx); // 영화 정보를 띄움
                }
                else { // 책이면
                    Book book = (Book)tppanel.getStp().getList().getSelectedValue();
                    selectBook(book, idx); // 책 정보를 띄움
                }
            }
        });
    }

    public void selectMovie(Movie movie, int idx){ // 영화를 선택했을 때 영화 정보를 띄움
        if(movie == null) return;
        if(movie.getPath() != null)  dpanel[idx].getIpanel().getImagePanel().setPoster(movie.getPath());

        String[] lstr = {"제목", "감독", "배우", "장르", "등급", "개봉년도", "별점"};
        String[] dstr = {movie.getTitle(), movie.getProducer(), movie.getActors(), movie.getGenre(), movie.getRated(), movie.getYear()+"년도", movie.getPoint()+"점"};

        for(int i=0;i<7;i++) {
            dpanel[idx].getIpanel().getLabel()[i].setText(lstr[i]);
            dpanel[idx].getIpanel().getDscrp()[i].setText(dstr[i]);
        }

        Item item;
        if(idx == 0) item = (Item) tppanel.getTotal().getSelectedValue();
        else if(idx == 1) item = (Item) tppanel.getMovies().getSelectedValue();
        else item = (Item) tppanel.getStp().getList().getSelectedValue();
        dpanel[idx].getsPanel().setTa(item.getSummary());
        dpanel[idx].getrPanel().setTa(item.getReview());
        dpanel[idx].revalidate();
        dpanel[idx].getIpanel().getImagePanel().repaint();
    }
    public void selectBook(Book book, int idx){ // 책을 선택했을 때 책 정보를 띄움
        if(book == null) return;
        if(book.getPath() != null) dpanel[idx].getIpanel().getImagePanel().setPoster(book.getPath());

        String[] lstr = {"제목", "저자", "출판사", "출판년도", "별점", "", ""};
        String[] dstr = {book.getTitle(), book.getProducer(), book.getPublisher(), book.getYear()+"년도", book.getPoint()+"점", "", ""};

        for(int i=0;i<7;i++) {
            dpanel[idx].getIpanel().getLabel()[i].setText(lstr[i]);
            dpanel[idx].getIpanel().getDscrp()[i].setText(dstr[i]);
        }

        Item item;
        if(idx == 0) item = (Item) tppanel.getTotal().getSelectedValue();
        else if(idx == 2) item = (Item) tppanel.getBooks().getSelectedValue();
        else item = (Item) tppanel.getStp().getList().getSelectedValue();
        dpanel[idx].getsPanel().setTa(item.getSummary());
        dpanel[idx].getrPanel().setTa(item.getReview());
        dpanel[idx].revalidate();
        dpanel[idx].getIpanel().getImagePanel().repaint();
    }

    public JPanel getCenterPanel() {
        return centerPanel;
    }
    public CardLayout getCard() { return card; }
    public InputDialog getDialog() {
        return dialog;
    }
    public ModifyDialog getMdialog() { return mdialog; }
    public TabbedPanePanel getTppanel() { return tppanel; }
    public DetailPanel[] getDpanel() { return dpanel; }

    public void renewDialog() { dialog = new InputDialog(this, "입력"); } // 입력 다이얼로그 초기화
    public void renewMdialog() { mdialog = new ModifyDialog(this, "수정"); } // 수정 다이얼로그 초기화
}