package shiro;

public class User {
	private int id; //为了匹配数据库字段
    private String name;
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String password;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
