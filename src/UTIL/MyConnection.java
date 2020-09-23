package UTIL;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HoaDD
 */
public class MyConnection {

    Connection cn = null;
    Statement st = null;

    public MyConnection() {
    }

    public static Connection connectDB() throws ClassNotFoundException, SQLException {
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://127.0.0.1\\SQLEXPRESS:1433; databaseName=ItemManagement; "
                + " user=sa; password=sa123456";
        Connection cn = null;
        Class.forName(driver);
        cn = DriverManager.getConnection(url);
        return cn;
    }
    
    public void connectDB(String driver, String url) {
        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(url);
            st = cn.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public ResultSet executeQuery(String selectSql) {
        if (cn == null) {
            return null;
        }
        try {
            return (st.executeQuery(selectSql));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    public int executeUpdate(String updatedSql) {
        if (cn == null) {
            return 0;
        }
        try {
            st = cn.createStatement();
            return (st.executeUpdate(updatedSql));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
}
