/*
 * @(#)ABPerson.java	1.2	10/10/17
 * 
 * Original source code provided by Professor Larry Page.
 * Modified by Kyle Campbell for educational use.
 *
 * UML Diagram
 * ------------------------------------
 *              ABPerson
 * ------------------------------------
 *  -address: Address
 *  -homePhoneNumber: String
 *  -cellPhoneNumber: String
 *  -workPhoneNumber: String
 *  -faxNumber: String
 *  -emailAddress: String
 *  -notes: String
 *  -picturePath: String
 * ------------------------------------
 *  +ABPerson()
 *  +ABPerson(ABPerson)
 *  +ABPerson(String,String,String)
 *  +ABPerson(String,String,String,String,String,String,String,String,String)
 *  +ABPerson(String,String,String,String,String,String,String,String,String,String,String,String,String,String)
 *  +getAddress(): Address
 *  +getCellPhone(): String
 *  +getCity(): String
 *  +getCopy(): ABPerson
 *  +getEmail(): String
 *  +getEmailAddress(): String
 *  +getFaxNumber(): String
 *  +getHomeNumber(): String
 *  +getInfo(): String
 *  +getListing(): String
 *  +getNotes(): String
 *  +getPhone(): String
 *  +getPhoneNumber(): String
 *  +getPicturePath(): String
 *  +getState(): String
 *  +getStreet(): String
 *  +getStreetAddress(): String
 *  +getWorkPhone(): String
 *  +getZip(): String
 *  +isEmpty(): boolean
 *  +isEmptyName(): boolean
 *  +makeCopy(ABPerson): void
 *  +printInfo(): void
 *  +setAddress(Address): void
 *  +setAddress(String,String,String,String): void
 *  +setCellPhone(String): void
 *  +setCity(String): void
 *  +setEmail(String): void
 *  +setFaxNumber(String): void
 *  +setHomePhone(String): void
 *  +setInfo(String,...x9): void
 *  +setPhone(String): void
 *  +setPicturePath(String): void
 *  +setState(String): void
 *  +setStreet(String): void
 *  +setWorkPhone(String): void
 *  +setZip(String): void
 *  +toString(): String
 * ------------------------------------
 * 
 * Change Log:
 * v1.2
 *   - added misc. other methods and fields
 * v1.1
 *   - added copy constructor
 *   - changed method comments to comment blocks to support IDEs with comment
 *     block collapsing and/or JavaDoc commenting
 *   - added UML diagram
 * v1.0
 *   - original source code from:
 *     http://studenthome.sunyocc.edu/~pagel/csc112/programs/ABPerson.java
 */


/**
 * The class ABPerson stores address book information about a person. This
 * information includes the persons name, residential address, phone number,
 * and email address.
 */
public class ABPerson extends Person
{
	/** Stores this persons residential address. */
	private Address address;

	/** Stores this persons home phone number. */
	private String homePhoneNumber;

	/** Stores this persons cell phone number. */
	private String cellPhoneNumber;

	/** Stores this persons work phone number. */
	private String workPhoneNumber;

	/** Stores this persons fax number. */
	private String faxNumber;

	/** Stores this persons email address. */
	private String emailAddress;

	/** Stores this persons additional contact notes. */
	private String notes;
	
	/** Stores the absolute path to this persons picture. */
	private String picturePath;

	/**
	 * Constructs this ABPerson with empty data.
	 */
	public ABPerson()
	{
		super();
		address = new Address();
		homePhoneNumber = "";
		workPhoneNumber = "";
		cellPhoneNumber = "";
		faxNumber = "";
		emailAddress = "";
		notes = "";
		picturePath = "";
	}

	/**
	 * Constructs this ABPerson with the same data as the specified otherPerson.
	 */
	public ABPerson(ABPerson otherPerson)
	{
		super(otherPerson.getFirstName(), otherPerson.getMiddleName(), otherPerson.getLastName());
		address = (Address) otherPerson.address.getCopy();
		homePhoneNumber = otherPerson.homePhoneNumber;
		workPhoneNumber = otherPerson.workPhoneNumber;
		cellPhoneNumber = otherPerson.cellPhoneNumber;
		faxNumber = otherPerson.faxNumber;
		emailAddress = otherPerson.emailAddress;
		notes = otherPerson.notes;
		picturePath = otherPerson.picturePath;
	}

