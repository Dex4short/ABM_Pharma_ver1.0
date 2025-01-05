package system.ui.panels.dialogs;

import javax.swing.BorderFactory;

import components.panels.DialogPanel;
import system.ui.Window;

public class DialogSystemInformation extends DialogPanel{
	private static final long serialVersionUID = 5111490821186000823L;

	public DialogSystemInformation() {
		super("System Information");
		setText(
			"System:\t\t DIMS - Distribution and Import Management System \n" +
			"Year Created:\t\t 2024 \n" +
			"Version:\t\t ver.1.0 \n" +
			"\n" +
			"Company:\t\t ABM Pharmaceuticals and Medical Industries \n" +
			"Stakeholder:\t\t Mr. Arnel B. Madula \n" +
			"\n" +
			"Software Developer Team:\n" +
			"\t\tProject Manager:\tDexter C. Pacaña\n" +
			"\t\tTechnical Lead:\tTristan Rhyl C. Penaso\n" +
			"\t\tUser Interface Designer:\tCarlo Jun Pesical\n" +
			"\t\tDatabase Designer:\tFitz Ulysses J. Geraldo\n" +
			"\t\tRequirements Analys:\tLouie Jean Mendaña\n" +
			"\t\tQuality Assurance:\tAbigail Badana	\n" +
			"\t\tDocumentation Specialist:\tShernielyn J. Cabahug\n"
		);

		getParagraphField().setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
		
		getButtonOk().setVisible(false);
		getButtonCancel().setText("close");
	}

	@Override
	public void onOk() {}
	@Override
	public void onCancel() {
		Window.getStackPanel().popPanel();
	}
}
