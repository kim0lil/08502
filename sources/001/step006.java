import java.sql.*;
import java.util.*;

class step001{

    private static Properties props = new Properties();

    static{
        props.load(ClassLoader.getSystemResourceAsStream("/001/step005.properties"));
    }

    public Connection getConnection() {

        Class.forName(props.getString("driver"));
        
        
        return DriverManager.getConnection(props.getString("url")
                                         , props.getString("username")
                                         , props.getString("password"));
    }

    public Object select() {

		Connection conn = null;

		try {
            conn = getConnection();
            
            // .. 처리 구문
		} catch ( SQLException e ) {
            e.printStackTrace();
		} finally {
            if(conn != null) {
                try{
                    conn.close();
                }catch(Exception e){

                }
            }
        }
    }
}