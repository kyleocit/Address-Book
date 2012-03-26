/*
 * @(#)Address.java	1.1	10/10/17
 * 
 * Original UML diagram provided by Professor Larry Page.
 * Implemented by Kyle Campbell.
 *
 * UML Diagram
 * ------------------------------------
 *               Address
 * ------------------------------------
 *  -street: String
 *  -city: String
 *  -state: String
 *  -zip: String
 * ------------------------------------
 *  +Address()
 *  +Address(Address)
 *  +Address(String,String,String,String)
 *  +compareTo(Address): int
 *  +equals(Address): boolean
 *  +getCity(): String
 *  +getCopy(): Address
 *  +getState(): String
 *  +getStreet(): String
 *  +getZip(): String
 *  +makeCopy(Address): void
 *  +print(): void
 *  +setAddress(String,String,String,String): void
 *  +setCity(String): void
 *  +setState(String): void
 *  +setStreet(String): void
 *  +setZip(String): void
 *  +toString(): String
 * ------------------------------------
 * 
 * Change Log:
 * v1.1
 *   - modified Address to extend DataElement and implemented copy and
 *     comparison methods
 *   - added copy constructor
 *   - re-ordered methods alphabetically
 * v1.0
 *   - initial release based on UML diagram provided:
 *     http://studenthome.sunyocc.edu/~pagel/csc112/programs/OnLineAddressBook.html
 */


/**
 * The class Address stores a postal address including the street, city, state,
 * and zip code of a location.
 */
public class Address extends DataElement
{
	/** Stores the street of this Address. */
	private String street;

	/** Stores the city of this Address. */
	private String city;

	/** Stores the state of this Address.*/
	private String state;

	/** Stores the zip code of this Address. */
	private String zip;

	/**
	 * Constructs this Address with an empty street, city, state, and zip code.
	 */
	public Address()
	{
		street = "";
		city = "";
		state = "";
		zip = "";
	}

	/**
	 * Constructs this Address with the same street, city, state, and zip code
	 * as the specified otherAddress.
	 */
	public Address(Address otherAddress)
	{
		street = otherAddress.street;
		city = otherAddress.city;
		state = otherAddress.state;
		zip = otherAddress.zip;
	}

	/**
	 * Constructs this Address with the specified street, city, state, and zip
	 * code parameters.
	 */
	public Address(String street, String city, String state, String zip)
	{
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	/**
	 * Compares this Address object to the specified otherAddress. The
	 * comparison is made in the following order: state, city, street, zip code
	 * using the String compareTo method. This comparison is not case sensitive.
	 * This comparison assumes alphabetical ascending order and returns:
	 *  - a negative integer if this Address belongs before otherAddress
	 *  - a positive integer if this Address belongs after otherAddress
	 *  - a 0 (zero) if this Address is equal to otherAddress
	 */
	public int compareTo(DataElement otherAddress)
	{
		int comp; //stores the comparison results

		if ((comp = state.compareToIgnoreCase(((Address)otherAddress).state)) != 0
				|| (comp = city.compareToIgnoreCase(((Address)otherAddress).city)) != 0
				|| (comp = street.compareToIgnoreCase(((Address)otherAddress).street)) != 0)
			return comp;
		else
			return zip.compareToIgnoreCase(((Address)otherAddress).zip);
	}

	/**
	 * Compares this Address object to the specified otherAddress. The
	 * comparison is not case sensitive. If this Address contains the same
	 * street, city, state, and zip code as otherAddress then a boolean value
	 * of true is returned; otherwise, a boolean value of false is returned.
	 */
	public boolean equals(DataElement otherAddress)
	{
		return (street.equalsIgnoreCase(((Address)otherAddress).street)
				&& city.equalsIgnoreCase(((Address)otherAddress).city)
				&& state.equalsIgnoreCase(((Address)otherAddress).state)
				&& zip.equalsIgnoreCase(((Address)otherAddress).zip));
	}

	/**
	 * Gets the city of this Address.
	 */
	public String getCity()
	{
		return city;
	}

	/**
	 * Gets a new Address containing the same street, city, state, and zip code
	 * as this Address.
	 */
	public DataElement getCopy()
	{
		return new Address(street, city, state, zip);
	}

	/**
	 * Gets the street of this Address.
	 */
	public String getStreet()
	{
		return street;
	}

	/**
	 * Gets the state of this Address.
	 */
	public String getState()
	{
		return state;
	}

	/**
	 * Gets the zip code of this Address.
	 */
	public String getZip()
	{
		return zip;
	}

	/**
	 * Sets the street, city, state, and zip code of this Address equal to the
	 * street, city, state, and zip code of the specified otherAddress.
	 */
	public void makeCopy(DataElement otherAddress)
	{
		street = ((Address)otherAddress).street;
		city = ((Address)otherAddress).city;
		state = ((Address)otherAddress).state;
		zip = ((Address)otherAddress).zip;
	}

	/**
	 * Prints this postal address to the default System output stream.
	 */
	public void print()
	{
		System.out.print(this);
	}

	/**
	 * Sets this Address' street, city, state, and zip code to the specified
	 * street, city, state, and zip code parameters.
	 */
	public void setAddress(String street, String city, String state, String zip)
	{
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	/**
	 * Sets the city of this Address to the specified city parameter.
	 */
	public void setCity(String city)
	{
		this.city = city;
	}

	/**
	 * Sets the state of this Address to the specified state parameter.
	 */
	public void setState(String state)
	{
		this.state = state;
	}

	/**
	 * Sets the street of this Address to the specified street parameter.
	 */
	public void setStreet(String street)
	{
		this.street = street;
	}

	/**
	 * Sets the zip code of this Address to the specified zip code parameter.
	 */
	public void setZip(String zip)
	{
		this.zip = zip;
	}

	/**
	 * Gets a String containing this postal address.
	 */
	public String toString()
	{
		return (street + "\n" + city + ", " + state + " " + zip);
	}
}