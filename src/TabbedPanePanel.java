import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        total = new JList(); movies = new JList(); books = new JList();
        total.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        movies.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        books.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        new JScrollPane(total); new JScrollPane(movies); new JScrollPane(books);

        total.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
             //   if()
            }
        });

        tp.addTab("전체", total);
        tp.addTab("영화", movies);
        tp.addTab("도서", books);
        tp.addTab("검색", stp);

        btn = new JButton("추가");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.getDialog().setVisible(true);
            }
        });
        p = new JPanel();
        p.add(btn);

        add(tp, BorderLayout.CENTER);
        add(p, BorderLayout.SOUTH);
    }
    public void renewTotal() {
        total.setListData(ItemCollections.getItems());
    }

    public void renewMovies() {
        movies.setListData(ItemCollections.getMovies());
    }

    public void renewBooks() {
        books.setListData(ItemCollections.getBooks());
    }
}
