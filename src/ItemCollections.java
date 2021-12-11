import java.io.Serializable;
import java.util.Vector;

public class ItemCollections implements Serializable {
    private static Vector<Item> v = new Vector<>(); // Item을 저장하는 벡터

    public static void addItem(Item item){
        v.add(item);
    }

    public static Vector<Item> getItems(){
        return v;
    }

    public static Vector<Book> getBooks(){
        Vector<Book> bv = new Vector<>();
        for(Item i : v) if(i instanceof Book) bv.add((Book)i);
        return bv;
    }

    public static Vector<Movie> getMovies(){
        Vector<Movie> mv = new Vector<>();
        for(Item i : v) if(i instanceof Movie) mv.add((Movie)i);
        return mv;
    }

    public static void setVector(Vector<Item> vec){
        v = vec;
    }
    public static void deleteItem(Item item){
        v.remove(item);
    }
}
