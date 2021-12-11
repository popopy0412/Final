import javax.swing.*;
import java.io.Serializable;

public class Item implements Serializable {
    protected String title; // 제목
    protected String producer; // 제작자(감독, 저자)
    protected String summary; // 줄거리, 내용
    protected String review; // 감상평
    protected ImageIcon poster; // 이미지 아이콘
    protected int point; // 별점
    protected int year; // 제작 년도

    public Item(String title, String producer, String summary, String review, ImageIcon poster, int point, int year) {
        this.title = title;
        this.producer = producer;
        this.summary = summary;
        this.review = review;
        this.poster = poster;
        this.point = point;
        this.year = year;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public ImageIcon getPoster() {
        return poster;
    }

    public void setPoster(ImageIcon poster) {
        this.poster = poster;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String toString() { return title; }
}
