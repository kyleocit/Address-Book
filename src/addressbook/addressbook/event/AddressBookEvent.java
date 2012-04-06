package addressbook.addressbook.event;

import java.util.EventObject;

import addressbook.addressbook.Contact;

/**
 * Stores information pertaining to a triggered address book event.
 * 
 * @author Kyle Campbell (kjcampbell.317@gmail.com)
 * @since 1.1
 */
public class AddressBookEvent extends EventObject
{
	/**
	 * Explicitly set class version unique id to prevent serialization errors.
	 * 
	 * @since 1.1
	 */
	private static final long serialVersionUID = 4197155004165402230L;

	/**
	 * Stores the contact information for the contact to which this event
	 * pertains.
	 * 
	 * @since 1.1
	 */
	protected Contact contact;
	
	/**
	 * Creates a new address book event.
	 * 
	 * @param source the object that triggered this event
	 * @param contact the contact pertaining to this event
	 * @since 1.1
	 */
	public AddressBookEvent(Object source, Contact contact)
	{
		super(source);
		this.contact = contact;
	}

	/**
	 * Gets the contact information for the contact to which this event
	 * pertains.
	 * 
	 * @since 1.1
	 */
	public Contact getContact()
	{
		return contact;
	}
}