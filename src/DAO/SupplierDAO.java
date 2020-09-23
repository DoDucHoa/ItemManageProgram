package DAO;

import DTO.Supplier;
import UTIL.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Vector;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class SupplierDAO extends Vector<Supplier> {

    public SupplierDAO() {
        super();
    }

    public int find(String supCode) {
        for (int i = 0; i < this.size(); i++) {
            if (supCode.equals(this.get(i).getSupCode())) {
                return i;
            }
        }
        return -1;
    }

    public Supplier findSupplier(String supCode) {
        int i = find(supCode);
        return i < 0 ? null : this.get(i);
    }

    public void loadFromDB(MyConnection dbObj) {
        String supCode, supName, address;
        boolean colloborating;
        String sql = "select * from tblSuppliers";
        try {
            ResultSet rs = dbObj.executeQuery(sql);
            while (rs.next()) {
                supCode = rs.getString(1);
                supName = rs.getString(2);
                address = rs.getString(3);
                colloborating = rs.getBoolean(4);
                Supplier supplier = new Supplier(supCode, supName, address, colloborating);
                this.add(supplier);
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static int addSupplier(String supCode, String supName, String address, boolean collaborating) throws SQLException {
        Connection cn = null;
        PreparedStatement st = null;
        int rs = 0;
        try {
            cn = MyConnection.connectDB();
            if (cn != null) {
                String sql = "insert tblSuppliers values(?,?,?,?)";
                st = cn.prepareStatement(sql);
                st.setString(1, supCode);
                st.setString(2, supName);
                st.setString(3, address);
                st.setBoolean(4, collaborating);
                rs = st.executeUpdate();
                JOptionPane.showMessageDialog(null, "A supplier has been added.");
            }
        } catch (Exception e) {
        } finally {
            if (st != null) {
                st.close();
            }
            if (cn != null) {
                cn.close();
            }

        }
        return rs;
    }

    public static int updateSupplier(String supCode, String supName, String address, boolean collaborating) throws SQLException {
        Connection cn = null;
        PreparedStatement st = null;
        int rs = 0;
        try {
            cn = MyConnection.connectDB();
            if (cn != null) {
                String sql = "update tblSuppliers set supName = ?, address = ?, collaborating = ? where supCode = ?";
                st = cn.prepareStatement(sql);

                st.setString(1, supName);
                st.setString(2, address);
                st.setBoolean(3, collaborating);
                st.setString(4, supCode);
                
                rs = st.executeUpdate();
                JOptionPane.showMessageDialog(null, "The supplier " + supCode + " has been updated.");
            }
        } catch (Exception e) {
        } finally {
            if (st != null) {
                st.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return rs;
    }

    public static int deleteSupplier(String supCode) throws SQLException {
        Connection cn = null;
        PreparedStatement st = null;
        int rs = 0;
        try {
            cn = MyConnection.connectDB();
            if (cn != null) {
                String sql = "delete from tblSuppliers where supCode = ?";
                st = cn.prepareStatement(sql);

                st.setString(1, supCode);

                rs = st.executeUpdate();
                JOptionPane.showMessageDialog(null, "The item " + supCode + " has been deleted.");
            }
        } catch (Exception e) {
        } finally {
            if (st != null) {
                st.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return rs;
    }
}
