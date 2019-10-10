import java.sql.*;

class step006{

    public Connection getConnection() {

        Class.forName("oracle.jdbc.driver.OracleDriver");
        
        return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ADMIN", "ADMIN");
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