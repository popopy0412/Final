import java.awt.*;
import javax.swing.*;

public class Movie extends Item{
    private String actors; // 배우
    private String genre; // 장르
    private String rated; // 등급

    public Movie(String title, String producer, String summary, String review, ImageIcon poster, int point, int year, String actors, String genre, String rated) {
        super(title, producer, summary, review, poster, point, year);
        this.actors = actors;
        this.genre = genre;
        this.rated = rated;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }
}
