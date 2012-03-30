package addressbook;

import addressbook.gui.AddressBookUI;

public class ABApplication
{
	public static void main(String[] args)
	{
		AddressBookUI gui = new AddressBookUI();
		
		gui.setVisible(true);
	}
}