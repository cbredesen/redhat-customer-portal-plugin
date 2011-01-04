package rhdn.command;

import java.util.Collection;
import java.util.HashSet;

/**
 * Attaches (uploads) files to a support case via the Red Hat Customer Portal
 * REST API. This command is designed to hold state that may be modified over
 * time and then performed once after everything is verified by the user.
 * 
 * @author Chris Bredesen
 */
public class AttachToCaseCommand {
	private Collection<String> filePaths = new HashSet<String>();
	private String caseNumber;
	private String username;
	private String password;

	/**
	 * Check the command's data for validity.  If valid, {@link #perform()} may be invoked.
	 * 
	 * @return <code>true</code> if the command can be performed.
	 */
	public boolean isValid() {
		return true;
	}

	public void perform() throws CommandException {
		int length = filePaths != null ? filePaths.size() : 0;
		System.out.println("Uploading " + length + " files to case " + caseNumber);
	}

	public Collection<String> getFilePaths() {
		return filePaths;
	}

	public void setFilePaths(Collection<String> filePaths) {
		this.filePaths = filePaths;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