	/**
	 * Constructs this ABPerson with the specified firstName, middleName, and
	 * lastName parameters.
	 */
	public ABPerson(String firstName, String middleName, String lastName)
	{
		super(firstName, middleName, lastName);
		address = new Address();
		homePhoneNumber = "";
		workPhoneNumber = "";
		cellPhoneNumber = "";
		faxNumber = "";
		emailAddress = "";
		notes = "";
		picturePath = "";
	}

	/**
	 * Constructs this ABPerson with the specified data parameters.
	 */
	public ABPerson(String firstName, String middleName, String lastName,
			String street, String city, String state, String zip,
			String phoneNumber, String emailAddress)
	{
		super(firstName, middleName, lastName);
		address = new Address(street, city, state, zip);
		this.homePhoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		workPhoneNumber = "";
		cellPhoneNumber = "";
		faxNumber = "";
		notes = "";
		picturePath = "";
	}
	
	/**
	 * Constructs this ABPerson with the specified data parameters.
	 */
	public ABPerson(String firstName, String middleName, String lastName,
			String street, String city, String state, String zip,
			String homePhoneNumber, String cellPhoneNumber,
			String workPhoneNumber, String faxNumber,
			String emailAddress, String notes, String picturePath)
	{
		super(firstName, middleName, lastName);
		address = new Address(street, city, state, zip);
		this.homePhoneNumber = homePhoneNumber;
		this.workPhoneNumber = workPhoneNumber;
		this.cellPhoneNumber = cellPhoneNumber;
		this.faxNumber = faxNumber;
		this.emailAddress = emailAddress;
		// TODO implement notes loading/saving support
		this.notes = notes;
		this.picturePath = picturePath;
	}

	/**
	 * Gets this persons residential address.
	 */
	public Address getAddress()
	{
		return address;
	}

	/**
	 * Gets this persons cell phone number.
	 */
	public String getCellPhone()
	{
		return cellPhoneNumber;
	}

	/**
	 * Gets the city of this persons residential address.
	 */
	public String getCity()
	{
		return address.getCity();
	}

	/**
	 * Gets a new ABPerson containing the same data as this ABPerson.
	 */
	public ABPerson getCopy()
	{
		return new ABPerson(this);
	}

	/**
	 * Gets this persons email address.
	 */
	public String getEmail()
	{
		return emailAddress;
	}

	/**
	 * Gets this persons email address.
	 */
	public String getEmailAddress()
	{
		return emailAddress;
	}

	/**
	 * Gets this persons phone number.
	 */
	public String getFaxNumber()
	{
		return faxNumber;
	}

	/**
	 * Gets this persons home phone number.
	 */
	public String getHomePhone()
	{
		return homePhoneNumber;
	}
	
	public String getInfo()
	{
		String info = getListing();
		info += "\n" + address;
		info += "\n Home Phone: " + getHomePhone();
		info += "\n Cell Phone: " + getCellPhone();
		info += "\n Work Phone: " + getWorkPhone();
		info += "\n Fax Number: " + getFaxNumber();
		info += "\n Email Address: " + getEmail();
		info += "\n Additional Notes:\n" + getNotes();
		return info;
	}

	/**
	 * Gets this persons listing name. This is the persons name in the
	 * following form: last, first middle
	 */
	public String getListing()
	{
		String name = (getLastName().isEmpty() ? "" : getLastName() + ", ");
		name += (getFirstName().isEmpty() ? "" : getFirstName() + " ");
		name += (getMiddleName().isEmpty() ? "" : " " + getMiddleName());
		name = name.trim();
		if (name.charAt(name.length()-1) == ',')
			name = name.substring(0, name.length()-2);

		return name;
	}

	/**
	 * Gets the additional contact notes for this person.
	 */
	public String getNotes()
	{
		return notes;
	}

	/**
	 * Gets this persons home phone number.
	 */
	public String getPhone()
	{
		return homePhoneNumber;
	}

	/**
	 * Gets this persons home phone number.
	 */
	public String getPhoneNumber()
	{
		return homePhoneNumber;
	}
	
	/**
	 * Gets the absolute path to this persons picture.
	 */
	public String getPicturePath()
	{
		return picturePath;
	}

	/**
	 * Gets the state of this persons residential address.
	 */
	public String getState()
	{
		return address.getState();
	}

	/**
	 * Gets the street of this persons residential address.
	 */
	public String getStreet()
	{
		return address.getStreet();
	}

	/**
	 * Gets the street of this persons residential address.
	 */
	public String getStreetAddress()
	{
		return address.getStreet();
	}

	/**
	 * Gets this persons work phone number.
	 */
	public String getWorkPhone()
	{
		return workPhoneNumber;
	}

	/**
	 * Gets the zip code of this persons residential address.
	 */
	public String getZip()
	{
		return address.getZip();
	}

