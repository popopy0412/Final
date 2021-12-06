import javax.swing.*;

public class TextPanel extends JPanel {
    private JTextArea ta;
    public TextPanel(String title){
        setBorder(BorderFactory.createTitledBorder(title));
        ta = new JTextArea();
        add(ta);
    }
}
