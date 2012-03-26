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

import java.io.File;
import java.util.Vector;

public class AddressBook extends OrderedLinkedList
{
	private static final String delimiter = ":ABD:";
	private static final String emptyToken = ":NIL:";
	private static final String lineToken = ":NLN:";
	
	//Stores objects listening for address book changes
	private Vector<AddressBookListener> listeners;

	public AddressBook()
	{
		super();
		listeners = new Vector<AddressBookListener>();
	}

	//Constructs this address book using data stored in a file
	public AddressBook(File f)
	{
		super();
		listeners = new Vector<AddressBookListener>();
		load(f);
	}
	
	//Registers object to be notified when this address book is modified
	public void addEventListener(AddressBookListener listener)
	{
		listeners.add(listener);
	}
	
	//Checks if the specified person is in this address book
	public boolean contains(ABPerson abp)
	{
		LinkedListNode current = first;
		
		while (current != null)
			if (abp.equals(current.info))
				return true;
		return false;
	}
	
	//Deletes the specified person from this address book and fires addressBookModified on listeners
	public void deleteNode(ABPerson deleteItem)
	{
		int size = count;
		super.deleteNode(deleteItem);
		
		if (size != count)
			notifyListeners();
	}

	//Gets a sub address book containing only people whose last name starts with the letter parameter
	public AddressBook getLetter(Character letter)
	{
		AddressBook sub = new AddressBook();
		LinkedListNode current = first;
		String listingLetter, ltr = "" + letter;

		while (current != null)
		{
			listingLetter = "" + ((ABPerson) current.info).getListing().charAt(0);
			if (listingLetter.equalsIgnoreCase(ltr))
				sub.insertNode(current.info);
			current = current.link;
		}

		return sub;
	}
	
	//Initializes list and fires addressBookModified on listeners
	public void initializeList()
	{
		super.initializeList();
		notifyListeners();
	}
	
	//Inserts person to address book and fires addressBookModified on listeners
	public void insertFirst(ABPerson insertItem)
	{
		int size = count;
		super.insertNode(insertItem);
		
		if (size != count)
			notifyListeners();
	}
	
	//Inserts person to address book and fires addressBookModified on listeners
	public void insertLast(ABPerson insertItem)
	{
		int size = count;
		super.insertNode(insertItem);
		
		if (size != count)
			notifyListeners();
	}
	
	//Inserts person to address book and fires addressBookModified on listeners
	public void insertNode(ABPerson insertItem)
	{
		int size = count;
		super.insertNode(insertItem);
		
		if (size != count)
			notifyListeners();
	}

	//Loads address book from file
	public void load(File f)
	{
		// if the file doesn't exist, then make a new empty one
		if (!f.exists())
			save(f);
		
		// load encrypted file reader
		DataFileReader fr = new DataFileReader(f);

		// stores line of data as each line is read
		String[] line;
		int lineNumber = 0;

		// read each line
		while (fr.hasNext())
		{
			lineNumber++;
			line = fr.next().split(delimiter);

			for (int i = 0; i < line.length; i++)
				if (line[i].contains(":NIL:"))
					line[i] = "";

			// add newline characters to notes if needed
			line[12] = line[12].replaceAll(lineToken, "\n");
			
			// make sure line is valid
			if (line.length != 14)
			{
				System.err.println("Line " + lineNumber + " of " + f.getAbsolutePath() + " is corrupt.");
				continue;
			}

			// add entry if valid
			insertNode(new ABPerson(line[0], line[1], line[2], line[3], line[4], line[5],
					line[6], line[7], line[8], line[9], line[10], line[11], line[12], line[13]));
		}
	}

