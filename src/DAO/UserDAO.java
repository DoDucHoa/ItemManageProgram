package DAO;

import DTO.User;
import GUI.MainFrame;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.naming.spi.DirStateFactory;
import java.sql.ResultSet;
import UTIL.MyConnection;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author HoaDD
 */
public class UserDAO extends Vector<User> {

    public UserDAO() {
        super();
    }

    public static String checkLogin(String us, String pw) throws SQLException {
        Connection cn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
      
        try {
            cn = MyConnection.connectDB();
            if (cn != null) {
                String sql = "SELECT userID, password, fullName FROM tblUsers WHERE userID = ? AND password = ?";
                stm = cn.prepareStatement(sql);
                stm.setString(1, us);
                stm.setString(2, pw);
                rs = stm.executeQuery();
                if (rs.next()) {
                    us = rs.getString(3);
                    return us;
                } else {
                    us = null;               
                }
            } 
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return us;
    }

    public static boolean checkRegister(String us) throws SQLException {
        boolean checkRe = true;
        Connection cn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            cn = MyConnection.connectDB();
            if (cn != null) {
                String sql = "SELECT userID FROM tblUsers WHERE userID =  ?";
                stm = cn.prepareStatement(sql);
                stm.setString(1, us);
                rs = stm.executeQuery();
                if (rs.next()) {
                    checkRe = false;
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return checkRe;
    }
}
