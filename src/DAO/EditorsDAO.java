package DAO;

import Entity.Movies;

import java.sql.*;
import java.util.ArrayList;


/**
 * Created by DYN on 2017/5/13.
 */
public class EditorsDAO {
    Connection connect = null;
    public void EditorsDAO(Connection connet) {
        this.connect = connet;
    }

    public boolean InsertEditor(String editorName) {
        String insert = "insert into editors (editorid, name)" +
                "values (null,?)";
        PreparedStatement pstmt;
        try {
            pstmt = connect.prepareStatement(insert);
            pstmt.setString(1,editorName);
            int i = pstmt.executeUpdate();
            if (i != 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Movies> SearchEditorSMovies(String editorName) {
        //嵌套查询
        String search = "select * from movies where movieid in " +
                "(select movieid from movies2editors where editorid in " +
                "(select editorid from editors where name = ?))";
        ArrayList<Movies> movieList = new ArrayList<Movies>();
        PreparedStatement pstmt;
        try {
            pstmt = connect.prepareStatement(search);
            pstmt.setString(1,editorName);
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
