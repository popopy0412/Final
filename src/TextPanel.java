import javax.swing.*;
import java.awt.*;

public class TextPanel extends JPanel { // 상세 정보 패널에 있는 줄거리, 감상평을 보여주는 패널
    private JTextArea ta; // 줄거리, 감상평이 들어감
    public TextPanel(String title){
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(title));
        ta = new JTextArea();
        ta.setLineWrap(true); // 자동 줄바꿈
        ta.setEditable(false); // 수정 불가
        add(new JScrollPane(ta), BorderLayout.CENTER);
    }
    public void setTa(String text){
        ta.setText(text);
    }
}
