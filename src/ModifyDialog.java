import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ModifyDialog extends JDialog {
    private JPanel northPanel, centerPanel, btnPanel;
    private MoviePanel moviePanel;
    private BookPanel bookPanel;
    private JRadioButton mbtn, bbtn; // Book, Movie 버튼
    private JButton okbtn;
    private ButtonGroup bg;
    private CardLayout card; // 카드 레이아웃
    private boolean btnClicked = false; // 정상 종료인지
    public ModifyDialog(JFrame frame, String title){
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
                do {
                    int idx = Main.getFrame().getTppanel().getTp().getSelectedIndex();
                    if (idx == 0) {
                        if (Main.getFrame().getTppanel().getTotal().getSelectedValue() instanceof Movie) {
                            mbtn.setSelected(true);
                            Movie movie = moviePanel.getInformation();
                            Movie smovie = (Movie) Main.getFrame().getTppanel().getTotal().getSelectedValue();
                            if (movie.getTitle().equals("")) {
                                JOptionPane.showMessageDialog(null, "제목은 필수 입력 항목입니다", "제목이 입력되지 않음", JOptionPane.WARNING_MESSAGE);
                                break;
                            }
                            smovie.setActors(movie.getActors());
                            smovie.setGenre(movie.getGenre());
                            smovie.setRated(movie.getRated());
                            smovie.setPoint(movie.getPoint());
                            smovie.setPath(movie.getPath());
                            smovie.setProducer(movie.getProducer());
                            smovie.setTitle(movie.getTitle());
                            smovie.setReview(movie.getReview());
                            smovie.setSummary(movie.getSummary());
                            smovie.setYear(movie.getYear());

                            Main.getFrame().getTppanel().renewMovies();
                        } else {
                            bbtn.setSelected(true);
                            card.show(centerPanel, "book");
                            Book book = bookPanel.getInformation();
                            Book sbook = (Book) Main.getFrame().getTppanel().getTotal().getSelectedValue();
                            sbook.setPath(book.getPath());
                            sbook.setPoint(book.getPoint());
                            sbook.setPublisher(book.getPublisher());
                            sbook.setProducer(book.getProducer());
                            sbook.setReview(book.getReview());
                            sbook.setSummary(book.getReview());
                            sbook.setTitle(book.getTitle());
                            sbook.setYear(book.getYear());

                            Main.getFrame().getTppanel().renewBooks();
                        }
                    }
                    if (idx == 1) {
                        mbtn.setSelected(true);
                        card.show(centerPanel, "movie");
                        Movie movie = moviePanel.getInformation();
                        Movie smovie = (Movie) Main.getFrame().getTppanel().getMovies().getSelectedValue();
                        smovie.setActors(movie.getActors());
                        smovie.setGenre(movie.getGenre());
                        smovie.setRated(movie.getRated());
                        smovie.setPoint(movie.getPoint());
                        smovie.setPath(movie.getPath());
                        smovie.setProducer(movie.getProducer());
                        smovie.setTitle(movie.getTitle());
                        smovie.setReview(movie.getReview());
                        smovie.setSummary(movie.getSummary());
                        smovie.setYear(movie.getYear());

                        Main.getFrame().getTppanel().renewMovies();
                    } else if (idx == 2) {
                        bbtn.setSelected(true);
                        card.show(centerPanel, "book");
                        Book book = bookPanel.getInformation();
                        Book sbook = (Book) Main.getFrame().getTppanel().getBooks().getSelectedValue();
                        sbook.setPath(book.getPath());
                        sbook.setPoint(book.getPoint());
                        sbook.setPublisher(book.getPublisher());
                        sbook.setProducer(book.getProducer());
                        sbook.setReview(book.getReview());
                        sbook.setSummary(book.getReview());
                        sbook.setTitle(book.getTitle());
                        sbook.setYear(book.getYear());

                        Main.getFrame().getTppanel().renewBooks();
                    } else if (idx == 3) {
                        if (Main.getFrame().getTppanel().getStp().getList().getSelectedValue() instanceof Movie) {
                            mbtn.setSelected(true);
                            card.show(centerPanel, "movie");
                            Movie movie = moviePanel.getInformation();
                            Movie smovie = (Movie) Main.getFrame().getTppanel().getStp().getList().getSelectedValue();
                            smovie.setActors(movie.getActors());
                            smovie.setGenre(movie.getGenre());
                            smovie.setRated(movie.getRated());
                            smovie.setPoint(movie.getPoint());
                            smovie.setPath(movie.getPath());
                            smovie.setProducer(movie.getProducer());
                            smovie.setTitle(movie.getTitle());
                            smovie.setReview(movie.getReview());
                            smovie.setSummary(movie.getSummary());
                            smovie.setYear(movie.getYear());

                            Main.getFrame().getTppanel().getStp().renewList(false);
                            Main.getFrame().getTppanel().renewMovies();
                        } else {
                            bbtn.setSelected(true);
                            card.show(centerPanel, "book");
                            Book book = bookPanel.getInformation();
                            Book sbook = (Book) Main.getFrame().getTppanel().getStp().getList().getSelectedValue();
                            sbook.setPath(book.getPath());
                            sbook.setPoint(book.getPoint());
                            sbook.setPublisher(book.getPublisher());
                            sbook.setProducer(book.getProducer());
                            sbook.setReview(book.getReview());
                            sbook.setSummary(book.getReview());
                            sbook.setTitle(book.getTitle());
                            sbook.setYear(book.getYear());

                            Main.getFrame().getTppanel().getStp().renewList(false);
                            Main.getFrame().getTppanel().renewBooks();
                        }
                    }
                    Main.getFrame().getCenterPanel().removeAll();
                    for (int i = 0; i < 4; i++) {
                        Main.getFrame().getDpanel()[i] = new DetailPanel();
                        Main.getFrame().getCenterPanel().add(Integer.toString(i), Main.getFrame().getDpanel()[i]);
                    }
                    Main.getFrame().getCard().show(Main.getFrame().getCenterPanel(), Integer.toString(idx));

                    Main.getFrame().getTppanel().renewTotal();
                    Main.getFrame().getTppanel().revalidate();
                    Main.getFrame().renewMdialog();

                    setVisible(false);
                    break;
                }while(true);
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
                if(!btnClicked) Main.getFrame().renewMdialog();
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

    public JPanel getCenterPanel() {
        return centerPanel;
    }

    public JRadioButton getMbtn() {
        return mbtn;
    }

    public JRadioButton getBbtn() {
        return bbtn;
    }

    public CardLayout getCard() {
        return card;
    }
}
