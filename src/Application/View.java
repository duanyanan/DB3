package Application;

import DAO.GenreDAO;
import java.util.HashMap;

/**
 * Created by DYN on 2017/5/13.
 */
public class View {
    private Connect connetion = new Connect();
    private HashMap<String,String> topGenre;
    private HashMap<String,String> topLang;

    public void ShowTopGenre() {
        connetion.GetDriver();
        connetion.Connection();
        GenreDAO newGenreDAO = new GenreDAO();
        newGenreDAO.setGenreDAO(connetion.connect);
        setTopGenre(newGenreDAO.GetTopGenre());
        connetion.DisCon();
    }

    public void
    public HashMap<String, String> getTopGenre() {
        return topGenre;
    }

    public void setTopGenre(HashMap<String, String> topGenre) {
        this.topGenre = topGenre;
    }

    public HashMap<String, String> getTopLang() {
        return topLang;
    }

    public void setTopLang(HashMap<String, String> topLang) {
        this.topLang = topLang;
    }

    public Connect getConnetion() {
        return connetion;

    }

    public void setConnetion(Connect connetion) {
        this.connetion = connetion;
    }
}
