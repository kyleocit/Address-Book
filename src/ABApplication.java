/*
 * @(#)ABApplication.java	1.0 10/10/21
 *
 * Title: Midterm
 * Course: CSC 112 - 001
 * Author: Kyle Campbell
 *
 * UML Diagram
 * --------------------------------------------------
 *                   ABApplication
 * --------------------------------------------------
 *  -MIN_SIZE: Dimension
 *  -ADD_MODE: int
 *  -EDIT_MODE: int
 *  -VIEW_MODE: int
 *  -mode: int
 *  -selected: ABPerson
 *  -addressBook: AddressBook
 *  -cancelButton: JButton
 *  -changePictureButton: JButton
 *  -continueButton: JButton
 *  -viewMapButton: JButton
 *  -pictureChooser: JFileChooser
 *  -frame: JFrame
 *  -notesLabel: JLabel
 *  -pictureLabel: JLabel
 *  -nameLabel: JLabel
 *  -streetLabel: JLabel
 *  -cityLabel: JLabel
 *  -stateLabel: JLabel
 *  -zipcodeLabel: JLabel
 *  -homePhoneLabel: JLabel
 *  -cellPhoneLabel: JLabel
 *  -workPhoneLabel: JLabel
 *  -faxNumberLabel: JLabel
 *  -emailLabel: JLabel
 *  -contactList: JList
 *  -addressBookPanel: JPanel
 *  -contentPanel: JPanel
 *  -contactInformationPanel: JPanel
 *  -informationPanel: JPanel
 *  -contactListScrollPane: JScrollPane
 *  -notesScrollPane: JScrollPane
 *  -contentSplitPane: JSPlitPane
 *  -modeTabbedPane: JTabbedPane
 *  -letterTabbedPane: JTabbedPane
 *  -notesField: JTextArea
 *  -nameField: JTextField
 *  -streetField: JTextField
 *  -cityField: JTextField
 *  -stateField: JTextField
 *  -zipcodeField: JTextField
 *  -homePhoneField: JTextField
 *  -cellPhoneField: JTextField
 *  -workPhoneField: JTextField
 *  -faxNumberField: JTextField
 *  -emailField: JTextField
 * --------------------------------------------------
 *  +main(String[]): void
 *  -initialize(): void
 *  -addMode(): void
 *  -clearFields(): void
 *  -editMode(): void
 *  -generate(int): void
 *  -isFieldsEmpty(): boolean
 *  -viewMode(): void
 *  -EventMgr
 *    +actionPerformed(ActionEvent): void
 *    +addressBookModified(): void
 *    +componentResized(ComponentEvent): void
 *    +keyPressed(KeyEvent): void
 *    +stateChanged(ChangeEvent): void
 *    +valueChanged(ListSelectionEvent): void
 * --------------------------------------------------
 * 
 * Change Log
 * v1.0
 *  - initial release
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ABApplication
{
	/** Stores minimum size dimensions for frame. */
	private static final Dimension MIN_SIZE = new Dimension(800, 600);

	/** Stores mode constants. */
	private static final int ADD_MODE = 0, EDIT_MODE = 1, VIEW_MODE = 2;

	/** Stores current mode. */
	private static int mode;

	/** Stores currently selected contact when editing. */
	private static ABPerson selected; // used for edit mode

	/** Stores address book. */
	private static AddressBook addressBook;

	// UI Components
	private static JButton cancelButton;
	private static JButton changePictureButton;
	private static JButton continueButton;
	private static JButton viewMapButton;
	private static JFileChooser pictureChooser;
	private static JFrame frame;
	private static JLabel notesLabel;
	private static JLabel pictureLabel;
	private static JLabel nameLabel;
	private static JLabel streetLabel;
	private static JLabel cityLabel;
	private static JLabel stateLabel;
	private static JLabel zipcodeLabel;
	private static JLabel homePhoneLabel;
	private static JLabel cellPhoneLabel;
	private static JLabel workPhoneLabel;
	private static JLabel faxNumberLabel;
	private static JLabel emailLabel;
	private static JList contactList;
	private static JPanel addressBookPanel;
	private static JPanel contentPanel;
	private static JPanel contactInformationPanel;
	private static JPanel informationPanel;
	private static JScrollPane contactListScrollPane;
	private static JScrollPane notesScrollPane;
	private static JSplitPane contentSplitPane;
	private static JTabbedPane modeTabbedPane;
	private static JTabbedPane letterTabbedPane;
	private static JTextArea notesField;
	private static JTextField nameField;
	private static JTextField streetField;
	private static JTextField cityField;
	private static JTextField stateField;
	private static JTextField zipcodeField;
	private static JTextField homePhoneField;
	private static JTextField cellPhoneField;
	private static JTextField workPhoneField;
	private static JTextField faxNumberField;
	private static JTextField emailField;
	private static JTextField searchField;

	/**
	 * Launches this application.
	 */
	public static void main(String[] args)
	{
		initialize();
		if (!(new File(ABConstants.userDatabase)).exists())
			JOptionPane.showMessageDialog(frame, "It appears that this is your first time using this program! You should begin by adding a contact. To add a new contact all you have to do is press the \"+\" tab at the top of the screen!", "Welcome!", JOptionPane.INFORMATION_MESSAGE);
		addressBook.load(new File(ABConstants.userDatabase));
		frame.setVisible(true);
		viewMode();
		System.out.println("Notice: nopicture.png was borrowed from: http://cosam.calpoly.edu/images/icons/no_photo.png");
	}

	/**
	 * Initializes and sets up all ui components, address book, and event handlers.
	 */
	private static void initialize()
	{
		//-------------------------------------------------------------------------
		// Setup Picture File Chooser
		//-------------------------------------------------------------------------
		
		pictureChooser = new JFileChooser();
		pictureChooser.setAcceptAllFileFilterUsed(false);
		pictureChooser.setApproveButtonText("Select");
		pictureChooser.setBackground(ABConstants.backgroundColor);
		pictureChooser.setDialogTitle("Select a new contact picture");
		pictureChooser.setFileFilter(new ImageFilter());
		pictureChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		pictureChooser.setMultiSelectionEnabled(false);
		
		//-------------------------------------------------------------------------
		// Setup Contact Information Panel
		//-------------------------------------------------------------------------

		// setup picture label
		pictureLabel = new JLabel(ABConstants.defaultPicture);
		pictureLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.GRAY));

		// setup name label and text field
		nameLabel = new JLabel(" Name");
		nameLabel.setBackground(Color.GRAY);
		nameLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		nameLabel.setVerticalAlignment(SwingConstants.TOP);
		nameField = new JTextField();
		nameField.setBorder(null);
		nameField.setEditable(false);
		nameField.setFont(ABConstants.infoFont);
		nameField.setOpaque(false);

		// setup street address label and text field
		streetLabel = new JLabel(" Address");
		streetLabel.setBackground(Color.GRAY);
		streetLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
		streetLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		streetLabel.setHorizontalAlignment(SwingConstants.LEFT);
		streetLabel.setVerticalAlignment(SwingConstants.TOP);
		streetField = new JTextField();
		streetField.setBorder(null);
		streetField.setEditable(false);
		streetField.setFont(ABConstants.infoFont);
		streetField.setOpaque(false);

		// setup city label and text field
		cityLabel = new JLabel(" City");
		cityLabel.setBackground(Color.GRAY);
		cityLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
		cityLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		cityLabel.setHorizontalAlignment(SwingConstants.LEFT);
		cityLabel.setVerticalAlignment(SwingConstants.TOP);
		cityField = new JTextField();
		cityField.setBorder(null);
		cityField.setEditable(false);
		cityField.setFont(ABConstants.infoFont);
		cityField.setOpaque(false);

		// setup state label and text field
		stateLabel = new JLabel(" State");
		stateLabel.setBackground(Color.GRAY);
		stateLabel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, Color.GRAY));
		stateLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		stateLabel.setHorizontalAlignment(SwingConstants.LEFT);
		stateLabel.setVerticalAlignment(SwingConstants.TOP);
		stateField = new JTextField();
		stateField.setBorder(null);
		stateField.setFont(ABConstants.infoFont);
		stateField.setOpaque(false);

		// setup zip code label and text field
		zipcodeLabel = new JLabel(" Zip Code");
		zipcodeLabel.setBackground(Color.GRAY);
		zipcodeLabel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, Color.GRAY));
		zipcodeLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		zipcodeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		zipcodeLabel.setVerticalAlignment(SwingConstants.TOP);
		zipcodeField = new JTextField();
		zipcodeField.setBorder(null);
		zipcodeField.setEditable(false);
		zipcodeField.setFont(ABConstants.infoFont);
		zipcodeField.setOpaque(false);

		// setup home phone label and text field
		homePhoneLabel = new JLabel(" Home Phone");
		homePhoneLabel.setBackground(Color.GRAY);
		homePhoneLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
		homePhoneLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		homePhoneLabel.setHorizontalAlignment(SwingConstants.LEFT);
		homePhoneLabel.setVerticalAlignment(SwingConstants.TOP);
		homePhoneField = new JTextField();
		homePhoneField.setBorder(null);
		homePhoneField.setEditable(false);
		homePhoneField.setFont(ABConstants.infoFont);
		homePhoneField.setOpaque(false);

		// setup cell phone label and text field
		cellPhoneLabel = new JLabel(" Cell Phone");
		cellPhoneLabel.setBackground(Color.GRAY);
		cellPhoneLabel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, Color.GRAY));
		cellPhoneLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		cellPhoneLabel.setHorizontalAlignment(SwingConstants.LEFT);
		cellPhoneLabel.setVerticalAlignment(SwingConstants.TOP);
		cellPhoneField = new JTextField();
		cellPhoneField.setBorder(null);
		cellPhoneField.setEditable(false);
		cellPhoneField.setFont(ABConstants.infoFont);
		cellPhoneField.setOpaque(false);

		// setup work phone label and text field
		workPhoneLabel = new JLabel(" Work Phone");
		workPhoneLabel.setBackground(Color.GRAY);
		workPhoneLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
		workPhoneLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		workPhoneLabel.setHorizontalAlignment(SwingConstants.LEFT);
		workPhoneLabel.setVerticalAlignment(SwingConstants.TOP);
		workPhoneField = new JTextField();
		workPhoneField.setBorder(null);
		workPhoneField.setEditable(false);
		workPhoneField.setFont(ABConstants.infoFont);
		workPhoneField.setOpaque(false);

		// setup fax number label and text field
		faxNumberLabel = new JLabel(" Fax Number");
		faxNumberLabel.setBackground(Color.GRAY);
		faxNumberLabel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, Color.GRAY));
		faxNumberLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		faxNumberLabel.setHorizontalAlignment(SwingConstants.LEFT);
		faxNumberLabel.setVerticalAlignment(SwingConstants.TOP);
		faxNumberField = new JTextField();
		faxNumberField.setBorder(null);
		faxNumberField.setEditable(false);
		faxNumberField.setFont(ABConstants.infoFont);
		faxNumberField.setOpaque(false);

		// setup email label and text field
		emailLabel = new JLabel(" Email Address");
		emailLabel.setBackground(Color.GRAY);
		emailLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
		emailLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		emailLabel.setHorizontalAlignment(SwingConstants.LEFT);
		emailLabel.setVerticalAlignment(SwingConstants.TOP);
		emailField = new JTextField();
		emailField.setBorder(null);
		emailField.setEditable(false);
		emailField.setFont(ABConstants.infoFont);
		emailField.setOpaque(false);

		// setup notes label
		notesLabel = new JLabel("Contact Notes");
		notesLabel.setBackground(ABConstants.backgroundColor);
		notesLabel.setFont(new Font("Calibri", Font.BOLD, 14));
		notesLabel.setForeground(Color.BLACK);
		notesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		notesLabel.setOpaque(true);
		notesLabel.setVerticalAlignment(SwingConstants.CENTER);

		// setup notes text area and scroll pane
		notesField = new JTextArea("Bla");
		notesField.setFont(new Font("Verdana", Font.PLAIN, 12));
		notesField.setLineWrap(true);
		notesField.setOpaque(false);
		notesField.setTabSize(4);
		notesField.setWrapStyleWord(true);
		notesScrollPane = new JScrollPane();
		notesScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		notesScrollPane.setViewportView(notesField);

		// setup change picture button
		changePictureButton = new JButton("Change Picture");
		changePictureButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.GRAY));

		// setup delete contact button
		cancelButton = new JButton("Delete Contact");
		cancelButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.GRAY));

		// setup edit contact button
		continueButton = new JButton("Edit Contact");
		continueButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

		// setup view map button
		viewMapButton = new JButton("View Map");
		viewMapButton.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));

		// setup information panel and add components
		informationPanel = new JPanel();
		informationPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		informationPanel.setLayout(null);
		informationPanel.add(pictureLabel);
		informationPanel.add(changePictureButton);
		informationPanel.add(cancelButton);
		informationPanel.add(continueButton);
		informationPanel.add(viewMapButton);
		informationPanel.add(nameField);
		informationPanel.add(nameLabel);
		informationPanel.add(streetField);
		informationPanel.add(streetLabel);
		informationPanel.add(cityField);
		informationPanel.add(cityLabel);
		informationPanel.add(stateField);
		informationPanel.add(stateLabel);
		informationPanel.add(zipcodeField);
		informationPanel.add(zipcodeLabel);
		informationPanel.add(homePhoneField);
		informationPanel.add(homePhoneLabel);
		informationPanel.add(cellPhoneField);
		informationPanel.add(cellPhoneLabel);
		informationPanel.add(workPhoneField);
		informationPanel.add(workPhoneLabel);
		informationPanel.add(faxNumberField);
		informationPanel.add(faxNumberLabel);
		informationPanel.add(emailField);
		informationPanel.add(emailLabel);
		informationPanel.add(notesScrollPane);
		informationPanel.add(notesLabel);

		// setup this panel
		contactInformationPanel = new JPanel();
		contactInformationPanel.setBackground(ABConstants.backgroundColor);
		contactInformationPanel.setLayout(null);
		contactInformationPanel.add(informationPanel);

		//-------------------------------------------------------------------------
		// Setup Address Book Panel
		//-------------------------------------------------------------------------

		// initialize and setup search field
		searchField = new JTextField();

		// initialize and setup contact list
		contactList = new JList();
		contactList.setBackground(ABConstants.backgroundColor);
		contactList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		contactListScrollPane = new JScrollPane();
		contactListScrollPane.getViewport().setView(contactList);
		contactListScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		// initialize and setup split pane for contact list and selected contact panel
		contentSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, contactListScrollPane, contactInformationPanel);
		contentSplitPane.setDividerLocation(180);
		contentSplitPane.setDividerSize(3);

		// initialize and setup content panel for search field and content split pane
		contentPanel = new JPanel();
		contentPanel.setBackground(ABConstants.backgroundColor);
		contentPanel.setLayout(new BorderLayout());
		contentPanel.add(searchField, BorderLayout.NORTH);
		contentPanel.add(contentSplitPane, BorderLayout.CENTER);

		// initialize and setup letter tab pane for listing filtering
		letterTabbedPane = new JTabbedPane();
		letterTabbedPane.setFont(new Font("Tahoma", Font.BOLD, 12));
		letterTabbedPane.setTabPlacement(JTabbedPane.LEFT);
		letterTabbedPane.addTab("All", contentPanel);
		letterTabbedPane.setToolTipTextAt(0, "Shows all contacts in this address book.");
		letterTabbedPane.addTab("A", null);
		letterTabbedPane.setToolTipTextAt(1, "Shows contacts in this address book whose last name starts with the letter A. If the contact does not have a last name, the first name is used.");
		letterTabbedPane.addTab("B", null);
		letterTabbedPane.setToolTipTextAt(2, "Shows contacts in this address book whose last name starts with the letter B. If the contact does not have a last name, the first name is used.");
		letterTabbedPane.addTab("C", null);
		letterTabbedPane.setToolTipTextAt(3, "Shows contacts in this address book whose last name starts with the letter C. If the contact does not have a last name, the first name is used.");
		letterTabbedPane.addTab("D", null);
		letterTabbedPane.setToolTipTextAt(4, "Shows contacts in this address book whose last name starts with the letter D. If the contact does not have a last name, the first name is used.");
		letterTabbedPane.addTab("E", null);
		letterTabbedPane.setToolTipTextAt(5, "Shows contacts in this address book whose last name starts with the letter E. If the contact does not have a last name, the first name is used.");
		letterTabbedPane.addTab("F", null);
		letterTabbedPane.setToolTipTextAt(6, "Shows contacts in this address book whose last name starts with the letter F. If the contact does not have a last name, the first name is used.");
		letterTabbedPane.addTab("G", null);
		letterTabbedPane.setToolTipTextAt(7, "Shows contacts in this address book whose last name starts with the letter G. If the contact does not have a last name, the first name is used.");
		letterTabbedPane.addTab("H", null);
		letterTabbedPane.setToolTipTextAt(8, "Shows contacts in this address book whose last name starts with the letter H. If the contact does not have a last name, the first name is used.");
		letterTabbedPane.addTab("I", null);
		letterTabbedPane.setToolTipTextAt(9, "Shows contacts in this address book whose last name starts with the letter I. If the contact does not have a last name, the first name is used.");
		letterTabbedPane.addTab("J", null);
		letterTabbedPane.setToolTipTextAt(10, "Shows contacts in this address book whose last name starts with the letter J. If the contact does not have a last name, the first name is used.");
		letterTabbedPane.addTab("K", null);
		letterTabbedPane.setToolTipTextAt(11, "Shows contacts in this address book whose last name starts with the letter K. If the contact does not have a last name, the first name is used.");
		letterTabbedPane.addTab("L", null);
		letterTabbedPane.setToolTipTextAt(12, "Shows contacts in this address book whose last name starts with the letter L. If the contact does not have a last name, the first name is used.");
		letterTabbedPane.addTab("M", null);
		letterTabbedPane.setToolTipTextAt(13, "Shows contacts in this address book whose last name starts with the letter M. If the contact does not have a last name, the first name is used.");
		letterTabbedPane.addTab("N", null);
		letterTabbedPane.setToolTipTextAt(14, "Shows contacts in this address book whose last name starts with the letter N. If the contact does not have a last name, the first name is used.");
		letterTabbedPane.addTab("O", null);
		letterTabbedPane.setToolTipTextAt(15, "Shows contacts in this address book whose last name starts with the letter O. If the contact does not have a last name, the first name is used.");
		letterTabbedPane.addTab("P", null);
		letterTabbedPane.setToolTipTextAt(16, "Shows contacts in this address book whose last name starts with the letter P. If the contact does not have a last name, the first name is used.");
		letterTabbedPane.addTab("Q", null);
		letterTabbedPane.setToolTipTextAt(17, "Shows contacts in this address book whose last name starts with the letter Q. If the contact does not have a last name, the first name is used.");
		letterTabbedPane.addTab("R", null);
		letterTabbedPane.setToolTipTextAt(18, "Shows contacts in this address book whose last name starts with the letter R. If the contact does not have a last name, the first name is used.");
		letterTabbedPane.addTab("S", null);
		letterTabbedPane.setToolTipTextAt(19, "Shows contacts in this address book whose last name starts with the letter S. If the contact does not have a last name, the first name is used.");
		letterTabbedPane.addTab("T", null);
		letterTabbedPane.setToolTipTextAt(20, "Shows contacts in this address book whose last name starts with the letter T. If the contact does not have a last name, the first name is used.");
		letterTabbedPane.addTab("U", null);
		letterTabbedPane.setToolTipTextAt(21, "Shows contacts in this address book whose last name starts with the letter U. If the contact does not have a last name, the first name is used.");
		letterTabbedPane.addTab("V", null);
		letterTabbedPane.setToolTipTextAt(22, "Shows contacts in this address book whose last name starts with the letter V. If the contact does not have a last name, the first name is used.");
		letterTabbedPane.addTab("W", null);
		letterTabbedPane.setToolTipTextAt(23, "Shows contacts in this address book whose last name starts with the letter W. If the contact does not have a last name, the first name is used.");
		letterTabbedPane.addTab("X", null);
		letterTabbedPane.setToolTipTextAt(24, "Shows contacts in this address book whose last name starts with the letter X. If the contact does not have a last name, the first name is used.");
		letterTabbedPane.addTab("Y", null);
		letterTabbedPane.setToolTipTextAt(25, "Shows contacts in this address book whose last name starts with the letter Y. If the contact does not have a last name, the first name is used.");
		letterTabbedPane.addTab("Z", null);
		letterTabbedPane.setToolTipTextAt(26, "Shows contacts in this address book whose last name starts with the letter Z. If the contact does not have a last name, the first name is used.");

		// initialize and setup mode tab pane for mode selection
		modeTabbedPane = new JTabbedPane();
		modeTabbedPane.setFont(new Font("Arial", Font.BOLD, 12));
		modeTabbedPane.addTab("My Contacts", letterTabbedPane);
		modeTabbedPane.setToolTipTextAt(0, "View the selected contacts information.");
		modeTabbedPane.addTab("+", null);
		modeTabbedPane.setToolTipTextAt(1, "Add a new contact.");

		// initialize and setup address book panel
		addressBookPanel = new JPanel();
		addressBookPanel.setBackground(ABConstants.backgroundColor);
		addressBookPanel.setLayout(null);
		addressBookPanel.add(modeTabbedPane);

		//-------------------------------------------------------------------------
		// Setup Application Event Listeners
		//-------------------------------------------------------------------------

		// intialize global event manager
		EventMgr evtMgr = new EventMgr();

		// setup component resize listeners
		addressBookPanel.addComponentListener(evtMgr);
		contactInformationPanel.addComponentListener(evtMgr);

		// add button listeners
		cancelButton.addActionListener(evtMgr);
		continueButton.addActionListener(evtMgr);
		changePictureButton.addActionListener(evtMgr);
		viewMapButton.addActionListener(evtMgr);

		// setup mode tab listeners
		modeTabbedPane.addChangeListener(evtMgr);

		// setup letter tab listener
		letterTabbedPane.addChangeListener(evtMgr);

		// setup list selection listeners
		contactList.addListSelectionListener(evtMgr);

		//-------------------------------------------------------------------------
		// Setup Field Verifiers
		//-------------------------------------------------------------------------

		addressBookPanel.addKeyListener(evtMgr);
		searchField.addKeyListener(evtMgr);
		nameField.addKeyListener(evtMgr);
		streetField.addKeyListener(evtMgr);
		cityField.addKeyListener(evtMgr);
		stateField.addKeyListener(evtMgr);
		zipcodeField.addKeyListener(evtMgr);
		homePhoneField.addKeyListener(evtMgr);
		cellPhoneField.addKeyListener(evtMgr);
		workPhoneField.addKeyListener(evtMgr);
		faxNumberField.addKeyListener(evtMgr);
		emailField.addKeyListener(evtMgr);

		//-------------------------------------------------------------------------
		// Setup Application Frame
		//-------------------------------------------------------------------------

		// initialize and setup application frame
		frame = new JFrame("Address Book: Welcome!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(MIN_SIZE);
		frame.setPreferredSize(MIN_SIZE);
		frame.getContentPane().add(addressBookPanel);
		frame.pack();
		frame.setLocationRelativeTo(null);

		//-------------------------------------------------------------------------
		// Setup Address Book
		//-------------------------------------------------------------------------

		// initialize and setup address book
		addressBook = new AddressBook();

		// register address book listener
		addressBook.addEventListener(evtMgr);
	}

	/**
	 * Switches address book to ADD_MODE.
	 */
	private static void addMode()
	{
		// disallow adding while editing
		if (mode == EDIT_MODE && JOptionPane.showConfirmDialog(frame, "Are you sure you wish to cancel editing this contact?", "Cancel editing contact...", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
			modeTabbedPane.setSelectedIndex(0);

		// update mode
		mode = ADD_MODE;
		if (modeTabbedPane.getSelectedIndex() != 1)
			modeTabbedPane.setSelectedIndex(1);

		// update tool tip text
		nameField.setToolTipText("The full name of this contact.");
		streetField.setToolTipText("The street address of this contacts residence.");
		cityField.setToolTipText("The city of this contacts residence.");
		stateField.setToolTipText("The state of this contacts residence.");
		zipcodeField.setToolTipText("The zip code of this contacts residence.");
		homePhoneField.setToolTipText("The home phone number of this contact.");
		cellPhoneField.setToolTipText("The cell phone number of this contact.");
		workPhoneField.setToolTipText("The work phone number of this contact.");
		faxNumberField.setToolTipText("The fax number of this contact.");
		emailField.setToolTipText("The email address of this contact.");
		notesField.setToolTipText("Additional notes for this contact.");
		changePictureButton.setToolTipText("Choose a new picture for this contact.");
		cancelButton.setToolTipText("Cancels adding this contact and discards all entered information.");
		continueButton.setToolTipText("Adds this new contact to the address book.");

		// clear fields
		clearFields();

		// rename and enable buttons
		cancelButton.setText("Cancel");
		continueButton.setText("Add Contact");
		cancelButton.setEnabled(true);
		continueButton.setEnabled(true);
		changePictureButton.setEnabled(false);
		viewMapButton.setEnabled(false);

		// enable field editing
		nameField.setEditable(true);
		streetField.setEditable(true);
		cityField.setEditable(true);
		stateField.setEditable(true);
		zipcodeField.setEditable(true);
		homePhoneField.setEditable(true);
		cellPhoneField.setEditable(true);
		workPhoneField.setEditable(true);
		faxNumberField.setEditable(true);
		emailField.setEditable(true);
		notesField.setEditable(true);
	}

	/**
	 * Clears all contact information fields.
	 */
	private static void clearFields()
	{
		nameField.setText("");
		streetField.setText("");
		cityField.setText("");
		stateField.setText("");
		zipcodeField.setText("");
		homePhoneField.setText("");
		cellPhoneField.setText("");
		workPhoneField.setText("");
		faxNumberField.setText("");
		emailField.setText("");
		notesField.setText("");
		pictureLabel.setIcon(resizeImage(ABConstants.defaultPicture));
	}

	/**
	 * Switches address book to EDIT_MODE.
	 */
	private static void editMode()
	{
		// update mode
		mode = EDIT_MODE;
		if (modeTabbedPane.getSelectedIndex() != 0)
			modeTabbedPane.setSelectedIndex(0);

		// update tool tip text
		nameField.setToolTipText("The full name of this contact.");
		streetField.setToolTipText("The street address of this contacts residence.");
		cityField.setToolTipText("The city of this contacts residence.");
		stateField.setToolTipText("The state of this contacts residence.");
		zipcodeField.setToolTipText("The zip code of this contacts residence.");
		homePhoneField.setToolTipText("The home phone number of this contact.");
		cellPhoneField.setToolTipText("The cell phone number of this contact.");
		workPhoneField.setToolTipText("The work phone number of this contact.");
		faxNumberField.setToolTipText("The fax number of this contact.");
		emailField.setToolTipText("The email address of this contact.");
		notesField.setToolTipText("Additional notes for this contact.");
		changePictureButton.setToolTipText("Choose a new picture for this contact.");
		cancelButton.setToolTipText("Cancels editing this contact and discards all changes made.");
		continueButton.setToolTipText("Changes this contacts information to the information entered.");

		// rename and enable buttons
		cancelButton.setText("Cancel");
		continueButton.setText("Save");
		cancelButton.setEnabled(true);
		continueButton.setEnabled(true);
		changePictureButton.setEnabled(true);
		viewMapButton.setEnabled(false);

		// disable field editing (except notes)
		nameField.setEditable(false);
		streetField.setEditable(true);
		cityField.setEditable(true);
		stateField.setEditable(true);
		zipcodeField.setEditable(true);
		homePhoneField.setEditable(true);
		cellPhoneField.setEditable(true);
		workPhoneField.setEditable(true);
		faxNumberField.setEditable(true);
		emailField.setEditable(true);
		notesField.setEditable(true);

		// have search field request focus
		searchField.requestFocus();

		// populate fields
		selected = (ABPerson) contactList.getSelectedValue();
		nameField.setText(selected.getFirstName() + " " + selected.getMiddleName() + " " + selected.getLastName());
		streetField.setText(selected.getStreet());
		cityField.setText(selected.getCity());
		stateField.setText(selected.getState());
		zipcodeField.setText(selected.getZip());
		homePhoneField.setText(selected.getHomePhone());
		cellPhoneField.setText(selected.getCellPhone());
		workPhoneField.setText(selected.getWorkPhone());
		faxNumberField.setText(selected.getFaxNumber());
		emailField.setText(selected.getEmail());
		notesField.setText(selected.getNotes());
		if (selected.getPicturePath().isEmpty() || !(new File(selected.getPicturePath())).exists())
			pictureLabel.setIcon(resizeImage(ABConstants.defaultPicture));
		else
			pictureLabel.setIcon(resizeImage(new ImageIcon(selected.getPicturePath())));
	}

	/**
	 * [DEVELOPMENT ONLY]
	 * Automatically generates random contacts for the address book.
	 * 
	 * @param threshold the number of entries to generate
	 */
	private static void generate(int threshold)
	{
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
		int initialCount = addressBook.length();
		while (addressBook.length()-initialCount < threshold)
		{
			ABPerson contact = new ABPerson();
			contact.setFirstName(first[rand.nextInt(first.length)]);
			contact.setMiddleName(middle[rand.nextInt(middle.length)]);
			contact.setLastName(last[rand.nextInt(last.length)]);
			contact.setStreet((rand.nextInt(9000) + 1000) + " " + street[rand.nextInt(street.length)] + " " + streetSuffix[rand.nextInt(streetSuffix.length)]);
			contact.setCity(city[rand.nextInt(city.length)]);
			contact.setState(state[rand.nextInt(state.length)]);
			contact.setZip("" + (rand.nextInt(90000) + 10000));
			contact.setHomePhone("(" + (rand.nextInt(900) + 100) + ") " + (rand.nextInt(900) + 100) + " - " + (rand.nextInt(9000) + 1000));
			contact.setCellPhone("(" + (rand.nextInt(900) + 100) + ") " + (rand.nextInt(900) + 100) + " - " + (rand.nextInt(9000) + 1000));
			contact.setWorkPhone("(" + (rand.nextInt(900) + 100) + ") " + (rand.nextInt(900) + 100) + " - " + (rand.nextInt(9000) + 1000));
			contact.setFaxNumber("(" + (rand.nextInt(900) + 100) + ") " + (rand.nextInt(900) + 100) + " - " + (rand.nextInt(9000) + 1000));
			contact.setEmail(emailPrefix[rand.nextInt(emailPrefix.length)] + "@" + emailSite[rand.nextInt(emailSite.length)] + "." + emailSuffix[rand.nextInt(emailSuffix.length)]);
			addressBook.insertNode(contact);
		}
	}
	
	/**
	 * Determines if all contact information fields are empty.
	 * 
	 * @return true if all contact info fields are empty
	 */
	private static boolean isFieldsEmpty()
	{
		return (nameField.getText().isEmpty() && streetField.getText().isEmpty() && cityField.getText().isEmpty()
				&& stateField.getText().isEmpty() && zipcodeField.getText().isEmpty() && homePhoneField.getText().isEmpty()
				&& cellPhoneField.getText().isEmpty() && workPhoneField.getText().isEmpty()
				&& faxNumberField.getText().isEmpty() && emailField.getText().isEmpty() && notesField.getText().isEmpty());
	}
	
	/**
	 * Resizes the ImageIcon to fit the pictureLabel.
	 * 
	 * @param i the ImageIcon to resize
	 * @return a resized ImageIcon
	 */
	private static ImageIcon resizeImage(ImageIcon i)
	{
		if (pictureLabel.getWidth() <= 0 || pictureLabel.getHeight() <= 0)
			return i;
		return new ImageIcon(i.getImage().getScaledInstance(pictureLabel.getWidth(), pictureLabel.getHeight(), Image.SCALE_SMOOTH));
	}

	/**
	 * Switches address book mode to VIEW_MODE.
	 */
	private static void viewMode()
	{
		// update mode
		mode = VIEW_MODE;
		if (modeTabbedPane.getSelectedIndex() != 0)
			modeTabbedPane.setSelectedIndex(0);

		// update tool tip text
		nameField.setToolTipText("The full name of this contact.");
		streetField.setToolTipText("The street address of this contacts residence.");
		cityField.setToolTipText("The city of this contacts residence.");
		stateField.setToolTipText("The state of this contacts residence.");
		zipcodeField.setToolTipText("The zip code of this contacts residence.");
		homePhoneField.setToolTipText("The home phone number of this contact.");
		cellPhoneField.setToolTipText("The cell phone number of this contact.");
		workPhoneField.setToolTipText("The work phone number of this contact.");
		faxNumberField.setToolTipText("The fax number of this contact.");
		emailField.setToolTipText("The email address of this contact.");
		notesField.setToolTipText("Additional notes for this contact.");
		changePictureButton.setToolTipText("Choose a new picture for this contact.");
		cancelButton.setToolTipText("Permanently removes this contact from this address book.");
		continueButton.setToolTipText("Edit this contact.");

		// rename and enable buttons
		cancelButton.setText("Remove Contact");
		continueButton.setText("Edit Contact");
		cancelButton.setEnabled(true);
		continueButton.setEnabled(true);
		changePictureButton.setEnabled(true);
		viewMapButton.setEnabled(true);

		// disable field editing (except notes)
		nameField.setEditable(false);
		streetField.setEditable(false);
		cityField.setEditable(false);
		stateField.setEditable(false);
		zipcodeField.setEditable(false);
		homePhoneField.setEditable(false);
		cellPhoneField.setEditable(false);
		workPhoneField.setEditable(false);
		faxNumberField.setEditable(false);
		emailField.setEditable(false);
		notesField.setEditable(false);

		// have search field request focus
		searchField.requestFocus();

		// populate fields
		if (contactList.isSelectionEmpty())
		{
			clearFields();
			nameField.setText("Select a name on the left to view information!");
			cancelButton.setEnabled(false);
			continueButton.setEnabled(false);
			changePictureButton.setEnabled(false);
			viewMapButton.setEnabled(false);

			// update tool tip text
			nameField.setToolTipText("");
			streetField.setToolTipText("");
			cityField.setToolTipText("");
			stateField.setToolTipText("");
			zipcodeField.setToolTipText("");
			homePhoneField.setToolTipText("");
			cellPhoneField.setToolTipText("");
			workPhoneField.setToolTipText("");
			faxNumberField.setToolTipText("");
			emailField.setToolTipText("");
			notesField.setToolTipText("");
			changePictureButton.setToolTipText("");
			cancelButton.setToolTipText("");
			continueButton.setToolTipText("");
		}
		else
		{
			// store the currently selected contact
			ABPerson contact = (ABPerson) contactList.getSelectedValue();
			
			// update contact information fields
			nameField.setText(contact.getFirstName() + " " + contact.getMiddleName() + " " + contact.getLastName());
			streetField.setText(contact.getStreet());
			cityField.setText(contact.getCity());
			stateField.setText(contact.getState());
			zipcodeField.setText(contact.getZip());
			homePhoneField.setText(contact.getHomePhone());
			cellPhoneField.setText(contact.getCellPhone());
			workPhoneField.setText(contact.getWorkPhone());
			faxNumberField.setText(contact.getFaxNumber());
			emailField.setText(contact.getEmail());
			notesField.setText(contact.getNotes());
			
			// update contact picture display
			if (contact.getPicturePath().isEmpty() || !(new File(contact.getPicturePath())).exists())
				pictureLabel.setIcon(resizeImage(ABConstants.defaultPicture));
			else
				pictureLabel.setIcon(resizeImage(new ImageIcon(contact.getPicturePath())));
		}
	}

	/**
	 * The sub-class EventMgr listens for all application events and fires the
	 * method corresponding to the event that occurs. This is what makes the GUI
	 * interactive!
	 */
	private static final class EventMgr implements ActionListener, AddressBookListener, ChangeListener, ComponentListener, KeyListener, ListSelectionListener
	{
		/**
		 * Handles ActionEvents fired when the cancelButton, continueButton,
		 * changePictureButton, or viewMapButton is pressed.
		 */
		public void actionPerformed(ActionEvent evt)
		{
			// When the cancel button is pressed...
			if (evt.getSource() == cancelButton)
			{
				// if adding a new contact...
				if (mode == ADD_MODE)
					// and user is sure they want to cancel adding, then return to viewing last selected contact
					if (isFieldsEmpty() || JOptionPane.showConfirmDialog(frame, "Are you sure you wish to cancel adding this contact?", "Cancel adding contact...", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
					{
						viewMode();
						modeTabbedPane.setSelectedIndex(0);
					}
					// otherwise, if user doesn't want to cancel, return to adding contact
					else
						nameField.requestFocus();
				
				// if editing the currently selected contact...
				else if (mode == EDIT_MODE)
					// and user is sure they want to cancel adding, then return to viewing contact information
					if (JOptionPane.showConfirmDialog(frame, "Are you sure you wish to cancel editing this contact?", "Cancel editing contact...", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
						viewMode();
					// otherwise, if user doesn't want to cancel, return to editing contact
					else
						streetField.requestFocus();
				// if viewing the currently selected contact...
				else if (mode == VIEW_MODE)
				{
					// and the user is sure they want to remove this contact, then delete it from address book
					ABPerson selected = (ABPerson) contactList.getSelectedValue();
					if (JOptionPane.showConfirmDialog(frame, "Are you sure you wish to permanently remove " + selected.getFirstName() + "?", "Remove contact...", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
					{
						addressBook.deleteNode(selected);
						addressBook.save(new File(ABConstants.userDatabase));
					}
				}
			}
			// When the continue button is pressed...
			else if (evt.getSource() == continueButton)
			{
				// add the new contact to the address book 
				if (mode == ADD_MODE)
				{
					ABPerson contact = new ABPerson();

					String[] name = nameField.getText().split(" ");
					if (name.length == 1)
						contact.setFirstName(name[0]);
					else if (name.length == 2)
					{
						contact.setFirstName(name[0]);
						contact.setLastName(name[1]);
					}
					else if (name.length > 2)
					{
						contact.setFirstName(name[0]);
						contact.setLastName(name[name.length - 1]);
						String middle = "";
						for (int i = 1; i < name.length - 1; i++)
							if (i == name.length - 2)
								middle += name[i];
							else
								middle += name[i] + " ";
						contact.setMiddleName(middle);
					}
					// make sure name is specified
					if (contact.isEmptyName())
					{
						JOptionPane.showMessageDialog(frame, "You must enter a name for this contact!", "Name required...", JOptionPane.WARNING_MESSAGE);
						nameField.requestFocus();
						return;
					}
					// make sure name doesn't already exist
					else if (addressBook.search(new Person(contact.getFirstName(), contact.getMiddleName(), contact.getLastName())))
					{
						JOptionPane.showMessageDialog(frame, "This address book already contains a contact with that name!", "Contact already exists...", JOptionPane.WARNING_MESSAGE);
						nameField.requestFocus();
						return;
					}
					contact.setStreet(streetField.getText());
					contact.setCity(cityField.getText());
					contact.setState(stateField.getText());
					contact.setZip(zipcodeField.getText());
					contact.setHomePhone(homePhoneField.getText());
					contact.setCellPhone(cellPhoneField.getText());
					contact.setWorkPhone(workPhoneField.getText());
					contact.setFaxNumber(faxNumberField.getText());
					contact.setEmail(emailField.getText());
					contact.setNotes(notesField.getText());
					addressBook.insertNode(contact);
					addressBook.save(new File(ABConstants.userDatabase));
					clearFields();
					JOptionPane.showMessageDialog(frame, contact.getFirstName() + " has been successfully added to the address book!", "Contact added...", JOptionPane.INFORMATION_MESSAGE);
				}
				// save the edited contact to the address book
				else if (mode == EDIT_MODE)
				{
					ABPerson contact = new ABPerson();
					contact.setFirstName(selected.getFirstName());
					contact.setMiddleName(selected.getMiddleName());
					contact.setLastName(selected.getLastName());
					contact.setStreet(streetField.getText());
					contact.setCity(cityField.getText());
					contact.setState(stateField.getText());
					contact.setZip(zipcodeField.getText());
					contact.setHomePhone(homePhoneField.getText());
					contact.setCellPhone(cellPhoneField.getText());
					contact.setWorkPhone(workPhoneField.getText());
					contact.setFaxNumber(faxNumberField.getText());
					contact.setEmail(emailField.getText());
					contact.setNotes(notesField.getText());
					contact.setPicturePath(selected.getPicturePath());
					addressBook.deleteNode(selected);
					addressBook.insertNode(contact);
					addressBook.save(new File(ABConstants.userDatabase));
					viewMode();
					for (int i = 0; i < contactList.getModel().getSize(); i++)
						if (((ABPerson)contactList.getModel().getElementAt(i)).equals(contact))
							contactList.setSelectedIndex(i);
					JOptionPane.showMessageDialog(frame, contact.getName() + " has been successfully modified!", "Contact edited...", JOptionPane.INFORMATION_MESSAGE);
				}
				// edit the currently selected contact
				else if (mode == VIEW_MODE)
				{
					editMode();
				}
			}
			// When the change picture button is pressed...
			else if (evt.getSource() == changePictureButton)
			{
				// if a new picture is selected, then update the picture label
				if (pictureChooser.showDialog(frame, "Select") == JFileChooser.APPROVE_OPTION)
				{
					pictureLabel.setIcon(resizeImage(new ImageIcon(pictureChooser.getSelectedFile().getAbsolutePath())));
					
					// if not adding a new contact, then update the selected contacts picture
					if (mode != ADD_MODE)
					{
						ABPerson contact = (ABPerson) contactList.getSelectedValue();
						contact.setPicturePath(pictureChooser.getSelectedFile().getAbsolutePath());
						
						// save address book not in EDIT_MODE
						if (mode != EDIT_MODE)
							addressBook.save(new File(ABConstants.userDatabase));
					}
				}
			}
			// When the view map button is pressed...
			else if (evt.getSource() == viewMapButton)
			{
				try
				{
					ABPerson contact = (ABPerson) contactList.getSelectedValue();
					String url = "http://maps.google.com/maps?q=" + contact.getStreet() + ", " + contact.getCity()
						+ ", " + contact.getState() + ", " + contact.getZip();
					Desktop.getDesktop().browse(URI.create(url.replaceAll(" ", "+")));
				}
				catch (IOException e)
				{
					JOptionPane.showMessageDialog(frame, "Unable to launch map page!", "Error!", JOptionPane.ERROR_MESSAGE);
				} 
			}
		}

		/**
		 * Refreshes the contact list whenever the addressBook is modified.
		 */
		public void addressBookModified()
		{
			contactList.setListData(addressBook.toArray());
		}

		/**
		 * Resizes all components whenever the frame is resized.
		 */
		public void componentResized(ComponentEvent evt)
		{
			if (evt.getComponent() == addressBookPanel)
			{
				Dimension d = addressBookPanel.getSize();
				modeTabbedPane.setBounds(0, 0, d.width, d.height);
			}
			else if (evt.getComponent() == contentPanel)
			{
				Dimension d = contentPanel.getSize();
				searchField.setBounds(0, 0, d.width, 22);
			}
			else if (evt.getSource() == contactInformationPanel)
			{
				Dimension d = contactInformationPanel.getSize();
				informationPanel.setBounds(2, 2, d.width - 4, d.height - 4);
				d = informationPanel.getSize();
				pictureLabel.setBounds(1, 1, 180, 180);
				changePictureButton.setBounds(1, 181, 180, 20);
				cancelButton.setBounds(181, 181, (d.width-182)/2, 20);
				continueButton.setBounds(d.width - ((d.width - 181)/2 + 1), 181, (d.width-182)/2, 20);
				nameLabel.setBounds(181, 1, d.width-182, 30);
				nameField.setBounds(183, 11, d.width-186, 20);
				streetField.setBounds(183, 41, d.width-186, 20);
				streetLabel.setBounds(181, 31, d.width-182, 30);
				cityField.setBounds(183, 71, d.width - (185 + 121), 20);
				cityLabel.setBounds(181, 61, d.width - (181 + 121), 30);
				stateField.setBounds(d.width - 119, 71, 36, 20);
				stateLabel.setBounds(d.width - 121, 61, 40, 30);
				zipcodeField.setBounds(d.width - 79, 71, 76, 20);
				zipcodeLabel.setBounds(d.width - 81, 61, 80, 30);
				homePhoneField.setBounds(183, 101, (d.width-186)/2, 20);
				homePhoneLabel.setBounds(181, 91, (d.width-182)/2, 30);
				cellPhoneField.setBounds(d.width - ((d.width - 181)/2 - 1), 101, (d.width-186)/2, 20);
				cellPhoneLabel.setBounds(d.width - ((d.width - 181)/2 + 1), 91, (d.width-182)/2, 30);
				workPhoneField.setBounds(183, 131, (d.width-186)/2, 20);
				workPhoneLabel.setBounds(181, 121, (d.width-182)/2, 30);
				faxNumberField.setBounds(d.width - ((d.width - 181)/2 - 1), 131, (d.width-186)/2, 20);
				faxNumberLabel.setBounds(d.width - ((d.width - 181)/2 + 1), 121, (d.width-182)/2, 30);
				emailField.setBounds(183, 161, d.width-186, 20);
				emailLabel.setBounds(181, 151, d.width-182, 30);
				notesLabel.setBounds(1, 201, d.width-2, 20);
				notesField.setBounds(1, 221, d.width-2, d.height-243);
				notesScrollPane.setBounds(1, 221, d.width-2, d.height-243);
				viewMapButton.setBounds(1, d.height - 21, d.width - 2, 20);
			}
		}

		/**
		 * Verifies all text field input.
		 */
		public void keyReleased(KeyEvent evt)
		{
			// allow left and right arrow movement
			if (evt.getKeyCode() == KeyEvent.VK_LEFT || evt.getKeyCode() == KeyEvent.VK_RIGHT
					|| evt.getKeyCode() == KeyEvent.VK_KP_LEFT || evt.getKeyCode() == KeyEvent.VK_KP_RIGHT)
				return;
			
			// activate continue button when enter is pressed
			// TODO analyze this code
			if (evt.getKeyCode() == KeyEvent.VK_ENTER && !(evt.getComponent().getParent() instanceof JDialog))
			{
				continueButton.doClick();
				if (mode == ADD_MODE)
					nameField.requestFocus();
				else if (mode == VIEW_MODE)
					streetField.requestFocus();
			}
			
			// activate cancel button when escape is pressed
			if (evt.getKeyCode() == KeyEvent.VK_ESCAPE)
				cancelButton.doClick();
			
			// Development-- Generate entries to specified threshold
			if (evt.getKeyCode() == KeyEvent.VK_G && evt.isShiftDown())
			{
				try
				{
					generate(Integer.parseInt(JOptionPane.showInputDialog(null)));
				}
				catch (Exception e)
				{
					System.out.println("Number not entered...");
				}
				searchField.setText("");
			}
			
			// Development-- reload data file
			if (evt.getKeyCode() == KeyEvent.VK_R && evt.isShiftDown())
			{
				addressBook = new AddressBook();
				addressBook.load(new File(ABConstants.userDatabase));
				searchField.setText("");
			}

			// if search field is used then filter contact list entries
			if (evt.getSource() == searchField)
				switch (letterTabbedPane.getSelectedIndex())
				{
				case 0: contactList.setListData(addressBook.toFilteredArray(searchField.getText().trim())); break;
				default: contactList.setListData(addressBook.getLetter(((char) (letterTabbedPane.getSelectedIndex() + 64))).toFilteredArray(searchField.getText().trim())); break;
				}
			// allow only letters and spaces in name field, automatic capitalization of first letter of each word
			else if (evt.getSource() == nameField)
			{
				String name = nameField.getText();
				String filtered = "";
				boolean capNext = true;

				for (int i = 0; i < name.length(); i++)
				{
					if (name.charAt(i) == ' ' && !capNext)
					{
						filtered += " ";
						capNext = true;
					}
					else if (capNext && Character.isLetter(name.charAt(i)))
					{
						filtered += Character.toUpperCase(name.charAt(i));
						capNext = false;
					}
					else if (Character.isLetter(name.charAt(i)))
						filtered += name.charAt(i);
				}

				nameField.setText(filtered);
			}
			// allow only letters, numbers, and spaces in street field, automatic capitalization of first letter of each word
			else if (evt.getSource() == streetField)
			{
				String street = streetField.getText();
				String filtered = "";
				boolean capNext = true;

				for (int i = 0; i < street.length(); i++)
				{
					if (street.charAt(i) == ' ' && !capNext)
					{
						filtered += " ";
						capNext = true;
					}
					else if (capNext && Character.isLetterOrDigit(street.charAt(i)))
					{
						filtered += Character.toUpperCase(street.charAt(i));
						capNext = false;
					}
					else if (Character.isLetterOrDigit(street.charAt(i)))
						filtered += street.charAt(i);
				}

				streetField.setText(filtered);
			}
			// allow only letters and spaces in city field, automatic capitalization of first letter of each word
			else if (evt.getSource() == cityField)
			{
				String city = cityField.getText();
				String filtered = "";
				boolean capNext = true;

				for (int i = 0; i < city.length(); i++)
				{
					if (city.charAt(i) == ' ' && !capNext)
					{
						filtered += " ";
						capNext = true;
					}
					else if (capNext && Character.isLetter(city.charAt(i)))
					{
						filtered += Character.toUpperCase(city.charAt(i));
						capNext = false;
					}
					else if (Character.isLetter(city.charAt(i)))
						filtered += city.charAt(i);
				}

				cityField.setText(filtered);
			}
			// allow only 2 capital letters in state field
			else if (evt.getSource() == stateField)
			{
				String state = stateField.getText();
				String filtered = "";

				for (int i = 0; i < state.length(); i++)
					if (Character.isLetter(state.charAt(i)) && filtered.length() < 2)
						filtered += Character.toUpperCase(state.charAt(i));

				stateField.setText(filtered);
			}
			// allow only up to 5 numbers for zipcode and an additional 4 numbers for zipcode extension
			else if (evt.getSource() == zipcodeField)
			{
				String zipcode = zipcodeField.getText();
				String filtered = "";

				for (int i = 0; i < zipcode.length(); i++)
					if (Character.isDigit(zipcode.charAt(i)) && filtered.length() < 9)
						filtered += zipcode.charAt(i);

				if (filtered.length() > 5)
					filtered = filtered.substring(0, 5) + " - " + filtered.substring(5, filtered.length());

				zipcodeField.setText(filtered);
			}
			// allow only numbers, numbers are automatically formatted
			else if (evt.getSource() == homePhoneField || evt.getSource() == cellPhoneField
					|| evt.getSource() == workPhoneField || evt.getSource() == faxNumberField)
			{
				String phone = ((JTextField)evt.getSource()).getText();
				String filtered = "";

				for (int i = 0; i < phone.length(); i++)
					if (Character.isDigit(phone.charAt(i)))
						filtered += phone.charAt(i);

				if (filtered.length() > 10)
					filtered = "(" + filtered.substring(0, 3) + ") " + filtered.substring(3, 6) + " - "
					+ filtered.substring(6, 10) + " Ext: " + filtered.substring(10, filtered.length());
				else if (filtered.length() > 7)
					filtered = "(" + filtered.substring(0, 3) + ") " + filtered.substring(3, 6) + " - "
					+ filtered.substring(6, filtered.length());
				else if (filtered.length() > 4)
					filtered = filtered.substring(0, filtered.length()-4) + " - " + filtered.substring(filtered.length()-4, filtered.length());

				((JTextField)evt.getSource()).setText(filtered);
			}
			// allow only letters, numbers, and select other email address characters
			else if (evt.getSource() == emailField)
			{
				String email = emailField.getText();
				String filtered = "";

				for (int i = 0; i < email.length(); i++)
					if (Character.isLetterOrDigit(email.charAt(i)) || email.charAt(i) == '.' || email.charAt(i) == '@'
						|| email.charAt(i) == '_' || email.charAt(i) == '-' || email.charAt(i) == '~' || email.charAt(i) == '#'
							|| email.charAt(i) == ':')
					{
						filtered += email.charAt(i);
					}

				emailField.setText(filtered);
			}
		}

		/**
		 * Handles changing letter and mode tabs.
		 */
		public void stateChanged(ChangeEvent evt)
		{
			// When mode is changed...
			if (evt.getSource() == modeTabbedPane)
			{
				// if VIEW_MODE tab is selected....
				if (modeTabbedPane.getSelectedIndex() == 0)
				{
					// and user is adding a contact
					if (mode == ADD_MODE && !isFieldsEmpty())
					{
						// and they want to cancel adding contact, switch to view mode
						if (JOptionPane.showConfirmDialog(frame, "Are you sure you wish to cancel adding this contact?", "Cancel adding contact...", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
							viewMode();
						// otherwise continue adding contact
						else
						{
							modeTabbedPane.setSelectedIndex(1);
							nameField.requestFocus();
						}
					}
					// and user is not adding a contact, switch to view mode
					else
					{
						viewMode();
					}
				}
				// if ADD_MODE tab is selected...
				else
				{
					// and user is not currently adding a contact, then add new contact
					if (mode != ADD_MODE)
					{
						addMode();
						nameField.requestFocus();
					}
				}
			}
			// When letter tab is changed...
			else
			{
				// if all is selected, then show all names after search filter is applied
				// otherwise, show only contacts whose listing name starts with the chosen letter
				switch (letterTabbedPane.getSelectedIndex())
				{
				case 0: contactList.setListData(addressBook.toFilteredArray(searchField.getText().trim())); break;
				default: contactList.setListData(addressBook.getLetter(((char) (letterTabbedPane.getSelectedIndex() + 64))).toFilteredArray(searchField.getText().trim())); break;
				}
			}
		}

		/**
		 * Refreshes contact information panel when a contact is selected in the contact list.
		 */
		public void valueChanged(ListSelectionEvent evt)
		{
			if ((modeTabbedPane.getSelectedIndex() == 0 || isFieldsEmpty()) && mode != EDIT_MODE)
			{
				viewMode();
			}
			else if (mode == EDIT_MODE && !contactList.isSelectionEmpty())
			{
				if (!selected.equals((ABPerson) contactList.getSelectedValue()))
				{
					if (JOptionPane.showConfirmDialog(frame, "Are you sure you wish to cancel editing this contact?", "Cancel editing contact...", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
						viewMode();
					else
						contactList.setSelectedValue(selected, true);
				}
			}
			else if (mode == ADD_MODE)
				if (JOptionPane.showConfirmDialog(frame, "Are you sure you wish to cancel adding this contact?", "Cancel adding contact...", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
					viewMode();
				else
				{
					modeTabbedPane.setSelectedIndex(1);
					nameField.requestFocus();
				}
		}

		//---
		// Unused Listener Methods
		public void componentHidden(ComponentEvent evt) {}
		public void componentMoved(ComponentEvent evt) {}
		public void componentShown(ComponentEvent evt) {}
		public void keyPressed(KeyEvent evt) {}
		public void keyTyped(KeyEvent evt) {}
		//---
	}
}