import java.sql.*;

class step001{
    public static void main(String[] args) throws ClassNotFoundException {

		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection conn = null;

		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@13.209.118.27:1521:xe", "ADMIN", "ADMIN");
		} catch ( SQLException e ) {
			System.out.println("접속 실패");
		} finally {
            if(conn != null) {
                try{
                    conn.close();
                }catch(Exception e){

                }
            }
        }
		System.out.println("접속 성공");
	}
}