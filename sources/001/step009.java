import java.sql.*;
import java.util.*;

class step009{

    private static Properties props = new Properties();
    private static Properties sql   = new Properties();

    static{
        props.load(ClassLoader.getSystemResourceAsStream("/001/step005.properties"));
        sql.load(ClassLoader.getSystemResourceAsStream("/001/step008.properties"));
    }

    public Connection getConnection() {

        Class.forName(props.getProperty("driver"));
        
        
        return DriverManager.getConnection(props.getProperty("url")
                                         , props.getProperty("username")
                                         , props.getProperty("password"));
    }

    public Object select() {

		Connection conn = null;
		PreparedStatement psmt = null;

		try {
            conn = getConnection();
            
            psmt = conn.prepareStatement(sql.getProperty("select"));
			
			psmt.setInt(1, 1);

            psmt.executeQuery();
            
            // ... 처리 구문
		} catch ( SQLException e ) {
            e.printStackTrace();
		} finally {
            if(conn != null) {
                try{
                    conn.close();
                }catch(Exception e){

                }
            }
            if(psmt != null) {
                try{
                    conn.close();
                }catch(Exception e){

                }
            }
        }
    }
}