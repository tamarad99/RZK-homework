package rzk.beans;

import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.EventType;

/**
 * Session Bean implementation class EventTypeBean
 */
@Singleton
@LocalBean
public class EventTypeBean implements EventTypeBeanLocal {
	
	HashMap types;

	@PersistenceContext
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public EventTypeBean() {
        // TODO Auto-generated constructor stub
    }

    
    @Override
	public List<EventType> getTypes() {
		Query q = em.createQuery("SELECT et FROM EventType et");
		List<EventType> types = q.getResultList(); 
		
		return types;
	}
}
