package rhdn.popup.actions;


import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import rhdn.popup.wizard.AttachToCaseWizard;

/**
 * File attachment action.  Activates with one or more selected file in the workspace and
 * allows attaching to an open support case.
 * 
 * @author Chris Bredesen
 */
public class FileSelectionAction implements IObjectActionDelegate {
	private ITreeSelection treeSelection;
	private Shell shell;

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		AttachToCaseWizard wiz = new AttachToCaseWizard(this.treeSelection);
		WizardDialog dialog = new WizardDialog(shell, wiz);
		dialog.create();
		dialog.open();
	}
	
	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof ITreeSelection) {
			this.treeSelection = (ITreeSelection) selection;
		}
	}

}
