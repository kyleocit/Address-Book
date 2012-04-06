package addressbook.addressbook;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Stores a contact's database id number and their listing for use in JList.
 * 
 * @author Kyle Campbell (kjcampbell.317@gmail.com)
 * @since 1.1
 */
public class Listing
{
	/**
	 * Stores the contact's id number. This is required for updating contact
	 * information within the database.
	 * 
	 * @since 1.1
	 */
	protected int id;

	/**
	 * Stores the contact's listing.
	 * 
	 * @since 1.1
	 */
	protected String listing;

	/**
	 * Creates a new empty listing.
	 * 
	 * @since 1.1
	 */
	protected Listing()
	{
		id = 0;
		listing = "";
	}

	/**
	 * Creates a new contact listing holding a contact's id number
	 * and listing name.
	 * <p>
	 * This constructor should ONLY be used by the address book!
	 * 
	 * @param dbrs the contact's information fresh from the database
	 * @throws SQLException thrown when column label or requested type is invalid
	 * @since 1.1
	 */
	public Listing(ResultSet dbrs) throws SQLException
	{
		id = dbrs.getInt("id");
		listing = dbrs.getString("listing");
	}

	/**
	 * Gets this listing's id number.
	 * 
	 * @return contact's id number
	 * @since 1.1
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * Gets this contact's listing.
	 * 
	 * @return current listing
	 * @since 1.1
	 */
	public String toString()
	{
		return listing;
	}
}