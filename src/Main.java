public class Main { // 메인
    private static MainFrame frame; // 메인 프레임
    public static void main(String[] args){
        frame = new MainFrame("JAVA 3분반 1714112 천소현");
    } // 메인 프레임 생성
    public static MainFrame getFrame() {
        return frame;
    } // 메인 프레임을 불러옴
}
