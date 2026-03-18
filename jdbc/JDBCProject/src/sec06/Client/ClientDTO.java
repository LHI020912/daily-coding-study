package sec06.Client;

public class ClientDTO {
	private String cliNo;
	private int cliPwd;
	
	public ClientDTO(String cliNo, int cliPwd) {
		this.cliNo = cliNo;
		this.cliPwd = cliPwd;
	}

	public String getCliNo() {
		return cliNo;
	}

	public void setCliNo(String cliNo) {
		this.cliNo = cliNo;
	}

	public int getCliPwd() {
		return cliPwd;
	}

	public void setCliPwd(int cliPwd) {
		this.cliPwd = cliPwd;
	}

}
