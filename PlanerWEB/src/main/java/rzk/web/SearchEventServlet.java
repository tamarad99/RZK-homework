package rzk.web;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Event;
import rzk.beans.PlanerBean;
import rzk.beans.PlanerBeanRemote;

/**
 * Servlet implementation class SearchEventServlet
 */
@WebServlet("/SearchEventServlet")
public class SearchEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Context initialContext;

	private static final String PKG_INTERFACES = "org.jboss.ejb.client.naming";

	public static Context getInitialContext() throws NamingException {
		if (initialContext == null) {
			Properties properties = new Properties();
			properties.put(Context.URL_PKG_PREFIXES, PKG_INTERFACES);
			initialContext = new InitialContext(properties);
		}
		return initialContext;
	}

	private static String getLookupName() {
		
		// The app name is the application name of the deployed EJBs. This is typically the ear name without the .ear suffix. 
        final String appName = "PlanerEAR";
        // This is the module name of the deployed EJBs on the server. This is typically the jar name of the EJB deployment, without the .jar suffix.
        final String moduleName = "PlanerEJB";
        // JBossAS allows each deployment to have an (optional) distinct name. We haven't specified a distinct name for
        // our EJB deployment, so this is an empty string
        final String distinctName = "";
        // The EJB name which by default is the simple class name of the bean implementation class
        final String beanName = PlanerBean.class.getSimpleName();
        // the remote interface fully qualified class name
        final String interfaceName = PlanerBeanRemote.class.getName();
        // let's do the lookup
		String name = "ejb:" + appName + "/" + moduleName + "/" +
				distinctName    + "/" + beanName + "!" + interfaceName;
		return name;
	}

	private static PlanerBeanRemote doLookup() {
		Context context = null;
		PlanerBeanRemote bean = null;
		try {
			context = getInitialContext();
			String lookupName = getLookupName();
			System.out.println("JNDI ime:   "+lookupName);
			bean = (PlanerBeanRemote) context.lookup(lookupName);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return bean;
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchEventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PlanerBeanRemote pbr = (PlanerBeanRemote) request.getSession().getAttribute("bean");
		if(pbr == null) {
			pbr = doLookup();
		}
		
		LocalDate fromDate;
		 
		fromDate = LocalDate.parse(request.getParameter("fromDate"));
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(fromDate.atStartOfDay(defaultZoneId).toInstant());
		
		List<Event> events = pbr.searchEvent(date);
		
		request.setAttribute("events", events);
		request.setAttribute("fromDate", fromDate);		
		request.getRequestDispatcher("searchEvent.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
