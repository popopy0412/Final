import javax.swing.*;
import java.awt.*;

public class TextPanel extends JPanel {
    private JTextArea ta;
    public TextPanel(String title){
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(title));
        ta = new JTextArea();
        ta.setLineWrap(true);
        ta.setEditable(false); // 수정 불가
        add(new JScrollPane(ta), BorderLayout.CENTER);
    }
    public void setTa(String text){
        ta.setText(text);
    }
}
