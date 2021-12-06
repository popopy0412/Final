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
                Vector<Item> v = new Vector<>();
                String str = tf.getText();
                for(Item i : ItemCollections.getItems()){
                    if(cb.getSelectedIndex() == 0){
                        if(i.getTitle().contains(str)) v.add(i);
                    }
                    else{
                        try{
                            if(i.getPoint() == Integer.parseInt(str)) v.add(i);
                        }
                        catch(Exception exception){
                            exception.printStackTrace();
                        }
                    }
                }
                list = new JList(v);
            }
        });

        panel.add(cb); panel.add(tf); panel.add(btn);
        list = new JList();

        add(panel, BorderLayout.NORTH);
        add(list, BorderLayout.CENTER);
    }
}
