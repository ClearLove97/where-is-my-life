package jpql;

public class MemberDTO {
	
	private String usernaem;
	private int age;
	
	
	
	public MemberDTO(String usernaem, int age) {
		super();
		this.usernaem = usernaem;
		this.age = age;
	}
	public String getUsernaem() {
		return usernaem;
	}
	public void setUsernaem(String usernaem) {
		this.usernaem = usernaem;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
