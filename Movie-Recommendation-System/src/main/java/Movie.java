import java.util.List;

public class Movie {
    private String title;
    private String Id;
    private List<String> genre;

    public Movie(String title, String id, List<String> genre) {
        this.title = title;
        Id = id;
        this.genre = genre;
    }

    public List<String> getGenre() {
        return genre;
    }

    public String getId() {
        return Id;
    }

    public String getTitle() {
        return title;
    }
}
