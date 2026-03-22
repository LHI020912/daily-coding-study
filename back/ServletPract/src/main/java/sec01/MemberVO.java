package sec01;

public class MemberVO {
	private String memId;
	private String memPwd;
	private String memName;
	private String memEmail;

	public MemberVO() {}
	
	public MemberVO(String id, String pwd, String name, String email) {
		this.memId = id;
		this.memPwd = pwd;
		this.memName = name;
		this.memEmail = email;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPwd() {
		return memPwd;
	}

	public void setMemPwd(String memPwd) {
		this.memPwd = memPwd;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemEmail() {
		return memEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}

	
}
