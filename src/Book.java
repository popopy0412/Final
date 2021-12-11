import javax.swing.*;
import java.io.Serializable;

public class Book extends Item implements Serializable {
    private String publisher; // 출판사

    public Book(String title, String producer, String summary, String review, ImageIcon poster, int point, int year, String publisher) {
        super(title, producer, summary, review, poster, point, year);
        this.publisher = publisher;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
