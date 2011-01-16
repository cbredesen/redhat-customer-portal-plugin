package rhdn.client;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.jboss.resteasy.client.ClientExecutor;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.client.core.executors.ApacheHttpClientExecutor;

import com.redhat.gss.strata.resource.CasesResource;

/**
 * A portal API resource locator backed by the RESTeasy client proxy framework.
 * 
 * @author Chris Bredesen
 */
public class ClientProxyPortalApiResourceLocator implements PortalApiResourceLocator {
	private String apiBaseUrl = "https://api.access.redhat.com/rs";

	@Override
	public CasesResource getCasesResource() {
		ClientExecutor executor = buildAuthExecutor("", ""); // obviusly this does not work ;)
		return ProxyFactory.create(CasesResource.class, apiBaseUrl + "/cases", executor);
	}

	private ClientExecutor buildAuthExecutor(String username, String password) {
		Credentials credentials = new UsernamePasswordCredentials(username, password);
		HttpClient httpClient = new HttpClient();
		httpClient.getState().setCredentials(AuthScope.ANY, credentials);
		httpClient.getParams().setAuthenticationPreemptive(true);
		return new ApacheHttpClientExecutor(httpClient);

	}

}
