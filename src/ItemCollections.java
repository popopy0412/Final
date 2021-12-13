import java.io.Serializable;
import java.util.Vector;

public class ItemCollections implements Serializable { // Item을 저장하는 ItemCollections
    private static Vector<Item> v = new Vector<>(); // Item을 저장하는 벡터

    public static void addItem(Item item){
        v.add(item);
    } // Item을 벡터에 추가
    
    public static Vector<Item> getItems(){
        return v;
    } // Item을 저장하는 벡터를 가져옴

    public static Vector<Book> getBooks(){ // 책 정보만 담는 벡터를 반환
        Vector<Book> bv = new Vector<>();
        for(Item i : v) if(i instanceof Book) bv.add((Book)i);
        return bv;
    }

    public static Vector<Movie> getMovies(){ // 영화 정보만 담는 벡터를 반환
        Vector<Movie> mv = new Vector<>();
        for(Item i : v) if(i instanceof Movie) mv.add((Movie)i);
        return mv;
    }

    public static void setVector(Vector<Item> vec){ v = vec; } // 파일을 불러왔을 때 그 저장 데이터로 백터 설정
    public static void deleteItem(Item item){
        v.remove(item);
    } // 벡터 내의 Item 삭제
}
