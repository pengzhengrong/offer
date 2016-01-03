package loggerVo;



public class DbVo extends BaseLoggerVo{

	private static final long serialVersionUID = 1L;
	private String sql;
	private String type;
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
