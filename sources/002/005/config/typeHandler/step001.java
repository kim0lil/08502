
public class IntegerParameterTypeHandler 
    extends BaseTypeHandler<Integer>{

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Integer parameter, JdbcType jdbcType)
			throws SQLException {

        System.out.println("==================");
        System.out.println("   Type Handling  ");
        System.out.println("==================");
		ps.setInt(i, parameter);
	}

	@Override
	public Integer getNullableResult(ResultSet rs, String columnName) throws SQLException {

        System.out.println("==================");
        System.out.println("   Type Handling  ");
        System.out.println("==================");
		return rs.getint(columnName);
	}

	@Override
	public Integer getNullableResult(ResultSet rs, int columnIndex) throws SQLException {

        System.out.println("==================");
        System.out.println("   Type Handling  ");
        System.out.println("==================");
		return rs.getint(columnName);
	}

	@Override
	public Integer getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        
        System.out.println("==================");
        System.out.println("   Type Handling  ");
        System.out.println("==================");
		return rs.getint(columnIndex);
	}

}