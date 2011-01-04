package rhdn.popup.wizard;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.wizard.Wizard;

import rhdn.command.AttachToCaseCommand;
import rhdn.command.CommandException;

/**
 * A wizard that guides the user through attaching selected files to an open
 * support case on the Red Hat Customer Portal.
 * 
 * @author Chris Bredesen
 */
public class AttachToCaseWizard extends Wizard {
	private AttachToCaseCommand command = new AttachToCaseCommand();
	private CaseListPage caseList;
	private FileListPage fileList;

	public AttachToCaseWizard(ITreeSelection selection) {
		setWindowTitle("Attach Files to Support Case");
		AttachToCaseCommand command = new AttachToCaseCommand(); 
		this.caseList = new CaseListPage(command);
		this.fileList = new FileListPage(command, getFilePaths(selection));
	}

	@Override
	public void addPages() {
		addPage(caseList);
		addPage(fileList);
	}

	@Override
	public boolean canFinish() {
		return command.isValid();
	}

	@Override
	public boolean performFinish() {
		try {
			command.perform();
			return true;
		} catch (CommandException e) {
			// TODO present e.getMessage() in the UI
			return false;
		}
	}

	/**
	 * Generate a map of workspace path to full path for each file selected. The
	 * former is useful for UI interaction and the latter for actual file
	 * manipulation.
	 * 
	 * @param treeSelection Tree selection.
	 * 
	 * @return Map("/project/myfile.txt", "/home/me/workspace/project/myfile.txt")
	 */
	private Map<String, String> getFilePaths(ITreeSelection treeSelection) {
		HashMap<String, String> filePaths = new HashMap<String, String>();
		for (TreePath path : treeSelection.getPaths()) {
			for (int i = 0; i < path.getSegmentCount(); i++) {
				Object segment = path.getSegment(i);
				if (segment instanceof IFile) {
					IFile file = (IFile) segment;
					String workspacePath = file.getFullPath().toString();
					String osPath = file.getRawLocation().toOSString();
					filePaths.put(workspacePath, osPath);
				}
			}
		}
		return filePaths;
	}
	
}
