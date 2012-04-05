package addressbook.addressbook;
/*
 * @(#)AddressBook.java	1.0 10/10/21
 *
 * Written by Kyle Campbell.
 *
 * UML Diagram
 * --------------------------------------------------
 *                   AddressBook
 * --------------------------------------------------
 *  -delimiter: String
 *  -emptyToken: String
 *  -lineToken: String
 *  -listeners: Vector<AddressBookListener>
 * --------------------------------------------------
 *  +AddressBook()
 *  +AddressBook(File)
 *  +addEventListener(AddressBookListener): void
 *  +contains(ABPerson): boolean
 *  +deleteNode(ABPerson): void
 *  +getLetter(Character): AddressBook
 *  +initializeList(): void
 *  +insertFirst(ABPerson): void
 *  +inserLast(ABPerson): void
 *  +insertNode(ABPerson): void
 *  +load(File): void
 *  +save(File): void
 *  +toFilteredArray(String): ABPerson[]
 *  +toArray(): ABPerson[]
 *  -notifyListeners(): void
 * --------------------------------------------------
 * 
 * Change Log
 * v1.0
 *  - initial release
 */

import java.sql.*;
import java.util.LinkedList;
import java.util.Vector;

import addressbook.addressbook.event.AddressBookListener;

import org.h2.jdbcx.JdbcDataSource;

/**
 * An address book for storing and retrieving contact information. Each
 * instance connects to a local Hypersonic2 database located in the user's
 * home directory called 'address-book'. This database contains a table
 * called 'contacts' which is used for storing and retrieving contact
 * information. Each row represents a single person, business, or entity.
 * <p>
 * If the database does not already exist (e.g. first run), then it will
 * automatically be created with the proper schema.
 * 
 * @author Kyle Campbell (kjcampbell.317@gmail.com)
 * @since 1.1
 */
public class AddressBook extends LinkedList<Contact>
{
	protected Vector<AddressBookListener> listeners;

	public AddressBook()
	{
		// initialize address book listeners vector
		listeners = new Vector<AddressBookListener>();

		// connect to address book database in users home directory
		JdbcDataSource ds = new JdbcDataSource();
		ds.setURL("jdbc:h2:˜/address-book");
		ds.setUser("address-book");
		// TODO allow the user to set a password
		ds.setPassword("address-book");

		// attempt to connect to database and retrieve data
		try
		{
			Connection db = ds.getConnection();

			// verify structure integrity
			ResultSet metadata = db.getMetaData().getTables(db.getCatalog(), null, "contacts", null);
			if (metadata.first())
			{
				// verify table integrity
				ResultSetMetaData cdata = metadata.getMetaData();
			}
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	//Registers object to be notified when this address book is modified
	public void addEventListener(AddressBookListener listener)
	{
		listeners.add(listener);
	}
	
	//Fires addressBookModified on all listeners when the address book is modified
	private void notifyListeners()
	{
		for (int i = 0; i < listeners.size(); i++)
			listeners.elementAt(i).addressBookModified();
	}
}