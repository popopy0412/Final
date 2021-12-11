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
                InputDialog i = Main.getFrame().getDialog();
                int idx = Main.getFrame().getTppanel().getTp().getSelectedIndex();
                if(idx == 0){
                    if(Main.getFrame().getTppanel().getTotal().getSelectedValue() instanceof Movie){
                        Movie movie = (Movie)Main.getFrame().getTppanel().getTotal().getSelectedValue();
                        Main.getFrame().getDialog().getMoviePanel().setInformation(movie);
                    }
                }
                else if(idx == 1){

                }
                else if(idx == 2){

                }
                else if(idx == 3){

                }
                i.setVisible(true);
            }
        });
        dbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                    Main.getFrame().getTppanel().renewTotal();
                    Main.getFrame().getTppanel().renewMovies();
                    Main.getFrame().getTppanel().renewBooks();
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
    public void setIpanel(InformationPanel ipanel){
        this.ipanel = ipanel;
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


}
