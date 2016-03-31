package db.assignment1.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author SUDIP
 */
public class ConnectionProvider {

    public static Connection con = null;

    static {
        try {
           Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager
                    .getConnection("jdbc:mysql://localhost/database_course",
                            "root","maha22"); 
        } catch (Exception ex) {
            
            System.out.println("Error in Database Connection");
        }
        
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        
          
        return con;
    }
    public static void closeConnection()
    {
        try
        {
        if(con!=null)
        con.close();
        }
        catch(Exception e)
        {
            System.out.println("Error Closing Connection:"+e.getMessage());
        }
    }

}
