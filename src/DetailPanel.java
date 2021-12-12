import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetailPanel extends JPanel{
    private JPanel panel;
    private JPanel btnPanel;
    private InformationPanel ipanel;
    private TextPanel sPanel;
    private TextPanel rPanel;
    private JButton mbtn;
    private JButton dbtn;
    private GridBagConstraints grid;
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

        mbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModifyDialog m = Main.getFrame().getMdialog();
                int idx = Main.getFrame().getTppanel().getTp().getSelectedIndex();
                if(idx == 0){
                    if(Main.getFrame().getTppanel().getTotal().getSelectedValue() == null) {
                        showErrorMessage();
                        return;
                    }

                    if(Main.getFrame().getTppanel().getTotal().getSelectedValue() instanceof Movie){
                        Main.getFrame().getMdialog().getMbtn().setSelected(true);
                        Main.getFrame().getMdialog().getCard().show(Main.getFrame().getMdialog().getCenterPanel(), "movie");
                        Movie movie = (Movie)Main.getFrame().getTppanel().getTotal().getSelectedValue();
                        Main.getFrame().getMdialog().getMoviePanel().setInformation(movie);
                    }
                    else{
                        Main.getFrame().getMdialog().getBbtn().setSelected(true);
                        Main.getFrame().getMdialog().getCard().show(Main.getFrame().getMdialog().getCenterPanel(), "book");
                        Book book = (Book)Main.getFrame().getTppanel().getTotal().getSelectedValue();
                        Main.getFrame().getMdialog().getBookPanel().setInformation(book);
                    }
                }
                else if(idx == 1){
                    if(Main.getFrame().getTppanel().getMovies().getSelectedValue() == null) {
                        showErrorMessage();
                        return;
                    }

                    Main.getFrame().getMdialog().getMbtn().setSelected(true);
                    Main.getFrame().getMdialog().getCard().show(Main.getFrame().getMdialog().getCenterPanel(), "movie");
                    Movie movie = (Movie)Main.getFrame().getTppanel().getMovies().getSelectedValue();
                    Main.getFrame().getMdialog().getMoviePanel().setInformation(movie);
                }
                else if(idx == 2){
                    if(Main.getFrame().getTppanel().getBooks().getSelectedValue() == null) {
                        showErrorMessage();
                        return;
                    }

                    Main.getFrame().getMdialog().getBbtn().setSelected(true);
                    Main.getFrame().getMdialog().getCard().show(Main.getFrame().getMdialog().getCenterPanel(), "book");
                    Book book = (Book)Main.getFrame().getTppanel().getBooks().getSelectedValue();
                    Main.getFrame().getMdialog().getBookPanel().setInformation(book);
                }
                else if(idx == 3){
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
                m.setVisible(true);
            }
        });
        dbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idx = Main.getFrame().getTppanel().getTp().getSelectedIndex();
                if(idx == 0) if(Main.getFrame().getTppanel().getTotal().getSelectedValue() == null) {
                    showErrorMessage();
                    return;
                }
                if(idx == 1) if(Main.getFrame().getTppanel().getMovies().getSelectedValue() == null) {
                    showErrorMessage();
                    return;
                }
                if(idx == 2) if(Main.getFrame().getTppanel().getBooks().getSelectedValue() == null){
                    return;
                }
                if(idx == 3) if(Main.getFrame().getTppanel().getStp().getList().getSelectedValue() == null){
                    if(Main.getFrame().getTppanel().getTotal().getSelectedValue() == null) JOptionPane.showMessageDialog(null, "항목을 선택해주세요", "오류", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int ret = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?", "삭제", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if(ret == JOptionPane.YES_OPTION) {
                    int s = Main.getFrame().getTppanel().getTp().getSelectedIndex();
                    if (s == 0)
                        ItemCollections.deleteItem((Item) Main.getFrame().getTppanel().getTotal().getSelectedValue());
                    if (s == 1)
                        ItemCollections.deleteItem((Item) Main.getFrame().getTppanel().getMovies().getSelectedValue());
                    if (s == 2)
                        ItemCollections.deleteItem((Item) Main.getFrame().getTppanel().getBooks().getSelectedValue());
                    if (s == 3)
                        ItemCollections.deleteItem((Item) Main.getFrame().getTppanel().getStp().getList().getSelectedValue());

                    Main.getFrame().getCenterPanel().removeAll();
                    for(int i=0;i<4;i++) {
                        Main.getFrame().getDpanel()[i] = new DetailPanel();
                        Main.getFrame().getCenterPanel().add(Integer.toString(i), Main.getFrame().getDpanel()[i]);
                    }
                    Main.getFrame().getCard().show(Main.getFrame().getCenterPanel(), Integer.toString(s));

                    Main.getFrame().getTppanel().renewTotal();
                    Main.getFrame().getTppanel().renewMovies();
                    Main.getFrame().getTppanel().renewBooks();
                    Main.getFrame().getTppanel().getStp().renewList(false);
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

    public void showErrorMessage(){
        JOptionPane.showMessageDialog(null, "항목을 선택해주세요", "오류", JOptionPane.WARNING_MESSAGE);
    }
}
