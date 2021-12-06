import javax.swing.*;
import java.awt.*;

public class TabbedPanePanel extends JPanel {

    private JList total;
    private JList movies;
    private JList books;
    private JTabbedPane tp;
    private SearchTabPanel stp;
    private JPanel p;
    private JButton btn;

    public TabbedPanePanel(){
        setLayout(new BorderLayout()); // 배치 관리자 설정

        tp = new JTabbedPane(JTabbedPane.TOP); // 탭의 위치 설정

        stp = new SearchTabPanel();

        total = new JList(ItemCollections.getItems());
        movies = new JList(ItemCollections.getMovies());
        books = new JList(ItemCollections.getBooks());

        tp.addTab("전체", total);
        tp.addTab("영화", movies);
        tp.addTab("도서", books);
        tp.addTab("검색", stp);

        btn = new JButton("추가");
        p = new JPanel();
        p.add(btn);

        add(tp, BorderLayout.CENTER);
        add(p, BorderLayout.SOUTH);
    }
}
