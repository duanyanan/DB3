package DAO;
import Entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Created by DYN on 2017/5/14.
 */
public class RatingDAO {
    Connection connect = null;
    public void setGenreDAO(Connection connet) {
        this.connect = connet;
    }

    public void InsertRating(String movieid,String rating) {
        String insert = " insert into ratings(movieid,rank,votes,distribution) values (?,?,1,\"..........\")";
        PreparedStatement pstmt;
        try {
            pstmt = connect.prepareStatement(insert);
            pstmt.setString(1,movieid);
            pstmt.setString(2,rating);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Ratings SearchRatingByMovieID(String movieid) {
        Ratings newRating = new Ratings();
        String search = "select * from ratings where movieid = ?";
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        try {
            pstmt = connect.prepareStatement(search);
            pstmt.setString(1, movieid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                newRating.setRank(rs.getString("rank"));
                newRating.setVotes(rs.getInt("votes"));
                newRating.setDistribution(rs.getString("distribution"));
            }
            rs.close();
            return newRating;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return newRating;
    }
}
