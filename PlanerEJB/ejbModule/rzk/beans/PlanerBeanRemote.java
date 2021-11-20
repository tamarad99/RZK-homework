package rzk.beans;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import model.Event;
import model.EventType;

@Remote
public interface PlanerBeanRemote {

	public String login(String email, String password);
	public boolean createEvent(String description, LocalDateTime fromDate, LocalDateTime toDate, int eventTypeID);
	public List<EventType> getTypes();
	public List<Event> searchEvent(Date date);
}
