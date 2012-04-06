package addressbook.addressbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.event.EventListenerList;

import addressbook.addressbook.event.AddressBookEvent;
import addressbook.addressbook.event.AddressBookListener;

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
public class AddressBook
{
	/**
	 * Stores the database connection object.
	 * 
	 * @since 1.1
	 */
	protected Connection database;

	/**
	 * Stores a list of registered event listeners.
	 * 
	 * @since 1.1
	 */
	protected EventListenerList listenerList;

	/**
	 * Creates a new AddressBook object that interfaces with a local H2
	 * database located in the users home directory. If the database does
	 * not already exist, it will be created automatically.
	 * 
	 * @since 1.1
	 */
	public AddressBook()
	{
		// initialize address book listeners vector
		listenerList = new EventListenerList();

		// attempt to connect (print error code)
		System.out.println(this.connect());

		// if database could not be connected to then don't bother continuing
		if (!this.isAvailable())
			return;

		// connect to the local address book database
		try
		{
			// create statement for executing queries
			Statement st = database.createStatement();

			// verify structure integrity
			ResultSet tables = st.executeQuery("show tables");

			// search for the contacts table
			while (tables.next())
			{
				if (tables.getString(1).equalsIgnoreCase("contacts"))
				{
					return;
				}
			}
			
			// no contacts table? make one
			st.execute("create table contacts(id integer auto_increment primary key, listing varchar(256), name varchar(256), street varchar(256), city varchar(40), state varchar(2), zipcode varchar(11), homePhone varchar(30), cellPhone varchar(30), workPhone varchar(30), faxNumber varchar(30), email varchar(256), notes varchar(3000))");
		}
		catch (SQLException e)
		{
			// wrong password?
			System.out.println(e.getErrorCode());
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds a new contact to the address book.
	 * <p>
	 * Adds the contact to the address book database, then to the list itself.
	 * 
	 * @param c the contact to add
	 * @return true if successful else false
	 * @since 1.1
	 */
	public boolean add(Contact c)
	{
		// if the database isn't available then this operation fails
		if (!isAvailable())
			return false;

		// attempt to add the contact to the database
		try
		{
			database.createStatement().execute("insert into contacts (listing, name, street, city, state, zipcode, homePhone, cellPhone, workPhone, faxNumber, email, notes)"
					+ " values ('" + c.toString() + "', '" + c.getName() + "', '" + c.getStreet() + "', '" + c.getCity() + "', '" + c.getState() + "', '" + c.getZipcode() + "', '" + c.getHomePhone() + "', '" + c.getCellPhone() + "', '" + c.getWorkPhone() + "', '" + c.getFaxNumber() +"', '" + c.getEmail() + "', '" + c.getNotes() + "')");
			fireContactAddedEvent(new AddressBookEvent(this, c));
			return true; // successfully added
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false; // add failed
		}
	}

	/**
	 * Gets the contact information of the contact with the specified id
	 * number.
	 * 
	 * @param id the id number of the contact to lookup
	 * @return the contact's information
	 * @throws SQLException thrown when the id number is invalid
	 */
	public Contact getById(int id) throws SQLException
	{
		return new Contact(database.createStatement().executeQuery("select * from contacts where id='" + id + "'"));
	}

	/**
	 * Gets a managed array of listings for all the contacts in the database.
	 */
	public Vector<Listing> getListings()
	{
		Vector<Listing> v = new Vector<Listing>();
		
		// attempt to populate vector
		if (isAvailable())
		{
			try
			{
				ResultSet result = database.createStatement().executeQuery("select id, listing from contacts order by listing");
				while (result.next())
				{
					v.add(new Listing(result));
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		// return vector
		return v;
	}

	/**
	 * Alias of connect(String) that supplies an empty password.
	 * 
	 * @return error code or 0 if no error occurred
	 * @since 1.1
	 */
	protected int connect()
	{
		return this.connect("");
	}
	
	/**
	 * Attempts to connect to the local address book database.
	 * 
	 * @param password the password to use this database
	 * @return error code or 0 if no error occurred
	 * @since 1.1
	 */
	protected int connect(String password)
	{
		// connect to the local address book database
		try
		{
			this.database = DriverManager.getConnection("jdbc:h2:file:~/address-book/address-book", "address-book", password);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return e.getErrorCode();
		}

		return 0;
	}

	/**
	 * Verify that the address book can connect to the local database.
	 * 
	 * @return true if database is accessible, else false
	 */
	public boolean isAvailable()
	{
		try
		{
			return database != null && database.isValid(0);
		}
		catch (SQLException e)
		{
			return false;
		}
	}

	/**
	 * Registers object to be notified when this address book is modified.
	 * 
	 * @param listener the object to register
	 * @since 1.1
	 */
	public void addAddressBookListener(AddressBookListener listener)
	{
		listenerList.add(AddressBookListener.class, listener);
	}

	/**
	 * Unregisters object from being notified when this address book is modified.
	 * 
	 * @param listener the object to unregister
	 * @since 1.1
	 */
	public void removeAddressBookListener(AddressBookListener listener)
	{
		listenerList.remove(AddressBookListener.class, listener);
	}

	/**
	 * Notifies all listeners that a contact has been added to the address
	 * book.
	 * 
	 * @param evt event containing contact information for added contact
	 * @since 1.1
	 */
	void fireContactAddedEvent(AddressBookEvent evt)
	{
		Object[] listeners = listenerList.getListenerList();

		// each listener occupies two elements - listener class, listener instance
		for (int i = 0; i < listeners.length; i += 2)
		{
			if (listeners[i] == AddressBookListener.class)
			{
				((AddressBookListener)listeners[i+1]).contactAdded(evt);
			}
		}
	}

	/**
	 * Notifies all listeners that a contact has been modified in the address
	 * book.
	 * 
	 * @param evt event containing the updated contact information for modified contact
	 * @since 1.1
	 */
	void fireContactModifiedEvent(AddressBookEvent evt)
	{
		Object[] listeners = listenerList.getListenerList();

		// each listener occupies two elements - listener class, listener instance
		for (int i = 0; i < listeners.length; i += 2)
		{
			if (listeners[i] == AddressBookListener.class)
			{
				((AddressBookListener)listeners[i+1]).contactModified(evt);
			}
		}
	}

	/**
	 * Notifies all listeners that a contact has been removed from the address
	 * book.
	 * 
	 * @param evt event containing contact information for removed contact
	 * @since 1.1
	 */
	void fireContactRemovedEvent(AddressBookEvent evt)
	{
		Object[] listeners = listenerList.getListenerList();

		// each listener occupies two elements - listener class, listener instance
		for (int i = 0; i < listeners.length; i += 2)
		{
			if (listeners[i] == AddressBookListener.class)
			{
				((AddressBookListener)listeners[i+1]).contactRemoved(evt);
			}
		}
	}
}