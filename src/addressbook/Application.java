package addressbook;

import addressbook.gui.AddressBookUI;

/**
 * An address book application that creates a GUI for the user to interact with
 * with a local AddressBook database.
 * 
 * @author Kyle Campbell (kjcampbell.317@gmail.com)
 * @since 1.1
 */
public class Application
{
	/**
	 * Application execution controller.
	 * 
	 * @param args unused
	 * @since 1.1
	 */
	public static void main(String[] args)
	{
		AddressBookUI gui = new AddressBookUI();
		
		gui.setVisible(true);
	}
}