import javax.swing.*;
import java.awt.*;

public class InformationPanel extends JPanel{ // 각 영화, 책에 대한 정보를 나타내는 패널

    private ImagePanel imagePanel; // 이미지가 들어갈 패널
    private JPanel centerPanel; // 항목 패널과 설명 패널이 들어갈 패널
    private JPanel labelPanel; // 제목 등등이 들어갈 항목 패널
    private JPanel dscrpPanel; // 각 항목에 대한 설명들이 들어갈 패널
    private JLabel[] label; // 제목 등등이 들어갈 항목 라벨
    private JLabel[] dscrp; // 각 항목에 대한 설명들이 들어갈 라벨
    public InformationPanel(){
        GridBagConstraints grid = new GridBagConstraints(); // GridBagLayout을 사용하기 위함
        setLayout(new GridBagLayout());

        imagePanel = new ImagePanel();
        centerPanel = new JPanel(new BorderLayout(20, 20));
        labelPanel = new JPanel(new GridLayout(7, 1));
        dscrpPanel = new JPanel(new GridLayout(7, 1));
        label = new JLabel[7];
        dscrp = new JLabel[7];
        for(int i=0;i<7;i++){
            label[i] = new JLabel();
            dscrp[i] = new JLabel();
            labelPanel.add(label[i]);
            dscrpPanel.add(dscrp[i]);
        }

        centerPanel.add(labelPanel, BorderLayout.WEST);
        centerPanel.add(dscrpPanel, BorderLayout.CENTER);

        grid.fill = GridBagConstraints.BOTH;

        grid.gridx = 0; grid.gridy = 0;
        grid.weightx = 1; grid.weighty = 1;
        add(imagePanel, grid);

        grid.gridx = 1; grid.gridy = 0;
        grid.weightx = 0.2; grid.weighty = 1;
        add(new JPanel(), grid); // 공백을 위한 빈 패널

        grid.gridx = 2; grid.gridy = 0;
        grid.weightx = 2; grid.weighty = 1;
        add(centerPanel, grid); // 각 항목 위치 설정 후 패널에 더함
    }
    class ImagePanel extends JPanel{ // 이미지가 들어갈 패널
        private ImageIcon poster; // 이미지(포스터)

        public void setPoster(String path) { // 이미지 경로로부터 이미지를 불러와 이미지를 설정
            if(path.equals("")) this.poster = null;
            else this.poster = new ImageIcon(path);
            repaint();
        }
        public void paintComponent(Graphics g){ // 이미지를 나타냄
            super.paintComponent(g);
            int x = getWidth();
            int y = getHeight();
            if(poster == null) { // 이미지가 없으면 이미지가 없다고 그림
                g.setColor(Color.BLACK);
                g.drawLine(0, 0, x, y);
                g.drawLine(x, 0, 0, y);
                g.drawString("이미지 없음", x / 2 - 30, y / 2 + 5);
            }
            else{ // 있다면 이미지를 나타냄
                g.drawImage(poster.getImage(), 0, 0, x, y, this);
            }
        }
    }

    public JLabel[] getLabel() {
        return label;
    }

    public JLabel[] getDscrp() {
        return dscrp;
    }

    public ImagePanel getImagePanel() {
        return imagePanel;
    }
}
