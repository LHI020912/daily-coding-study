package sec06.Client;

public interface IClientDAO {
	// 로그인 회원 아이디
	public ClientDTO loginIdClient(String cliNo);
	
	// 로그인 비밀번호
	public ClientDTO loginPwdClient(int cliPwd);

}
