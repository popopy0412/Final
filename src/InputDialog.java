import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InputDialog extends JDialog { // 입력 다이얼로그
    private JPanel northPanel, centerPanel, btnPanel; // 영화, 책 선택 버튼이 들어갈 패널, 정보 입력란이 들어갈 패널, 하단 OK버튼이 들어갈 패널
    private MoviePanel moviePanel; // 영화 입력창
    private BookPanel bookPanel; // 책 입력창
    private JRadioButton mbtn, bbtn; // Book, Movie 버튼
    private JButton okbtn; // 하단 OK 버튼
    private ButtonGroup bg; // 영화, 책 버튼이 들어갈 버튼그룹
    private CardLayout card; // 영화, 책 입력창을 나타내줄 카드 레이아웃
    private boolean btnClicked; // 정상 종료인지(X 버튼이 아닌 OK 버튼을 눌러서 종료했는지 아닌지)
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

        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        okbtn.addActionListener(new ActionListener() { // OK 버튼을 눌렀을 떄
            @Override
            public void actionPerformed(ActionEvent e) {
                do { // 영화 제목이나 책 제목이 입력되었을 때까지 반복
                    if (mbtn.isSelected()) { // 영화 정보였을 때
                        Movie movie = moviePanel.getInformation();
                        if (movie.getTitle().equals("")) { // 영화 제목이 입력되지 않았을 때
                            JOptionPane.showMessageDialog(null, "제목은 필수 입력 항목입니다", "제목이 입력되지 않음", JOptionPane.WARNING_MESSAGE);
                            break;
                            // 다시 입력하게 함
                        }
                        ItemCollections.addItem(movie);
                        Main.getFrame().getTppanel().renewMovies(); // 영화 정보를 받아서 ItemCollections에 넣고 영화 리스트 갱신
                    } else { // 책 정보였을 때
                        Book book = bookPanel.getInformation();
                        if (book.getTitle().equals("")) { // 책 제목이 입력되지 않았을 때
                            JOptionPane.showMessageDialog(null, "제목은 필수 입력 항목입니다", "제목이 입력되지 않음", JOptionPane.WARNING_MESSAGE);
                            break;
                            // 다시 입력하게 함
                        }
                        ItemCollections.addItem(book);
                        Main.getFrame().getTppanel().renewBooks(); // 책 정보를 받아서 ItemCollections에 넣고 책 리스트 갱신
                    }

                    Main.getFrame().getTppanel().renewTotal(); // 전체 리스트 갱신
                    Main.getFrame().renewDialog(); // 다이얼로그 초기화
                    setVisible(false); // 다이얼로그를 안보이게 설정
                    break; // 정상 종료
                }while(true);
            }
        });

        mbtn.addItemListener(new ItemListener() { // 영화 버튼의 상태가 변했을 때
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.DESELECTED) return; // 선택되지 않았으면 종료
                if(mbtn.isSelected()) { // 선택됐을 때
                    moviePanel.add(btnPanel, BorderLayout.SOUTH);
                    card.show(centerPanel, "movie"); // 영화 정보 입력 패널을 보여줌
                }
            }
        });

        bbtn.addItemListener(new ItemListener() { // 책 버튼의 상태가 변했을 때
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.DESELECTED) return; // 선택되지 않았으면 종료
                if(bbtn.isSelected()) { // 선택됐을 때
                    bookPanel.add(btnPanel, BorderLayout.SOUTH);
                    card.show(centerPanel, "book"); // 책 정보 입력 패널을 보여줌
                }
            }
        });

        this.addWindowListener(new WindowAdapter() { // X 버튼을 눌렀을 때
            @Override
            public void windowClosing(WindowEvent e) {
                if(!btnClicked) Main.getFrame().renewDialog(); // X 버튼을 눌러서 종료했으면 다이얼로그 초기화
            }
        });


    }
    public void actionPerformed(ActionEvent e){
        btnClicked = true;
    } // 만약 OK 버튼이 눌렸으면 버튼을 눌렀다고 상태 전환
    public MoviePanel getMoviePanel() {
        return moviePanel;
    }
    public BookPanel getBookPanel() {
        return bookPanel;
    }
}
