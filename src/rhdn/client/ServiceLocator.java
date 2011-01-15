package rhdn.client;

import com.redhat.gss.strata.resource.CasesResource;

public interface ServiceLocator {
	
	CasesResource getCasesResource();

}
