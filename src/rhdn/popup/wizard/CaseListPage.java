package rhdn.popup.wizard;

import java.util.List;
import java.util.logging.Logger;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.jboss.resteasy.client.ClientResponseFailure;

import rhdn.client.ClientProxyPortalApiResourceLocator;
import rhdn.client.PortalApiResourceLocator;
import rhdn.command.AttachToCaseCommand;

import com.redhat.gss.strata.model.Case;
import com.redhat.gss.strata.model.Cases;

/**
 * A wizard page that presents a list of open support cases from which the user
 * can select a single one.
 * 
 * @author Chris Bredesen
 */
class CaseListPage extends WizardPage {
	private Logger log = Logger.getLogger(getClass().getName());
	private AttachToCaseCommand command;

	CaseListPage(AttachToCaseCommand command) {
		super("Case List");
		setTitle("Case List");
		setDescription("Select an open support case to which to attach files");
		this.command = command;
	}

	@Override
	public void createControl(Composite parent) {
		
		// try to list cases first; if there's an error we need to display it
		List<Case> cases;
		try {
			cases = getOpenCases();
		} catch (ClientResponseFailure e) {
			setErrorMessage(e.getMessage());
			return;
		}

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FillLayout());

		// build the table
		final Table caseTable = new Table(composite, SWT.BORDER | SWT.V_SCROLL
				| SWT.H_SCROLL);
		TableColumn caseCol = new TableColumn(caseTable, SWT.LEFT);
		caseCol.setText("Case");
		caseCol.setWidth(80);
		TableColumn descCol = new TableColumn(caseTable, SWT.LEFT);
		descCol.setText("Description");
		descCol.pack(); // needed?
		caseTable.setHeaderVisible(true);

		// capture case number when an event lands
		caseTable.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				String caseNumber = caseTable.getSelection()[0].getText();
				command.setCaseNumber(caseNumber);
			}
		});

		for (Case c : cases) {
			TableItem item = new TableItem(caseTable, SWT.NONE);
			item.setText(new String[] { c.getCaseNumber(), c.getSummary() });
		}

		setControl(composite);
	}

	private List<Case> getOpenCases() {
		PortalApiResourceLocator locator = new ClientProxyPortalApiResourceLocator();
		Cases cases = locator.getCasesResource().listCases(true, null, null, null);
		return cases.getCase();
	}

}
