package rhdn.popup.wizard;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import rhdn.command.AttachToCaseCommand;

class FileListPage extends WizardPage {
	private AttachToCaseCommand command;
	private Map<String, String> paths = new HashMap<String, String>();

	FileListPage(AttachToCaseCommand command, Map<String, String> paths) {
		super("File List");
		setTitle("Files to Attach");
		setDescription("Review the list of files to be attached");
		this.command = command;
		this.paths = paths;
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FillLayout());

		final Table table = new Table(composite, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		for (String path : paths.keySet()) {
			TableItem item = new TableItem(table, 0);
			item.setText(path);
		}
		
		table.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				for (TableItem item : table.getSelection()) {
					command.getFilePaths().add(item.getText());
				}
			}
		});

		setControl(composite);
	}

}
