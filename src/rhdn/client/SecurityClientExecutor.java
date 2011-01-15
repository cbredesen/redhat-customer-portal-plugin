package rhdn.client;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.core.executors.ApacheHttpClientExecutor;

/**
 * An interceptor that adds HTTP BASIC authentication credentials.
 * 
 * @author Chris Bredesen
 */
public class SecurityClientExecutor extends ApacheHttpClientExecutor {

	@Override
	public ClientResponse execute(ClientRequest request) throws Exception {
		// TODO add security info
		return super.execute(request);
	}


}
