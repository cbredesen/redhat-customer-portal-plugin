package rhdn.client;

import com.redhat.gss.strata.resource.CasesResource;

/**
 * A resource locator for Customer Portal API resources. No client methodology
 * is specified here; implementors are free to use the RESTeasy client framework
 * or something else which requires fewer dependencies.
 * 
 * @author Chris Bredesen
 */
public interface PortalApiResourceLocator {

	/**
	 * Get a cases resource client instance.
	 */
	CasesResource getCasesResource();

}
