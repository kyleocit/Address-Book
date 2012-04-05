package addressbook.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import addressbook.gui.components.*;

/**
 * Creates a graphical user interface window for the user to interact with
 * an AddressBook object.
 * 
 * @author Kyle Campbell (kjcampbell.317@gmail.com)
 * @since 1.1
 */
public class AddressBookUI extends JFrame
{
	/**
	 * Explicitly set class version unique id to prevent serialization errors.
	 * 
	 * @since 1.1
	 */
	private static final long serialVersionUID = -8564653696547099726L;

	/**
	 * Explicitly set the minimum size allowed for the GUI frame.
	 * 
	 * @since 1.1
	 */
	protected static final Dimension MIN_SIZE = new Dimension(800, 600);

	/**
	 * A text field for entering a search pattern to filter the contact's list.
	 * 
	 * @since 1.1
	 */
	protected JTextField search;

	/**
	 * A contact information panel to display the contents of the currently
	 * selected contact in the contact's list.
	 * 
	 * @since 1.1
	 */
	protected ContactInformationPanel contactInfo;

	/**
	 * A list of (possibly filtered) contacts from the address book.
	 */
	protected ContactListPanel contactList;

	/**
	 * A wrapper panel to hold the contacts list and contact information
	 * panel using a split pane.
	 * 
	 * @since 1.1
	 */
	protected JPanel content;

	/**
	 * A tabbed pane for filtering the contacts list by the first letter of
	 * contacts' last name.
	 * 
	 * @since 1.1
	 */
	protected JTabbedPane listings;

	/**
	 * A tabbed pane to select between viewing a contacts information and
	 * adding a new contact.
	 * 
	 * @since 1.1
	 */
	protected JTabbedPane modes;

	// TODO require an AddressBook object to be passed
	/**
	 * Sets up a new GUI for the specified AddressBook object.
	 *
	 * @since 1.1
	 */
	public AddressBookUI()
	{
		this.setupContent();
		this.setupListings();
		this.setupModes();
		this.render();
	}

	/**
	 * Sets up the content panel by adding a search field to the top, a contact
	 * list to the left, and a contact information panel to the right.
	 * 
	 * @since 1.1
	 */
	protected void setupContent()
	{
		// create search text field
		search = new JTextField();

		// create contact list and contact information panels
		contactList = new ContactListPanel();
		contactInfo = new ContactInformationPanel();

		// create a split pane for resizing the width of the contact
		// list and contact information panels relative to one another
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, contactList, contactInfo);
		split.setDividerLocation(180);
		split.setDividerSize(3);

