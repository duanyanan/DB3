package DAO;
import Entity.*;

import java.sql.*;
import java.util.*;
/**
 * Created by DYN on 2017/5/13.
 */
public class DirectorDAO {
    Connection connect = null;
    public void DirectorDAO(Connection connet) {
        this.connect = connet;
    }

    public boolean InsertDirector(String dirName) {
        String insert = "insert into directors (directorid, name)" +
                "values (null,?)";
        PreparedStatement pstmt;
        try {
            pstmt = connect.prepareStatement(insert);
            pstmt.setString(1,dirName);
            int i = pstmt.executeUpdate();
            if (i != 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String SearchDirectorByName(String dirName) {
        String search = "select directorid from directors where name = ?";
        PreparedStatement pstmt;
        try {
            pstmt = connect.prepareStatement(search);
            pstmt.setString(1,dirName);
            ResultSet rs = null;
            rs = pstmt.executeQuery();
            String directorId = null;
            while (rs.next()) {
                directorId = rs.getString("directorid");
            }
            return directorId;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Movies> SearchDirectorSMovies(String dirName) {
        //嵌套查询
        String search = "select * from movies where movieid in " +
                "(select movieid from movies2directors where directorid in " +
                "(select directorid from directors where name = ?))";
        ArrayList<Movies> movieList = new ArrayList<Movies>();
        PreparedStatement pstmt;
        try {
            pstmt = connect.prepareStatement(search);
            pstmt.setString(1,dirName);
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
}
