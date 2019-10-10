import java.sql.*;
import java.util.*;

class step007{

    private static Properties props = new Properties();

    static{
        props.load(ClassLoader.getSystemResourceAsStream("/001/step005.properties"));
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
            
            psmt = conn.prepareStatement("SELECT SHOP_NO, SHOP_NAME, SHOP_LOCATION, SHOP_STATUS "
                                        +"  FROM TB_SHOP"
                                        +" WHERE SHOP_NO = ?");
			
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