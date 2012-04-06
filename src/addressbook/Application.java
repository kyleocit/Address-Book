package addressbook;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JOptionPane;

import addressbook.addressbook.AddressBook;
import addressbook.addressbook.Contact;
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
		AddressBook ab = new AddressBook();
		AddressBookUI gui = new AddressBookUI(ab);
		gui.addKeyListener(new Development(ab));
		
		gui.setVisible(true);
	}

	/**
	 * Special functions for testing the application during the development phase.
	 * 
	 * @since 1.1
	 */
	protected static class Development implements KeyListener
	{
		/**
		 * Stores the address book to work with.
		 * 
		 * @since 1.1
		 */
		protected AddressBook addressBook;
		
		/**
		 * Creates a new development object.
		 * 
		 * @param addressBook the address book to work on
		 */
		public Development(AddressBook addressBook)
		{
			this.addressBook = addressBook;
		}
		
		/**
		 * [DEVELOPMENT ONLY]
		 * Automatically generates random contacts for the address book.
		 * 
		 * @param threshold the number of entries to generate
		 * @since 1.1
		 */
		private void generate()
		{
			int threshold = Integer.parseInt(JOptionPane.showInputDialog(null, "How many entries should be generated?", "20"));
			String[] first = {"Jim", "John", "Jane", "Alison", "Thomas", "Katie"};
			String[] middle = {"Winter", "Summer", "Jamie", "Fall", "Hon", "Von"};
			String[] last = {"Smith", "Doe", "Griffin", "Pritchard", "Durenzo", "Drake"};
			String[] street = {"1st", "Main", "Mechanic", "Broadway", "Frost", "Ginger"};
			String[] streetSuffix = {"Street", "Ave", "Lane", "Drive", "Road"};
			String[] city = {"Jordan", "Elbridge", "Cato", "Meridian", "Weedsport", "Auburn", "Syracuse"};
			String[] state = {"NY", "CA", "PA", "FL", "VA", "MN"};
			String[] emailPrefix = {"happygal", "ducky", "cooldude", "drtest", "candybar", "roflcopter"};
			String[] emailSite = {"mysite", "coolweb", "freemail", "sonic", "pillbottle", "fail"};
			String[] emailSuffix = {"com", "org", "edu", "co.uk", "us", "tv", "me", "tk"};
			
			Random rand = new Random();
			while (threshold-- > 0)
			{
				Contact contact = new Contact();
				contact.setName(first[rand.nextInt(first.length)] + " " + middle[rand.nextInt(middle.length)] + " " + last[rand.nextInt(last.length)]);
				contact.setStreet((rand.nextInt(9000) + 1000) + " " + street[rand.nextInt(street.length)] + " " + streetSuffix[rand.nextInt(streetSuffix.length)]);
				contact.setCity(city[rand.nextInt(city.length)]);
				contact.setState(state[rand.nextInt(state.length)]);
				contact.setZipcode("" + (rand.nextInt(90000) + 10000));
				contact.setHomePhone("(" + (rand.nextInt(900) + 100) + ") " + (rand.nextInt(900) + 100) + " - " + (rand.nextInt(9000) + 1000));
				contact.setCellPhone("(" + (rand.nextInt(900) + 100) + ") " + (rand.nextInt(900) + 100) + " - " + (rand.nextInt(9000) + 1000));
				contact.setWorkPhone("(" + (rand.nextInt(900) + 100) + ") " + (rand.nextInt(900) + 100) + " - " + (rand.nextInt(9000) + 1000));
				contact.setFaxNumber("(" + (rand.nextInt(900) + 100) + ") " + (rand.nextInt(900) + 100) + " - " + (rand.nextInt(9000) + 1000));
				contact.setEmail(emailPrefix[rand.nextInt(emailPrefix.length)] + "@" + emailSite[rand.nextInt(emailSite.length)] + "." + emailSuffix[rand.nextInt(emailSuffix.length)]);
				addressBook.add(contact);
			}
		}
	
		/**
		 * Whenever a key is released within a text field or text area that
		 * has registered this listener the input is purified.
		 * 
		 * @since 1.1
		 */
		@Override public void keyReleased(KeyEvent e)
		{
			// allow left and right cursor movement
			if (e.getKeyCode() == KeyEvent.VK_G && e.isShiftDown())
			{
				generate();
			}
		}

		//---
		// Unused Listener Methods
		@Override public void keyTyped(KeyEvent e) {}
		@Override public void keyPressed(KeyEvent e) {}
		//---
	}
}