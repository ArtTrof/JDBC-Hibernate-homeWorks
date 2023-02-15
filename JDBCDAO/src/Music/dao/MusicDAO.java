package Music.dao;

import Music.entity.Music;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MusicDAO implements IMusicDAO {
    @Override
    public void add(Music music) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        connection = getConnection();

        preparedStatement = connection.prepareStatement("Insert into music values(?,?,?,?)");
        preparedStatement.setInt(1, 1);
        preparedStatement.setDouble(3, 5);
        preparedStatement.setString(2, "We will rock you");
        preparedStatement.setString(4, "Rock");
        preparedStatement.executeUpdate();
        connection.close();
        preparedStatement.close();
    }


    @Override
    public double countMusicTime() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        connection = getConnection();
        double result = 0;
        preparedStatement = connection.prepareStatement("Select sum(time) from music");
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            rs.getDouble("time");
        }
        return result;
    }

    @Override
    public List<Music> findByGenre(String genre) throws SQLException {
        List<Music> music = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        connection = getConnection();
        connection = getConnection();
        preparedStatement = connection.prepareStatement("Select * from music where genre = ?");
        preparedStatement.setString(1,"Rock");
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()){
            Music singleMusic = new Music(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getString(4));
                    music.add(singleMusic);

        }
        return music;
    }

    @Override
    public List<Music> findMusicByTime(double min,double max) throws SQLException {
        List<Music> music = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        connection = getConnection();
        connection = getConnection();
        preparedStatement = connection.prepareStatement("Select * from music where time in (?,?)");
        preparedStatement.setDouble(1,min);
        preparedStatement.setDouble(1,max);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()){
            Music singleMusic = new Music(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getString(4));
            music.add(singleMusic);

        }
        return music;
    }

    private Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsshop", "root", "root");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
