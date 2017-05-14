package DAO;
import Application.Connect;
import Entity.*;


import java.sql.*;
import java.util.*;

/**
 * Created by DYN on 2017/5/13.
 */
    public class MoviesDAO {
    private Connection connect = null;
    public void setMovieDAO(Connection connect) {
        this.connect = connect;
    }
    public void InsetMovie(Movies newMovie) {
        String insert = "insert into movies (movieid,title,year,imdbid)" +
                "values (null,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = connect.prepareStatement(insert);
            pstmt.setString(1,newMovie.getTitle());
            pstmt.setString(2,newMovie.getYear());
            pstmt.setString(3,newMovie.getImdbid());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Movies> SearchMovie (String movieTitle, String imdbid ) {
        ArrayList<Movies> movieList = new ArrayList<Movies>();
        if (movieTitle == null && imdbid == null)
            return null;
        else{
            String search = "select * from movies where title like ? and year > 2010";//and imdb = ?";
            ResultSet rs = null;
            PreparedStatement pstmt = null;
            try {
                pstmt = connect.prepareStatement(search);
                String data = "%"+movieTitle+"%";
                System.out.print(data);
                pstmt.setString(1,data);
                //pstmt.setString(2,"%"+imdbid+"%");
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    Movies newMovie = new Movies();
                    newMovie.setMovieid(rs.getString("movieid"));
                    newMovie.setTitle(rs.getString("title"));
                    System.out.print(newMovie.getTitle());
                    newMovie.setYear(rs.getString("year"));
                    newMovie.setImdbid(rs.getString("imdbid"));
                    movieList.add(newMovie);
                }
                rs.close();
                if (movieList == null)
                    System.out.println("lalala");
                else
                    System.out.println("papapa");
                return movieList;
            }catch (SQLException e) {
                e.printStackTrace();
            }
            return movieList;
        }
    }

    public ArrayList<Directors> SearchMovieDirectorsByMoiveID(String movieid) {
        ArrayList<Directors> directorsList = new ArrayList<Directors>();
        String search = "select * from directors,movies2directors where movies.movieid = ? and directors.directorid = movies2directors.directorid";
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            pstmt = connect.prepareStatement(search);
            pstmt.setString(1, movieid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Directors newDirector = new Directors();
                newDirector.setDirectorid(rs.getString("directorid"));
                newDirector.setName(rs.getString("name"));
                directorsList.add(newDirector);
            }
            rs.close();
            return directorsList;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return directorsList;
    }

    public ArrayList<Editors> SearchMovieEditorsByMoiveID(String movieid) {
        ArrayList<Editors> editorsList = new ArrayList<Editors>();
        String search = "select * from editors,movies2editors where movies.movieid = ? and editors.editoid = movies2editors.editorid";
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            pstmt = connect.prepareStatement(search);
            pstmt.setString(1, movieid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Editors newEditor = new Editors();
                newEditor.setEditorid(rs.getString("editorid"));
                newEditor.setName(rs.getString("name"));
                editorsList.add(newEditor);
            }
            rs.close();
            return editorsList;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return editorsList;
    }



    public boolean DeleteMovie(String movieTitle,String imdbid) {
        if (movieTitle == null || imdbid == null)
            return false;
        else{
            ArrayList<Movies> movieList = SearchMovie(movieTitle,imdbid);
            if (movieList == null) {
                return false;
            }
            else {
                PreparedStatement pstmt;
                String delete = "delete from movies where title = ? and imdbid = ?";
                try {
                    pstmt = connect.prepareStatement(delete);
                    pstmt.setString(1,movieTitle);
                    pstmt.setString(2,imdbid);
                    int i = pstmt.executeUpdate();
                    if (i != 0) {
                        return true;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }
    }

    public boolean UpdateMovie(Movies newMovie) {
        String update = "update movies set title=? and year=? and imdbid = ?" +
                " where movieid = ?";
        PreparedStatement pstmt;
        try {
            pstmt = connect.prepareStatement(update);
            pstmt.setString(1,newMovie.getTitle());
            pstmt.setString(2,newMovie.getYear());
            pstmt.setString(3,newMovie.getImdbid());
            pstmt.setString(4,newMovie.getMovieid());
            int i = pstmt.executeUpdate();
            if (i != 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
