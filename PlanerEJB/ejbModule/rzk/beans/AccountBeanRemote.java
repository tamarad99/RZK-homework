package rzk.beans;

import javax.ejb.Remote;

@Remote
public interface AccountBeanRemote {

	public boolean createAccount(String email, String password, String firstname, String lastname);
}
