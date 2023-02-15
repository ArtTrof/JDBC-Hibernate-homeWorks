package Music.entity;

public class Music {
    private int id;
    private String musicName;
    private double musicTime;
    private String genre;

    public Music() {
    }

    public Music(int id, String musicName, double musicTime, String genre) {
        this.id = id;
        this.musicName = musicName;
        this.musicTime = musicTime;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public double getMusicTime() {
        return musicTime;
    }

    public void setMusicTime(double musicTime) {
        this.musicTime = musicTime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
