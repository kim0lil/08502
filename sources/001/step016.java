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

    public step010 selectOne(){

		Connection conn = null;
        PreparedStatement psmt = null;
		ResultSet rs = null;
        step010 shop = null;

		try {
            conn = getConnection();
            
            psmt = createPrepareStatement(conn, "select", new Object[]{1});
			
            rs = psmt.executeQuery();

            while(rs.next()) {
                shop = resultSetMapping(rs, new Mapper<step010>(){

                    public Shop mapping(int row, ResultSet rs) {

                        step010 shop = new step010();
                        
                        try {
                            shop.setShopNo(rs.getInt("SHOP_NO"));
                            shop.setShopName(rs.getString("SHOP_NAME"));
                            shop.setShopLocation(rs.getString("SHOP_LOCATION"));
                            shop.setShopStatus(rs.getString("SHOP_STATUS"));
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        
                        return shop;
                    }
                });
            }
            return shop;
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