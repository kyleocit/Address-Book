/*
 * @(#)Person.java	1.3 10/10/20
 *
 * Source code provided by Professor Larry Page.
 * Modified by Kyle Campbell.
 *
 * UML Diagram
 * --------------------------------------------------
 *                     Person
 * --------------------------------------------------
 *  -firstName: String
 *  -middleName: String
 *  -lastName: String
 * --------------------------------------------------
 *  +Person()
 *  +Person(Person)
 *  +Person(String,String)
 *  +Person(String,String,String)
 *  +compareTo(DataElement): int
 *  +equals(DataElement): boolean
 *  +getCopy(): DataElement
 *  +getFirstName(): String
 *  +getLastName(): String
 *  +getMiddleName(): String
 *  +getName(): String
 *  +isFirstName(String): boolean
 *  +isLastName(String): boolean
 *  +isMiddleName(String): boolean
 *  +makeCopy(DataElement): void
 *  +setFirstName(String): void
 *  +setLastName(String): void
 *  +setMiddleName(String): void
 *  +setName(): void
 *  +setName(String,String): void
 *  +setName(String,String,String): void
 *  +toString(): String
 * --------------------------------------------------
 * 
 * Change Log
 * v1.3
 *  - converted class to extend DataElement and implemented abstract methods
 *  - added getName() method to get full name and updated toString to use
 *    getName() method
 * v1.2
 *  - added method setName() to set name via console input
 * v1.1
 *  - added support for middle name
 * v1.0
 *  - original source code provided by professor
 */

import java.util.Scanner;

public class Person extends DataElement
{
	private String firstName; //store the first name
	private String middleName;//store the middle name
	private String lastName;  //store the last name

	//Default constructor;
	//Initialize firstName, middleName, and lastName to empty string
	//Postcondition: firstName = ""; middleName = ""; lastName = "";
	public Person()
	{
		firstName = "";
		middleName = "";
		lastName = "";
	}

	// copy constructor
	// Example:  Person person2 = new Person(person1);
	public Person(Person other)
	{
		firstName = other.firstName;
		middleName = other.middleName;
		lastName = other.lastName;
	}

	//Constructor with parameters
	//Set firstName and lastName according to the parameters
	//Postcondition: firstName = first; middleName = ""; lastName = last;
	public Person(String first, String last)
	{
		firstName = first;
		middleName = "";
		lastName = last;
	}

	//Constructor with parameters
	//Set firstName, middleName and lastName according to the parameters
	//Postcondition: firstName = first; middleName = middle; lastName = last;
	public Person(String first, String middle, String last)
	{
		firstName = first;
		middleName = middle;
		lastName = last;
	}

	// method to compare 2 Person objects and tell if they are < 0.  = 0. or > 0
	// using string compares for each of last, first and middle names
	// Example:
	// if (person1.compareTo(person2) < 0)
	//     System.out.println(person1 + " comes first in alphabetical order before " + person2);
	// else if (person1.compareTo(person2) > 0)
	//     System.out.println(person2 + " comes first in alphabetical order before " + person1);    
	// else
	//     System.out.println(person1 + " is the same as " + person2);
	public int compareTo(DataElement other)
	{
		int comp; //stores the comparison results

		if ((comp = lastName.compareToIgnoreCase(((Person) other).lastName)) != 0
				|| (comp = firstName.compareToIgnoreCase(((Person) other).firstName)) != 0)
			return comp;
		else
			return middleName.compareToIgnoreCase(((Person) other).middleName);
	}

	// method to compare first, middle and last names of 2 Person objects
	// Example:  
	// System.out.println(person1 + " is the same as " + person2 + " is " + person1.equals(person2);
	public boolean equals(DataElement other)
	{
		return (firstName.equalsIgnoreCase(((Person) other).firstName)
				&& middleName.equalsIgnoreCase(((Person) other).middleName)
				&& lastName.equalsIgnoreCase(((Person) other).lastName));
	}

	// method to create a new or existing object the same as an existing object
	// Example:  Person person2 = person1.getCopy();
	public DataElement getCopy()
	{
		return new Person(firstName, middleName, lastName);
	}

	//Method to return the firstName
	//Postcondition: the value of firstName is returned
	public String getFirstName()
	{
		return firstName;
	}

	//Method to return the lastName
	//Postcondition: the value of lastName is returned
	public String getLastName()
	{
		return lastName;
	}

	//Method to return the middleName
	//Postcondition: the value of middleName is returned
	public String getMiddleName()
	{
		return middleName;
	}
	
	//Method to return this persons full name
	//Postcondition: the value of firstName, middleName, and lastName is returned
	public String getName()
	{
		String name = "";
		name += (firstName.isEmpty() ? "" : firstName + " ");
		name += (middleName.isEmpty() ? "" : middleName + " ");
		name += (lastName.isEmpty() ? "" : lastName);
		return name.trim();
	}

	// boolean firstName comparison method
	// Example:
	// if (person1.isFirstName(person2.getFirstName()))
	//     System.out.println(person1.getFirstName() + " = " + person2.getFirstName())
	// else
	//     System.out.println(person1.getFirstName() + " != " + person2.getFirstName())
	boolean isFirstName(String first)
	{
		return firstName.equalsIgnoreCase(first);
	}

	// boolean lastName comparison method
	// Example:
	// if (person1.isLastName(person2.getLastName()))
	//     System.out.println(person1.getLastName() + " = " + person2.getLastName())
	// else
	//     System.out.println(person1.getLastName() + " != " + person2.getLastName())
	boolean isLastName(String last)
	{
		return lastName.equalsIgnoreCase(last);
	}

	// boolean middleName comparison method
	// Example:
	// if (person1.isMiddleName(person2.getMiddleName()))
	//     System.out.println(person1.getMiddleName() + " = " + person2.getMiddleName())
	// else
	//     System.out.println(person1.getMiddleName() + " != " + person2.getMiddleName())
	boolean isMiddleName(String middle)
	{
		return middleName.equalsIgnoreCase(middle);
	}

	// method to copy the contents of one person object to another existing person object
	// Example:  person2.makeCopy(person1);
	public void makeCopy(DataElement other)
	{
		firstName = ((Person) other).firstName;
		middleName = ((Person) other).middleName;
		lastName = ((Person) other).lastName;
	}

	//Method to set firstName according to the parameters
	//Postcondition: firstName = first;
	public void setFirstName(String first)
	{
		firstName = first;
	}

	//Method to set lastName according to the parameters
	//Postcondition: lastName = last;
	public void setLastName(String last)
	{
		lastName = last;
	}

	//Method to set middleName according to the parameters
	//Postcondition: middleName = middle;
	public void setMiddleName(String middle)
	{
		middleName = middle;
	}

	//Method to collect a first middle and last name from System.in
	public Person setName()
	{
		Scanner cin = new Scanner(System.in);

		System.out.print("Please enter the first middle and last name: ");
		firstName = cin.next();
		middleName = cin.next();
		lastName = cin.next();

		return this;
	}

	//Method to set firstName and lastName according to
	//the parameters
	//Postcondition: firstName = first; middleName = ""; lastName = last;
	public void setName(String first, String last)
	{
		firstName = first;
		middleName = "";
		lastName = last;
	}

	//Method to set firstName, middleName, and lastName according to
	//the parameters
	//Postcondition: firstName = first; middleName = middle; lastName = last;
	public void setName(String first, String middle, String last)
	{
		firstName = first;
		middleName = middle;
		lastName = last;
	}

	//Method to output the first name, middle name,  and last name
	//in the form firstName middleName lastName
	public String toString()
	{
		return getName();
	}
}