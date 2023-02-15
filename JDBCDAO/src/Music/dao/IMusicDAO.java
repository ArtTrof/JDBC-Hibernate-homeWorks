package Music.dao;

import Music.entity.Music;

import java.sql.SQLException;
import java.util.List;

public interface IMusicDAO {
    public void add(Music music) throws SQLException;

    public double countMusicTime() throws SQLException;

    public List<Music> findByGenre(String genre) throws SQLException;

    public List<Music> findMusicByTime(double min,double max) throws SQLException;
}
