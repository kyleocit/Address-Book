package addressbook.addressbook.event;

import java.util.EventListener;

/**
 * All classes that implement this interface may register for event
 * notifications with the AddressBook class via the method:
 * someAddressBookInstance.addAddressBookListener(myEventManager);
 * 
 * @author Kyle Campbell (kjcampbell.317@gmail.com)
 * @since 1.1
 */
public interface AddressBookListener extends EventListener
{
	/**
	 * Notifies all registered listeners when a contact is added to the
	 * address book.
	 * 
	 * @since 1.1
	 */
	public void contactAdded(AddressBookEvent evt);

	/**
	 * Notifies all registered listeners when a contact is modified in the
	 * address book.
	 * 
	 * @since 1.1
	 */
	public void contactModified(AddressBookEvent evt);

	/**
	 * Notifies all registered listeners when a contact is removed from the
	 * address book.
	 * 
	 * @since 1.1
	 */
	public void contactRemoved(AddressBookEvent evt);
}