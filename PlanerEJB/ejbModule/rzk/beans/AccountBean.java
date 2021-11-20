package rzk.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.User;

/**
 * Session Bean implementation class AccountBean
 */
@Stateless
@LocalBean
public class AccountBean implements AccountBeanRemote {
	
	@PersistenceContext
	EntityManager em;

    /**
     * Default constructor. 
     */
    public AccountBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean createAccount(String email, String password, String firstname, String lastname) {
		if(firstname != null && lastname != null && email != null && password != null) {
			User user = new User();
			user.setFirstName(firstname);
			user.setLastName(lastname);
			user.setEmail(email);
			user.setPassword(password);
			em.persist(user);
			return true;
		}
		return false;
	}
	
	@PostConstruct
    public void construct() {
    	System.out.println("Bean je kreiran!");
    }

	@PreDestroy
	public void destroy() {
		System.out.println("Bean je unisten!");
	}

}
