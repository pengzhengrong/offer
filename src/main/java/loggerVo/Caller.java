package loggerVo;

public class Caller {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = -3188990205172995018L;
	private String className;
	private String methodName;
	private String fullClassName;
	
	public Caller()
	{
		StackTraceElement element = new Exception().getStackTrace()[2];
		this.fullClassName = element.getClassName();
		this.methodName = element.getMethodName();
		String[] path = this.fullClassName.split("\\.");
		className = path[path.length-1];
	}
	public Caller(int i)
	{
		StackTraceElement element = new Exception().getStackTrace()[i + 2];
		this.fullClassName = element.getClassName();
		this.methodName = element.getMethodName();
		String[] path = this.fullClassName.split("\\.");
		className = path[path.length-1];
	}
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getFullClassName() {
		return fullClassName;
	}

	public void setFullClassName(String fullClassName) {
		this.fullClassName = fullClassName;
	}
	
	public String getHeader() {
		return className + "." + methodName + "() - ";
	}
}
