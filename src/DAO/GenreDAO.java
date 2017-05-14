package DAO;
import Entity.*;

import java.sql.*;
import java.util.*;


/**
 * Created by DYN on 2017/5/13.
 */
public class GenreDAO {
    Connection connect = null;
    public void setGenreDAO(Connection connet) {
        this.connect = connet;
    }

    public HashMap<String,String> GetTopGenre() {
        HashMap<String,String> genrelist = new HashMap<String,String>();
        String search = "select genre,count(movieid) as a " +
                "from genres group by genre order by a DESC";
        PreparedStatement pstmt;
        try {
            pstmt = connect.prepareStatement(search);
            ResultSet rs = null;
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String genre = rs.getString("genre");
                String count_id = rs.getString("a");
                genrelist.put(genre,count_id);
            }
            rs.close();
            return genrelist;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genrelist;
    }

    public ArrayList<Genre> SearchGenreByMovieID(String movieid) {
        ArrayList<Genre> genresList = new ArrayList<Genre>();
        String search = "select genre from genres where movieid = ?";
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            pstmt = connect.prepareStatement(search);
            pstmt.setString(1, movieid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Genre newGenre = new Genre();
                newGenre.setGenreType(rs.getString("genre"));
                genresList.add(newGenre);
            }
            rs.close();
            return genresList;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return genresList;
    }

    public ArrayList<Movies>  SearchMoviesByGenre(String genreStyle) {
        //连接查询
        ArrayList<Movies> movieList = new ArrayList<Movies>();
        String search = "select * from movies,genres where movies.movieid = genre.movieid " +
                "and genre = ?";
        PreparedStatement pstmt;
        try {
            pstmt = connect.prepareStatement(search);
            ResultSet rs = null;
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Movies newMovie = new Movies();
                newMovie.setMovieid(rs.getString("movieid"));
                newMovie.setTitle(rs.getString("title"));
                newMovie.setYear(rs.getString("year"));
                newMovie.setImdbid(rs.getString("imdbid"));
                movieList.add(newMovie);
            }
            rs.close();
            return movieList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movieList;
    }

    public boolean InsertGenre(String movieid,String genre) {
        ArrayList<Genre> genresList = SearchGenreByMovieID(movieid);
        for(Iterator it = genresList.iterator();it.hasNext();){
            Genre g = (Genre)it.next();
            if (g.getGenreType().equals(genre)) {
                return false;
            }
        }
        String insert = "insert into genres(movieid,genre) values(?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = connect.prepareStatement(insert);
            pstmt.setString(1,movieid);
            pstmt.setString(2,genre);
            int i = pstmt.executeUpdate();
            if (i != 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
