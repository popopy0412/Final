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
                int ret = ch.showOpenDialog(null);
                if(ret == JFileChooser.APPROVE_OPTION){
                    String pathName = ch.getSelectedFile().getPath();
                    try {
                        FileInputStream fi = new FileInputStream(pathName);
                        ObjectInputStream oi = new ObjectInputStream(fi);
                        ItemCollections.setVector((Vector<Item>)oi.readObject());
                        oi.close();
                        Main.getFrame().getCenterPanel().removeAll();
                        for(int i=0;i<4;i++){
                            Main.getFrame().getDpanel()[i] = new DetailPanel();
                            Main.getFrame().getCenterPanel().add(Integer.toString(i), Main.getFrame().getDpanel()[i]);
                        }
                        Main.getFrame().getTppanel().renewTotal();
                        Main.getFrame().getTppanel().renewMovies();
                        Main.getFrame().getTppanel().renewBooks();
                    }
                    catch (FileNotFoundException ex) {
                        showErrorMessage("파일을 찾을 수 없습니다.", "파일을 찾을 수 없음");
                    } catch (IOException ioException) {
                        showErrorMessage("파일을 불러오는 과정에서 오류가 발생했습니다.", "입출력 오류");
                    } catch (ClassNotFoundException classNotFoundException) {
                        showErrorMessage("데이터 오류가 발생했습니다.", "데이터 오류");
                    } catch (Exception exp){
                        showErrorMessage("알 수 없는 오류가 발생했습니다.", "알 수 없는 오류");
                    }
                }
            }
        });
        items[0][1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser ch = new JFileChooser();
                int ret = ch.showSaveDialog(null);
                if(ret == JFileChooser.APPROVE_OPTION){
                    String pathName = ch.getSelectedFile().getPath();
                    try {
                        FileOutputStream fo = new FileOutputStream(pathName);
                        ObjectOutputStream oo = new ObjectOutputStream(fo);
                        oo.writeObject(ItemCollections.getItems());
                        oo.close();
                    }

                    catch (FileNotFoundException ex) {
                        showErrorMessage("파일을 찾을 수 없습니다.", "파일을 찾을 수 없음");
                    } catch (IOException ioException) {
                        showErrorMessage("파일을 저장하는 과정에서 오류가 발생했습니다.", "입출력 오류");
                    } catch (Exception exp){
                        showErrorMessage("알 수 없는 오류가 발생했습니다.", "알 수 없는 오류");
                    }
                }
            }
        });
        items[0][2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ret = JOptionPane.showConfirmDialog(null, "정말 종료하시겠습니까?");
                if(ret == JOptionPane.YES_OPTION) System.exit(0);
            }
        });
        items[1][0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "MyNotes 시스템 v 1.0 입니다.", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
    public void showErrorMessage(String msg, String title){
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.WARNING_MESSAGE);
    }
}
