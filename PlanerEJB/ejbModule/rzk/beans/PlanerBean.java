package rzk.beans;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Event;
import model.EventType;
import model.User;

/**
 * Session Bean implementation class PlanerBean
 */
@Stateful
@LocalBean
public class PlanerBean implements PlanerBeanRemote {
	
	
	int userId;
	User user;
	
	@PersistenceContext
	EntityManager em;
	@EJB
	EventTypeBeanLocal etbl;

    /**
     * Default constructor. 
     */
    public PlanerBean() {
        // TODO Auto-generated constructor stub
    }

    @PostConstruct
    public void construct() {
    	System.out.println("Bean je kreiran!");
    }
    
	@Override
	public String login(String email, String password) {
		
		Query q = em.createQuery("SELECT u FROM User u WHERE u.email LIKE :user AND u.password like :pass");
		q.setParameter("user", email);
		q.setParameter("pass", password);
		List<User> users = q.getResultList();
		System.out.println(users.size());
		try {
			user = users.get(0);
			userId = users.get(0).getId();
			return users.get(0).getEmail();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public boolean createEvent(String description, LocalDateTime fromDate, LocalDateTime toDate, int id) {

		if(description != null && toDate != null && fromDate != null) {
			Query q = em.createQuery("SELECT et FROM EventType et WHERE et.id = :id");
			q.setParameter("id", id);
			EventType et = (EventType) q.getSingleResult();
			
			Event newE = new Event();
			newE.setDescription(description);
			newE.setFromDate(fromDate);
			newE.setToDate(toDate);
			newE.setEventType(et);
			newE.setUser(user);
			em.persist(newE);
			
			return true;
		}
		return false;
	}
	
	
	@Override
	public List<EventType> getTypes() {		
		List<EventType> types = etbl.getTypes();
		
		return types;
	}

	@PreDestroy
	public void destroy() {
		System.out.println("Bean je unisten!");
	}

	@Override
	public List<Event> searchEvent(Date date) {
		
		Query q = em.createQuery("SELECT e FROM Event e WHERE DATE (e.fromDate) = :date AND e.user = :user");//da dobijem samo svoje
		q.setParameter("date", date);
		q.setParameter("user", user);
		List<Event> events = q.getResultList();
		
		return events;
	}
	
}
