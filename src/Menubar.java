import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Vector;

public class Menubar extends JMenuBar { // 상단 메뉴바
    private JMenu[] menus; // 메뉴
    private JMenuItem[][] items; // 메뉴 아이템
    private String[] mstr = {"파일", "도움말"}; // 메뉴 종류
    private String[][] istr = {{"불러오기", "저장하기", "종료"}, {"시스템 정보"}}; // 각 메뉴에 들어갈 메뉴
    private int itemnum[] = {3, 1}; // 각 메뉴에 들어가는 아이템 수
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
        }//메뉴 생성

        items[0][0].addActionListener(new ActionListener() { // 불러오기 버튼을 눌렀을 때
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser ch = new JFileChooser();
                int ret = ch.showOpenDialog(null); // 불러오기 다이얼로그를 띄움
                if(ret == JFileChooser.APPROVE_OPTION){ // 확인 버튼을 눌렀을 때
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
                        Main.getFrame().getTppanel().renewBooks(); // 파일을 불러오고 전체, 영화, 책에 있는 리스트 갱신
                    }
                    catch (FileNotFoundException ex) { // 파일을 찾을 수 없을 때
                        showErrorMessage("파일을 찾을 수 없습니다.", "파일을 찾을 수 없음");
                    } catch (IOException ioException) { // 불러오는 과정에서 오류가 생겼을 때
                        showErrorMessage("파일을 불러오는 과정에서 오류가 발생했습니다.", "입출력 오류");
                    } catch (ClassNotFoundException classNotFoundException) { // 데이터 오류가 발생했을 때
                        showErrorMessage("데이터 오류가 발생했습니다.", "데이터 오류");
                    } catch (Exception exp){ // 기타 오류가 발생했을 때
                        showErrorMessage("알 수 없는 오류가 발생했습니다.", "알 수 없는 오류");
                    }
                }
            }
        });
        items[0][1].addActionListener(new ActionListener() { // 저장하기 버튼을 눌렀을 때
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser ch = new JFileChooser();
                int ret = ch.showSaveDialog(null); // 저장하기 다이얼로그를 띄움
                if(ret == JFileChooser.APPROVE_OPTION){ // 확인 버튼을 눌렀을 때
                    String pathName = ch.getSelectedFile().getPath(); // 저장할 파일의 이름이 포함된 경로
                    try {
                        FileOutputStream fo = new FileOutputStream(pathName);
                        ObjectOutputStream oo = new ObjectOutputStream(fo);
                        oo.writeObject(ItemCollections.getItems());
                        oo.close();
                    } // 파일을 저장

                    catch (FileNotFoundException ex) { // 파일을 찾을 수 없을 때
                        showErrorMessage("파일을 찾을 수 없습니다.", "파일을 찾을 수 없음");
                    } catch (IOException ioException) { // 저장 과정에서 오류가 발생했을 때
                        showErrorMessage("파일을 저장하는 과정에서 오류가 발생했습니다.", "입출력 오류");
                    } catch (Exception exp){ // 기타 오류가 발생했을 때
                        showErrorMessage("알 수 없는 오류가 발생했습니다.", "알 수 없는 오류");
                    }
                }
            }
        });
        items[0][2].addActionListener(new ActionListener() { // 종료 버튼을 눌렀을 때
            @Override
            public void actionPerformed(ActionEvent e) {
                int ret = JOptionPane.showConfirmDialog(null, "정말 종료하시겠습니까?"); // 종료를 확인하는 다이얼로그를 띄우고
                if(ret == JOptionPane.YES_OPTION) System.exit(0); // 확인을 누르면 종료
            }
        });
        items[1][0].addActionListener(new ActionListener() { // 시스템 정보를 눌렀을 때
            @Override
            public void actionPerformed(ActionEvent e) { // 시스템 정보를 띄움
                JOptionPane.showMessageDialog(null, "MyNotes 시스템 v 1.0 입니다.", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
    public void showErrorMessage(String msg, String title){ // 오류 메시지 출력
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.WARNING_MESSAGE);
    }
}