		// create contact panel
		content = new JPanel();
		content.setBackground(StyleConstants.BACKGROUND_COLOR);
		content.setLayout(new BorderLayout());
		content.add(search, BorderLayout.NORTH);
		content.add(split, BorderLayout.CENTER);
	}

	/**
	 * Set's up the listings tabbed pane for filtering the contact list by last name.
	 * 
	 * @since 1.1
	 */
	protected void setupListings()
	{
		listings = new JTabbedPane();
		listings.setFont(StyleConstants.LISTINGS_FONT);
		listings.setTabPlacement(JTabbedPane.LEFT);
		listings.addTab("All", content);
		listings.setToolTipTextAt(0, "Shows all contacts in this address book.");
		listings.addTab("A", null);
		listings.setToolTipTextAt(1, "Shows contacts in this address book whose last name starts with the letter A. If the contact does not have a last name, the first name is used.");
		listings.addTab("B", null);
		listings.setToolTipTextAt(2, "Shows contacts in this address book whose last name starts with the letter B. If the contact does not have a last name, the first name is used.");
		listings.addTab("C", null);
		listings.setToolTipTextAt(3, "Shows contacts in this address book whose last name starts with the letter C. If the contact does not have a last name, the first name is used.");
		listings.addTab("D", null);
		listings.setToolTipTextAt(4, "Shows contacts in this address book whose last name starts with the letter D. If the contact does not have a last name, the first name is used.");
		listings.addTab("E", null);
		listings.setToolTipTextAt(5, "Shows contacts in this address book whose last name starts with the letter E. If the contact does not have a last name, the first name is used.");
		listings.addTab("F", null);
		listings.setToolTipTextAt(6, "Shows contacts in this address book whose last name starts with the letter F. If the contact does not have a last name, the first name is used.");
		listings.addTab("G", null);
		listings.setToolTipTextAt(7, "Shows contacts in this address book whose last name starts with the letter G. If the contact does not have a last name, the first name is used.");
		listings.addTab("H", null);
		listings.setToolTipTextAt(8, "Shows contacts in this address book whose last name starts with the letter H. If the contact does not have a last name, the first name is used.");
		listings.addTab("I", null);
		listings.setToolTipTextAt(9, "Shows contacts in this address book whose last name starts with the letter I. If the contact does not have a last name, the first name is used.");
		listings.addTab("J", null);
		listings.setToolTipTextAt(10, "Shows contacts in this address book whose last name starts with the letter J. If the contact does not have a last name, the first name is used.");
		listings.addTab("K", null);
		listings.setToolTipTextAt(11, "Shows contacts in this address book whose last name starts with the letter K. If the contact does not have a last name, the first name is used.");
		listings.addTab("L", null);
		listings.setToolTipTextAt(12, "Shows contacts in this address book whose last name starts with the letter L. If the contact does not have a last name, the first name is used.");
		listings.addTab("M", null);
		listings.setToolTipTextAt(13, "Shows contacts in this address book whose last name starts with the letter M. If the contact does not have a last name, the first name is used.");
		listings.addTab("N", null);
		listings.setToolTipTextAt(14, "Shows contacts in this address book whose last name starts with the letter N. If the contact does not have a last name, the first name is used.");
		listings.addTab("O", null);
		listings.setToolTipTextAt(15, "Shows contacts in this address book whose last name starts with the letter O. If the contact does not have a last name, the first name is used.");
		listings.addTab("P", null);
		listings.setToolTipTextAt(16, "Shows contacts in this address book whose last name starts with the letter P. If the contact does not have a last name, the first name is used.");
		listings.addTab("Q", null);
		listings.setToolTipTextAt(17, "Shows contacts in this address book whose last name starts with the letter Q. If the contact does not have a last name, the first name is used.");
		listings.addTab("R", null);
		listings.setToolTipTextAt(18, "Shows contacts in this address book whose last name starts with the letter R. If the contact does not have a last name, the first name is used.");
		listings.addTab("S", null);
		listings.setToolTipTextAt(19, "Shows contacts in this address book whose last name starts with the letter S. If the contact does not have a last name, the first name is used.");
		listings.addTab("T", null);
		listings.setToolTipTextAt(20, "Shows contacts in this address book whose last name starts with the letter T. If the contact does not have a last name, the first name is used.");
		listings.addTab("U", null);
		listings.setToolTipTextAt(21, "Shows contacts in this address book whose last name starts with the letter U. If the contact does not have a last name, the first name is used.");
		listings.addTab("V", null);
		listings.setToolTipTextAt(22, "Shows contacts in this address book whose last name starts with the letter V. If the contact does not have a last name, the first name is used.");
		listings.addTab("W", null);
		listings.setToolTipTextAt(23, "Shows contacts in this address book whose last name starts with the letter W. If the contact does not have a last name, the first name is used.");
		listings.addTab("X", null);
		listings.setToolTipTextAt(24, "Shows contacts in this address book whose last name starts with the letter X. If the contact does not have a last name, the first name is used.");
		listings.addTab("Y", null);
		listings.setToolTipTextAt(25, "Shows contacts in this address book whose last name starts with the letter Y. If the contact does not have a last name, the first name is used.");
		listings.addTab("Z", null);
		listings.setToolTipTextAt(26, "Shows contacts in this address book whose last name starts with the letter Z. If the contact does not have a last name, the first name is used.");
	}

	/**
	 * Sets up the modes tabbed pane to switch between viewing and adding a
	 * contact.
	 * 
	 * @since 1.1
	 */
	protected void setupModes()
	{
		modes = new JTabbedPane();
		modes.setFont(StyleConstants.MODES_FONT);
		modes.addTab("My Contacts", listings);
		modes.setToolTipTextAt(0, "View the selected contacts information.");
		modes.addTab("+", null);
		modes.setToolTipTextAt(1, "Add a new contact.");
		this.getContentPane().add(modes);
	}

	/**
	 * Sets up the GUI frame and renders it to the screen.
	 * 
	 * @since 1.1
	 */
	protected void render()
	{
		this.setTitle("Address Book: Welcome!");
		this.getContentPane().setBackground(StyleConstants.BACKGROUND_COLOR);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(MIN_SIZE);
		this.setPreferredSize(MIN_SIZE);
		this.pack();
		this.setLocationRelativeTo(null);
	}
}