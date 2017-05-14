package Application;

import java.sql.*;
import java.util.*;
/**
 * Created by DYN on 2017/5/13.
 */
public class Connect {
    Connection connect = null;
    public void GetDriver()
    {
        String driver = "com.mysql.jdbc.Driver";
        try {
            Class.forName(driver);
            System.out.println("driver!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void Connection()
    {
        String url = "jdbc:mysql://localhost:3306/jmdb";
        String root = "root";
        String password = "802396";
        try {
            connect = DriverManager.getConnection(url,root,password);
            System.out.println("connect!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DisCon()
    {
        if (connect != null)
        {
            try {
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Database close!");
        }
        else
            System.out.println("lalala");
    }
}
