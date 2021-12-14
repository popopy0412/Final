import java.io.Serializable;

public class Movie extends Item implements Serializable { // Item을 상속받는 Movie
    private String actors; // 배우
    private String genre; // 장르
    private String rated; // 등급

    public Movie(String title, String producer, String summary, String review, String path, int point, int year, String actors, String genre, String rated) { // 생성자
        super(title, producer, summary, review, path, point, year);
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
