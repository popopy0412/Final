import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Vector;

public class Menubar extends JMenuBar {
    private JMenu[] menus;
    private JMenuItem[][] items;
    private String[] mstr = {"파일", "도움말"};
    private String[][] istr = {{"불러오기", "저장하기", "종료"}, {"시스템 정보"}};
    private int itemnum[] = {3, 1};
    public Menubar(){
        menus = new JMenu[2];
        items = new JMenuItem[2][];
        for(int i=0;i<2;i++) {
            menus[i] = new JMenu(mstr[i]);
            items[i] = new JMenuItem[itemnum[i]];
            for(int j=0;j<itemnum[i];j++){
                items[i][j] = new JMenuItem(istr[i][j]);
                menus[i].add(items[i][j]);
                if(i != 0 || j != 1) continue;
                menus[i].addSeparator();
            }
            add(menus[i]);
        }

        items[0][0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser ch = new JFileChooser();
                int ret = ch.showSaveDialog(null);
                if(ret == JFileChooser.APPROVE_OPTION){
                    String pathName = ch.getSelectedFile().getPath();
                    try {
                        FileInputStream fi = new FileInputStream(pathName);
                        ObjectInputStream oi = new ObjectInputStream(fi);
                        ItemCollections.setVector((Vector<Item>)oi.readObject());
                        oi.close();
                        Main.getFrame().getTppanel().renewTotal();
                        Main.getFrame().getTppanel().renewMovies();
                        Main.getFrame().getTppanel().renewBooks();
                    }
                    catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }
                }
            }
        });
        items[0][1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser ch = new JFileChooser();
                int ret = ch.showOpenDialog(null);
                if(ret == JFileChooser.APPROVE_OPTION){
                    String pathName = ch.getSelectedFile().getPath();
                    try {
                        FileOutputStream fo = new FileOutputStream(pathName);
                        ObjectOutputStream oo = new ObjectOutputStream(fo);
                        oo.writeObject(ItemCollections.getItems());
                        oo.close();
                    }
                    catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });
        items[0][2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        items[1][0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "MyNotes 시스템 v 1.0 입니다.", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