	//Saves address book to file
	public void save(File f)
	{
		// load encrypted file writer
		DataFileWriter fw = new DataFileWriter(f);

		// stores each line as it is built and written
		String line;

		// write each entry to new line
		LinkedListNode current = first;
		while (current != null)
		{
			// add first name to line
			if (((Person) current.info).getFirstName().isEmpty())
				line = emptyToken;
			else
				line = ((Person) current.info).getFirstName();

			// add middle name to line
			if (((Person) current.info).getMiddleName().isEmpty())
				line += delimiter + emptyToken;
			else
				line += delimiter + ((Person) current.info).getMiddleName();

			// add last name to line
			if (((Person) current.info).getLastName().isEmpty())
				line += delimiter + emptyToken;
			else
				line += delimiter + ((Person) current.info).getLastName();

			// add street to line
			if (((ABPerson) current.info).getStreet().isEmpty())
				line += delimiter + emptyToken;
			else
				line += delimiter + ((ABPerson) current.info).getStreet();

			// add city to line
			if (((ABPerson) current.info).getCity().isEmpty())
				line += delimiter + emptyToken;
			else
				line += delimiter + ((ABPerson) current.info).getCity();

			// add state to line
			if (((ABPerson) current.info).getState().isEmpty())
				line += delimiter + emptyToken;
			else
				line += delimiter + ((ABPerson) current.info).getState();

			// add zip code to line
			if (((ABPerson) current.info).getZip().isEmpty())
				line += delimiter + emptyToken;
			else
				line += delimiter + ((ABPerson) current.info).getZip();

			// add home phone number to line
			if (((ABPerson) current.info).getPhone().isEmpty())
				line += delimiter + emptyToken;
			else
				line += delimiter + ((ABPerson) current.info).getHomePhone();
			
			// add cell phone number to line
			if (((ABPerson) current.info).getCellPhone().isEmpty())
				line += delimiter + emptyToken;
			else
				line += delimiter + ((ABPerson) current.info).getCellPhone();
			
			// add work phone number to line
			if (((ABPerson) current.info).getWorkPhone().isEmpty())
				line += delimiter + emptyToken;
			else
				line += delimiter + ((ABPerson) current.info).getWorkPhone();
			
			// add phone number to line
			if (((ABPerson) current.info).getFaxNumber().isEmpty())
				line += delimiter + emptyToken;
			else
				line += delimiter + ((ABPerson) current.info).getFaxNumber();

			// add email address to line
			if (((ABPerson) current.info).getEmail().isEmpty())
				line += delimiter + emptyToken;
			else
				line += delimiter + ((ABPerson) current.info).getEmail();
			
			// add notes to line
			if (((ABPerson) current.info).getNotes().isEmpty())
				line += delimiter + emptyToken;
			else
				line += delimiter + ((ABPerson) current.info).getNotes().replaceAll("\n", lineToken);
			
			// add picture path to line
			if (((ABPerson) current.info).getPicturePath().isEmpty())
				line += delimiter + emptyToken;
			else
				line += delimiter + ((ABPerson) current.info).getPicturePath();

			// write line to file
			fw.write(line);

			// step current forward to next node
			current = current.link;
		}

		fw.close();
	}
	
	//Gets an array of ABPerson from this address book that has data that contains the search pattern parameter
	public synchronized ABPerson[] toFilteredArray(String pattern)
	{
		// if no search pattern is given just return this address book as array
		if (pattern.isEmpty())
			return this.toArray();
		
		// otherwise add each entry that contains the search pattern to sub address book
		AddressBook sub = new AddressBook();
		LinkedListNode current = first;
		while (current != null)
		{
			if (((ABPerson)current.info).getInfo().toLowerCase().contains(pattern.toLowerCase()))
				sub.insertNode(current.info);
			current = current.link;
		}
		return sub.toArray();
	}
	
	//Gets an array containing all the ABPerson in this address book
	public ABPerson[] toArray()
	{
		ABPerson[] array = new ABPerson[count];
		
		LinkedListNode current = first;
		int i = 0;
		while (current != null)
		{
			array[i++] = (ABPerson) current.info;
			current = current.link;
		}
		
		return array;
	}
	
	//Fires addressBookModified on all listeners when the address book is modified
	private void notifyListeners()
	{
		for (int i = 0; i < listeners.size(); i++)
			listeners.elementAt(i).addressBookModified();
	}
}