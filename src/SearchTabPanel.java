import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class SearchTabPanel extends JPanel{ // TabbedPane의 검색 탭에 들어가는 패널
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
        btn.addActionListener(new ActionListener() { // 검색 버튼을 눌렀을 때
            @Override
            public void actionPerformed(ActionEvent e) {
                text = tf.getText(); // 검색할 때 입력한 문자열을 저장
                renewList(true); // 검색을 위한 목적으로 검색 탭 리스트를 갱신
            }
        });

        panel.add(cb); panel.add(tf); panel.add(btn);
        list = new JList();

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(list), BorderLayout.CENTER); // 각 컴포넌트들 생성 후 위치 설정
    }

    public JList getList() {
        return list;
    }
    public void renewList(boolean forfind){ // 검색 탭에 나오는 리스트들을 갱신(forfind : 검색 버튼을 통해 갱신인지 아닌지 판별)
        Vector<Item> v = new Vector<>(); // 리스트에 들어갈 벡터
        String str; // 문자열이 포함된 결과를 나타낼 때 그 기준이 되는 문자열
        if(forfind) str = tf.getText(); // 검색을 위한 갱신이면 검색창에 입력된 텍스트를 기준 문자열로 설정
        else str = text; // 아니면 이전에 검색을 위해 입력했던 텍스트를 기준 문자열로 설정
        if(str == null) return; // 텍스트가 없으면 갱신하지 않음
        try {
            for (Item i : ItemCollections.getItems()) { // 현재 저장된 정보들을 가져옴
                if (cb.getSelectedIndex() == 0) { // 제목 기준 검색일 때
                    if(str.equals("")) throw new IllegalArgumentException(); // 제목을 입력하지 않았을 때 오류 메시지 출력
                    if (i.getTitle().contains(str)) v.add(i); // 제목에 입력된 문자열이 포함된 정보들을 벡터에 추가
                } else { // 별점 기준 검색일 때
                    int point = Integer.parseInt(str); // 입력된 숫자를 String에서 int형으로 변환
                    if(point < 0 || point > 10) throw new Exception(); // 별점이 0 미만 10 초과일 경우 오류 메시지 출력
                    if (i.getPoint() >= point) v.add(i); // 입력된 별점 이상의 정보들만 벡터에 추가
                }
            }
            list.setListData(v);
            if(forfind && v.size() == 0) showErrorMessage("["+str+"] 검색 결과가 없습니다.", "검색 결과가 없음"); // 검색했을 때 검색된 게 없으면 메시지 출력
        }
        catch(IllegalArgumentException ex){ // 제목이 주어지지 않았을 때
            showErrorMessage("제목을 1글자 이상 입력해주세요", "제목이 입력되지 않음");
        }
        catch(Exception exception){ // 별점 기준 검색일 때 숫자를 입력하지 않았을 때
            showErrorMessage("0 ~ 10 사이의 숫자만 입력해주세요", "입력값 오류");
        }
    }
    public void showErrorMessage(String msg, String title){ // 오류 메시지 출력
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
