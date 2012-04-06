package addressbook.addressbook;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Stores contact information for one person, business or entity.
 * 
 * @author Kyle Campbell (kjcampbell.317@gmail.com)
 * @since 1.1
 */
public class Contact extends Listing
{
	/**
	 * Stores the contact's full name.
	 * 
	 * @since 1.1
	 */
	protected String name;

	/**
	 * Stores the contact's address.
	 * 
	 * @since 1.1
	 */
	protected String street, city, state, zipcode;

	/**
	 * Stores the contact's phone and fax numbers.
	 * 
	 * @since 1.1
	 */
	protected String homePhone, cellPhone, workPhone, faxNumber;
	
	/**
	 * Stores the contact's email address.
	 * 
	 * @since 1.1
	 */
	protected String email;
	
	/**
	 * Stores the user's contact-related notes.
	 * 
	 * @since 1.1
	 */
	protected String notes;

	/**
	 * Creates a new contact to store a person, business, or entities
	 * contact information.
	 * 
	 * @since 1.1
	 */
	public Contact()
	{
		name = "";
		street = "";
		city = "";
		state = "";
		zipcode = "";
		homePhone = "";
		cellPhone =	"";
		workPhone = "";
		faxNumber =	"";
		email = "";
		notes = "";
	}

	/**
	 * Creates a new contact to store a person, business, or entities
	 * contact information from a row in the address book's database.
	 * <p>
	 * This constructor should ONLY be used by the address book!
	 * 
	 * @param dbrs the contact's information fresh from the database
	 * @throws SQLException thrown when column label or requested type is invalid
	 * @since 1.1
	 */
	public Contact(ResultSet dbrs) throws SQLException
	{
		super(dbrs);
		name = dbrs.getString("name");
		street = dbrs.getString("street");
		city = dbrs.getString("city");
		state = dbrs.getString("state");
		zipcode = dbrs.getString("zipcode");
		homePhone = dbrs.getString("homePhone");
		cellPhone =	dbrs.getString("cellPhone");
		workPhone = dbrs.getString("workPhone");
		faxNumber =	dbrs.getString("faxNumber");
		email = dbrs.getString("email");
		notes = dbrs.getString("notes");
	}
	
	/**
	 * Gets this contact's name.
	 * 
	 * @return the current name
	 * @since 1.1
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets this contact's name to the specified string.
	 * 
	 * @param name new name
	 * @since 1.1
	 */
	public void setName(String name)
	{
		this.name = name;
		
		// set listing: Lastname, Firstname Middlename...
		listing = name.trim().replaceAll("  ", " ");
		
		if (listing.contains(" "))
			listing = listing.substring(listing.lastIndexOf(" ") + 1) + ", " + listing.substring(0, listing.lastIndexOf(" "));
	}

	/**
	 * Gets this contact's street address.
	 * 
	 * @return current street address
	 * @since 1.1
	 */
	public String getStreet()
	{
		return street;
	}

	/**
	 * Sets this contact's street address to the specified string.
	 * 
	 * @param street new street address
	 * @since 1.1
	 */
	public void setStreet(String street)
	{
		this.street = street;
	}

	/**
	 * Gets this contact's city address.
	 * 
	 * @return current city address
	 * @since 1.1
	 */
	public String getCity()
	{
		return city;
	}

	/**
	 * Sets this contact's city address to the specified string.
	 * 
	 * @param city new city address
	 * @since 1.1
	 */
	public void setCity(String city)
	{
		this.city = city;
	}

	/**
	 * Gets this contact's state address.
	 * 
	 * @return current state address
	 * @since 1.1
	 */
	public String getState()
	{
		return state;
	}

	/**
	 * Sets this current state address to the specified string.
	 * 
	 * @param state new state address
	 * @since 1.1
	 */
	public void setState(String state)
	{
		this.state = state;
	}

	/**
	 * Gets this contact's ZIP code address.
	 * 
	 * @return current zip code address
	 * @since 1.1
	 */
	public String getZipcode()
	{
		return zipcode;
	}

	/**
	 * Sets this contact's ZIP code to the specified string.
	 * 
	 * @param zipcode new ZIP code address
	 * @since 1.1
	 */
	public void setZipcode(String zipcode)
	{
		this.zipcode = zipcode;
	}

	/**
	 * Gets this contact's home phone number.
	 * 
	 * @return current home phone number
	 * @since 1.1
	 */
	public String getHomePhone()
	{
		return homePhone;
	}

	/**
	 * Sets this contact's home phone number to the specified string.
	 * 
	 * @param homePhone new home phone number
	 * @since 1.1
	 */
	public void setHomePhone(String homePhone)
	{
		this.homePhone = homePhone;
	}

	/**
	 * Gets this contact's cell phone number.
	 * 
	 * @return current cell phone number
	 * @since 1.1
	 */
	public String getCellPhone()
	{
		return cellPhone;
	}

	/**
	 * Sets this contact's cell phone number to the specified string.
	 * 
	 * @param cellPhone new cell phone number
	 * @since 1.1
	 */
	public void setCellPhone(String cellPhone)
	{
		this.cellPhone = cellPhone;
	}

	/**
	 * Gets this contact's work phone number.
	 * 
	 * @return current work phone number
	 * @since 1.1
	 */
	public String getWorkPhone()
	{
		return workPhone;
	}

	/**
	 * Sets this contact's work phone number to the specified string.
	 * 
	 * @param workPhone new work phone number
	 * @since 1.1
	 */
	public void setWorkPhone(String workPhone)
	{
		this.workPhone = workPhone;
	}

	/**
	 * Gets this contact's fax number.
	 * 
	 * @return current fax number
	 * @since 1.1
	 */
	public String getFaxNumber()
	{
		return faxNumber;
	}

	/**
	 * Sets this contact's fax number to the specified string.
	 * 
	 * @param faxNumber new fax number
	 * @since 1.1
	 */
	public void setFaxNumber(String faxNumber)
	{
		this.faxNumber = faxNumber;
	}

	/**
	 * Gets this contact's email address.
	 * 
	 * @return current email address
	 * @since 1.1
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * Sets this contact's email address to the specified string.
	 * 
	 * @param email new email address
	 * @since 1.1
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * Gets this contact's notes.
	 * 
	 * @return current contact notes
	 * @since 1.1
	 */
	public String getNotes()
	{
		return notes;
	}

	/**
	 * Sets this contact's notes to the specified string.
	 * 
	 * @param notes new contact notes
	 * @since 1.1
	 */
	public void setNotes(String notes)
	{
		this.notes = notes;
	}
}