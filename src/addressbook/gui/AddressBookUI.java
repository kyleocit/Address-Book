package addressbook.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import addressbook.gui.components.*;

public class AddressBookUI extends JFrame
{
	protected static final Dimension MIN_SIZE = new Dimension(800, 600);

	protected JTextField search;

	protected ContactInformationPanel contactInfo;

	protected ContactListPanel contactList;

	protected JPanel content;

	protected JTabbedPane listings;

	protected JTabbedPane modes;

	public AddressBookUI()
	{
		this.setupContent();
		this.setupListings();
		this.setupModes();
		this.render();
	}

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