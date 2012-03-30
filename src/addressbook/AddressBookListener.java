package addressbook;
/*
 * @(#)AddressBookListener.java	1.0 10/10/21
 *
 * Written by Kyle Campbell.
 *
 * UML Diagram
 * --------------------------------------------------
 *                AddressBookListener
 * --------------------------------------------------
 *  (no fields)
 * --------------------------------------------------
 *  +addressBookModified(): void
 * --------------------------------------------------
 * 
 * Change Log
 * v1.0
 *  - initial release
 */
public interface AddressBookListener
{
	//Fires this method on classes that implement this interface when the
	//AddressBook being listened to is modified
	public void addressBookModified();
}