package rhdn.client;

import org.jboss.resteasy.client.ProxyFactory;

import com.redhat.gss.strata.resource.CasesResource;

/**
 * A service locator for RESTFul clients.
 * 
 * @author Chris Bredesen
 */
public class ServiceLocatorImpl implements ServiceLocator {
	private String apiBaseUrl = "https://api.access.redaht.com/rs";
	
	@Override
	public CasesResource getCasesResource() {
		return ProxyFactory.create(CasesResource.class, apiBaseUrl + "/cases");
	}

}
