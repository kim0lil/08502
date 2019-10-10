import java.sql.*;

class step003{

    public int insert() {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection conn = null;

		try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ADMIN", "ADMIN");
            
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

    public int delete() {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection conn = null;

		try { 
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ADMIN", "ADMIN");
            
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

    public int update() {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection conn = null;

		try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ADMIN", "ADMIN");
            
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

    public Object select() {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection conn = null;

		try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ADMIN", "ADMIN");
            
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