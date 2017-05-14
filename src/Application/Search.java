package Application;
import DAO.*;
import Entity.*;

import java.util.ArrayList;

/**
 * Created by DYN on 2017/5/13.
 */
public class Search {
    private Connect connetion = new Connect();

    private String search_movie_name ;
    private String search_movie_imdb;
    String lalala;
    private ArrayList<Movies> searchresult_movies;
    public String SearchMovieByName() {
        connetion.GetDriver();
        connetion.Connection();
        MoviesDAO newMovieDAO = new MoviesDAO();
        newMovieDAO.setMovieDAO(connetion.connect);
        setSearchresult_movies(newMovieDAO.SearchMovie(
                search_movie_name,search_movie_imdb));
        connetion.DisCon();
        lalala = "laala";
        return "1";
    }


    public Connect getConnet() {
        return connetion;
    }

    public void setConnet(Connect connet) {
        this.connetion = connet;
    }

    public String getSearch_movie_imdb() {
        return search_movie_imdb;
    }

    public void setSearch_movie_imdb(String search_movie_imdb) {
        this.search_movie_imdb = search_movie_imdb;
    }

    public ArrayList<Movies> getSearchresult_movies() {
        return searchresult_movies;
    }

    public void setSearchresult_movies(ArrayList<Movies> searchresult_movies) {
        this.searchresult_movies = searchresult_movies;
    }

    public String getSearch_movie_name() {
        return search_movie_name;
    }

    public void setSearch_movie_name(String search_movie_name) {
        this.search_movie_name = search_movie_name;
    }


}
