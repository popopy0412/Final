import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadbtnListener implements ActionListener { // 사진 불러오기 버튼을 눌렀을 때
    private JTextField tf; // 사진 경로가 나타날 텍스트 필드
    public LoadbtnListener(JTextField tf){ this.tf = tf; } // 생성자
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser ch = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, JPEG, PNG", "jpg", "png", "jpeg");
        ch.setFileFilter(filter);

        do { // 사진을 선택했을 때나 창을 끌 때까지 반복
            String name; // 파일 이름
            String pngjpg, jpeg; // 사진인지 아닌지 확인하는 문자열
            int len; // 파일 이름 길이
            int ret = ch.showOpenDialog(null); // 불러오기 다이얼로그 실행
            if (ret == JFileChooser.APPROVE_OPTION) { // 열기 버튼을 눌렀을 때
                name = ch.getSelectedFile().getName(); // 선택된 파일의 이름
                len = name.length(); // 파일의 이름 길이
                pngjpg = name.substring(len-4, len); // 파일의 끝 4글자(.PNG, .JPG 인지 확인하기 위함)
                jpeg = name.substring(len-5, len); // 파일의 끝 5글자(.JPEG인지 확인하지 위함)
                if(pngjpg.equals(".png") || pngjpg.equals(".jpg") || jpeg.equals(".jpeg")) { // 확장자가 사진 파일이면
                    tf.setText(ch.getSelectedFile().getPath()); // 사진 경로를 텍스트 필드에 설정
                    break; // 종료
                }
                else{ // 사진이 아니면
                    JOptionPane.showMessageDialog(null, "PNG, JPG, JPEG 파일이 아닙니다. 다시 선택해주세요", "잘못된 파일 선택", JOptionPane.WARNING_MESSAGE);
                    //에러 메시지 출력
                }
            }
            else break; // 아니면 종료
        }while(true);
    }
}
