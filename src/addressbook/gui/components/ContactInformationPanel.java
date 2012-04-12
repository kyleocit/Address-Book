package addressbook.gui.components;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import addressbook.addressbook.Contact;
import addressbook.gui.StyleConstants;
import addressbook.gui.purifiers.*;

/**
 * Creates a new contact information panel for displaying a contacts
 * details. The sub-components of this panel are dynamic and will
 * automatically expand or contract to fit this panels dimensions.
 * <p>
 * In order to display the information of a contact you need to call
 * the setContact(Contact) method.
 * <p>
 * To clear all information from this form, simply call the clear()
 * method.
 *
 * @author Kyle Campbell (kjcampbell.317@gmail.com)
 * @since 1.1
 */
public class ContactInformationPanel extends JPanel implements ComponentListener, ActionListener
{
	/**
	 * Explicitly set class version unique id to prevent serialization errors.
	 * 
	 * @since 1.1
	 */
	private static final long serialVersionUID = 8706667470302683014L;

	/**
	 * Whether or not this panels input fields can be edited.
	 * 
	 * @since 1.1
	 */
	protected boolean fieldsWritable;

	/**
	 * Stores the contact to display information for.
	 * 
	 * @since 1.1
	 */
	protected Contact contact;

	/**
	 * Stores the picture of the current contact.
	 * 
	 * @since 1.1
	 */
	protected ContactPicturePanel picture;

	/**
	 * Stores the name of current contact.
	 *
	 * @since 1.1
	 */
	protected LabeledTextField name;

	/**
	 * Stores the address of the current contact.
	 *
	 * @since 1.1
	 */
	protected LabeledTextField street, city, state, zipcode;

	/**
	 * Stores the phone/fax numbers of the current contact.
	 *
	 * @since 1.1
	 */
	protected LabeledTextField homePhone, cellPhone, workPhone, faxNumber;

	/**
	 * Stores the email address of the current contact
	 *
	 * @since 1.1
	 */
	protected LabeledTextField email;

	/**
	 * Cancels the current action and returns the form elements to read-only
	 * state.
	 *
	 * @since 1.1
	 */
	protected JButton cancelButton;

	/**
	 * Confirms the current action and returns the form elements to read-only
	 * state.
	 *
	 * @since 1.1
	 */
	protected JButton actionButton;

	/**
	 * Stores the user's notes associated with the current contact.
	 *
	 * @since 1.1
	 */
	protected LabeledTextArea notes;

	/**
	 * Launches Google Maps on the user's default browser pointed to the
	 * current contact's address.
	 *
	 * @since 1.1
	 */
	protected JButton viewMap;

