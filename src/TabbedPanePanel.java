import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TabbedPanePanel extends JPanel { // TabbedPane이 들어갈 패널

    private JList total; // 전체 탭에 들어갈 리스트
    private JList movies; // 영화 탭에 들어갈 리스트
    private JList books; // 책 탭에 들어갈 리스트
    private JTabbedPane tp; // TabbedPane
    private SearchTabPanel stp; // 검색 탭에 들어가는 패널
    private JPanel p; // 하단 추가 버튼이 들어가는 패널
    private JButton btn; // 하단 추가 버튼

    public TabbedPanePanel(){
        setLayout(new BorderLayout()); // 배치 관리자 설정

        tp = new JTabbedPane(JTabbedPane.TOP); // 탭의 위치 설정

        stp = new SearchTabPanel();

        total = new JList(); movies = new JList(); books = new JList();
        total.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        movies.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        books.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 각 리스트는 하나의 정보만 선택할 수 있도록 설정

        tp.addTab("전체", new JScrollPane(total));
        tp.addTab("영화", new JScrollPane(movies));
        tp.addTab("도서", new JScrollPane(books)); // 정보가 많을 때 스크롤이 생기도록 설정
        tp.addTab("검색", stp);

        btn = new JButton("추가");
        btn.addActionListener(new ActionListener() { // 추가 버튼을 눌렀을 때
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.getFrame().getDialog().setVisible(true);
            } // 입력 다이얼로그를 띄움
        });
        p = new JPanel();
        p.add(btn);

        add(tp, BorderLayout.CENTER);
        add(p, BorderLayout.SOUTH); // 각 컴포넌트들을 생성 후 위치 설정
    }
    public void renewTotal() { total.setListData(ItemCollections.getItems()); }

    public void renewMovies() {
        movies.setListData(ItemCollections.getMovies());
    }

    public void renewBooks() {
        books.setListData(ItemCollections.getBooks());
    }

    public JTabbedPane getTp() { return tp; }

    public SearchTabPanel getStp() { return stp; }

    public JList getTotal() { return total; }

    public JList getMovies() {
        return movies;
    }

    public JList getBooks() {
        return books;
    }
}
