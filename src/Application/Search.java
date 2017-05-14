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
    private String search_movie_id;

    String lalala;
    private ArrayList<Movies> searchresult_movies;
    private ArrayList<Directors> searchresult_directors;
    private ArrayList<Editors> searchresult_editos;
    private ArrayList<Genre> searchresult_genres;
    private Ratings searchresult_rating;
    private Movies searchresult_movie;



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


    public String SearchMovieDetail() {
        connetion.GetDriver();
        connetion.Connection();
        MoviesDAO newMovieDAO = new MoviesDAO();
        newMovieDAO.setMovieDAO(connetion.connect);
        setSearchresult_movie(newMovieDAO.SearchMovieByMovieID(getSearch_movie_id()));
        setSearchresult_directors(newMovieDAO.SearchMovieDirectorsByMoiveID(getSearch_movie_id()));
        setSearchresult_editos(newMovieDAO.SearchMovieEditorsByMoiveID(getSearch_movie_id()));
        System.out.print(getSearch_movie_id());
        System.out.print(getSearchresult_directors());
        RatingDAO newRatingDAO = new RatingDAO();
        newRatingDAO.setRatingDAO(connetion.connect);
        setSearchresult_rating(newRatingDAO.SearchRatingByMovieID(getSearch_movie_id()));
        GenreDAO newGenreDAO = new GenreDAO();
        newGenreDAO.setGenreDAO(connetion.connect);
        setSearchresult_genres(newGenreDAO.SearchGenreByMovieID(getSearch_movie_id()));
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

    public String getLalala() {
        return lalala;
    }

    public void setLalala(String lalala) {
        this.lalala = lalala;
    }

    public String getSearch_movie_id() {
        return search_movie_id;
    }

    public void setSearch_movie_id(String search_movie_id) {
        this.search_movie_id = search_movie_id;
    }

    public ArrayList<Directors> getSearchresult_directors() {
        return searchresult_directors;
    }

    public void setSearchresult_directors(ArrayList<Directors> searchresult_directors) {
        this.searchresult_directors = searchresult_directors;
    }

    public ArrayList<Editors> getSearchresult_editos() {
        return searchresult_editos;
    }

    public void setSearchresult_editos(ArrayList<Editors> searchresult_editos) {
        this.searchresult_editos = searchresult_editos;
    }

    public Ratings getSearchresult_rating() {
        return searchresult_rating;
    }

    public void setSearchresult_rating(Ratings searchresult_rating) {
        this.searchresult_rating = searchresult_rating;
    }

    public Movies getSearchresult_movie() {
        return searchresult_movie;
    }

    public void setSearchresult_movie(Movies searchresult_movie) {
        this.searchresult_movie = searchresult_movie;
    }

    public ArrayList<Genre> getSearchresult_genres() {
        return searchresult_genres;
    }

    public void setSearchresult_genres(ArrayList<Genre> searchresult_genres) {
        this.searchresult_genres = searchresult_genres;
    }
}
