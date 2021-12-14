import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ModifyDialog extends JDialog { // 수정 다이얼로그
    private JPanel northPanel, centerPanel, btnPanel; // 영화, 책 선택 버튼이 들어갈 패널, 정보 입력란이 들어갈 패널, 하단 OK버튼이 들어갈 패널
    private MoviePanel moviePanel; // 영화 입력창
    private BookPanel bookPanel; // 책 입력창
    private JRadioButton mbtn, bbtn; // Book, Movie 버튼
    private JButton okbtn; // 하단 OK 버튼
    private ButtonGroup bg; // 영화, 책 버튼이 들어갈 버튼그룹
    private CardLayout card; // 영화, 책 입력창을 나타내줄 카드 레이아웃
    private boolean btnClicked; // 정상 종료인지(X 버튼이 아닌 OK 버튼을 눌러서 종료했는지 아닌지)
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
        centerPanel.add(bookPanel, "book"); // 카드 레이아웃에 영화 정보와 책 정보 입력 패널을 추가 및 기타 컴포넌트 위치 설정

        okbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                do { // 영화 제목이나 책 제목이 입력될 때까지 반복
                    int idx = Main.getFrame().getTppanel().getTp().getSelectedIndex(); // TabbedPane의 몇 번째 탭이 선택되었는지 나타냄
                    if (idx == 0) { // 전체 탭일 때
                        if (Main.getFrame().getTppanel().getTotal().getSelectedValue() instanceof Movie) { // 선택된 정보가 영화일 때
                            mbtn.setSelected(true);
                            //card.show(centerPanel, "movie"); // 영화 정보 입력 패널을 보여줌
                            Movie movie = moviePanel.getInformation(); // 정보 입력창에 수정된 영화 정보를 가져옴
                            Movie smovie = (Movie) Main.getFrame().getTppanel().getTotal().getSelectedValue(); // 선택된 영화
                            if (movie.getTitle().equals("")) { // 영화 제목이 입력이 안돼있으면
                                JOptionPane.showMessageDialog(null, "제목은 필수 입력 항목입니다", "제목이 입력되지 않음", JOptionPane.WARNING_MESSAGE);
                                break;
                                // 오류 메시지 띄움
                            }
                            setMovie(smovie, movie); // 아니면 영화 정보 갱신

                            Main.getFrame().getTppanel().renewMovies(); // 영화 리스트 갱신
                        } else { // 선택된 정보가 책일 때
                            bbtn.setSelected(true);
                            //card.show(centerPanel, "book"); // 책 정보 입력 패널을 보여줌
                            Book book = bookPanel.getInformation(); // 정보 입력창에 수정된 책 정보를 가져옴
                            Book sbook = (Book) Main.getFrame().getTppanel().getTotal().getSelectedValue(); // 선택된 책
                            if (book.getTitle().equals("")) { // 책 제목이 입력이 안돼있으면
                                JOptionPane.showMessageDialog(null, "제목은 필수 입력 항목입니다", "제목이 입력되지 않음", JOptionPane.WARNING_MESSAGE);
                                break;
                                // 오류 메시지 띄움
                            }
                            setBook(sbook, book); // 아니면 책 정보 갱신

                            Main.getFrame().getTppanel().renewBooks(); // 책 리스트 갱신
                        }
                    } // 이하 영화, 책, 검색 탭에 대해서도 동일한 작업 수행
                    if (idx == 1) { // 영화 탭일 때
                        mbtn.setSelected(true);
                        //card.show(centerPanel, "movie");
                        Movie movie = moviePanel.getInformation();
                        Movie smovie = (Movie) Main.getFrame().getTppanel().getMovies().getSelectedValue();
                        if (movie.getTitle().equals("")) { // 영화 제목이 입력이 안돼있으면
                            JOptionPane.showMessageDialog(null, "제목은 필수 입력 항목입니다", "제목이 입력되지 않음", JOptionPane.WARNING_MESSAGE);
                            break;
                            // 오류 메시지 띄움
                        }
                        setMovie(smovie, movie);

                        Main.getFrame().getTppanel().renewMovies();
                    } else if (idx == 2) { // 책 탭일 때
                        bbtn.setSelected(true);
                        //card.show(centerPanel, "book");
                        Book book = bookPanel.getInformation();
                        Book sbook = (Book) Main.getFrame().getTppanel().getBooks().getSelectedValue();
                        if (book.getTitle().equals("")) { // 책 제목이 입력이 안돼있으면
                            JOptionPane.showMessageDialog(null, "제목은 필수 입력 항목입니다", "제목이 입력되지 않음", JOptionPane.WARNING_MESSAGE);
                            break;
                            // 오류 메시지 띄움
                        }
                        setBook(sbook, book);

                        Main.getFrame().getTppanel().renewBooks();
                    } else if (idx == 3) { // 검색 탭일 때
                        if (Main.getFrame().getTppanel().getStp().getList().getSelectedValue() instanceof Movie) { // 선택된 정보가 영화일 때
                            mbtn.setSelected(true);
                            //card.show(centerPanel, "movie");
                            Movie movie = moviePanel.getInformation();
                            Movie smovie = (Movie) Main.getFrame().getTppanel().getStp().getList().getSelectedValue();
                            if (movie.getTitle().equals("")) { // 영화 제목이 입력이 안돼있으면
                                JOptionPane.showMessageDialog(null, "제목은 필수 입력 항목입니다", "제목이 입력되지 않음", JOptionPane.WARNING_MESSAGE);
                                break;
                                // 오류 메시지 띄움
                            }
                            setMovie(smovie, movie);

                            Main.getFrame().getTppanel().getStp().renewList(false); // 검색 탭 리스트를 갱신(검색이 아닌 다른 이유로 갱신)
                            Main.getFrame().getTppanel().renewMovies();
                        } else { // 선택된 정보가 책일 때
                            bbtn.setSelected(true);
                            //card.show(centerPanel, "book");
                            Book book = bookPanel.getInformation();
                            Book sbook = (Book) Main.getFrame().getTppanel().getStp().getList().getSelectedValue();
                            if (book.getTitle().equals("")) { // 책 제목이 입력이 안돼있으면
                                JOptionPane.showMessageDialog(null, "제목은 필수 입력 항목입니다", "제목이 입력되지 않음", JOptionPane.WARNING_MESSAGE);
                                break;
                                // 오류 메시지 띄움
                            }
                            setBook(sbook, book);

                            Main.getFrame().getTppanel().getStp().renewList(false); // 검색 탭 리스트를 갱신(검색이 아닌 다른 이유로 갱신)
                            Main.getFrame().getTppanel().renewBooks();
                        }
                    }
                    Main.getFrame().getCenterPanel().removeAll();
                    for (int i = 0; i < 4; i++) {
                        Main.getFrame().getDpanel()[i] = new DetailPanel();
                        Main.getFrame().getCenterPanel().add(Integer.toString(i), Main.getFrame().getDpanel()[i]);
                    }
                    Main.getFrame().getCard().show(Main.getFrame().getCenterPanel(), Integer.toString(idx)); // 수정 작업을 실행 후, 세부 정보 패널을 모두 초기화

                    Main.getFrame().getTppanel().renewTotal(); // 전체 탭 리스트를 갱신
                    Main.getFrame().renewMdialog(); // 수정 다이얼로그 초기화

                    setVisible(false); // 안 보이게 설정
                    break; // 정상 종료
                }while(true);
            }
        });

        mbtn.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) { // 영화 버튼의 상태가 변경되었을 때
                if(e.getStateChange() == ItemEvent.DESELECTED) return; // 영화 버튼이 선택 해제되었을 때는 종료
                if(mbtn.isSelected()) { // 영화 버튼이 선택되었을 때
                    moviePanel.add(btnPanel, BorderLayout.SOUTH);
                    card.show(centerPanel, "movie"); // 영화 정보 입력 패널을 보여줌
                }
            }
        });

        bbtn.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) { // 책 버튼의 상태가 변경되었을 때
                if(e.getStateChange() == ItemEvent.DESELECTED) return; // 책 버튼이 선택 해제되었을 때는 종료
                if(bbtn.isSelected()) { // 책 버튼이 선택되었을 때
                    bookPanel.add(btnPanel, BorderLayout.SOUTH);
                    card.show(centerPanel, "book"); // 책 정보 입력 패널을 보여줌
                }
            }
        });

        this.addWindowListener(new WindowAdapter() { // 수정 창이 닫힐 때
            @Override
            public void windowClosing(WindowEvent e) {
                if(!btnClicked) Main.getFrame().renewMdialog(); // X 버튼으로 창이 닫혔을 때 수정 다이얼로그 초기화
            }
        });

        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }

    public void setMovie(Movie smovie, Movie movie){ // 선택된 영화 정보를 수정함
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
    }
    public void setBook(Book sbook, Book book){ // 선택된 책 정보를 수정함
        sbook.setPath(book.getPath());
        sbook.setPoint(book.getPoint());
        sbook.setPublisher(book.getPublisher());
        sbook.setProducer(book.getProducer());
        sbook.setReview(book.getReview());
        sbook.setSummary(book.getReview());
        sbook.setTitle(book.getTitle());
        sbook.setYear(book.getYear());
    }
    public void actionPerformed(ActionEvent e){
        btnClicked = true;
    } // X 버튼이 아닌 OK 버튼으로 눌렀을 때 true(정상 종료를 식별하기 위함)
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
