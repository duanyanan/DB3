package DAO;

import Entity.Movies;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by DYN on 2017/5/13.
 */
public class LanguageDAO {
    Connection connect = null;
    public void setlanguageDAO(Connection connet) {
        this.connect = connet;
    }

    public ArrayList<Movies> SearchTopMoviesByLanguage(String language) {
        ArrayList<Movies> movieList = new ArrayList<Movies>();
        String search = " select * from movies where movieid in " +
                "(select movieid from ratings where rank>9.0) " +
                "and movieid in (select movieid from language where language = ?)";
        PreparedStatement pstmt;
        try {
            pstmt = connect.prepareStatement(search);
            pstmt.setString(1,language);
            ResultSet rs = null;
            rs = pstmt.executeQuery();
            int i = 0;
            while (rs.next() && i <20) {
                Movies newMovie = new Movies();
                newMovie.setMovieid(rs.getString("movieid"));
                newMovie.setTitle(rs.getString("title"));
                newMovie.setYear(rs.getString("year"));
                newMovie.setImdbid(rs.getString("imdbid"));
                movieList.add(newMovie);
                i++;
            }
            rs.close();
            return movieList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movieList;
    }

    public HashMap<String,String> GetTopLang() {
        HashMap<String,String> langList = new HashMap<String,String>();
        String search = "select language,count(movieid) as a " +
                "from language group by language order by a DESC limit 10" ;
        PreparedStatement pstmt;
        try {
            pstmt = connect.prepareStatement(search);
            ResultSet rs = null;
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String lang = rs.getString("language");
                String count_id = rs.getString("a");
                langList.put(lang,count_id);
            }
            rs.close();
            return langList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return langList;
    }
}
