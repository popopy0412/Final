import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class SearchTabPanel extends JPanel{
    private JPanel panel; // 상단 패널
    private JComboBox<String> cb; // 검색창 콤보 박스
    private JTextField tf; // 검색창 입력란
    private JButton btn; // 검색 버튼
    private JList list;
    private String text; // 검색창에 입력한 문자열
    public SearchTabPanel(){
        setLayout(new BorderLayout()); // 배치 관리자 설정

        panel = new JPanel(new FlowLayout());

        cb = new JComboBox<>();
        cb.addItem("제목"); cb.addItem("별점");

        tf = new JTextField(10);

        btn = new JButton("검색");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text = tf.getText();
                renewList(true);
            }
        });

        panel.add(cb); panel.add(tf); panel.add(btn);
        list = new JList();

        add(panel, BorderLayout.NORTH);
        add(list, BorderLayout.CENTER);
    }

    public JList getList() {
        return list;
    }
    public void renewList(boolean forfind){
        Vector<Item> v = new Vector<>();
        String str;
        if(forfind) str = tf.getText();
        else str = text;
        if(str == null) return;
        try {
            for (Item i : ItemCollections.getItems()) {
                if (cb.getSelectedIndex() == 0) {
                    if(str.equals("")) throw new IllegalArgumentException();
                    if (i.getTitle().contains(str)) v.add(i);
                } else {
                    if (i.getPoint() >= Integer.parseInt(str)) v.add(i);
                }
            }
            list.setListData(v);
        }
        catch(IllegalArgumentException ex){
            showErrorMessage("제목을 1글자 이상 입력해주세요", "제목이 입력되지 않음");
        }
        catch(Exception exception){
            showErrorMessage("0 ~ 10 사이의 숫자만 입력해주세요", "입력값 오류");
        }
    }
    public void showErrorMessage(String msg, String title){
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
