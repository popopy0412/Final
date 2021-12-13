import java.io.Serializable;

public class Book extends Item implements Serializable { // Item을 상속받는 Book
    private String publisher; // 출판사

    public Book(String title, String producer, String summary, String review, String path, int point, int year, String publisher) { // 생성자
        super(title, producer, summary, review, path, point, year);
        this.publisher = publisher;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
