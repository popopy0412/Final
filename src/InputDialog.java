import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InputDialog extends JDialog {
    private JPanel northPanel, centerPanel, btnPanel;
    private MoviePanel moviePanel;
    private BookPanel bookPanel;
    private JRadioButton mbtn, bbtn; // Book, Movie 버튼
    private JButton okbtn;
    private ButtonGroup bg;
    private CardLayout card; // 카드 레이아웃
    private boolean btnClicked; // 정상 종료인지
    public InputDialog(JFrame frame, String title){
        super(frame, title, true); // 제목 설정, 모달
        setSize(400, 700);

        card = new CardLayout();
        northPanel = new JPanel(new FlowLayout());
        centerPanel = new JPanel(card);
        btnPanel = new JPanel();
        bg = new ButtonGroup();
        mbtn = new JRadioButton("Movie");
        bbtn = new JRadioButton("Book");
        okbtn = new JButton("OK");
        btnPanel.add(okbtn);

        bg.add(mbtn); bg.add(bbtn);
        northPanel.add(mbtn); northPanel.add(bbtn);
        mbtn.setSelected(true);

        moviePanel = new MoviePanel();
        bookPanel = new BookPanel();
        moviePanel.add(btnPanel, BorderLayout.SOUTH);

        centerPanel.add(moviePanel, "movie");
        centerPanel.add(bookPanel, "book");

        okbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                if(mbtn.isSelected()){
                    Movie movie = moviePanel.getInformation();
                    ItemCollections.addItem(movie);
                    Main.getFrame().getTppanel().renewMovies();
                }
                else{
                    Book book = bookPanel.getInformation();
                    ItemCollections.addItem(book);
                    Main.getFrame().getTppanel().renewBooks();
                }
                Main.getFrame().getTppanel().renewTotal();
                Main.getFrame().getTppanel().revalidate();
                Main.getFrame().renewDialog();
            }
        });

        mbtn.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.DESELECTED) return;
                if(mbtn.isSelected()) {
                    moviePanel.add(btnPanel, BorderLayout.SOUTH);
                    card.show(centerPanel, "movie");
                }
            }
        });

        bbtn.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.DESELECTED) return;
                if(bbtn.isSelected()) {
                    bookPanel.add(btnPanel, BorderLayout.SOUTH);
                    card.show(centerPanel, "book");
                }
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(!btnClicked) Main.getFrame().renewDialog();
            }
        });

        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }
    public void actionPerformed(ActionEvent e){
        btnClicked = true;
    }
    public MoviePanel getMoviePanel() {
        return moviePanel;
    }

    public BookPanel getBookPanel() {
        return bookPanel;
    }
}
