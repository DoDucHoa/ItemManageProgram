package UTIL;

/**
 *
 * @author HoaDD
 */
public class MyConnectionItem extends MyConnection{
    final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    final String url =  "jdbc:sqlserver://127.0.0.1\\SQLEXPRESS:1433; databaseName=ItemManagement; "
                + " user=sa; password=sa123456";
    public MyConnectionItem(){
        super();
        connectDB(driver, url);
    }
}