	/**
	 * Creates a new instance of this class and sets up all the sub-components
	 * used to display a user-friendly contact information form.
	 * 
	 * @since 1.1
	 */
	public ContactInformationPanel()
	{
		//---
		// Reusable Purifiers
		//---
		Purifier namePurifier = new NamePurifier();
		Purifier streetPurifier = new StreetPurifier();
		Purifier statePurifier = new StatePurifier();
		Purifier zipcodePurifier = new ZipCodePurifier();
		Purifier phonePurifier = new PhoneNumberPurifier();
		Purifier emailPurifier = new EmailAddressPurifier();

		//---
		// Component's Setup
		//---
		picture = new ContactPicturePanel();
		picture.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, StyleConstants.CI_BORDER_COLOR));
		this.add(picture);

		name = new LabeledTextField("Name");
		name.addPurifier(namePurifier);
		name.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, StyleConstants.CI_BORDER_COLOR));
		this.add(name);

		street = new LabeledTextField("Street");
		street.addPurifier(streetPurifier);
		street.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, StyleConstants.CI_BORDER_COLOR));
		this.add(street);

		city = new LabeledTextField("City");
		city.addPurifier(namePurifier);
		city.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, StyleConstants.CI_BORDER_COLOR));
		this.add(city);

		state = new LabeledTextField("State");
		state.addPurifier(statePurifier);
		state.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, StyleConstants.CI_BORDER_COLOR));
		this.add(state);

		zipcode = new LabeledTextField("Zip Code");
		zipcode.addPurifier(zipcodePurifier);
		zipcode.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, StyleConstants.CI_BORDER_COLOR));
		this.add(zipcode);

		homePhone = new LabeledTextField("Home Phone");
		homePhone.addPurifier(phonePurifier);
		homePhone.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, StyleConstants.CI_BORDER_COLOR));
		this.add(homePhone);

		cellPhone = new LabeledTextField("Cell Phone");
		cellPhone.addPurifier(phonePurifier);
		cellPhone.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, StyleConstants.CI_BORDER_COLOR));
		this.add(cellPhone);

		workPhone = new LabeledTextField("Work Phone");
		workPhone.addPurifier(phonePurifier);
		workPhone.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, StyleConstants.CI_BORDER_COLOR));
		this.add(workPhone);

		faxNumber = new LabeledTextField("Fax Number");
		faxNumber.addPurifier(phonePurifier);
		faxNumber.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, StyleConstants.CI_BORDER_COLOR));
		this.add(faxNumber);

		email = new LabeledTextField("Email Address");
		email.addPurifier(emailPurifier);
		email.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, StyleConstants.CI_BORDER_COLOR));
		this.add(email);

		cancelButton = new JButton("Delete Contact");
		cancelButton.addActionListener(this);
		cancelButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, StyleConstants.CI_BORDER_COLOR));
		this.add(cancelButton);

		actionButton = new JButton("Edit Contact");
		actionButton.addActionListener(this);
		actionButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, StyleConstants.CI_BORDER_COLOR));
		this.add(actionButton);

		notes = new LabeledTextArea("Contact Notes");
		this.add(notes);

		viewMap = new JButton("View Map");
		viewMap.addActionListener(this);
		viewMap.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, StyleConstants.CI_BORDER_COLOR));
		this.add(viewMap);

		this.addComponentListener(this);
		this.setBackground(StyleConstants.CI_BACKGROUND_COLOR);
		this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, StyleConstants.CI_BORDER_COLOR));
		this.setLayout(null);

		this.setEditable(false); // read-only by default
	}

	/**
	 * Clears the contact information panel of data and unsets the current
	 * contact.
	 * 
	 * @since 1.1
	 */
	public void clear()
	{
		name.clear();
		street.clear();
		city.clear();
		state.clear();
		zipcode.clear();
		homePhone.clear();
		cellPhone.clear();
		workPhone.clear();
		faxNumber.clear();
		email.clear();
		notes.clear();
	}

	/**
	 * Checks if the contact information form is writable.
	 * 
	 * @return true if it is, else false
	 * @since 1.1
	 */
	public boolean isEditable()
	{
		return fieldsWritable;
	}

	/**
	 * Sets the current contact to use for displaying contact information.
	 * <p>
	 * If the for is in read-only mode it will be updated immediately.
	 * 
	 * @param contact contact to display
	 * @since 1.1
	 */
	public void setContact(Contact contact)
	{
		// if no contact then return
		if (contact == null)
			return;

		this.contact = contact;
		
		if (!isEditable())
			reset();
	}
	
	/**
	 * Permits or revokes permission to edit the contents inside of this panels
	 * fields. If the passed argument is true, then all fields will become
	 * writable and the buttons' text will change to reflect saving or canceling
	 * changes. However, if false is passed then the fields will all become
	 * read-only and the buttons' text will change to reflect deleting or
	 * editing the contact information.
	 * 
	 * @param fieldsWritable whether or not the input fields are writable
	 * @since 1.1
	 */
	public void setEditable(boolean fieldsWritable)
	{
		this.fieldsWritable = fieldsWritable;
		
		if (fieldsWritable)
		{
			name.setEditable(true);
			street.setEditable(true);
			city.setEditable(true);
			state.setEditable(true);
			zipcode.setEditable(true);
			homePhone.setEditable(true);
			cellPhone.setEditable(true);
			workPhone.setEditable(true);
			faxNumber.setEditable(true);
			email.setEditable(true);
			cancelButton.setText("Cancel");
			actionButton.setText("Save Contact");

			name.requestFocusInWindow();
		}
		else
		{
			name.setEditable(false);
			street.setEditable(false);
			city.setEditable(false);
			state.setEditable(false);
			zipcode.setEditable(false);
			homePhone.setEditable(false);
			cellPhone.setEditable(false);
			workPhone.setEditable(false);
			faxNumber.setEditable(false);
			email.setEditable(false);
			cancelButton.setText("Delete Contact");
			actionButton.setText("Edit Contact");
		}
	}

	/**
	 * Resets the contact information data to the data of the current contact.
	 * 
	 * @since 1.1
	 */
	public void reset()
	{
		name.setText(contact.getName());
		street.setText(contact.getStreet());
		city.setText(contact.getCity());
		state.setText(contact.getState());
		zipcode.setText(contact.getZipcode());
		homePhone.setText(contact.getHomePhone());
		cellPhone.setText(contact.getCellPhone());
		workPhone.setText(contact.getWorkPhone());
		faxNumber.setText(contact.getFaxNumber());
		email.setText(contact.getEmail());
		notes.setText(contact.getNotes());
	}

	/**
	 * Resizes all sub-components to fit this panel whenever it is resized.
	 * 
	 * @param e low-level event which indicates component has changed size
	 * @since 1.1
	 */
	@Override public void componentResized(ComponentEvent e)
	{
		Dimension d = this.getSize();
		picture.setBounds(1, 1, 180, 200);
		cancelButton.setBounds(181, 181, (d.width-182)/2, 20);
		actionButton.setBounds(d.width - ((d.width - 181)/2 + 1), 181, (d.width-182)/2, 20);
		name.setBounds(181, 1, d.width-182, 30);
		street.setBounds(181, 31, d.width-182, 30);
		city.setBounds(181, 61, d.width - (181 + 121), 30);
		state.setBounds(d.width - 121, 61, 40, 30);
		zipcode.setBounds(d.width - 81, 61, 80, 30);
		homePhone.setBounds(181, 91, (d.width-182)/2, 30);
		cellPhone.setBounds(d.width - ((d.width - 181)/2 + 1), 91, (d.width-182)/2, 30);
		workPhone.setBounds(181, 121, (d.width-182)/2, 30);
		faxNumber.setBounds(d.width - ((d.width - 181)/2 + 1), 121, (d.width-182)/2, 30);
		email.setBounds(181, 151, d.width-182, 30);
		notes.setBounds(1, 201, d.width-2, d.height-223);
		viewMap.setBounds(1, d.height - 21, d.width - 2, 20);
	}

	//---
	// Unused Listener Methods
	@Override public void componentMoved(ComponentEvent e) {}
	@Override public void componentShown(ComponentEvent e) {}
	@Override public void componentHidden(ComponentEvent e) {}
	//---

	@Override public void actionPerformed(ActionEvent e)
	{
		System.out.println("action performed");
		if (e.getSource() == cancelButton)
		{
			if (fieldsWritable && JOptionPane.showConfirmDialog(this, "Are you sure you wish to cancel without saving this contact?", "Cancel operation...", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			{
				this.reset();
				this.requestFocus();
			}
		}
	}
}