	public boolean isEmpty()
	{
		if (getFirstName().isEmpty() && getMiddleName().isEmpty() && getLastName().isEmpty()
				&& getStreet().isEmpty() && getCity().isEmpty() && getState().isEmpty()
				&& getZip().isEmpty() && getHomePhone().isEmpty() && getCellPhone().isEmpty()
				&& getWorkPhone().isEmpty() && getFaxNumber().isEmpty()&& getEmail().isEmpty()
				&& getNotes().isEmpty())
		{
			return true;
		}
		return false;
	}

	public boolean isEmptyName()
	{
		if (getFirstName().isEmpty() && getMiddleName().isEmpty() && getLastName().isEmpty())
			return true;
		return false;
	}

	/**
	 * Copies the data of the specified otherPerson into this ABPerson.
	 */
	public void makeCopy(ABPerson otherPerson)
	{
		setName(otherPerson.getFirstName(), otherPerson.getMiddleName(), otherPerson.getLastName());
		address = (Address) otherPerson.address.getCopy();
		homePhoneNumber = otherPerson.homePhoneNumber;
		emailAddress = otherPerson.emailAddress;
	}

	/**
	 * Prints the data stored in this ABPerson to the default system output
	 * stream.
	 */
	public void printInfo()
	{
		System.out.println(super.toString());
		System.out.println("Phone Number: " + homePhoneNumber);
		System.out.println("Email Address: " + emailAddress);
		System.out.println(address);
	}

	/**
	 * Sets this persons residential address to the specified address
	 * parameter.
	 */
	public void setAddress(Address address)
	{
		this.address = address;
	}

	/**
	 * Sets this persons residential address to the specified address
	 * parameters: street, city, state, zip
	 */
	public void setAddress(String street, String city, String state, String zip)
	{
		address = new Address(street, city, state, zip);
	}

	/**
	 * Sets this persons cell phone number to the specified phoneNumber parameter.
	 */
	public void setCellPhone(String phoneNumber)
	{
		this.cellPhoneNumber = phoneNumber;
	}

	/**
	 * Sets the city of this persons residential address to the specified city
	 * parameter.
	 */
	public void setCity(String city)
	{
		address.setCity(city);
	}

	/**
	 * Sets this persons email address to the specified emailAddress parameter.
	 */
	public void setEmail(String emailAddress)
	{
		this.emailAddress = emailAddress;
	}

	/**
	 * Sets this persons fax number to the specified faxNumber parameter.
	 */
	public void setFaxNumber(String faxNumber)
	{
		this.faxNumber = faxNumber;
	}

	/**
	 * Sets this persons home phone number to the specified phoneNumber parameter.
	 */
	public void setHomePhone(String phoneNumber)
	{
		this.homePhoneNumber = phoneNumber;
	}

	/**
	 * Sets this persons name, address, phone number, and email address to the
	 * specified data parameters.
	 */
	public void setInfo(String firstName, String middleName, String lastName,
			String street, String city, String state, String zip,
			String phoneNumber, String emailAddress)
	{
		setName(firstName, middleName, lastName);
		address = new Address(street, city, state, zip);
		this.homePhoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
	}

	/**
	 * Sets this persons additional contact notes to the specified notes parameter.
	 */
	public void setNotes(String notes)
	{
		this.notes = notes;
	}

	/**
	 * Sets this persons home  phone number to the specified phoneNumber parameter.
	 */
	public void setPhone(String phoneNumber)
	{
		this.homePhoneNumber = phoneNumber;
	}
	
	/**
	 * Sets the absolute path to this persons picture.
	 */
	public void setPicturePath(String picturePath)
	{
		this.picturePath = picturePath;
	}

	/**
	 * Sets the state of this persons residential address to the specified
	 * state parameter.
	 */
	public void setState(String state)
	{
		address.setState(state);
	}

	/**
	 * Sets the street of this persons residential address to the specified
	 * street parameter.
	 */
	public void setStreet(String street)
	{
		address.setStreet(street);
	}

	/**
	 * Sets this persons work phone number to the specified phoneNumber parameter.
	 */
	public void setWorkPhone(String phoneNumber)
	{
		this.workPhoneNumber = phoneNumber;
	}

	/**
	 * Sets the zip code of this persons residential address to the specified
	 * zip parameter.
	 */
	public void setZip(String zip)
	{
		address.setZip(zip);
	}

	/**
	 * Gets the data stored in this ABPerson as a String.
	 */
	public String toString()
	{
		return getListing();
	}
}