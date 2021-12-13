import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetailPanel extends JPanel{ // 상세 정보 패널
    private JPanel panel; // 상세 정보 컴포넌트들이 들어갈 패널
    private JPanel btnPanel; // 하단 수정, 삭제 버튼이 들어갈 패널
    private InformationPanel ipanel; // 영화와 책의 상세 정보가 표현될 패널
    private TextPanel sPanel; // 줄거리가 표시될 패널
    private TextPanel rPanel; // 감상평이 표시될 패널
    private JButton mbtn; // 수정 버튼
    private JButton dbtn; // 삭제 버튼
    private GridBagConstraints grid; // GridBagLayout을 사용하기 위해 선언
    public DetailPanel() {
        GridBagConstraints grid = new GridBagConstraints();
        setLayout(new BorderLayout());
        panel = new JPanel(new GridBagLayout()); // GridBagLayout을 사용
        btnPanel = new JPanel(new FlowLayout());

        panel.setBorder(BorderFactory.createTitledBorder("상세 보기"));
        ipanel = new InformationPanel();
        sPanel = new TextPanel("줄거리");
        rPanel = new TextPanel("감상평");
        mbtn = new JButton("수정");
        dbtn = new JButton("삭제");

        mbtn.addActionListener(new ActionListener() { // 수정 버튼을 눌렀을 때
            @Override
            public void actionPerformed(ActionEvent e) {
                ModifyDialog m = Main.getFrame().getMdialog(); // 수정 다이얼로그를 불러옴
                m.getBbtn().setEnabled(false); m.getMbtn().setEnabled(false); // 영화를 책으로, 책을 영화로 바꾸지 못하기 위해 버튼 비활성화
                int idx = Main.getFrame().getTppanel().getTp().getSelectedIndex(); // 왼쪽 TabbedPane에서 몇 번째 항목인지(전체, 영화, 책, 검색)
                if(idx == 0){ // 전체일 때
                    if(Main.getFrame().getTppanel().getTotal().getSelectedValue() == null) { // 선택된 항목이 없을 때 오류 메시지를 출력
                        showErrorMessage();
                        return;
                    }

                    if(Main.getFrame().getTppanel().getTotal().getSelectedValue() instanceof Movie){ // 선택된 항목이 영화일 때
                        Main.getFrame().getMdialog().getMbtn().setSelected(true);
                        Main.getFrame().getMdialog().getCard().show(Main.getFrame().getMdialog().getCenterPanel(), "movie");
                        Movie movie = (Movie)Main.getFrame().getTppanel().getTotal().getSelectedValue();
                        Main.getFrame().getMdialog().getMoviePanel().setInformation(movie); // 다이얼로그에서 영화 입력창 패널을 보여주고 수정할 영화 정보를 받아와 입력창에 설정
                    }
                    else{
                        Main.getFrame().getMdialog().getBbtn().setSelected(true);
                        Main.getFrame().getMdialog().getCard().show(Main.getFrame().getMdialog().getCenterPanel(), "book");
                        Book book = (Book)Main.getFrame().getTppanel().getTotal().getSelectedValue();
                        Main.getFrame().getMdialog().getBookPanel().setInformation(book); // 다이얼로그에 책 입력창 패널을 보여주고 수정할 책 정보를 받아와 입력창에 설정
                    }
                }// 이하 if문에 대해서 위와 같은 작업을 수행
                else if(idx == 1){ // 영화일 때
                    if(Main.getFrame().getTppanel().getMovies().getSelectedValue() == null) {
                        showErrorMessage();
                        return;
                    }

                    Main.getFrame().getMdialog().getMbtn().setSelected(true);
                    Main.getFrame().getMdialog().getCard().show(Main.getFrame().getMdialog().getCenterPanel(), "movie");
                    Movie movie = (Movie)Main.getFrame().getTppanel().getMovies().getSelectedValue();
                    Main.getFrame().getMdialog().getMoviePanel().setInformation(movie);
                }
                else if(idx == 2){ // 책일 때
                    if(Main.getFrame().getTppanel().getBooks().getSelectedValue() == null) {
                        showErrorMessage();
                        return;
                    }

                    Main.getFrame().getMdialog().getBbtn().setSelected(true);
                    Main.getFrame().getMdialog().getCard().show(Main.getFrame().getMdialog().getCenterPanel(), "book");
                    Book book = (Book)Main.getFrame().getTppanel().getBooks().getSelectedValue();
                    Main.getFrame().getMdialog().getBookPanel().setInformation(book);
                }
                else if(idx == 3){ // 검색일 때
                    if(Main.getFrame().getTppanel().getStp().getList().getSelectedValue() == null){
                        if(Main.getFrame().getTppanel().getTotal().getSelectedValue() == null) JOptionPane.showMessageDialog(null, "항목을 선택해주세요", "오류", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    if(Main.getFrame().getTppanel().getStp().getList().getSelectedValue() instanceof Movie){
                        Main.getFrame().getMdialog().getMbtn().setSelected(true);
                        Main.getFrame().getMdialog().getCard().show(Main.getFrame().getMdialog().getCenterPanel(), "movie");
                        Movie movie = (Movie)Main.getFrame().getTppanel().getStp().getList().getSelectedValue();
                        Main.getFrame().getMdialog().getMoviePanel().setInformation(movie);
                    }
                    else{
                        Main.getFrame().getMdialog().getBbtn().setSelected(true);
                        Main.getFrame().getMdialog().getCard().show(Main.getFrame().getMdialog().getCenterPanel(), "book");
                        Book book = (Book)Main.getFrame().getTppanel().getStp().getList().getSelectedValue();
                        Main.getFrame().getMdialog().getBookPanel().setInformation(book);
                    }
                }
                m.setVisible(true); // 수정 다이얼로그를 보이게 설정
            }
        });
        dbtn.addActionListener(new ActionListener() { // 삭제 버튼을 눌렀을 때
            @Override
            public void actionPerformed(ActionEvent e) {
                int idx = Main.getFrame().getTppanel().getTp().getSelectedIndex(); // 왼쪽 TabbedPane에서 몇 번째 항목인지(전체, 영화, 책, 검색)
                if(idx == 0) if(Main.getFrame().getTppanel().getTotal().getSelectedValue() == null) {
                    showErrorMessage();
                    return;
                }
                if(idx == 1) if(Main.getFrame().getTppanel().getMovies().getSelectedValue() == null) {
                    showErrorMessage();
                    return;
                }
                if(idx == 2) if(Main.getFrame().getTppanel().getBooks().getSelectedValue() == null){
                    showErrorMessage();
                    return;
                }
                if(idx == 3) if(Main.getFrame().getTppanel().getStp().getList().getSelectedValue() == null){
                    showErrorMessage();
                    return;
                } // 각 탭에서 선택한 항목이 없을 때 오류 메시지 출력

                //선택한 항목이 있으면 삭제할 것인지 물어보는 다이얼로그를 띄움
                int ret = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?", "삭제", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if(ret == JOptionPane.YES_OPTION) { // 예를 눌렀을 때
                    if (idx == 0) // 전체일 때
                        ItemCollections.deleteItem((Item) Main.getFrame().getTppanel().getTotal().getSelectedValue());
                    if (idx == 1) // 영화일 때
                        ItemCollections.deleteItem((Item) Main.getFrame().getTppanel().getMovies().getSelectedValue());
                    if (idx == 2) // 책일 때
                        ItemCollections.deleteItem((Item) Main.getFrame().getTppanel().getBooks().getSelectedValue());
                    if (idx == 3) // 검색일 때
                        ItemCollections.deleteItem((Item) Main.getFrame().getTppanel().getStp().getList().getSelectedValue());

                    Main.getFrame().getCenterPanel().removeAll();
                    for(int i=0;i<4;i++) {
                        Main.getFrame().getDpanel()[i] = new DetailPanel();
                        Main.getFrame().getCenterPanel().add(Integer.toString(i), Main.getFrame().getDpanel()[i]);
                    }
                    Main.getFrame().getCard().show(Main.getFrame().getCenterPanel(), Integer.toString(idx)); // 삭제된 정보가 상세 정보 패널에 뜨지 않도록 전부 새로고침

                    Main.getFrame().getTppanel().renewTotal();
                    Main.getFrame().getTppanel().renewMovies();
                    Main.getFrame().getTppanel().renewBooks();
                    Main.getFrame().getTppanel().getStp().renewList(false); // 전체, 영화, 책, 검색 정보 리스트를 새로고침
                }
            }
        });

        grid.fill = GridBagConstraints.BOTH; // 패널 전체를 채우도록 함

        grid.gridx = 0; grid.gridy = 0;
        grid.weightx = 1; grid.weighty = 2;
        panel.add(ipanel, grid); // 간단한 정보들은 Y축 방향으로 2배율로 채움

        grid.gridx = 0; grid.gridy = 2;
        grid.weightx = 1; grid.weighty = 1; // 나머지는 1배율로 채움
        panel.add(sPanel, grid);

        grid.gridx = 0; grid.gridy = 3;
        grid.weightx = 1; grid.weighty = 1; // 나머지는 1배율로 채움
        panel.add(rPanel, grid);

        btnPanel.add(mbtn); btnPanel.add(dbtn);

        add(panel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }
    public InformationPanel getIpanel() {
        return ipanel;
    }
    public TextPanel getsPanel() {
        return sPanel;
    }
    public TextPanel getrPanel() {
        return rPanel;
    }

    public void showErrorMessage(){ // 오류 메시지 출력
        JOptionPane.showMessageDialog(null, "항목을 선택해주세요", "오류", JOptionPane.WARNING_MESSAGE);
    }
}
