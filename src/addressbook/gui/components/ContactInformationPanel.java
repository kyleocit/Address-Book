package addressbook.gui.components;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import addressbook.gui.StyleConstants;

/**
 * UML Diagram
 *
 * --------------------------------------------------
 *              ContactInformationPanel
 * --------------------------------------------------
 *  #picture: ContactPicturePanel
 *  #name: LabeledTextField
 *  #street: LabeledTextField
 *  #city: LabeledTextField
 *  #state: LabeledTextField
 *  #zipcode: LabeledTextField
 *  #homePhone: LabeledTextField
 *  #cellPhone: LabeledTextField
 *  #workPhone: LabeledTextField
 *  #faxNumber: LabeledTextField
 *  #email: LabeledTextField
 *  #cancelButton: JButton
 *  #actionButton: JButton
 *  #notes: LabeledTextArea
 *  #viewMap: JButton
 * --------------------------------------------------
 *  +ContactInformationPanel()
 *  +componentResized(ComponentEvent): void
 *  +componentMoved(ComponentEvent): void
 *  +componentShown(ComponentEvent): void
 *  +componentHidden(ComponentEvent): void
 * --------------------------------------------------
 */

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
public class ContactInformationPanel extends JPanel implements ComponentListener
{
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
		picture = new ContactPicturePanel();
		picture.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, StyleConstants.CI_BORDER_COLOR));
		this.add(picture);

		name = new LabeledTextField("Name");
		name.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, StyleConstants.CI_BORDER_COLOR));
		this.add(name);

		street = new LabeledTextField("Street");
		street.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, StyleConstants.CI_BORDER_COLOR));
		this.add(street);

		city = new LabeledTextField("City");
		city.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, StyleConstants.CI_BORDER_COLOR));
		this.add(city);

		state = new LabeledTextField("State");
		state.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, StyleConstants.CI_BORDER_COLOR));
		this.add(state);

		zipcode = new LabeledTextField("Zip Code");
		zipcode.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, StyleConstants.CI_BORDER_COLOR));
		this.add(zipcode);

		homePhone = new LabeledTextField("Home Phone");
		homePhone.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, StyleConstants.CI_BORDER_COLOR));
		this.add(homePhone);

		cellPhone = new LabeledTextField("Cell Phone");
		cellPhone.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, StyleConstants.CI_BORDER_COLOR));
		this.add(cellPhone);

		workPhone = new LabeledTextField("Work Phone");
		workPhone.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, StyleConstants.CI_BORDER_COLOR));
		this.add(workPhone);

		faxNumber = new LabeledTextField("Fax Number");
		faxNumber.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, StyleConstants.CI_BORDER_COLOR));
		this.add(faxNumber);

		email = new LabeledTextField("Email Address");
		email.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, StyleConstants.CI_BORDER_COLOR));
		this.add(email);

		cancelButton = new JButton("Delete Contact");
		cancelButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, StyleConstants.CI_BORDER_COLOR));
		this.add(cancelButton);

		actionButton = new JButton("Edit Contact");
		actionButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, StyleConstants.CI_BORDER_COLOR));
		this.add(actionButton);

		notes = new LabeledTextArea("Contact Notes");
		this.add(notes);

		viewMap = new JButton("View Map");
		viewMap.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, StyleConstants.CI_BORDER_COLOR));
		this.add(viewMap);

		this.addComponentListener(this);
		this.setBackground(StyleConstants.CI_BACKGROUND_COLOR);
		this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, StyleConstants.CI_BORDER_COLOR));
		this.setLayout(null);
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
}