import java.sql.ResultSet;

class step016 {

    interface Mapper <T> {
        T resolve(ResultSet rs, int row);
    }

    private static Properties props = new Properties();
    private static Properties sql   = new Properties();

    static {
        props.load(ClassLoader.getSystemResourceAsStream("/001/step005.properties"));
        sql.load(ClassLoader.getSystemResourceAsStream("/001/step008.properties"));
    }

    public Connection getConnection() {

        Class.forName(props.getProperty("driver"));
        
        
        return DriverManager.getConnection(props.getProperty("url")
                                         , props.getProperty("username")
                                         , props.getProperty("password"));
    }

    public PreparedStatement createPrepareStatement(Connection conn, String id, Object[] params) throws SQLException {
    	
    	PreparedStatement pstmt = conn.prepareStatement(sql.getProperty(id));
    	
    	for(int e = 0 ; e < params.length ; e++) {
    		pstmt.setObject(e, params[e]); 
    	}
    	
    	return pstmt;
    }

    public <T> T resultSetMapping(ResultSet rs, Mapper<T> mapper) throws SQLException {
    	
    	T t = null;
    	
    	int iRow = 0;
    	
    	while(rs.next()) {
    		
    		t = mapper.mapping(iRow++, rs);
    	}
    	
    	return t;
    }

    public <T> T selectOne(String sqlId, Object[] params, Mapper<T> mapper){

        T out = null;

		Connection conn = null;
        PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
            conn = getConnection();

            psmt = createPrepareStatement(conn, sqlId, params);

            rs   = psmt.executeQuery();

            while(rs.next()){
                out  = resultSetMapping(rs, mapper);
            }

            return out;
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
            if(rs != null) {
                try{
                    rs.close();
                }catch(Exception e){

                }
            }
        }
    }
}