import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

public class step001 extends DefaultObjectFactory{

	private Properties properties;

	@Override
	public <T> T create(Class<T> type) {
		if(type == TB_USER.class) {
			System.out.println("Parameter Handle : "+properties.getProperty("message"));
			return type.cast(new TB_USER());
		} else {
			return super.create(type);
		}
	}

	@Override
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
}